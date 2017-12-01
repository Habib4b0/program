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

import com.stpl.app.exception.NoSuchFormulaDetailsMasterException;
import com.stpl.app.model.FormulaDetailsMaster;

import java.util.Date;

/**
 * The persistence interface for the formula details master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.FormulaDetailsMasterPersistenceImpl
 * @see FormulaDetailsMasterUtil
 * @generated
 */
@ProviderType
public interface FormulaDetailsMasterPersistence extends BasePersistence<FormulaDetailsMaster> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link FormulaDetailsMasterUtil} to access the formula details master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the formula details masters where formulaId = &#63;.
	*
	* @param formulaId the formula ID
	* @return the matching formula details masters
	*/
	public java.util.List<FormulaDetailsMaster> findByFormulaId(
		java.lang.String formulaId);

	/**
	* Returns a range of all the formula details masters where formulaId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param formulaId the formula ID
	* @param start the lower bound of the range of formula details masters
	* @param end the upper bound of the range of formula details masters (not inclusive)
	* @return the range of matching formula details masters
	*/
	public java.util.List<FormulaDetailsMaster> findByFormulaId(
		java.lang.String formulaId, int start, int end);

	/**
	* Returns an ordered range of all the formula details masters where formulaId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param formulaId the formula ID
	* @param start the lower bound of the range of formula details masters
	* @param end the upper bound of the range of formula details masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching formula details masters
	*/
	public java.util.List<FormulaDetailsMaster> findByFormulaId(
		java.lang.String formulaId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FormulaDetailsMaster> orderByComparator);

	/**
	* Returns an ordered range of all the formula details masters where formulaId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param formulaId the formula ID
	* @param start the lower bound of the range of formula details masters
	* @param end the upper bound of the range of formula details masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching formula details masters
	*/
	public java.util.List<FormulaDetailsMaster> findByFormulaId(
		java.lang.String formulaId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FormulaDetailsMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first formula details master in the ordered set where formulaId = &#63;.
	*
	* @param formulaId the formula ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching formula details master
	* @throws NoSuchFormulaDetailsMasterException if a matching formula details master could not be found
	*/
	public FormulaDetailsMaster findByFormulaId_First(
		java.lang.String formulaId,
		com.liferay.portal.kernel.util.OrderByComparator<FormulaDetailsMaster> orderByComparator)
		throws NoSuchFormulaDetailsMasterException;

	/**
	* Returns the first formula details master in the ordered set where formulaId = &#63;.
	*
	* @param formulaId the formula ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching formula details master, or <code>null</code> if a matching formula details master could not be found
	*/
	public FormulaDetailsMaster fetchByFormulaId_First(
		java.lang.String formulaId,
		com.liferay.portal.kernel.util.OrderByComparator<FormulaDetailsMaster> orderByComparator);

	/**
	* Returns the last formula details master in the ordered set where formulaId = &#63;.
	*
	* @param formulaId the formula ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching formula details master
	* @throws NoSuchFormulaDetailsMasterException if a matching formula details master could not be found
	*/
	public FormulaDetailsMaster findByFormulaId_Last(
		java.lang.String formulaId,
		com.liferay.portal.kernel.util.OrderByComparator<FormulaDetailsMaster> orderByComparator)
		throws NoSuchFormulaDetailsMasterException;

	/**
	* Returns the last formula details master in the ordered set where formulaId = &#63;.
	*
	* @param formulaId the formula ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching formula details master, or <code>null</code> if a matching formula details master could not be found
	*/
	public FormulaDetailsMaster fetchByFormulaId_Last(
		java.lang.String formulaId,
		com.liferay.portal.kernel.util.OrderByComparator<FormulaDetailsMaster> orderByComparator);

	/**
	* Returns the formula details masters before and after the current formula details master in the ordered set where formulaId = &#63;.
	*
	* @param formulaDetailsMasterSid the primary key of the current formula details master
	* @param formulaId the formula ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next formula details master
	* @throws NoSuchFormulaDetailsMasterException if a formula details master with the primary key could not be found
	*/
	public FormulaDetailsMaster[] findByFormulaId_PrevAndNext(
		int formulaDetailsMasterSid, java.lang.String formulaId,
		com.liferay.portal.kernel.util.OrderByComparator<FormulaDetailsMaster> orderByComparator)
		throws NoSuchFormulaDetailsMasterException;

	/**
	* Removes all the formula details masters where formulaId = &#63; from the database.
	*
	* @param formulaId the formula ID
	*/
	public void removeByFormulaId(java.lang.String formulaId);

	/**
	* Returns the number of formula details masters where formulaId = &#63;.
	*
	* @param formulaId the formula ID
	* @return the number of matching formula details masters
	*/
	public int countByFormulaId(java.lang.String formulaId);

	/**
	* Returns all the formula details masters where formulaNo = &#63;.
	*
	* @param formulaNo the formula no
	* @return the matching formula details masters
	*/
	public java.util.List<FormulaDetailsMaster> findByFormulaNo(
		java.lang.String formulaNo);

	/**
	* Returns a range of all the formula details masters where formulaNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param formulaNo the formula no
	* @param start the lower bound of the range of formula details masters
	* @param end the upper bound of the range of formula details masters (not inclusive)
	* @return the range of matching formula details masters
	*/
	public java.util.List<FormulaDetailsMaster> findByFormulaNo(
		java.lang.String formulaNo, int start, int end);

	/**
	* Returns an ordered range of all the formula details masters where formulaNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param formulaNo the formula no
	* @param start the lower bound of the range of formula details masters
	* @param end the upper bound of the range of formula details masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching formula details masters
	*/
	public java.util.List<FormulaDetailsMaster> findByFormulaNo(
		java.lang.String formulaNo, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FormulaDetailsMaster> orderByComparator);

	/**
	* Returns an ordered range of all the formula details masters where formulaNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param formulaNo the formula no
	* @param start the lower bound of the range of formula details masters
	* @param end the upper bound of the range of formula details masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching formula details masters
	*/
	public java.util.List<FormulaDetailsMaster> findByFormulaNo(
		java.lang.String formulaNo, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FormulaDetailsMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first formula details master in the ordered set where formulaNo = &#63;.
	*
	* @param formulaNo the formula no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching formula details master
	* @throws NoSuchFormulaDetailsMasterException if a matching formula details master could not be found
	*/
	public FormulaDetailsMaster findByFormulaNo_First(
		java.lang.String formulaNo,
		com.liferay.portal.kernel.util.OrderByComparator<FormulaDetailsMaster> orderByComparator)
		throws NoSuchFormulaDetailsMasterException;

	/**
	* Returns the first formula details master in the ordered set where formulaNo = &#63;.
	*
	* @param formulaNo the formula no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching formula details master, or <code>null</code> if a matching formula details master could not be found
	*/
	public FormulaDetailsMaster fetchByFormulaNo_First(
		java.lang.String formulaNo,
		com.liferay.portal.kernel.util.OrderByComparator<FormulaDetailsMaster> orderByComparator);

	/**
	* Returns the last formula details master in the ordered set where formulaNo = &#63;.
	*
	* @param formulaNo the formula no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching formula details master
	* @throws NoSuchFormulaDetailsMasterException if a matching formula details master could not be found
	*/
	public FormulaDetailsMaster findByFormulaNo_Last(
		java.lang.String formulaNo,
		com.liferay.portal.kernel.util.OrderByComparator<FormulaDetailsMaster> orderByComparator)
		throws NoSuchFormulaDetailsMasterException;

	/**
	* Returns the last formula details master in the ordered set where formulaNo = &#63;.
	*
	* @param formulaNo the formula no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching formula details master, or <code>null</code> if a matching formula details master could not be found
	*/
	public FormulaDetailsMaster fetchByFormulaNo_Last(
		java.lang.String formulaNo,
		com.liferay.portal.kernel.util.OrderByComparator<FormulaDetailsMaster> orderByComparator);

	/**
	* Returns the formula details masters before and after the current formula details master in the ordered set where formulaNo = &#63;.
	*
	* @param formulaDetailsMasterSid the primary key of the current formula details master
	* @param formulaNo the formula no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next formula details master
	* @throws NoSuchFormulaDetailsMasterException if a formula details master with the primary key could not be found
	*/
	public FormulaDetailsMaster[] findByFormulaNo_PrevAndNext(
		int formulaDetailsMasterSid, java.lang.String formulaNo,
		com.liferay.portal.kernel.util.OrderByComparator<FormulaDetailsMaster> orderByComparator)
		throws NoSuchFormulaDetailsMasterException;

	/**
	* Removes all the formula details masters where formulaNo = &#63; from the database.
	*
	* @param formulaNo the formula no
	*/
	public void removeByFormulaNo(java.lang.String formulaNo);

	/**
	* Returns the number of formula details masters where formulaNo = &#63;.
	*
	* @param formulaNo the formula no
	* @return the number of matching formula details masters
	*/
	public int countByFormulaNo(java.lang.String formulaNo);

	/**
	* Returns all the formula details masters where itemId = &#63;.
	*
	* @param itemId the item ID
	* @return the matching formula details masters
	*/
	public java.util.List<FormulaDetailsMaster> findByItemId(
		java.lang.String itemId);

	/**
	* Returns a range of all the formula details masters where itemId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemId the item ID
	* @param start the lower bound of the range of formula details masters
	* @param end the upper bound of the range of formula details masters (not inclusive)
	* @return the range of matching formula details masters
	*/
	public java.util.List<FormulaDetailsMaster> findByItemId(
		java.lang.String itemId, int start, int end);

	/**
	* Returns an ordered range of all the formula details masters where itemId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemId the item ID
	* @param start the lower bound of the range of formula details masters
	* @param end the upper bound of the range of formula details masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching formula details masters
	*/
	public java.util.List<FormulaDetailsMaster> findByItemId(
		java.lang.String itemId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FormulaDetailsMaster> orderByComparator);

	/**
	* Returns an ordered range of all the formula details masters where itemId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemId the item ID
	* @param start the lower bound of the range of formula details masters
	* @param end the upper bound of the range of formula details masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching formula details masters
	*/
	public java.util.List<FormulaDetailsMaster> findByItemId(
		java.lang.String itemId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FormulaDetailsMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first formula details master in the ordered set where itemId = &#63;.
	*
	* @param itemId the item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching formula details master
	* @throws NoSuchFormulaDetailsMasterException if a matching formula details master could not be found
	*/
	public FormulaDetailsMaster findByItemId_First(java.lang.String itemId,
		com.liferay.portal.kernel.util.OrderByComparator<FormulaDetailsMaster> orderByComparator)
		throws NoSuchFormulaDetailsMasterException;

	/**
	* Returns the first formula details master in the ordered set where itemId = &#63;.
	*
	* @param itemId the item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching formula details master, or <code>null</code> if a matching formula details master could not be found
	*/
	public FormulaDetailsMaster fetchByItemId_First(java.lang.String itemId,
		com.liferay.portal.kernel.util.OrderByComparator<FormulaDetailsMaster> orderByComparator);

	/**
	* Returns the last formula details master in the ordered set where itemId = &#63;.
	*
	* @param itemId the item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching formula details master
	* @throws NoSuchFormulaDetailsMasterException if a matching formula details master could not be found
	*/
	public FormulaDetailsMaster findByItemId_Last(java.lang.String itemId,
		com.liferay.portal.kernel.util.OrderByComparator<FormulaDetailsMaster> orderByComparator)
		throws NoSuchFormulaDetailsMasterException;

	/**
	* Returns the last formula details master in the ordered set where itemId = &#63;.
	*
	* @param itemId the item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching formula details master, or <code>null</code> if a matching formula details master could not be found
	*/
	public FormulaDetailsMaster fetchByItemId_Last(java.lang.String itemId,
		com.liferay.portal.kernel.util.OrderByComparator<FormulaDetailsMaster> orderByComparator);

	/**
	* Returns the formula details masters before and after the current formula details master in the ordered set where itemId = &#63;.
	*
	* @param formulaDetailsMasterSid the primary key of the current formula details master
	* @param itemId the item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next formula details master
	* @throws NoSuchFormulaDetailsMasterException if a formula details master with the primary key could not be found
	*/
	public FormulaDetailsMaster[] findByItemId_PrevAndNext(
		int formulaDetailsMasterSid, java.lang.String itemId,
		com.liferay.portal.kernel.util.OrderByComparator<FormulaDetailsMaster> orderByComparator)
		throws NoSuchFormulaDetailsMasterException;

	/**
	* Removes all the formula details masters where itemId = &#63; from the database.
	*
	* @param itemId the item ID
	*/
	public void removeByItemId(java.lang.String itemId);

	/**
	* Returns the number of formula details masters where itemId = &#63;.
	*
	* @param itemId the item ID
	* @return the number of matching formula details masters
	*/
	public int countByItemId(java.lang.String itemId);

	/**
	* Returns all the formula details masters where companyStringId = &#63;.
	*
	* @param companyStringId the company string ID
	* @return the matching formula details masters
	*/
	public java.util.List<FormulaDetailsMaster> findByCompanyId(
		java.lang.String companyStringId);

	/**
	* Returns a range of all the formula details masters where companyStringId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyStringId the company string ID
	* @param start the lower bound of the range of formula details masters
	* @param end the upper bound of the range of formula details masters (not inclusive)
	* @return the range of matching formula details masters
	*/
	public java.util.List<FormulaDetailsMaster> findByCompanyId(
		java.lang.String companyStringId, int start, int end);

	/**
	* Returns an ordered range of all the formula details masters where companyStringId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyStringId the company string ID
	* @param start the lower bound of the range of formula details masters
	* @param end the upper bound of the range of formula details masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching formula details masters
	*/
	public java.util.List<FormulaDetailsMaster> findByCompanyId(
		java.lang.String companyStringId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FormulaDetailsMaster> orderByComparator);

	/**
	* Returns an ordered range of all the formula details masters where companyStringId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyStringId the company string ID
	* @param start the lower bound of the range of formula details masters
	* @param end the upper bound of the range of formula details masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching formula details masters
	*/
	public java.util.List<FormulaDetailsMaster> findByCompanyId(
		java.lang.String companyStringId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FormulaDetailsMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first formula details master in the ordered set where companyStringId = &#63;.
	*
	* @param companyStringId the company string ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching formula details master
	* @throws NoSuchFormulaDetailsMasterException if a matching formula details master could not be found
	*/
	public FormulaDetailsMaster findByCompanyId_First(
		java.lang.String companyStringId,
		com.liferay.portal.kernel.util.OrderByComparator<FormulaDetailsMaster> orderByComparator)
		throws NoSuchFormulaDetailsMasterException;

	/**
	* Returns the first formula details master in the ordered set where companyStringId = &#63;.
	*
	* @param companyStringId the company string ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching formula details master, or <code>null</code> if a matching formula details master could not be found
	*/
	public FormulaDetailsMaster fetchByCompanyId_First(
		java.lang.String companyStringId,
		com.liferay.portal.kernel.util.OrderByComparator<FormulaDetailsMaster> orderByComparator);

	/**
	* Returns the last formula details master in the ordered set where companyStringId = &#63;.
	*
	* @param companyStringId the company string ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching formula details master
	* @throws NoSuchFormulaDetailsMasterException if a matching formula details master could not be found
	*/
	public FormulaDetailsMaster findByCompanyId_Last(
		java.lang.String companyStringId,
		com.liferay.portal.kernel.util.OrderByComparator<FormulaDetailsMaster> orderByComparator)
		throws NoSuchFormulaDetailsMasterException;

	/**
	* Returns the last formula details master in the ordered set where companyStringId = &#63;.
	*
	* @param companyStringId the company string ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching formula details master, or <code>null</code> if a matching formula details master could not be found
	*/
	public FormulaDetailsMaster fetchByCompanyId_Last(
		java.lang.String companyStringId,
		com.liferay.portal.kernel.util.OrderByComparator<FormulaDetailsMaster> orderByComparator);

	/**
	* Returns the formula details masters before and after the current formula details master in the ordered set where companyStringId = &#63;.
	*
	* @param formulaDetailsMasterSid the primary key of the current formula details master
	* @param companyStringId the company string ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next formula details master
	* @throws NoSuchFormulaDetailsMasterException if a formula details master with the primary key could not be found
	*/
	public FormulaDetailsMaster[] findByCompanyId_PrevAndNext(
		int formulaDetailsMasterSid, java.lang.String companyStringId,
		com.liferay.portal.kernel.util.OrderByComparator<FormulaDetailsMaster> orderByComparator)
		throws NoSuchFormulaDetailsMasterException;

	/**
	* Removes all the formula details masters where companyStringId = &#63; from the database.
	*
	* @param companyStringId the company string ID
	*/
	public void removeByCompanyId(java.lang.String companyStringId);

	/**
	* Returns the number of formula details masters where companyStringId = &#63;.
	*
	* @param companyStringId the company string ID
	* @return the number of matching formula details masters
	*/
	public int countByCompanyId(java.lang.String companyStringId);

	/**
	* Returns all the formula details masters where startDate = &#63;.
	*
	* @param startDate the start date
	* @return the matching formula details masters
	*/
	public java.util.List<FormulaDetailsMaster> findByStartDate(Date startDate);

	/**
	* Returns a range of all the formula details masters where startDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param startDate the start date
	* @param start the lower bound of the range of formula details masters
	* @param end the upper bound of the range of formula details masters (not inclusive)
	* @return the range of matching formula details masters
	*/
	public java.util.List<FormulaDetailsMaster> findByStartDate(
		Date startDate, int start, int end);

	/**
	* Returns an ordered range of all the formula details masters where startDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param startDate the start date
	* @param start the lower bound of the range of formula details masters
	* @param end the upper bound of the range of formula details masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching formula details masters
	*/
	public java.util.List<FormulaDetailsMaster> findByStartDate(
		Date startDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FormulaDetailsMaster> orderByComparator);

	/**
	* Returns an ordered range of all the formula details masters where startDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param startDate the start date
	* @param start the lower bound of the range of formula details masters
	* @param end the upper bound of the range of formula details masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching formula details masters
	*/
	public java.util.List<FormulaDetailsMaster> findByStartDate(
		Date startDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FormulaDetailsMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first formula details master in the ordered set where startDate = &#63;.
	*
	* @param startDate the start date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching formula details master
	* @throws NoSuchFormulaDetailsMasterException if a matching formula details master could not be found
	*/
	public FormulaDetailsMaster findByStartDate_First(Date startDate,
		com.liferay.portal.kernel.util.OrderByComparator<FormulaDetailsMaster> orderByComparator)
		throws NoSuchFormulaDetailsMasterException;

	/**
	* Returns the first formula details master in the ordered set where startDate = &#63;.
	*
	* @param startDate the start date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching formula details master, or <code>null</code> if a matching formula details master could not be found
	*/
	public FormulaDetailsMaster fetchByStartDate_First(Date startDate,
		com.liferay.portal.kernel.util.OrderByComparator<FormulaDetailsMaster> orderByComparator);

	/**
	* Returns the last formula details master in the ordered set where startDate = &#63;.
	*
	* @param startDate the start date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching formula details master
	* @throws NoSuchFormulaDetailsMasterException if a matching formula details master could not be found
	*/
	public FormulaDetailsMaster findByStartDate_Last(Date startDate,
		com.liferay.portal.kernel.util.OrderByComparator<FormulaDetailsMaster> orderByComparator)
		throws NoSuchFormulaDetailsMasterException;

	/**
	* Returns the last formula details master in the ordered set where startDate = &#63;.
	*
	* @param startDate the start date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching formula details master, or <code>null</code> if a matching formula details master could not be found
	*/
	public FormulaDetailsMaster fetchByStartDate_Last(Date startDate,
		com.liferay.portal.kernel.util.OrderByComparator<FormulaDetailsMaster> orderByComparator);

	/**
	* Returns the formula details masters before and after the current formula details master in the ordered set where startDate = &#63;.
	*
	* @param formulaDetailsMasterSid the primary key of the current formula details master
	* @param startDate the start date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next formula details master
	* @throws NoSuchFormulaDetailsMasterException if a formula details master with the primary key could not be found
	*/
	public FormulaDetailsMaster[] findByStartDate_PrevAndNext(
		int formulaDetailsMasterSid, Date startDate,
		com.liferay.portal.kernel.util.OrderByComparator<FormulaDetailsMaster> orderByComparator)
		throws NoSuchFormulaDetailsMasterException;

	/**
	* Removes all the formula details masters where startDate = &#63; from the database.
	*
	* @param startDate the start date
	*/
	public void removeByStartDate(Date startDate);

	/**
	* Returns the number of formula details masters where startDate = &#63;.
	*
	* @param startDate the start date
	* @return the number of matching formula details masters
	*/
	public int countByStartDate(Date startDate);

	/**
	* Returns all the formula details masters where endDate = &#63;.
	*
	* @param endDate the end date
	* @return the matching formula details masters
	*/
	public java.util.List<FormulaDetailsMaster> findByEndDate(Date endDate);

	/**
	* Returns a range of all the formula details masters where endDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param endDate the end date
	* @param start the lower bound of the range of formula details masters
	* @param end the upper bound of the range of formula details masters (not inclusive)
	* @return the range of matching formula details masters
	*/
	public java.util.List<FormulaDetailsMaster> findByEndDate(Date endDate,
		int start, int end);

	/**
	* Returns an ordered range of all the formula details masters where endDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param endDate the end date
	* @param start the lower bound of the range of formula details masters
	* @param end the upper bound of the range of formula details masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching formula details masters
	*/
	public java.util.List<FormulaDetailsMaster> findByEndDate(Date endDate,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FormulaDetailsMaster> orderByComparator);

	/**
	* Returns an ordered range of all the formula details masters where endDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param endDate the end date
	* @param start the lower bound of the range of formula details masters
	* @param end the upper bound of the range of formula details masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching formula details masters
	*/
	public java.util.List<FormulaDetailsMaster> findByEndDate(Date endDate,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FormulaDetailsMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first formula details master in the ordered set where endDate = &#63;.
	*
	* @param endDate the end date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching formula details master
	* @throws NoSuchFormulaDetailsMasterException if a matching formula details master could not be found
	*/
	public FormulaDetailsMaster findByEndDate_First(Date endDate,
		com.liferay.portal.kernel.util.OrderByComparator<FormulaDetailsMaster> orderByComparator)
		throws NoSuchFormulaDetailsMasterException;

	/**
	* Returns the first formula details master in the ordered set where endDate = &#63;.
	*
	* @param endDate the end date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching formula details master, or <code>null</code> if a matching formula details master could not be found
	*/
	public FormulaDetailsMaster fetchByEndDate_First(Date endDate,
		com.liferay.portal.kernel.util.OrderByComparator<FormulaDetailsMaster> orderByComparator);

	/**
	* Returns the last formula details master in the ordered set where endDate = &#63;.
	*
	* @param endDate the end date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching formula details master
	* @throws NoSuchFormulaDetailsMasterException if a matching formula details master could not be found
	*/
	public FormulaDetailsMaster findByEndDate_Last(Date endDate,
		com.liferay.portal.kernel.util.OrderByComparator<FormulaDetailsMaster> orderByComparator)
		throws NoSuchFormulaDetailsMasterException;

	/**
	* Returns the last formula details master in the ordered set where endDate = &#63;.
	*
	* @param endDate the end date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching formula details master, or <code>null</code> if a matching formula details master could not be found
	*/
	public FormulaDetailsMaster fetchByEndDate_Last(Date endDate,
		com.liferay.portal.kernel.util.OrderByComparator<FormulaDetailsMaster> orderByComparator);

	/**
	* Returns the formula details masters before and after the current formula details master in the ordered set where endDate = &#63;.
	*
	* @param formulaDetailsMasterSid the primary key of the current formula details master
	* @param endDate the end date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next formula details master
	* @throws NoSuchFormulaDetailsMasterException if a formula details master with the primary key could not be found
	*/
	public FormulaDetailsMaster[] findByEndDate_PrevAndNext(
		int formulaDetailsMasterSid, Date endDate,
		com.liferay.portal.kernel.util.OrderByComparator<FormulaDetailsMaster> orderByComparator)
		throws NoSuchFormulaDetailsMasterException;

	/**
	* Removes all the formula details masters where endDate = &#63; from the database.
	*
	* @param endDate the end date
	*/
	public void removeByEndDate(Date endDate);

	/**
	* Returns the number of formula details masters where endDate = &#63;.
	*
	* @param endDate the end date
	* @return the number of matching formula details masters
	*/
	public int countByEndDate(Date endDate);

	/**
	* Returns all the formula details masters where formulaId = &#63;.
	*
	* @param formulaId the formula ID
	* @return the matching formula details masters
	*/
	public java.util.List<FormulaDetailsMaster> findByFormulaDetailsUnique(
		java.lang.String formulaId);

	/**
	* Returns a range of all the formula details masters where formulaId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param formulaId the formula ID
	* @param start the lower bound of the range of formula details masters
	* @param end the upper bound of the range of formula details masters (not inclusive)
	* @return the range of matching formula details masters
	*/
	public java.util.List<FormulaDetailsMaster> findByFormulaDetailsUnique(
		java.lang.String formulaId, int start, int end);

	/**
	* Returns an ordered range of all the formula details masters where formulaId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param formulaId the formula ID
	* @param start the lower bound of the range of formula details masters
	* @param end the upper bound of the range of formula details masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching formula details masters
	*/
	public java.util.List<FormulaDetailsMaster> findByFormulaDetailsUnique(
		java.lang.String formulaId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FormulaDetailsMaster> orderByComparator);

	/**
	* Returns an ordered range of all the formula details masters where formulaId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param formulaId the formula ID
	* @param start the lower bound of the range of formula details masters
	* @param end the upper bound of the range of formula details masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching formula details masters
	*/
	public java.util.List<FormulaDetailsMaster> findByFormulaDetailsUnique(
		java.lang.String formulaId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FormulaDetailsMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first formula details master in the ordered set where formulaId = &#63;.
	*
	* @param formulaId the formula ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching formula details master
	* @throws NoSuchFormulaDetailsMasterException if a matching formula details master could not be found
	*/
	public FormulaDetailsMaster findByFormulaDetailsUnique_First(
		java.lang.String formulaId,
		com.liferay.portal.kernel.util.OrderByComparator<FormulaDetailsMaster> orderByComparator)
		throws NoSuchFormulaDetailsMasterException;

	/**
	* Returns the first formula details master in the ordered set where formulaId = &#63;.
	*
	* @param formulaId the formula ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching formula details master, or <code>null</code> if a matching formula details master could not be found
	*/
	public FormulaDetailsMaster fetchByFormulaDetailsUnique_First(
		java.lang.String formulaId,
		com.liferay.portal.kernel.util.OrderByComparator<FormulaDetailsMaster> orderByComparator);

	/**
	* Returns the last formula details master in the ordered set where formulaId = &#63;.
	*
	* @param formulaId the formula ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching formula details master
	* @throws NoSuchFormulaDetailsMasterException if a matching formula details master could not be found
	*/
	public FormulaDetailsMaster findByFormulaDetailsUnique_Last(
		java.lang.String formulaId,
		com.liferay.portal.kernel.util.OrderByComparator<FormulaDetailsMaster> orderByComparator)
		throws NoSuchFormulaDetailsMasterException;

	/**
	* Returns the last formula details master in the ordered set where formulaId = &#63;.
	*
	* @param formulaId the formula ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching formula details master, or <code>null</code> if a matching formula details master could not be found
	*/
	public FormulaDetailsMaster fetchByFormulaDetailsUnique_Last(
		java.lang.String formulaId,
		com.liferay.portal.kernel.util.OrderByComparator<FormulaDetailsMaster> orderByComparator);

	/**
	* Returns the formula details masters before and after the current formula details master in the ordered set where formulaId = &#63;.
	*
	* @param formulaDetailsMasterSid the primary key of the current formula details master
	* @param formulaId the formula ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next formula details master
	* @throws NoSuchFormulaDetailsMasterException if a formula details master with the primary key could not be found
	*/
	public FormulaDetailsMaster[] findByFormulaDetailsUnique_PrevAndNext(
		int formulaDetailsMasterSid, java.lang.String formulaId,
		com.liferay.portal.kernel.util.OrderByComparator<FormulaDetailsMaster> orderByComparator)
		throws NoSuchFormulaDetailsMasterException;

	/**
	* Removes all the formula details masters where formulaId = &#63; from the database.
	*
	* @param formulaId the formula ID
	*/
	public void removeByFormulaDetailsUnique(java.lang.String formulaId);

	/**
	* Returns the number of formula details masters where formulaId = &#63;.
	*
	* @param formulaId the formula ID
	* @return the number of matching formula details masters
	*/
	public int countByFormulaDetailsUnique(java.lang.String formulaId);

	/**
	* Caches the formula details master in the entity cache if it is enabled.
	*
	* @param formulaDetailsMaster the formula details master
	*/
	public void cacheResult(FormulaDetailsMaster formulaDetailsMaster);

	/**
	* Caches the formula details masters in the entity cache if it is enabled.
	*
	* @param formulaDetailsMasters the formula details masters
	*/
	public void cacheResult(
		java.util.List<FormulaDetailsMaster> formulaDetailsMasters);

	/**
	* Creates a new formula details master with the primary key. Does not add the formula details master to the database.
	*
	* @param formulaDetailsMasterSid the primary key for the new formula details master
	* @return the new formula details master
	*/
	public FormulaDetailsMaster create(int formulaDetailsMasterSid);

	/**
	* Removes the formula details master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param formulaDetailsMasterSid the primary key of the formula details master
	* @return the formula details master that was removed
	* @throws NoSuchFormulaDetailsMasterException if a formula details master with the primary key could not be found
	*/
	public FormulaDetailsMaster remove(int formulaDetailsMasterSid)
		throws NoSuchFormulaDetailsMasterException;

	public FormulaDetailsMaster updateImpl(
		FormulaDetailsMaster formulaDetailsMaster);

	/**
	* Returns the formula details master with the primary key or throws a {@link NoSuchFormulaDetailsMasterException} if it could not be found.
	*
	* @param formulaDetailsMasterSid the primary key of the formula details master
	* @return the formula details master
	* @throws NoSuchFormulaDetailsMasterException if a formula details master with the primary key could not be found
	*/
	public FormulaDetailsMaster findByPrimaryKey(int formulaDetailsMasterSid)
		throws NoSuchFormulaDetailsMasterException;

	/**
	* Returns the formula details master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param formulaDetailsMasterSid the primary key of the formula details master
	* @return the formula details master, or <code>null</code> if a formula details master with the primary key could not be found
	*/
	public FormulaDetailsMaster fetchByPrimaryKey(int formulaDetailsMasterSid);

	@Override
	public java.util.Map<java.io.Serializable, FormulaDetailsMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the formula details masters.
	*
	* @return the formula details masters
	*/
	public java.util.List<FormulaDetailsMaster> findAll();

	/**
	* Returns a range of all the formula details masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of formula details masters
	* @param end the upper bound of the range of formula details masters (not inclusive)
	* @return the range of formula details masters
	*/
	public java.util.List<FormulaDetailsMaster> findAll(int start, int end);

	/**
	* Returns an ordered range of all the formula details masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of formula details masters
	* @param end the upper bound of the range of formula details masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of formula details masters
	*/
	public java.util.List<FormulaDetailsMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FormulaDetailsMaster> orderByComparator);

	/**
	* Returns an ordered range of all the formula details masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of formula details masters
	* @param end the upper bound of the range of formula details masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of formula details masters
	*/
	public java.util.List<FormulaDetailsMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FormulaDetailsMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the formula details masters from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of formula details masters.
	*
	* @return the number of formula details masters
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}