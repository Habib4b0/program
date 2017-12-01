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

import com.stpl.app.exception.NoSuchGlBalanceMasterException;
import com.stpl.app.model.GlBalanceMaster;

import java.util.Date;

/**
 * The persistence interface for the gl balance master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.GlBalanceMasterPersistenceImpl
 * @see GlBalanceMasterUtil
 * @generated
 */
@ProviderType
public interface GlBalanceMasterPersistence extends BasePersistence<GlBalanceMaster> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link GlBalanceMasterUtil} to access the gl balance master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the gl balance masters where accountNo = &#63;.
	*
	* @param accountNo the account no
	* @return the matching gl balance masters
	*/
	public java.util.List<GlBalanceMaster> findByAccountNo(
		java.lang.String accountNo);

	/**
	* Returns a range of all the gl balance masters where accountNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountNo the account no
	* @param start the lower bound of the range of gl balance masters
	* @param end the upper bound of the range of gl balance masters (not inclusive)
	* @return the range of matching gl balance masters
	*/
	public java.util.List<GlBalanceMaster> findByAccountNo(
		java.lang.String accountNo, int start, int end);

	/**
	* Returns an ordered range of all the gl balance masters where accountNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountNo the account no
	* @param start the lower bound of the range of gl balance masters
	* @param end the upper bound of the range of gl balance masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching gl balance masters
	*/
	public java.util.List<GlBalanceMaster> findByAccountNo(
		java.lang.String accountNo, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<GlBalanceMaster> orderByComparator);

	/**
	* Returns an ordered range of all the gl balance masters where accountNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountNo the account no
	* @param start the lower bound of the range of gl balance masters
	* @param end the upper bound of the range of gl balance masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching gl balance masters
	*/
	public java.util.List<GlBalanceMaster> findByAccountNo(
		java.lang.String accountNo, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<GlBalanceMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first gl balance master in the ordered set where accountNo = &#63;.
	*
	* @param accountNo the account no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching gl balance master
	* @throws NoSuchGlBalanceMasterException if a matching gl balance master could not be found
	*/
	public GlBalanceMaster findByAccountNo_First(java.lang.String accountNo,
		com.liferay.portal.kernel.util.OrderByComparator<GlBalanceMaster> orderByComparator)
		throws NoSuchGlBalanceMasterException;

	/**
	* Returns the first gl balance master in the ordered set where accountNo = &#63;.
	*
	* @param accountNo the account no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching gl balance master, or <code>null</code> if a matching gl balance master could not be found
	*/
	public GlBalanceMaster fetchByAccountNo_First(java.lang.String accountNo,
		com.liferay.portal.kernel.util.OrderByComparator<GlBalanceMaster> orderByComparator);

	/**
	* Returns the last gl balance master in the ordered set where accountNo = &#63;.
	*
	* @param accountNo the account no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching gl balance master
	* @throws NoSuchGlBalanceMasterException if a matching gl balance master could not be found
	*/
	public GlBalanceMaster findByAccountNo_Last(java.lang.String accountNo,
		com.liferay.portal.kernel.util.OrderByComparator<GlBalanceMaster> orderByComparator)
		throws NoSuchGlBalanceMasterException;

	/**
	* Returns the last gl balance master in the ordered set where accountNo = &#63;.
	*
	* @param accountNo the account no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching gl balance master, or <code>null</code> if a matching gl balance master could not be found
	*/
	public GlBalanceMaster fetchByAccountNo_Last(java.lang.String accountNo,
		com.liferay.portal.kernel.util.OrderByComparator<GlBalanceMaster> orderByComparator);

	/**
	* Returns the gl balance masters before and after the current gl balance master in the ordered set where accountNo = &#63;.
	*
	* @param glBalanceMasterSid the primary key of the current gl balance master
	* @param accountNo the account no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next gl balance master
	* @throws NoSuchGlBalanceMasterException if a gl balance master with the primary key could not be found
	*/
	public GlBalanceMaster[] findByAccountNo_PrevAndNext(
		int glBalanceMasterSid, java.lang.String accountNo,
		com.liferay.portal.kernel.util.OrderByComparator<GlBalanceMaster> orderByComparator)
		throws NoSuchGlBalanceMasterException;

	/**
	* Removes all the gl balance masters where accountNo = &#63; from the database.
	*
	* @param accountNo the account no
	*/
	public void removeByAccountNo(java.lang.String accountNo);

	/**
	* Returns the number of gl balance masters where accountNo = &#63;.
	*
	* @param accountNo the account no
	* @return the number of matching gl balance masters
	*/
	public int countByAccountNo(java.lang.String accountNo);

	/**
	* Returns all the gl balance masters where isActive = &#63;.
	*
	* @param isActive the is active
	* @return the matching gl balance masters
	*/
	public java.util.List<GlBalanceMaster> findByIsActive(
		java.lang.String isActive);

	/**
	* Returns a range of all the gl balance masters where isActive = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param isActive the is active
	* @param start the lower bound of the range of gl balance masters
	* @param end the upper bound of the range of gl balance masters (not inclusive)
	* @return the range of matching gl balance masters
	*/
	public java.util.List<GlBalanceMaster> findByIsActive(
		java.lang.String isActive, int start, int end);

	/**
	* Returns an ordered range of all the gl balance masters where isActive = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param isActive the is active
	* @param start the lower bound of the range of gl balance masters
	* @param end the upper bound of the range of gl balance masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching gl balance masters
	*/
	public java.util.List<GlBalanceMaster> findByIsActive(
		java.lang.String isActive, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<GlBalanceMaster> orderByComparator);

	/**
	* Returns an ordered range of all the gl balance masters where isActive = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param isActive the is active
	* @param start the lower bound of the range of gl balance masters
	* @param end the upper bound of the range of gl balance masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching gl balance masters
	*/
	public java.util.List<GlBalanceMaster> findByIsActive(
		java.lang.String isActive, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<GlBalanceMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first gl balance master in the ordered set where isActive = &#63;.
	*
	* @param isActive the is active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching gl balance master
	* @throws NoSuchGlBalanceMasterException if a matching gl balance master could not be found
	*/
	public GlBalanceMaster findByIsActive_First(java.lang.String isActive,
		com.liferay.portal.kernel.util.OrderByComparator<GlBalanceMaster> orderByComparator)
		throws NoSuchGlBalanceMasterException;

	/**
	* Returns the first gl balance master in the ordered set where isActive = &#63;.
	*
	* @param isActive the is active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching gl balance master, or <code>null</code> if a matching gl balance master could not be found
	*/
	public GlBalanceMaster fetchByIsActive_First(java.lang.String isActive,
		com.liferay.portal.kernel.util.OrderByComparator<GlBalanceMaster> orderByComparator);

	/**
	* Returns the last gl balance master in the ordered set where isActive = &#63;.
	*
	* @param isActive the is active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching gl balance master
	* @throws NoSuchGlBalanceMasterException if a matching gl balance master could not be found
	*/
	public GlBalanceMaster findByIsActive_Last(java.lang.String isActive,
		com.liferay.portal.kernel.util.OrderByComparator<GlBalanceMaster> orderByComparator)
		throws NoSuchGlBalanceMasterException;

	/**
	* Returns the last gl balance master in the ordered set where isActive = &#63;.
	*
	* @param isActive the is active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching gl balance master, or <code>null</code> if a matching gl balance master could not be found
	*/
	public GlBalanceMaster fetchByIsActive_Last(java.lang.String isActive,
		com.liferay.portal.kernel.util.OrderByComparator<GlBalanceMaster> orderByComparator);

	/**
	* Returns the gl balance masters before and after the current gl balance master in the ordered set where isActive = &#63;.
	*
	* @param glBalanceMasterSid the primary key of the current gl balance master
	* @param isActive the is active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next gl balance master
	* @throws NoSuchGlBalanceMasterException if a gl balance master with the primary key could not be found
	*/
	public GlBalanceMaster[] findByIsActive_PrevAndNext(
		int glBalanceMasterSid, java.lang.String isActive,
		com.liferay.portal.kernel.util.OrderByComparator<GlBalanceMaster> orderByComparator)
		throws NoSuchGlBalanceMasterException;

	/**
	* Removes all the gl balance masters where isActive = &#63; from the database.
	*
	* @param isActive the is active
	*/
	public void removeByIsActive(java.lang.String isActive);

	/**
	* Returns the number of gl balance masters where isActive = &#63;.
	*
	* @param isActive the is active
	* @return the number of matching gl balance masters
	*/
	public int countByIsActive(java.lang.String isActive);

	/**
	* Returns all the gl balance masters where uploadDate = &#63;.
	*
	* @param uploadDate the upload date
	* @return the matching gl balance masters
	*/
	public java.util.List<GlBalanceMaster> findByUploadDate(Date uploadDate);

	/**
	* Returns a range of all the gl balance masters where uploadDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uploadDate the upload date
	* @param start the lower bound of the range of gl balance masters
	* @param end the upper bound of the range of gl balance masters (not inclusive)
	* @return the range of matching gl balance masters
	*/
	public java.util.List<GlBalanceMaster> findByUploadDate(Date uploadDate,
		int start, int end);

	/**
	* Returns an ordered range of all the gl balance masters where uploadDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uploadDate the upload date
	* @param start the lower bound of the range of gl balance masters
	* @param end the upper bound of the range of gl balance masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching gl balance masters
	*/
	public java.util.List<GlBalanceMaster> findByUploadDate(Date uploadDate,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<GlBalanceMaster> orderByComparator);

	/**
	* Returns an ordered range of all the gl balance masters where uploadDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uploadDate the upload date
	* @param start the lower bound of the range of gl balance masters
	* @param end the upper bound of the range of gl balance masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching gl balance masters
	*/
	public java.util.List<GlBalanceMaster> findByUploadDate(Date uploadDate,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<GlBalanceMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first gl balance master in the ordered set where uploadDate = &#63;.
	*
	* @param uploadDate the upload date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching gl balance master
	* @throws NoSuchGlBalanceMasterException if a matching gl balance master could not be found
	*/
	public GlBalanceMaster findByUploadDate_First(Date uploadDate,
		com.liferay.portal.kernel.util.OrderByComparator<GlBalanceMaster> orderByComparator)
		throws NoSuchGlBalanceMasterException;

	/**
	* Returns the first gl balance master in the ordered set where uploadDate = &#63;.
	*
	* @param uploadDate the upload date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching gl balance master, or <code>null</code> if a matching gl balance master could not be found
	*/
	public GlBalanceMaster fetchByUploadDate_First(Date uploadDate,
		com.liferay.portal.kernel.util.OrderByComparator<GlBalanceMaster> orderByComparator);

	/**
	* Returns the last gl balance master in the ordered set where uploadDate = &#63;.
	*
	* @param uploadDate the upload date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching gl balance master
	* @throws NoSuchGlBalanceMasterException if a matching gl balance master could not be found
	*/
	public GlBalanceMaster findByUploadDate_Last(Date uploadDate,
		com.liferay.portal.kernel.util.OrderByComparator<GlBalanceMaster> orderByComparator)
		throws NoSuchGlBalanceMasterException;

	/**
	* Returns the last gl balance master in the ordered set where uploadDate = &#63;.
	*
	* @param uploadDate the upload date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching gl balance master, or <code>null</code> if a matching gl balance master could not be found
	*/
	public GlBalanceMaster fetchByUploadDate_Last(Date uploadDate,
		com.liferay.portal.kernel.util.OrderByComparator<GlBalanceMaster> orderByComparator);

	/**
	* Returns the gl balance masters before and after the current gl balance master in the ordered set where uploadDate = &#63;.
	*
	* @param glBalanceMasterSid the primary key of the current gl balance master
	* @param uploadDate the upload date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next gl balance master
	* @throws NoSuchGlBalanceMasterException if a gl balance master with the primary key could not be found
	*/
	public GlBalanceMaster[] findByUploadDate_PrevAndNext(
		int glBalanceMasterSid, Date uploadDate,
		com.liferay.portal.kernel.util.OrderByComparator<GlBalanceMaster> orderByComparator)
		throws NoSuchGlBalanceMasterException;

	/**
	* Removes all the gl balance masters where uploadDate = &#63; from the database.
	*
	* @param uploadDate the upload date
	*/
	public void removeByUploadDate(Date uploadDate);

	/**
	* Returns the number of gl balance masters where uploadDate = &#63;.
	*
	* @param uploadDate the upload date
	* @return the number of matching gl balance masters
	*/
	public int countByUploadDate(Date uploadDate);

	/**
	* Returns all the gl balance masters where accountId = &#63;.
	*
	* @param accountId the account ID
	* @return the matching gl balance masters
	*/
	public java.util.List<GlBalanceMaster> findByAccountId(
		java.lang.String accountId);

	/**
	* Returns a range of all the gl balance masters where accountId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountId the account ID
	* @param start the lower bound of the range of gl balance masters
	* @param end the upper bound of the range of gl balance masters (not inclusive)
	* @return the range of matching gl balance masters
	*/
	public java.util.List<GlBalanceMaster> findByAccountId(
		java.lang.String accountId, int start, int end);

	/**
	* Returns an ordered range of all the gl balance masters where accountId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountId the account ID
	* @param start the lower bound of the range of gl balance masters
	* @param end the upper bound of the range of gl balance masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching gl balance masters
	*/
	public java.util.List<GlBalanceMaster> findByAccountId(
		java.lang.String accountId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<GlBalanceMaster> orderByComparator);

	/**
	* Returns an ordered range of all the gl balance masters where accountId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountId the account ID
	* @param start the lower bound of the range of gl balance masters
	* @param end the upper bound of the range of gl balance masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching gl balance masters
	*/
	public java.util.List<GlBalanceMaster> findByAccountId(
		java.lang.String accountId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<GlBalanceMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first gl balance master in the ordered set where accountId = &#63;.
	*
	* @param accountId the account ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching gl balance master
	* @throws NoSuchGlBalanceMasterException if a matching gl balance master could not be found
	*/
	public GlBalanceMaster findByAccountId_First(java.lang.String accountId,
		com.liferay.portal.kernel.util.OrderByComparator<GlBalanceMaster> orderByComparator)
		throws NoSuchGlBalanceMasterException;

	/**
	* Returns the first gl balance master in the ordered set where accountId = &#63;.
	*
	* @param accountId the account ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching gl balance master, or <code>null</code> if a matching gl balance master could not be found
	*/
	public GlBalanceMaster fetchByAccountId_First(java.lang.String accountId,
		com.liferay.portal.kernel.util.OrderByComparator<GlBalanceMaster> orderByComparator);

	/**
	* Returns the last gl balance master in the ordered set where accountId = &#63;.
	*
	* @param accountId the account ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching gl balance master
	* @throws NoSuchGlBalanceMasterException if a matching gl balance master could not be found
	*/
	public GlBalanceMaster findByAccountId_Last(java.lang.String accountId,
		com.liferay.portal.kernel.util.OrderByComparator<GlBalanceMaster> orderByComparator)
		throws NoSuchGlBalanceMasterException;

	/**
	* Returns the last gl balance master in the ordered set where accountId = &#63;.
	*
	* @param accountId the account ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching gl balance master, or <code>null</code> if a matching gl balance master could not be found
	*/
	public GlBalanceMaster fetchByAccountId_Last(java.lang.String accountId,
		com.liferay.portal.kernel.util.OrderByComparator<GlBalanceMaster> orderByComparator);

	/**
	* Returns the gl balance masters before and after the current gl balance master in the ordered set where accountId = &#63;.
	*
	* @param glBalanceMasterSid the primary key of the current gl balance master
	* @param accountId the account ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next gl balance master
	* @throws NoSuchGlBalanceMasterException if a gl balance master with the primary key could not be found
	*/
	public GlBalanceMaster[] findByAccountId_PrevAndNext(
		int glBalanceMasterSid, java.lang.String accountId,
		com.liferay.portal.kernel.util.OrderByComparator<GlBalanceMaster> orderByComparator)
		throws NoSuchGlBalanceMasterException;

	/**
	* Removes all the gl balance masters where accountId = &#63; from the database.
	*
	* @param accountId the account ID
	*/
	public void removeByAccountId(java.lang.String accountId);

	/**
	* Returns the number of gl balance masters where accountId = &#63;.
	*
	* @param accountId the account ID
	* @return the number of matching gl balance masters
	*/
	public int countByAccountId(java.lang.String accountId);

	/**
	* Returns all the gl balance masters where year = &#63;.
	*
	* @param year the year
	* @return the matching gl balance masters
	*/
	public java.util.List<GlBalanceMaster> findByYear(java.lang.String year);

	/**
	* Returns a range of all the gl balance masters where year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param year the year
	* @param start the lower bound of the range of gl balance masters
	* @param end the upper bound of the range of gl balance masters (not inclusive)
	* @return the range of matching gl balance masters
	*/
	public java.util.List<GlBalanceMaster> findByYear(java.lang.String year,
		int start, int end);

	/**
	* Returns an ordered range of all the gl balance masters where year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param year the year
	* @param start the lower bound of the range of gl balance masters
	* @param end the upper bound of the range of gl balance masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching gl balance masters
	*/
	public java.util.List<GlBalanceMaster> findByYear(java.lang.String year,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<GlBalanceMaster> orderByComparator);

	/**
	* Returns an ordered range of all the gl balance masters where year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param year the year
	* @param start the lower bound of the range of gl balance masters
	* @param end the upper bound of the range of gl balance masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching gl balance masters
	*/
	public java.util.List<GlBalanceMaster> findByYear(java.lang.String year,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<GlBalanceMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first gl balance master in the ordered set where year = &#63;.
	*
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching gl balance master
	* @throws NoSuchGlBalanceMasterException if a matching gl balance master could not be found
	*/
	public GlBalanceMaster findByYear_First(java.lang.String year,
		com.liferay.portal.kernel.util.OrderByComparator<GlBalanceMaster> orderByComparator)
		throws NoSuchGlBalanceMasterException;

	/**
	* Returns the first gl balance master in the ordered set where year = &#63;.
	*
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching gl balance master, or <code>null</code> if a matching gl balance master could not be found
	*/
	public GlBalanceMaster fetchByYear_First(java.lang.String year,
		com.liferay.portal.kernel.util.OrderByComparator<GlBalanceMaster> orderByComparator);

	/**
	* Returns the last gl balance master in the ordered set where year = &#63;.
	*
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching gl balance master
	* @throws NoSuchGlBalanceMasterException if a matching gl balance master could not be found
	*/
	public GlBalanceMaster findByYear_Last(java.lang.String year,
		com.liferay.portal.kernel.util.OrderByComparator<GlBalanceMaster> orderByComparator)
		throws NoSuchGlBalanceMasterException;

	/**
	* Returns the last gl balance master in the ordered set where year = &#63;.
	*
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching gl balance master, or <code>null</code> if a matching gl balance master could not be found
	*/
	public GlBalanceMaster fetchByYear_Last(java.lang.String year,
		com.liferay.portal.kernel.util.OrderByComparator<GlBalanceMaster> orderByComparator);

	/**
	* Returns the gl balance masters before and after the current gl balance master in the ordered set where year = &#63;.
	*
	* @param glBalanceMasterSid the primary key of the current gl balance master
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next gl balance master
	* @throws NoSuchGlBalanceMasterException if a gl balance master with the primary key could not be found
	*/
	public GlBalanceMaster[] findByYear_PrevAndNext(int glBalanceMasterSid,
		java.lang.String year,
		com.liferay.portal.kernel.util.OrderByComparator<GlBalanceMaster> orderByComparator)
		throws NoSuchGlBalanceMasterException;

	/**
	* Removes all the gl balance masters where year = &#63; from the database.
	*
	* @param year the year
	*/
	public void removeByYear(java.lang.String year);

	/**
	* Returns the number of gl balance masters where year = &#63;.
	*
	* @param year the year
	* @return the number of matching gl balance masters
	*/
	public int countByYear(java.lang.String year);

	/**
	* Returns all the gl balance masters where period = &#63;.
	*
	* @param period the period
	* @return the matching gl balance masters
	*/
	public java.util.List<GlBalanceMaster> findByPeriod(java.lang.String period);

	/**
	* Returns a range of all the gl balance masters where period = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param period the period
	* @param start the lower bound of the range of gl balance masters
	* @param end the upper bound of the range of gl balance masters (not inclusive)
	* @return the range of matching gl balance masters
	*/
	public java.util.List<GlBalanceMaster> findByPeriod(
		java.lang.String period, int start, int end);

	/**
	* Returns an ordered range of all the gl balance masters where period = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param period the period
	* @param start the lower bound of the range of gl balance masters
	* @param end the upper bound of the range of gl balance masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching gl balance masters
	*/
	public java.util.List<GlBalanceMaster> findByPeriod(
		java.lang.String period, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<GlBalanceMaster> orderByComparator);

	/**
	* Returns an ordered range of all the gl balance masters where period = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param period the period
	* @param start the lower bound of the range of gl balance masters
	* @param end the upper bound of the range of gl balance masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching gl balance masters
	*/
	public java.util.List<GlBalanceMaster> findByPeriod(
		java.lang.String period, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<GlBalanceMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first gl balance master in the ordered set where period = &#63;.
	*
	* @param period the period
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching gl balance master
	* @throws NoSuchGlBalanceMasterException if a matching gl balance master could not be found
	*/
	public GlBalanceMaster findByPeriod_First(java.lang.String period,
		com.liferay.portal.kernel.util.OrderByComparator<GlBalanceMaster> orderByComparator)
		throws NoSuchGlBalanceMasterException;

	/**
	* Returns the first gl balance master in the ordered set where period = &#63;.
	*
	* @param period the period
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching gl balance master, or <code>null</code> if a matching gl balance master could not be found
	*/
	public GlBalanceMaster fetchByPeriod_First(java.lang.String period,
		com.liferay.portal.kernel.util.OrderByComparator<GlBalanceMaster> orderByComparator);

	/**
	* Returns the last gl balance master in the ordered set where period = &#63;.
	*
	* @param period the period
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching gl balance master
	* @throws NoSuchGlBalanceMasterException if a matching gl balance master could not be found
	*/
	public GlBalanceMaster findByPeriod_Last(java.lang.String period,
		com.liferay.portal.kernel.util.OrderByComparator<GlBalanceMaster> orderByComparator)
		throws NoSuchGlBalanceMasterException;

	/**
	* Returns the last gl balance master in the ordered set where period = &#63;.
	*
	* @param period the period
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching gl balance master, or <code>null</code> if a matching gl balance master could not be found
	*/
	public GlBalanceMaster fetchByPeriod_Last(java.lang.String period,
		com.liferay.portal.kernel.util.OrderByComparator<GlBalanceMaster> orderByComparator);

	/**
	* Returns the gl balance masters before and after the current gl balance master in the ordered set where period = &#63;.
	*
	* @param glBalanceMasterSid the primary key of the current gl balance master
	* @param period the period
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next gl balance master
	* @throws NoSuchGlBalanceMasterException if a gl balance master with the primary key could not be found
	*/
	public GlBalanceMaster[] findByPeriod_PrevAndNext(int glBalanceMasterSid,
		java.lang.String period,
		com.liferay.portal.kernel.util.OrderByComparator<GlBalanceMaster> orderByComparator)
		throws NoSuchGlBalanceMasterException;

	/**
	* Removes all the gl balance masters where period = &#63; from the database.
	*
	* @param period the period
	*/
	public void removeByPeriod(java.lang.String period);

	/**
	* Returns the number of gl balance masters where period = &#63;.
	*
	* @param period the period
	* @return the number of matching gl balance masters
	*/
	public int countByPeriod(java.lang.String period);

	/**
	* Returns all the gl balance masters where accountNo = &#63; and period = &#63; and uploadDate = &#63;.
	*
	* @param accountNo the account no
	* @param period the period
	* @param uploadDate the upload date
	* @return the matching gl balance masters
	*/
	public java.util.List<GlBalanceMaster> findByGlBalanceUnique(
		java.lang.String accountNo, java.lang.String period, Date uploadDate);

	/**
	* Returns a range of all the gl balance masters where accountNo = &#63; and period = &#63; and uploadDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountNo the account no
	* @param period the period
	* @param uploadDate the upload date
	* @param start the lower bound of the range of gl balance masters
	* @param end the upper bound of the range of gl balance masters (not inclusive)
	* @return the range of matching gl balance masters
	*/
	public java.util.List<GlBalanceMaster> findByGlBalanceUnique(
		java.lang.String accountNo, java.lang.String period, Date uploadDate,
		int start, int end);

	/**
	* Returns an ordered range of all the gl balance masters where accountNo = &#63; and period = &#63; and uploadDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountNo the account no
	* @param period the period
	* @param uploadDate the upload date
	* @param start the lower bound of the range of gl balance masters
	* @param end the upper bound of the range of gl balance masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching gl balance masters
	*/
	public java.util.List<GlBalanceMaster> findByGlBalanceUnique(
		java.lang.String accountNo, java.lang.String period, Date uploadDate,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<GlBalanceMaster> orderByComparator);

	/**
	* Returns an ordered range of all the gl balance masters where accountNo = &#63; and period = &#63; and uploadDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountNo the account no
	* @param period the period
	* @param uploadDate the upload date
	* @param start the lower bound of the range of gl balance masters
	* @param end the upper bound of the range of gl balance masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching gl balance masters
	*/
	public java.util.List<GlBalanceMaster> findByGlBalanceUnique(
		java.lang.String accountNo, java.lang.String period, Date uploadDate,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<GlBalanceMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first gl balance master in the ordered set where accountNo = &#63; and period = &#63; and uploadDate = &#63;.
	*
	* @param accountNo the account no
	* @param period the period
	* @param uploadDate the upload date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching gl balance master
	* @throws NoSuchGlBalanceMasterException if a matching gl balance master could not be found
	*/
	public GlBalanceMaster findByGlBalanceUnique_First(
		java.lang.String accountNo, java.lang.String period, Date uploadDate,
		com.liferay.portal.kernel.util.OrderByComparator<GlBalanceMaster> orderByComparator)
		throws NoSuchGlBalanceMasterException;

	/**
	* Returns the first gl balance master in the ordered set where accountNo = &#63; and period = &#63; and uploadDate = &#63;.
	*
	* @param accountNo the account no
	* @param period the period
	* @param uploadDate the upload date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching gl balance master, or <code>null</code> if a matching gl balance master could not be found
	*/
	public GlBalanceMaster fetchByGlBalanceUnique_First(
		java.lang.String accountNo, java.lang.String period, Date uploadDate,
		com.liferay.portal.kernel.util.OrderByComparator<GlBalanceMaster> orderByComparator);

	/**
	* Returns the last gl balance master in the ordered set where accountNo = &#63; and period = &#63; and uploadDate = &#63;.
	*
	* @param accountNo the account no
	* @param period the period
	* @param uploadDate the upload date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching gl balance master
	* @throws NoSuchGlBalanceMasterException if a matching gl balance master could not be found
	*/
	public GlBalanceMaster findByGlBalanceUnique_Last(
		java.lang.String accountNo, java.lang.String period, Date uploadDate,
		com.liferay.portal.kernel.util.OrderByComparator<GlBalanceMaster> orderByComparator)
		throws NoSuchGlBalanceMasterException;

	/**
	* Returns the last gl balance master in the ordered set where accountNo = &#63; and period = &#63; and uploadDate = &#63;.
	*
	* @param accountNo the account no
	* @param period the period
	* @param uploadDate the upload date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching gl balance master, or <code>null</code> if a matching gl balance master could not be found
	*/
	public GlBalanceMaster fetchByGlBalanceUnique_Last(
		java.lang.String accountNo, java.lang.String period, Date uploadDate,
		com.liferay.portal.kernel.util.OrderByComparator<GlBalanceMaster> orderByComparator);

	/**
	* Returns the gl balance masters before and after the current gl balance master in the ordered set where accountNo = &#63; and period = &#63; and uploadDate = &#63;.
	*
	* @param glBalanceMasterSid the primary key of the current gl balance master
	* @param accountNo the account no
	* @param period the period
	* @param uploadDate the upload date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next gl balance master
	* @throws NoSuchGlBalanceMasterException if a gl balance master with the primary key could not be found
	*/
	public GlBalanceMaster[] findByGlBalanceUnique_PrevAndNext(
		int glBalanceMasterSid, java.lang.String accountNo,
		java.lang.String period, Date uploadDate,
		com.liferay.portal.kernel.util.OrderByComparator<GlBalanceMaster> orderByComparator)
		throws NoSuchGlBalanceMasterException;

	/**
	* Removes all the gl balance masters where accountNo = &#63; and period = &#63; and uploadDate = &#63; from the database.
	*
	* @param accountNo the account no
	* @param period the period
	* @param uploadDate the upload date
	*/
	public void removeByGlBalanceUnique(java.lang.String accountNo,
		java.lang.String period, Date uploadDate);

	/**
	* Returns the number of gl balance masters where accountNo = &#63; and period = &#63; and uploadDate = &#63;.
	*
	* @param accountNo the account no
	* @param period the period
	* @param uploadDate the upload date
	* @return the number of matching gl balance masters
	*/
	public int countByGlBalanceUnique(java.lang.String accountNo,
		java.lang.String period, Date uploadDate);

	/**
	* Caches the gl balance master in the entity cache if it is enabled.
	*
	* @param glBalanceMaster the gl balance master
	*/
	public void cacheResult(GlBalanceMaster glBalanceMaster);

	/**
	* Caches the gl balance masters in the entity cache if it is enabled.
	*
	* @param glBalanceMasters the gl balance masters
	*/
	public void cacheResult(java.util.List<GlBalanceMaster> glBalanceMasters);

	/**
	* Creates a new gl balance master with the primary key. Does not add the gl balance master to the database.
	*
	* @param glBalanceMasterSid the primary key for the new gl balance master
	* @return the new gl balance master
	*/
	public GlBalanceMaster create(int glBalanceMasterSid);

	/**
	* Removes the gl balance master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param glBalanceMasterSid the primary key of the gl balance master
	* @return the gl balance master that was removed
	* @throws NoSuchGlBalanceMasterException if a gl balance master with the primary key could not be found
	*/
	public GlBalanceMaster remove(int glBalanceMasterSid)
		throws NoSuchGlBalanceMasterException;

	public GlBalanceMaster updateImpl(GlBalanceMaster glBalanceMaster);

	/**
	* Returns the gl balance master with the primary key or throws a {@link NoSuchGlBalanceMasterException} if it could not be found.
	*
	* @param glBalanceMasterSid the primary key of the gl balance master
	* @return the gl balance master
	* @throws NoSuchGlBalanceMasterException if a gl balance master with the primary key could not be found
	*/
	public GlBalanceMaster findByPrimaryKey(int glBalanceMasterSid)
		throws NoSuchGlBalanceMasterException;

	/**
	* Returns the gl balance master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param glBalanceMasterSid the primary key of the gl balance master
	* @return the gl balance master, or <code>null</code> if a gl balance master with the primary key could not be found
	*/
	public GlBalanceMaster fetchByPrimaryKey(int glBalanceMasterSid);

	@Override
	public java.util.Map<java.io.Serializable, GlBalanceMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the gl balance masters.
	*
	* @return the gl balance masters
	*/
	public java.util.List<GlBalanceMaster> findAll();

	/**
	* Returns a range of all the gl balance masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of gl balance masters
	* @param end the upper bound of the range of gl balance masters (not inclusive)
	* @return the range of gl balance masters
	*/
	public java.util.List<GlBalanceMaster> findAll(int start, int end);

	/**
	* Returns an ordered range of all the gl balance masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of gl balance masters
	* @param end the upper bound of the range of gl balance masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of gl balance masters
	*/
	public java.util.List<GlBalanceMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<GlBalanceMaster> orderByComparator);

	/**
	* Returns an ordered range of all the gl balance masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of gl balance masters
	* @param end the upper bound of the range of gl balance masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of gl balance masters
	*/
	public java.util.List<GlBalanceMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<GlBalanceMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the gl balance masters from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of gl balance masters.
	*
	* @return the number of gl balance masters
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}