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

import com.stpl.app.exception.NoSuchActualsMasterException;
import com.stpl.app.model.ActualsMaster;

/**
 * The persistence interface for the actuals master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.ActualsMasterPersistenceImpl
 * @see ActualsMasterUtil
 * @generated
 */
@ProviderType
public interface ActualsMasterPersistence extends BasePersistence<ActualsMaster> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ActualsMasterUtil} to access the actuals master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the actuals masters where accountId = &#63;.
	*
	* @param accountId the account ID
	* @return the matching actuals masters
	*/
	public java.util.List<ActualsMaster> findByAccountId(
		java.lang.String accountId);

	/**
	* Returns a range of all the actuals masters where accountId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountId the account ID
	* @param start the lower bound of the range of actuals masters
	* @param end the upper bound of the range of actuals masters (not inclusive)
	* @return the range of matching actuals masters
	*/
	public java.util.List<ActualsMaster> findByAccountId(
		java.lang.String accountId, int start, int end);

	/**
	* Returns an ordered range of all the actuals masters where accountId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountId the account ID
	* @param start the lower bound of the range of actuals masters
	* @param end the upper bound of the range of actuals masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching actuals masters
	*/
	public java.util.List<ActualsMaster> findByAccountId(
		java.lang.String accountId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator);

	/**
	* Returns an ordered range of all the actuals masters where accountId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountId the account ID
	* @param start the lower bound of the range of actuals masters
	* @param end the upper bound of the range of actuals masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching actuals masters
	*/
	public java.util.List<ActualsMaster> findByAccountId(
		java.lang.String accountId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first actuals master in the ordered set where accountId = &#63;.
	*
	* @param accountId the account ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching actuals master
	* @throws NoSuchActualsMasterException if a matching actuals master could not be found
	*/
	public ActualsMaster findByAccountId_First(java.lang.String accountId,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator)
		throws NoSuchActualsMasterException;

	/**
	* Returns the first actuals master in the ordered set where accountId = &#63;.
	*
	* @param accountId the account ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching actuals master, or <code>null</code> if a matching actuals master could not be found
	*/
	public ActualsMaster fetchByAccountId_First(java.lang.String accountId,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator);

	/**
	* Returns the last actuals master in the ordered set where accountId = &#63;.
	*
	* @param accountId the account ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching actuals master
	* @throws NoSuchActualsMasterException if a matching actuals master could not be found
	*/
	public ActualsMaster findByAccountId_Last(java.lang.String accountId,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator)
		throws NoSuchActualsMasterException;

	/**
	* Returns the last actuals master in the ordered set where accountId = &#63;.
	*
	* @param accountId the account ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching actuals master, or <code>null</code> if a matching actuals master could not be found
	*/
	public ActualsMaster fetchByAccountId_Last(java.lang.String accountId,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator);

	/**
	* Returns the actuals masters before and after the current actuals master in the ordered set where accountId = &#63;.
	*
	* @param actualsMasterSid the primary key of the current actuals master
	* @param accountId the account ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next actuals master
	* @throws NoSuchActualsMasterException if a actuals master with the primary key could not be found
	*/
	public ActualsMaster[] findByAccountId_PrevAndNext(int actualsMasterSid,
		java.lang.String accountId,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator)
		throws NoSuchActualsMasterException;

	/**
	* Removes all the actuals masters where accountId = &#63; from the database.
	*
	* @param accountId the account ID
	*/
	public void removeByAccountId(java.lang.String accountId);

	/**
	* Returns the number of actuals masters where accountId = &#63;.
	*
	* @param accountId the account ID
	* @return the number of matching actuals masters
	*/
	public int countByAccountId(java.lang.String accountId);

	/**
	* Returns all the actuals masters where actualId = &#63;.
	*
	* @param actualId the actual ID
	* @return the matching actuals masters
	*/
	public java.util.List<ActualsMaster> findByActualId(
		java.lang.String actualId);

	/**
	* Returns a range of all the actuals masters where actualId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param actualId the actual ID
	* @param start the lower bound of the range of actuals masters
	* @param end the upper bound of the range of actuals masters (not inclusive)
	* @return the range of matching actuals masters
	*/
	public java.util.List<ActualsMaster> findByActualId(
		java.lang.String actualId, int start, int end);

	/**
	* Returns an ordered range of all the actuals masters where actualId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param actualId the actual ID
	* @param start the lower bound of the range of actuals masters
	* @param end the upper bound of the range of actuals masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching actuals masters
	*/
	public java.util.List<ActualsMaster> findByActualId(
		java.lang.String actualId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator);

	/**
	* Returns an ordered range of all the actuals masters where actualId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param actualId the actual ID
	* @param start the lower bound of the range of actuals masters
	* @param end the upper bound of the range of actuals masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching actuals masters
	*/
	public java.util.List<ActualsMaster> findByActualId(
		java.lang.String actualId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first actuals master in the ordered set where actualId = &#63;.
	*
	* @param actualId the actual ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching actuals master
	* @throws NoSuchActualsMasterException if a matching actuals master could not be found
	*/
	public ActualsMaster findByActualId_First(java.lang.String actualId,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator)
		throws NoSuchActualsMasterException;

	/**
	* Returns the first actuals master in the ordered set where actualId = &#63;.
	*
	* @param actualId the actual ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching actuals master, or <code>null</code> if a matching actuals master could not be found
	*/
	public ActualsMaster fetchByActualId_First(java.lang.String actualId,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator);

	/**
	* Returns the last actuals master in the ordered set where actualId = &#63;.
	*
	* @param actualId the actual ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching actuals master
	* @throws NoSuchActualsMasterException if a matching actuals master could not be found
	*/
	public ActualsMaster findByActualId_Last(java.lang.String actualId,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator)
		throws NoSuchActualsMasterException;

	/**
	* Returns the last actuals master in the ordered set where actualId = &#63;.
	*
	* @param actualId the actual ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching actuals master, or <code>null</code> if a matching actuals master could not be found
	*/
	public ActualsMaster fetchByActualId_Last(java.lang.String actualId,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator);

	/**
	* Returns the actuals masters before and after the current actuals master in the ordered set where actualId = &#63;.
	*
	* @param actualsMasterSid the primary key of the current actuals master
	* @param actualId the actual ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next actuals master
	* @throws NoSuchActualsMasterException if a actuals master with the primary key could not be found
	*/
	public ActualsMaster[] findByActualId_PrevAndNext(int actualsMasterSid,
		java.lang.String actualId,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator)
		throws NoSuchActualsMasterException;

	/**
	* Removes all the actuals masters where actualId = &#63; from the database.
	*
	* @param actualId the actual ID
	*/
	public void removeByActualId(java.lang.String actualId);

	/**
	* Returns the number of actuals masters where actualId = &#63;.
	*
	* @param actualId the actual ID
	* @return the number of matching actuals masters
	*/
	public int countByActualId(java.lang.String actualId);

	/**
	* Returns all the actuals masters where divisionId = &#63;.
	*
	* @param divisionId the division ID
	* @return the matching actuals masters
	*/
	public java.util.List<ActualsMaster> findByDivisionId(
		java.lang.String divisionId);

	/**
	* Returns a range of all the actuals masters where divisionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param divisionId the division ID
	* @param start the lower bound of the range of actuals masters
	* @param end the upper bound of the range of actuals masters (not inclusive)
	* @return the range of matching actuals masters
	*/
	public java.util.List<ActualsMaster> findByDivisionId(
		java.lang.String divisionId, int start, int end);

	/**
	* Returns an ordered range of all the actuals masters where divisionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param divisionId the division ID
	* @param start the lower bound of the range of actuals masters
	* @param end the upper bound of the range of actuals masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching actuals masters
	*/
	public java.util.List<ActualsMaster> findByDivisionId(
		java.lang.String divisionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator);

	/**
	* Returns an ordered range of all the actuals masters where divisionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param divisionId the division ID
	* @param start the lower bound of the range of actuals masters
	* @param end the upper bound of the range of actuals masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching actuals masters
	*/
	public java.util.List<ActualsMaster> findByDivisionId(
		java.lang.String divisionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first actuals master in the ordered set where divisionId = &#63;.
	*
	* @param divisionId the division ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching actuals master
	* @throws NoSuchActualsMasterException if a matching actuals master could not be found
	*/
	public ActualsMaster findByDivisionId_First(java.lang.String divisionId,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator)
		throws NoSuchActualsMasterException;

	/**
	* Returns the first actuals master in the ordered set where divisionId = &#63;.
	*
	* @param divisionId the division ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching actuals master, or <code>null</code> if a matching actuals master could not be found
	*/
	public ActualsMaster fetchByDivisionId_First(java.lang.String divisionId,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator);

	/**
	* Returns the last actuals master in the ordered set where divisionId = &#63;.
	*
	* @param divisionId the division ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching actuals master
	* @throws NoSuchActualsMasterException if a matching actuals master could not be found
	*/
	public ActualsMaster findByDivisionId_Last(java.lang.String divisionId,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator)
		throws NoSuchActualsMasterException;

	/**
	* Returns the last actuals master in the ordered set where divisionId = &#63;.
	*
	* @param divisionId the division ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching actuals master, or <code>null</code> if a matching actuals master could not be found
	*/
	public ActualsMaster fetchByDivisionId_Last(java.lang.String divisionId,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator);

	/**
	* Returns the actuals masters before and after the current actuals master in the ordered set where divisionId = &#63;.
	*
	* @param actualsMasterSid the primary key of the current actuals master
	* @param divisionId the division ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next actuals master
	* @throws NoSuchActualsMasterException if a actuals master with the primary key could not be found
	*/
	public ActualsMaster[] findByDivisionId_PrevAndNext(int actualsMasterSid,
		java.lang.String divisionId,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator)
		throws NoSuchActualsMasterException;

	/**
	* Removes all the actuals masters where divisionId = &#63; from the database.
	*
	* @param divisionId the division ID
	*/
	public void removeByDivisionId(java.lang.String divisionId);

	/**
	* Returns the number of actuals masters where divisionId = &#63;.
	*
	* @param divisionId the division ID
	* @return the number of matching actuals masters
	*/
	public int countByDivisionId(java.lang.String divisionId);

	/**
	* Returns all the actuals masters where contractId = &#63;.
	*
	* @param contractId the contract ID
	* @return the matching actuals masters
	*/
	public java.util.List<ActualsMaster> findByContractId(
		java.lang.String contractId);

	/**
	* Returns a range of all the actuals masters where contractId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param contractId the contract ID
	* @param start the lower bound of the range of actuals masters
	* @param end the upper bound of the range of actuals masters (not inclusive)
	* @return the range of matching actuals masters
	*/
	public java.util.List<ActualsMaster> findByContractId(
		java.lang.String contractId, int start, int end);

	/**
	* Returns an ordered range of all the actuals masters where contractId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param contractId the contract ID
	* @param start the lower bound of the range of actuals masters
	* @param end the upper bound of the range of actuals masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching actuals masters
	*/
	public java.util.List<ActualsMaster> findByContractId(
		java.lang.String contractId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator);

	/**
	* Returns an ordered range of all the actuals masters where contractId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param contractId the contract ID
	* @param start the lower bound of the range of actuals masters
	* @param end the upper bound of the range of actuals masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching actuals masters
	*/
	public java.util.List<ActualsMaster> findByContractId(
		java.lang.String contractId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first actuals master in the ordered set where contractId = &#63;.
	*
	* @param contractId the contract ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching actuals master
	* @throws NoSuchActualsMasterException if a matching actuals master could not be found
	*/
	public ActualsMaster findByContractId_First(java.lang.String contractId,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator)
		throws NoSuchActualsMasterException;

	/**
	* Returns the first actuals master in the ordered set where contractId = &#63;.
	*
	* @param contractId the contract ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching actuals master, or <code>null</code> if a matching actuals master could not be found
	*/
	public ActualsMaster fetchByContractId_First(java.lang.String contractId,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator);

	/**
	* Returns the last actuals master in the ordered set where contractId = &#63;.
	*
	* @param contractId the contract ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching actuals master
	* @throws NoSuchActualsMasterException if a matching actuals master could not be found
	*/
	public ActualsMaster findByContractId_Last(java.lang.String contractId,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator)
		throws NoSuchActualsMasterException;

	/**
	* Returns the last actuals master in the ordered set where contractId = &#63;.
	*
	* @param contractId the contract ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching actuals master, or <code>null</code> if a matching actuals master could not be found
	*/
	public ActualsMaster fetchByContractId_Last(java.lang.String contractId,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator);

	/**
	* Returns the actuals masters before and after the current actuals master in the ordered set where contractId = &#63;.
	*
	* @param actualsMasterSid the primary key of the current actuals master
	* @param contractId the contract ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next actuals master
	* @throws NoSuchActualsMasterException if a actuals master with the primary key could not be found
	*/
	public ActualsMaster[] findByContractId_PrevAndNext(int actualsMasterSid,
		java.lang.String contractId,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator)
		throws NoSuchActualsMasterException;

	/**
	* Removes all the actuals masters where contractId = &#63; from the database.
	*
	* @param contractId the contract ID
	*/
	public void removeByContractId(java.lang.String contractId);

	/**
	* Returns the number of actuals masters where contractId = &#63;.
	*
	* @param contractId the contract ID
	* @return the number of matching actuals masters
	*/
	public int countByContractId(java.lang.String contractId);

	/**
	* Returns all the actuals masters where provisionId = &#63;.
	*
	* @param provisionId the provision ID
	* @return the matching actuals masters
	*/
	public java.util.List<ActualsMaster> findByProvisionId(
		java.lang.String provisionId);

	/**
	* Returns a range of all the actuals masters where provisionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param provisionId the provision ID
	* @param start the lower bound of the range of actuals masters
	* @param end the upper bound of the range of actuals masters (not inclusive)
	* @return the range of matching actuals masters
	*/
	public java.util.List<ActualsMaster> findByProvisionId(
		java.lang.String provisionId, int start, int end);

	/**
	* Returns an ordered range of all the actuals masters where provisionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param provisionId the provision ID
	* @param start the lower bound of the range of actuals masters
	* @param end the upper bound of the range of actuals masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching actuals masters
	*/
	public java.util.List<ActualsMaster> findByProvisionId(
		java.lang.String provisionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator);

	/**
	* Returns an ordered range of all the actuals masters where provisionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param provisionId the provision ID
	* @param start the lower bound of the range of actuals masters
	* @param end the upper bound of the range of actuals masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching actuals masters
	*/
	public java.util.List<ActualsMaster> findByProvisionId(
		java.lang.String provisionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first actuals master in the ordered set where provisionId = &#63;.
	*
	* @param provisionId the provision ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching actuals master
	* @throws NoSuchActualsMasterException if a matching actuals master could not be found
	*/
	public ActualsMaster findByProvisionId_First(java.lang.String provisionId,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator)
		throws NoSuchActualsMasterException;

	/**
	* Returns the first actuals master in the ordered set where provisionId = &#63;.
	*
	* @param provisionId the provision ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching actuals master, or <code>null</code> if a matching actuals master could not be found
	*/
	public ActualsMaster fetchByProvisionId_First(
		java.lang.String provisionId,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator);

	/**
	* Returns the last actuals master in the ordered set where provisionId = &#63;.
	*
	* @param provisionId the provision ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching actuals master
	* @throws NoSuchActualsMasterException if a matching actuals master could not be found
	*/
	public ActualsMaster findByProvisionId_Last(java.lang.String provisionId,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator)
		throws NoSuchActualsMasterException;

	/**
	* Returns the last actuals master in the ordered set where provisionId = &#63;.
	*
	* @param provisionId the provision ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching actuals master, or <code>null</code> if a matching actuals master could not be found
	*/
	public ActualsMaster fetchByProvisionId_Last(java.lang.String provisionId,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator);

	/**
	* Returns the actuals masters before and after the current actuals master in the ordered set where provisionId = &#63;.
	*
	* @param actualsMasterSid the primary key of the current actuals master
	* @param provisionId the provision ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next actuals master
	* @throws NoSuchActualsMasterException if a actuals master with the primary key could not be found
	*/
	public ActualsMaster[] findByProvisionId_PrevAndNext(int actualsMasterSid,
		java.lang.String provisionId,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator)
		throws NoSuchActualsMasterException;

	/**
	* Removes all the actuals masters where provisionId = &#63; from the database.
	*
	* @param provisionId the provision ID
	*/
	public void removeByProvisionId(java.lang.String provisionId);

	/**
	* Returns the number of actuals masters where provisionId = &#63;.
	*
	* @param provisionId the provision ID
	* @return the number of matching actuals masters
	*/
	public int countByProvisionId(java.lang.String provisionId);

	/**
	* Returns all the actuals masters where itemId = &#63;.
	*
	* @param itemId the item ID
	* @return the matching actuals masters
	*/
	public java.util.List<ActualsMaster> findByItemId(java.lang.String itemId);

	/**
	* Returns a range of all the actuals masters where itemId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemId the item ID
	* @param start the lower bound of the range of actuals masters
	* @param end the upper bound of the range of actuals masters (not inclusive)
	* @return the range of matching actuals masters
	*/
	public java.util.List<ActualsMaster> findByItemId(java.lang.String itemId,
		int start, int end);

	/**
	* Returns an ordered range of all the actuals masters where itemId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemId the item ID
	* @param start the lower bound of the range of actuals masters
	* @param end the upper bound of the range of actuals masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching actuals masters
	*/
	public java.util.List<ActualsMaster> findByItemId(java.lang.String itemId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator);

	/**
	* Returns an ordered range of all the actuals masters where itemId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemId the item ID
	* @param start the lower bound of the range of actuals masters
	* @param end the upper bound of the range of actuals masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching actuals masters
	*/
	public java.util.List<ActualsMaster> findByItemId(java.lang.String itemId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first actuals master in the ordered set where itemId = &#63;.
	*
	* @param itemId the item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching actuals master
	* @throws NoSuchActualsMasterException if a matching actuals master could not be found
	*/
	public ActualsMaster findByItemId_First(java.lang.String itemId,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator)
		throws NoSuchActualsMasterException;

	/**
	* Returns the first actuals master in the ordered set where itemId = &#63;.
	*
	* @param itemId the item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching actuals master, or <code>null</code> if a matching actuals master could not be found
	*/
	public ActualsMaster fetchByItemId_First(java.lang.String itemId,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator);

	/**
	* Returns the last actuals master in the ordered set where itemId = &#63;.
	*
	* @param itemId the item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching actuals master
	* @throws NoSuchActualsMasterException if a matching actuals master could not be found
	*/
	public ActualsMaster findByItemId_Last(java.lang.String itemId,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator)
		throws NoSuchActualsMasterException;

	/**
	* Returns the last actuals master in the ordered set where itemId = &#63;.
	*
	* @param itemId the item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching actuals master, or <code>null</code> if a matching actuals master could not be found
	*/
	public ActualsMaster fetchByItemId_Last(java.lang.String itemId,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator);

	/**
	* Returns the actuals masters before and after the current actuals master in the ordered set where itemId = &#63;.
	*
	* @param actualsMasterSid the primary key of the current actuals master
	* @param itemId the item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next actuals master
	* @throws NoSuchActualsMasterException if a actuals master with the primary key could not be found
	*/
	public ActualsMaster[] findByItemId_PrevAndNext(int actualsMasterSid,
		java.lang.String itemId,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator)
		throws NoSuchActualsMasterException;

	/**
	* Removes all the actuals masters where itemId = &#63; from the database.
	*
	* @param itemId the item ID
	*/
	public void removeByItemId(java.lang.String itemId);

	/**
	* Returns the number of actuals masters where itemId = &#63;.
	*
	* @param itemId the item ID
	* @return the number of matching actuals masters
	*/
	public int countByItemId(java.lang.String itemId);

	/**
	* Returns all the actuals masters where itemNo = &#63;.
	*
	* @param itemNo the item no
	* @return the matching actuals masters
	*/
	public java.util.List<ActualsMaster> findByItemNo(java.lang.String itemNo);

	/**
	* Returns a range of all the actuals masters where itemNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemNo the item no
	* @param start the lower bound of the range of actuals masters
	* @param end the upper bound of the range of actuals masters (not inclusive)
	* @return the range of matching actuals masters
	*/
	public java.util.List<ActualsMaster> findByItemNo(java.lang.String itemNo,
		int start, int end);

	/**
	* Returns an ordered range of all the actuals masters where itemNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemNo the item no
	* @param start the lower bound of the range of actuals masters
	* @param end the upper bound of the range of actuals masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching actuals masters
	*/
	public java.util.List<ActualsMaster> findByItemNo(java.lang.String itemNo,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator);

	/**
	* Returns an ordered range of all the actuals masters where itemNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemNo the item no
	* @param start the lower bound of the range of actuals masters
	* @param end the upper bound of the range of actuals masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching actuals masters
	*/
	public java.util.List<ActualsMaster> findByItemNo(java.lang.String itemNo,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first actuals master in the ordered set where itemNo = &#63;.
	*
	* @param itemNo the item no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching actuals master
	* @throws NoSuchActualsMasterException if a matching actuals master could not be found
	*/
	public ActualsMaster findByItemNo_First(java.lang.String itemNo,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator)
		throws NoSuchActualsMasterException;

	/**
	* Returns the first actuals master in the ordered set where itemNo = &#63;.
	*
	* @param itemNo the item no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching actuals master, or <code>null</code> if a matching actuals master could not be found
	*/
	public ActualsMaster fetchByItemNo_First(java.lang.String itemNo,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator);

	/**
	* Returns the last actuals master in the ordered set where itemNo = &#63;.
	*
	* @param itemNo the item no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching actuals master
	* @throws NoSuchActualsMasterException if a matching actuals master could not be found
	*/
	public ActualsMaster findByItemNo_Last(java.lang.String itemNo,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator)
		throws NoSuchActualsMasterException;

	/**
	* Returns the last actuals master in the ordered set where itemNo = &#63;.
	*
	* @param itemNo the item no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching actuals master, or <code>null</code> if a matching actuals master could not be found
	*/
	public ActualsMaster fetchByItemNo_Last(java.lang.String itemNo,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator);

	/**
	* Returns the actuals masters before and after the current actuals master in the ordered set where itemNo = &#63;.
	*
	* @param actualsMasterSid the primary key of the current actuals master
	* @param itemNo the item no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next actuals master
	* @throws NoSuchActualsMasterException if a actuals master with the primary key could not be found
	*/
	public ActualsMaster[] findByItemNo_PrevAndNext(int actualsMasterSid,
		java.lang.String itemNo,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator)
		throws NoSuchActualsMasterException;

	/**
	* Removes all the actuals masters where itemNo = &#63; from the database.
	*
	* @param itemNo the item no
	*/
	public void removeByItemNo(java.lang.String itemNo);

	/**
	* Returns the number of actuals masters where itemNo = &#63;.
	*
	* @param itemNo the item no
	* @return the number of matching actuals masters
	*/
	public int countByItemNo(java.lang.String itemNo);

	/**
	* Returns all the actuals masters where accountNo = &#63;.
	*
	* @param accountNo the account no
	* @return the matching actuals masters
	*/
	public java.util.List<ActualsMaster> findByAccountNo(
		java.lang.String accountNo);

	/**
	* Returns a range of all the actuals masters where accountNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountNo the account no
	* @param start the lower bound of the range of actuals masters
	* @param end the upper bound of the range of actuals masters (not inclusive)
	* @return the range of matching actuals masters
	*/
	public java.util.List<ActualsMaster> findByAccountNo(
		java.lang.String accountNo, int start, int end);

	/**
	* Returns an ordered range of all the actuals masters where accountNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountNo the account no
	* @param start the lower bound of the range of actuals masters
	* @param end the upper bound of the range of actuals masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching actuals masters
	*/
	public java.util.List<ActualsMaster> findByAccountNo(
		java.lang.String accountNo, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator);

	/**
	* Returns an ordered range of all the actuals masters where accountNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountNo the account no
	* @param start the lower bound of the range of actuals masters
	* @param end the upper bound of the range of actuals masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching actuals masters
	*/
	public java.util.List<ActualsMaster> findByAccountNo(
		java.lang.String accountNo, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first actuals master in the ordered set where accountNo = &#63;.
	*
	* @param accountNo the account no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching actuals master
	* @throws NoSuchActualsMasterException if a matching actuals master could not be found
	*/
	public ActualsMaster findByAccountNo_First(java.lang.String accountNo,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator)
		throws NoSuchActualsMasterException;

	/**
	* Returns the first actuals master in the ordered set where accountNo = &#63;.
	*
	* @param accountNo the account no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching actuals master, or <code>null</code> if a matching actuals master could not be found
	*/
	public ActualsMaster fetchByAccountNo_First(java.lang.String accountNo,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator);

	/**
	* Returns the last actuals master in the ordered set where accountNo = &#63;.
	*
	* @param accountNo the account no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching actuals master
	* @throws NoSuchActualsMasterException if a matching actuals master could not be found
	*/
	public ActualsMaster findByAccountNo_Last(java.lang.String accountNo,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator)
		throws NoSuchActualsMasterException;

	/**
	* Returns the last actuals master in the ordered set where accountNo = &#63;.
	*
	* @param accountNo the account no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching actuals master, or <code>null</code> if a matching actuals master could not be found
	*/
	public ActualsMaster fetchByAccountNo_Last(java.lang.String accountNo,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator);

	/**
	* Returns the actuals masters before and after the current actuals master in the ordered set where accountNo = &#63;.
	*
	* @param actualsMasterSid the primary key of the current actuals master
	* @param accountNo the account no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next actuals master
	* @throws NoSuchActualsMasterException if a actuals master with the primary key could not be found
	*/
	public ActualsMaster[] findByAccountNo_PrevAndNext(int actualsMasterSid,
		java.lang.String accountNo,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator)
		throws NoSuchActualsMasterException;

	/**
	* Removes all the actuals masters where accountNo = &#63; from the database.
	*
	* @param accountNo the account no
	*/
	public void removeByAccountNo(java.lang.String accountNo);

	/**
	* Returns the number of actuals masters where accountNo = &#63;.
	*
	* @param accountNo the account no
	* @return the number of matching actuals masters
	*/
	public int countByAccountNo(java.lang.String accountNo);

	/**
	* Returns all the actuals masters where marketId = &#63;.
	*
	* @param marketId the market ID
	* @return the matching actuals masters
	*/
	public java.util.List<ActualsMaster> findByMarketId(
		java.lang.String marketId);

	/**
	* Returns a range of all the actuals masters where marketId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param marketId the market ID
	* @param start the lower bound of the range of actuals masters
	* @param end the upper bound of the range of actuals masters (not inclusive)
	* @return the range of matching actuals masters
	*/
	public java.util.List<ActualsMaster> findByMarketId(
		java.lang.String marketId, int start, int end);

	/**
	* Returns an ordered range of all the actuals masters where marketId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param marketId the market ID
	* @param start the lower bound of the range of actuals masters
	* @param end the upper bound of the range of actuals masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching actuals masters
	*/
	public java.util.List<ActualsMaster> findByMarketId(
		java.lang.String marketId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator);

	/**
	* Returns an ordered range of all the actuals masters where marketId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param marketId the market ID
	* @param start the lower bound of the range of actuals masters
	* @param end the upper bound of the range of actuals masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching actuals masters
	*/
	public java.util.List<ActualsMaster> findByMarketId(
		java.lang.String marketId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first actuals master in the ordered set where marketId = &#63;.
	*
	* @param marketId the market ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching actuals master
	* @throws NoSuchActualsMasterException if a matching actuals master could not be found
	*/
	public ActualsMaster findByMarketId_First(java.lang.String marketId,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator)
		throws NoSuchActualsMasterException;

	/**
	* Returns the first actuals master in the ordered set where marketId = &#63;.
	*
	* @param marketId the market ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching actuals master, or <code>null</code> if a matching actuals master could not be found
	*/
	public ActualsMaster fetchByMarketId_First(java.lang.String marketId,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator);

	/**
	* Returns the last actuals master in the ordered set where marketId = &#63;.
	*
	* @param marketId the market ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching actuals master
	* @throws NoSuchActualsMasterException if a matching actuals master could not be found
	*/
	public ActualsMaster findByMarketId_Last(java.lang.String marketId,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator)
		throws NoSuchActualsMasterException;

	/**
	* Returns the last actuals master in the ordered set where marketId = &#63;.
	*
	* @param marketId the market ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching actuals master, or <code>null</code> if a matching actuals master could not be found
	*/
	public ActualsMaster fetchByMarketId_Last(java.lang.String marketId,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator);

	/**
	* Returns the actuals masters before and after the current actuals master in the ordered set where marketId = &#63;.
	*
	* @param actualsMasterSid the primary key of the current actuals master
	* @param marketId the market ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next actuals master
	* @throws NoSuchActualsMasterException if a actuals master with the primary key could not be found
	*/
	public ActualsMaster[] findByMarketId_PrevAndNext(int actualsMasterSid,
		java.lang.String marketId,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator)
		throws NoSuchActualsMasterException;

	/**
	* Removes all the actuals masters where marketId = &#63; from the database.
	*
	* @param marketId the market ID
	*/
	public void removeByMarketId(java.lang.String marketId);

	/**
	* Returns the number of actuals masters where marketId = &#63;.
	*
	* @param marketId the market ID
	* @return the number of matching actuals masters
	*/
	public int countByMarketId(java.lang.String marketId);

	/**
	* Returns all the actuals masters where brandId = &#63;.
	*
	* @param brandId the brand ID
	* @return the matching actuals masters
	*/
	public java.util.List<ActualsMaster> findByBrandId(java.lang.String brandId);

	/**
	* Returns a range of all the actuals masters where brandId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param brandId the brand ID
	* @param start the lower bound of the range of actuals masters
	* @param end the upper bound of the range of actuals masters (not inclusive)
	* @return the range of matching actuals masters
	*/
	public java.util.List<ActualsMaster> findByBrandId(
		java.lang.String brandId, int start, int end);

	/**
	* Returns an ordered range of all the actuals masters where brandId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param brandId the brand ID
	* @param start the lower bound of the range of actuals masters
	* @param end the upper bound of the range of actuals masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching actuals masters
	*/
	public java.util.List<ActualsMaster> findByBrandId(
		java.lang.String brandId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator);

	/**
	* Returns an ordered range of all the actuals masters where brandId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param brandId the brand ID
	* @param start the lower bound of the range of actuals masters
	* @param end the upper bound of the range of actuals masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching actuals masters
	*/
	public java.util.List<ActualsMaster> findByBrandId(
		java.lang.String brandId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first actuals master in the ordered set where brandId = &#63;.
	*
	* @param brandId the brand ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching actuals master
	* @throws NoSuchActualsMasterException if a matching actuals master could not be found
	*/
	public ActualsMaster findByBrandId_First(java.lang.String brandId,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator)
		throws NoSuchActualsMasterException;

	/**
	* Returns the first actuals master in the ordered set where brandId = &#63;.
	*
	* @param brandId the brand ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching actuals master, or <code>null</code> if a matching actuals master could not be found
	*/
	public ActualsMaster fetchByBrandId_First(java.lang.String brandId,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator);

	/**
	* Returns the last actuals master in the ordered set where brandId = &#63;.
	*
	* @param brandId the brand ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching actuals master
	* @throws NoSuchActualsMasterException if a matching actuals master could not be found
	*/
	public ActualsMaster findByBrandId_Last(java.lang.String brandId,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator)
		throws NoSuchActualsMasterException;

	/**
	* Returns the last actuals master in the ordered set where brandId = &#63;.
	*
	* @param brandId the brand ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching actuals master, or <code>null</code> if a matching actuals master could not be found
	*/
	public ActualsMaster fetchByBrandId_Last(java.lang.String brandId,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator);

	/**
	* Returns the actuals masters before and after the current actuals master in the ordered set where brandId = &#63;.
	*
	* @param actualsMasterSid the primary key of the current actuals master
	* @param brandId the brand ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next actuals master
	* @throws NoSuchActualsMasterException if a actuals master with the primary key could not be found
	*/
	public ActualsMaster[] findByBrandId_PrevAndNext(int actualsMasterSid,
		java.lang.String brandId,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator)
		throws NoSuchActualsMasterException;

	/**
	* Removes all the actuals masters where brandId = &#63; from the database.
	*
	* @param brandId the brand ID
	*/
	public void removeByBrandId(java.lang.String brandId);

	/**
	* Returns the number of actuals masters where brandId = &#63;.
	*
	* @param brandId the brand ID
	* @return the number of matching actuals masters
	*/
	public int countByBrandId(java.lang.String brandId);

	/**
	* Returns all the actuals masters where actualId = &#63;.
	*
	* @param actualId the actual ID
	* @return the matching actuals masters
	*/
	public java.util.List<ActualsMaster> findByActualsUnique(
		java.lang.String actualId);

	/**
	* Returns a range of all the actuals masters where actualId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param actualId the actual ID
	* @param start the lower bound of the range of actuals masters
	* @param end the upper bound of the range of actuals masters (not inclusive)
	* @return the range of matching actuals masters
	*/
	public java.util.List<ActualsMaster> findByActualsUnique(
		java.lang.String actualId, int start, int end);

	/**
	* Returns an ordered range of all the actuals masters where actualId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param actualId the actual ID
	* @param start the lower bound of the range of actuals masters
	* @param end the upper bound of the range of actuals masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching actuals masters
	*/
	public java.util.List<ActualsMaster> findByActualsUnique(
		java.lang.String actualId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator);

	/**
	* Returns an ordered range of all the actuals masters where actualId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param actualId the actual ID
	* @param start the lower bound of the range of actuals masters
	* @param end the upper bound of the range of actuals masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching actuals masters
	*/
	public java.util.List<ActualsMaster> findByActualsUnique(
		java.lang.String actualId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first actuals master in the ordered set where actualId = &#63;.
	*
	* @param actualId the actual ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching actuals master
	* @throws NoSuchActualsMasterException if a matching actuals master could not be found
	*/
	public ActualsMaster findByActualsUnique_First(java.lang.String actualId,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator)
		throws NoSuchActualsMasterException;

	/**
	* Returns the first actuals master in the ordered set where actualId = &#63;.
	*
	* @param actualId the actual ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching actuals master, or <code>null</code> if a matching actuals master could not be found
	*/
	public ActualsMaster fetchByActualsUnique_First(java.lang.String actualId,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator);

	/**
	* Returns the last actuals master in the ordered set where actualId = &#63;.
	*
	* @param actualId the actual ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching actuals master
	* @throws NoSuchActualsMasterException if a matching actuals master could not be found
	*/
	public ActualsMaster findByActualsUnique_Last(java.lang.String actualId,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator)
		throws NoSuchActualsMasterException;

	/**
	* Returns the last actuals master in the ordered set where actualId = &#63;.
	*
	* @param actualId the actual ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching actuals master, or <code>null</code> if a matching actuals master could not be found
	*/
	public ActualsMaster fetchByActualsUnique_Last(java.lang.String actualId,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator);

	/**
	* Returns the actuals masters before and after the current actuals master in the ordered set where actualId = &#63;.
	*
	* @param actualsMasterSid the primary key of the current actuals master
	* @param actualId the actual ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next actuals master
	* @throws NoSuchActualsMasterException if a actuals master with the primary key could not be found
	*/
	public ActualsMaster[] findByActualsUnique_PrevAndNext(
		int actualsMasterSid, java.lang.String actualId,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator)
		throws NoSuchActualsMasterException;

	/**
	* Removes all the actuals masters where actualId = &#63; from the database.
	*
	* @param actualId the actual ID
	*/
	public void removeByActualsUnique(java.lang.String actualId);

	/**
	* Returns the number of actuals masters where actualId = &#63;.
	*
	* @param actualId the actual ID
	* @return the number of matching actuals masters
	*/
	public int countByActualsUnique(java.lang.String actualId);

	/**
	* Caches the actuals master in the entity cache if it is enabled.
	*
	* @param actualsMaster the actuals master
	*/
	public void cacheResult(ActualsMaster actualsMaster);

	/**
	* Caches the actuals masters in the entity cache if it is enabled.
	*
	* @param actualsMasters the actuals masters
	*/
	public void cacheResult(java.util.List<ActualsMaster> actualsMasters);

	/**
	* Creates a new actuals master with the primary key. Does not add the actuals master to the database.
	*
	* @param actualsMasterSid the primary key for the new actuals master
	* @return the new actuals master
	*/
	public ActualsMaster create(int actualsMasterSid);

	/**
	* Removes the actuals master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param actualsMasterSid the primary key of the actuals master
	* @return the actuals master that was removed
	* @throws NoSuchActualsMasterException if a actuals master with the primary key could not be found
	*/
	public ActualsMaster remove(int actualsMasterSid)
		throws NoSuchActualsMasterException;

	public ActualsMaster updateImpl(ActualsMaster actualsMaster);

	/**
	* Returns the actuals master with the primary key or throws a {@link NoSuchActualsMasterException} if it could not be found.
	*
	* @param actualsMasterSid the primary key of the actuals master
	* @return the actuals master
	* @throws NoSuchActualsMasterException if a actuals master with the primary key could not be found
	*/
	public ActualsMaster findByPrimaryKey(int actualsMasterSid)
		throws NoSuchActualsMasterException;

	/**
	* Returns the actuals master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param actualsMasterSid the primary key of the actuals master
	* @return the actuals master, or <code>null</code> if a actuals master with the primary key could not be found
	*/
	public ActualsMaster fetchByPrimaryKey(int actualsMasterSid);

	@Override
	public java.util.Map<java.io.Serializable, ActualsMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the actuals masters.
	*
	* @return the actuals masters
	*/
	public java.util.List<ActualsMaster> findAll();

	/**
	* Returns a range of all the actuals masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of actuals masters
	* @param end the upper bound of the range of actuals masters (not inclusive)
	* @return the range of actuals masters
	*/
	public java.util.List<ActualsMaster> findAll(int start, int end);

	/**
	* Returns an ordered range of all the actuals masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of actuals masters
	* @param end the upper bound of the range of actuals masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of actuals masters
	*/
	public java.util.List<ActualsMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator);

	/**
	* Returns an ordered range of all the actuals masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of actuals masters
	* @param end the upper bound of the range of actuals masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of actuals masters
	*/
	public java.util.List<ActualsMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ActualsMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the actuals masters from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of actuals masters.
	*
	* @return the number of actuals masters
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}