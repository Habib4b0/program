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

import com.stpl.app.exception.NoSuchCompanyMasterException;
import com.stpl.app.model.CompanyMaster;

/**
 * The persistence interface for the company master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.CompanyMasterPersistenceImpl
 * @see CompanyMasterUtil
 * @generated
 */
@ProviderType
public interface CompanyMasterPersistence extends BasePersistence<CompanyMaster> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CompanyMasterUtil} to access the company master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the company masters where companyNo = &#63;.
	*
	* @param companyNo the company no
	* @return the matching company masters
	*/
	public java.util.List<CompanyMaster> findByCompanyNo(
		java.lang.String companyNo);

	/**
	* Returns a range of all the company masters where companyNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyNo the company no
	* @param start the lower bound of the range of company masters
	* @param end the upper bound of the range of company masters (not inclusive)
	* @return the range of matching company masters
	*/
	public java.util.List<CompanyMaster> findByCompanyNo(
		java.lang.String companyNo, int start, int end);

	/**
	* Returns an ordered range of all the company masters where companyNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyNo the company no
	* @param start the lower bound of the range of company masters
	* @param end the upper bound of the range of company masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching company masters
	*/
	public java.util.List<CompanyMaster> findByCompanyNo(
		java.lang.String companyNo, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyMaster> orderByComparator);

	/**
	* Returns an ordered range of all the company masters where companyNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyNo the company no
	* @param start the lower bound of the range of company masters
	* @param end the upper bound of the range of company masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching company masters
	*/
	public java.util.List<CompanyMaster> findByCompanyNo(
		java.lang.String companyNo, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first company master in the ordered set where companyNo = &#63;.
	*
	* @param companyNo the company no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching company master
	* @throws NoSuchCompanyMasterException if a matching company master could not be found
	*/
	public CompanyMaster findByCompanyNo_First(java.lang.String companyNo,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyMaster> orderByComparator)
		throws NoSuchCompanyMasterException;

	/**
	* Returns the first company master in the ordered set where companyNo = &#63;.
	*
	* @param companyNo the company no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching company master, or <code>null</code> if a matching company master could not be found
	*/
	public CompanyMaster fetchByCompanyNo_First(java.lang.String companyNo,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyMaster> orderByComparator);

	/**
	* Returns the last company master in the ordered set where companyNo = &#63;.
	*
	* @param companyNo the company no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching company master
	* @throws NoSuchCompanyMasterException if a matching company master could not be found
	*/
	public CompanyMaster findByCompanyNo_Last(java.lang.String companyNo,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyMaster> orderByComparator)
		throws NoSuchCompanyMasterException;

	/**
	* Returns the last company master in the ordered set where companyNo = &#63;.
	*
	* @param companyNo the company no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching company master, or <code>null</code> if a matching company master could not be found
	*/
	public CompanyMaster fetchByCompanyNo_Last(java.lang.String companyNo,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyMaster> orderByComparator);

	/**
	* Returns the company masters before and after the current company master in the ordered set where companyNo = &#63;.
	*
	* @param companyMasterSid the primary key of the current company master
	* @param companyNo the company no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next company master
	* @throws NoSuchCompanyMasterException if a company master with the primary key could not be found
	*/
	public CompanyMaster[] findByCompanyNo_PrevAndNext(int companyMasterSid,
		java.lang.String companyNo,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyMaster> orderByComparator)
		throws NoSuchCompanyMasterException;

	/**
	* Removes all the company masters where companyNo = &#63; from the database.
	*
	* @param companyNo the company no
	*/
	public void removeByCompanyNo(java.lang.String companyNo);

	/**
	* Returns the number of company masters where companyNo = &#63;.
	*
	* @param companyNo the company no
	* @return the number of matching company masters
	*/
	public int countByCompanyNo(java.lang.String companyNo);

	/**
	* Returns all the company masters where companyStringId = &#63;.
	*
	* @param companyStringId the company string ID
	* @return the matching company masters
	*/
	public java.util.List<CompanyMaster> findByCompanyId(
		java.lang.String companyStringId);

	/**
	* Returns a range of all the company masters where companyStringId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyStringId the company string ID
	* @param start the lower bound of the range of company masters
	* @param end the upper bound of the range of company masters (not inclusive)
	* @return the range of matching company masters
	*/
	public java.util.List<CompanyMaster> findByCompanyId(
		java.lang.String companyStringId, int start, int end);

	/**
	* Returns an ordered range of all the company masters where companyStringId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyStringId the company string ID
	* @param start the lower bound of the range of company masters
	* @param end the upper bound of the range of company masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching company masters
	*/
	public java.util.List<CompanyMaster> findByCompanyId(
		java.lang.String companyStringId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyMaster> orderByComparator);

	/**
	* Returns an ordered range of all the company masters where companyStringId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyStringId the company string ID
	* @param start the lower bound of the range of company masters
	* @param end the upper bound of the range of company masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching company masters
	*/
	public java.util.List<CompanyMaster> findByCompanyId(
		java.lang.String companyStringId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first company master in the ordered set where companyStringId = &#63;.
	*
	* @param companyStringId the company string ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching company master
	* @throws NoSuchCompanyMasterException if a matching company master could not be found
	*/
	public CompanyMaster findByCompanyId_First(
		java.lang.String companyStringId,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyMaster> orderByComparator)
		throws NoSuchCompanyMasterException;

	/**
	* Returns the first company master in the ordered set where companyStringId = &#63;.
	*
	* @param companyStringId the company string ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching company master, or <code>null</code> if a matching company master could not be found
	*/
	public CompanyMaster fetchByCompanyId_First(
		java.lang.String companyStringId,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyMaster> orderByComparator);

	/**
	* Returns the last company master in the ordered set where companyStringId = &#63;.
	*
	* @param companyStringId the company string ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching company master
	* @throws NoSuchCompanyMasterException if a matching company master could not be found
	*/
	public CompanyMaster findByCompanyId_Last(
		java.lang.String companyStringId,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyMaster> orderByComparator)
		throws NoSuchCompanyMasterException;

	/**
	* Returns the last company master in the ordered set where companyStringId = &#63;.
	*
	* @param companyStringId the company string ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching company master, or <code>null</code> if a matching company master could not be found
	*/
	public CompanyMaster fetchByCompanyId_Last(
		java.lang.String companyStringId,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyMaster> orderByComparator);

	/**
	* Returns the company masters before and after the current company master in the ordered set where companyStringId = &#63;.
	*
	* @param companyMasterSid the primary key of the current company master
	* @param companyStringId the company string ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next company master
	* @throws NoSuchCompanyMasterException if a company master with the primary key could not be found
	*/
	public CompanyMaster[] findByCompanyId_PrevAndNext(int companyMasterSid,
		java.lang.String companyStringId,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyMaster> orderByComparator)
		throws NoSuchCompanyMasterException;

	/**
	* Removes all the company masters where companyStringId = &#63; from the database.
	*
	* @param companyStringId the company string ID
	*/
	public void removeByCompanyId(java.lang.String companyStringId);

	/**
	* Returns the number of company masters where companyStringId = &#63;.
	*
	* @param companyStringId the company string ID
	* @return the number of matching company masters
	*/
	public int countByCompanyId(java.lang.String companyStringId);

	/**
	* Returns all the company masters where companyName = &#63;.
	*
	* @param companyName the company name
	* @return the matching company masters
	*/
	public java.util.List<CompanyMaster> findByCompanyName(
		java.lang.String companyName);

	/**
	* Returns a range of all the company masters where companyName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyName the company name
	* @param start the lower bound of the range of company masters
	* @param end the upper bound of the range of company masters (not inclusive)
	* @return the range of matching company masters
	*/
	public java.util.List<CompanyMaster> findByCompanyName(
		java.lang.String companyName, int start, int end);

	/**
	* Returns an ordered range of all the company masters where companyName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyName the company name
	* @param start the lower bound of the range of company masters
	* @param end the upper bound of the range of company masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching company masters
	*/
	public java.util.List<CompanyMaster> findByCompanyName(
		java.lang.String companyName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyMaster> orderByComparator);

	/**
	* Returns an ordered range of all the company masters where companyName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyName the company name
	* @param start the lower bound of the range of company masters
	* @param end the upper bound of the range of company masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching company masters
	*/
	public java.util.List<CompanyMaster> findByCompanyName(
		java.lang.String companyName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first company master in the ordered set where companyName = &#63;.
	*
	* @param companyName the company name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching company master
	* @throws NoSuchCompanyMasterException if a matching company master could not be found
	*/
	public CompanyMaster findByCompanyName_First(java.lang.String companyName,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyMaster> orderByComparator)
		throws NoSuchCompanyMasterException;

	/**
	* Returns the first company master in the ordered set where companyName = &#63;.
	*
	* @param companyName the company name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching company master, or <code>null</code> if a matching company master could not be found
	*/
	public CompanyMaster fetchByCompanyName_First(
		java.lang.String companyName,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyMaster> orderByComparator);

	/**
	* Returns the last company master in the ordered set where companyName = &#63;.
	*
	* @param companyName the company name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching company master
	* @throws NoSuchCompanyMasterException if a matching company master could not be found
	*/
	public CompanyMaster findByCompanyName_Last(java.lang.String companyName,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyMaster> orderByComparator)
		throws NoSuchCompanyMasterException;

	/**
	* Returns the last company master in the ordered set where companyName = &#63;.
	*
	* @param companyName the company name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching company master, or <code>null</code> if a matching company master could not be found
	*/
	public CompanyMaster fetchByCompanyName_Last(java.lang.String companyName,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyMaster> orderByComparator);

	/**
	* Returns the company masters before and after the current company master in the ordered set where companyName = &#63;.
	*
	* @param companyMasterSid the primary key of the current company master
	* @param companyName the company name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next company master
	* @throws NoSuchCompanyMasterException if a company master with the primary key could not be found
	*/
	public CompanyMaster[] findByCompanyName_PrevAndNext(int companyMasterSid,
		java.lang.String companyName,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyMaster> orderByComparator)
		throws NoSuchCompanyMasterException;

	/**
	* Removes all the company masters where companyName = &#63; from the database.
	*
	* @param companyName the company name
	*/
	public void removeByCompanyName(java.lang.String companyName);

	/**
	* Returns the number of company masters where companyName = &#63;.
	*
	* @param companyName the company name
	* @return the number of matching company masters
	*/
	public int countByCompanyName(java.lang.String companyName);

	/**
	* Returns all the company masters where companyType = &#63;.
	*
	* @param companyType the company type
	* @return the matching company masters
	*/
	public java.util.List<CompanyMaster> findByCompanyType(int companyType);

	/**
	* Returns a range of all the company masters where companyType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyType the company type
	* @param start the lower bound of the range of company masters
	* @param end the upper bound of the range of company masters (not inclusive)
	* @return the range of matching company masters
	*/
	public java.util.List<CompanyMaster> findByCompanyType(int companyType,
		int start, int end);

	/**
	* Returns an ordered range of all the company masters where companyType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyType the company type
	* @param start the lower bound of the range of company masters
	* @param end the upper bound of the range of company masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching company masters
	*/
	public java.util.List<CompanyMaster> findByCompanyType(int companyType,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyMaster> orderByComparator);

	/**
	* Returns an ordered range of all the company masters where companyType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyType the company type
	* @param start the lower bound of the range of company masters
	* @param end the upper bound of the range of company masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching company masters
	*/
	public java.util.List<CompanyMaster> findByCompanyType(int companyType,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first company master in the ordered set where companyType = &#63;.
	*
	* @param companyType the company type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching company master
	* @throws NoSuchCompanyMasterException if a matching company master could not be found
	*/
	public CompanyMaster findByCompanyType_First(int companyType,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyMaster> orderByComparator)
		throws NoSuchCompanyMasterException;

	/**
	* Returns the first company master in the ordered set where companyType = &#63;.
	*
	* @param companyType the company type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching company master, or <code>null</code> if a matching company master could not be found
	*/
	public CompanyMaster fetchByCompanyType_First(int companyType,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyMaster> orderByComparator);

	/**
	* Returns the last company master in the ordered set where companyType = &#63;.
	*
	* @param companyType the company type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching company master
	* @throws NoSuchCompanyMasterException if a matching company master could not be found
	*/
	public CompanyMaster findByCompanyType_Last(int companyType,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyMaster> orderByComparator)
		throws NoSuchCompanyMasterException;

	/**
	* Returns the last company master in the ordered set where companyType = &#63;.
	*
	* @param companyType the company type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching company master, or <code>null</code> if a matching company master could not be found
	*/
	public CompanyMaster fetchByCompanyType_Last(int companyType,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyMaster> orderByComparator);

	/**
	* Returns the company masters before and after the current company master in the ordered set where companyType = &#63;.
	*
	* @param companyMasterSid the primary key of the current company master
	* @param companyType the company type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next company master
	* @throws NoSuchCompanyMasterException if a company master with the primary key could not be found
	*/
	public CompanyMaster[] findByCompanyType_PrevAndNext(int companyMasterSid,
		int companyType,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyMaster> orderByComparator)
		throws NoSuchCompanyMasterException;

	/**
	* Removes all the company masters where companyType = &#63; from the database.
	*
	* @param companyType the company type
	*/
	public void removeByCompanyType(int companyType);

	/**
	* Returns the number of company masters where companyType = &#63;.
	*
	* @param companyType the company type
	* @return the number of matching company masters
	*/
	public int countByCompanyType(int companyType);

	/**
	* Returns all the company masters where companyStatus = &#63;.
	*
	* @param companyStatus the company status
	* @return the matching company masters
	*/
	public java.util.List<CompanyMaster> findByCompanyStatus(int companyStatus);

	/**
	* Returns a range of all the company masters where companyStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyStatus the company status
	* @param start the lower bound of the range of company masters
	* @param end the upper bound of the range of company masters (not inclusive)
	* @return the range of matching company masters
	*/
	public java.util.List<CompanyMaster> findByCompanyStatus(
		int companyStatus, int start, int end);

	/**
	* Returns an ordered range of all the company masters where companyStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyStatus the company status
	* @param start the lower bound of the range of company masters
	* @param end the upper bound of the range of company masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching company masters
	*/
	public java.util.List<CompanyMaster> findByCompanyStatus(
		int companyStatus, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyMaster> orderByComparator);

	/**
	* Returns an ordered range of all the company masters where companyStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyStatus the company status
	* @param start the lower bound of the range of company masters
	* @param end the upper bound of the range of company masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching company masters
	*/
	public java.util.List<CompanyMaster> findByCompanyStatus(
		int companyStatus, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first company master in the ordered set where companyStatus = &#63;.
	*
	* @param companyStatus the company status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching company master
	* @throws NoSuchCompanyMasterException if a matching company master could not be found
	*/
	public CompanyMaster findByCompanyStatus_First(int companyStatus,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyMaster> orderByComparator)
		throws NoSuchCompanyMasterException;

	/**
	* Returns the first company master in the ordered set where companyStatus = &#63;.
	*
	* @param companyStatus the company status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching company master, or <code>null</code> if a matching company master could not be found
	*/
	public CompanyMaster fetchByCompanyStatus_First(int companyStatus,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyMaster> orderByComparator);

	/**
	* Returns the last company master in the ordered set where companyStatus = &#63;.
	*
	* @param companyStatus the company status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching company master
	* @throws NoSuchCompanyMasterException if a matching company master could not be found
	*/
	public CompanyMaster findByCompanyStatus_Last(int companyStatus,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyMaster> orderByComparator)
		throws NoSuchCompanyMasterException;

	/**
	* Returns the last company master in the ordered set where companyStatus = &#63;.
	*
	* @param companyStatus the company status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching company master, or <code>null</code> if a matching company master could not be found
	*/
	public CompanyMaster fetchByCompanyStatus_Last(int companyStatus,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyMaster> orderByComparator);

	/**
	* Returns the company masters before and after the current company master in the ordered set where companyStatus = &#63;.
	*
	* @param companyMasterSid the primary key of the current company master
	* @param companyStatus the company status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next company master
	* @throws NoSuchCompanyMasterException if a company master with the primary key could not be found
	*/
	public CompanyMaster[] findByCompanyStatus_PrevAndNext(
		int companyMasterSid, int companyStatus,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyMaster> orderByComparator)
		throws NoSuchCompanyMasterException;

	/**
	* Removes all the company masters where companyStatus = &#63; from the database.
	*
	* @param companyStatus the company status
	*/
	public void removeByCompanyStatus(int companyStatus);

	/**
	* Returns the number of company masters where companyStatus = &#63;.
	*
	* @param companyStatus the company status
	* @return the number of matching company masters
	*/
	public int countByCompanyStatus(int companyStatus);

	/**
	* Returns all the company masters where companyStringId = &#63;.
	*
	* @param companyStringId the company string ID
	* @return the matching company masters
	*/
	public java.util.List<CompanyMaster> findByCompanyUnique(
		java.lang.String companyStringId);

	/**
	* Returns a range of all the company masters where companyStringId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyStringId the company string ID
	* @param start the lower bound of the range of company masters
	* @param end the upper bound of the range of company masters (not inclusive)
	* @return the range of matching company masters
	*/
	public java.util.List<CompanyMaster> findByCompanyUnique(
		java.lang.String companyStringId, int start, int end);

	/**
	* Returns an ordered range of all the company masters where companyStringId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyStringId the company string ID
	* @param start the lower bound of the range of company masters
	* @param end the upper bound of the range of company masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching company masters
	*/
	public java.util.List<CompanyMaster> findByCompanyUnique(
		java.lang.String companyStringId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyMaster> orderByComparator);

	/**
	* Returns an ordered range of all the company masters where companyStringId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyStringId the company string ID
	* @param start the lower bound of the range of company masters
	* @param end the upper bound of the range of company masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching company masters
	*/
	public java.util.List<CompanyMaster> findByCompanyUnique(
		java.lang.String companyStringId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first company master in the ordered set where companyStringId = &#63;.
	*
	* @param companyStringId the company string ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching company master
	* @throws NoSuchCompanyMasterException if a matching company master could not be found
	*/
	public CompanyMaster findByCompanyUnique_First(
		java.lang.String companyStringId,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyMaster> orderByComparator)
		throws NoSuchCompanyMasterException;

	/**
	* Returns the first company master in the ordered set where companyStringId = &#63;.
	*
	* @param companyStringId the company string ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching company master, or <code>null</code> if a matching company master could not be found
	*/
	public CompanyMaster fetchByCompanyUnique_First(
		java.lang.String companyStringId,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyMaster> orderByComparator);

	/**
	* Returns the last company master in the ordered set where companyStringId = &#63;.
	*
	* @param companyStringId the company string ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching company master
	* @throws NoSuchCompanyMasterException if a matching company master could not be found
	*/
	public CompanyMaster findByCompanyUnique_Last(
		java.lang.String companyStringId,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyMaster> orderByComparator)
		throws NoSuchCompanyMasterException;

	/**
	* Returns the last company master in the ordered set where companyStringId = &#63;.
	*
	* @param companyStringId the company string ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching company master, or <code>null</code> if a matching company master could not be found
	*/
	public CompanyMaster fetchByCompanyUnique_Last(
		java.lang.String companyStringId,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyMaster> orderByComparator);

	/**
	* Returns the company masters before and after the current company master in the ordered set where companyStringId = &#63;.
	*
	* @param companyMasterSid the primary key of the current company master
	* @param companyStringId the company string ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next company master
	* @throws NoSuchCompanyMasterException if a company master with the primary key could not be found
	*/
	public CompanyMaster[] findByCompanyUnique_PrevAndNext(
		int companyMasterSid, java.lang.String companyStringId,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyMaster> orderByComparator)
		throws NoSuchCompanyMasterException;

	/**
	* Removes all the company masters where companyStringId = &#63; from the database.
	*
	* @param companyStringId the company string ID
	*/
	public void removeByCompanyUnique(java.lang.String companyStringId);

	/**
	* Returns the number of company masters where companyStringId = &#63;.
	*
	* @param companyStringId the company string ID
	* @return the number of matching company masters
	*/
	public int countByCompanyUnique(java.lang.String companyStringId);

	/**
	* Caches the company master in the entity cache if it is enabled.
	*
	* @param companyMaster the company master
	*/
	public void cacheResult(CompanyMaster companyMaster);

	/**
	* Caches the company masters in the entity cache if it is enabled.
	*
	* @param companyMasters the company masters
	*/
	public void cacheResult(java.util.List<CompanyMaster> companyMasters);

	/**
	* Creates a new company master with the primary key. Does not add the company master to the database.
	*
	* @param companyMasterSid the primary key for the new company master
	* @return the new company master
	*/
	public CompanyMaster create(int companyMasterSid);

	/**
	* Removes the company master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param companyMasterSid the primary key of the company master
	* @return the company master that was removed
	* @throws NoSuchCompanyMasterException if a company master with the primary key could not be found
	*/
	public CompanyMaster remove(int companyMasterSid)
		throws NoSuchCompanyMasterException;

	public CompanyMaster updateImpl(CompanyMaster companyMaster);

	/**
	* Returns the company master with the primary key or throws a {@link NoSuchCompanyMasterException} if it could not be found.
	*
	* @param companyMasterSid the primary key of the company master
	* @return the company master
	* @throws NoSuchCompanyMasterException if a company master with the primary key could not be found
	*/
	public CompanyMaster findByPrimaryKey(int companyMasterSid)
		throws NoSuchCompanyMasterException;

	/**
	* Returns the company master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param companyMasterSid the primary key of the company master
	* @return the company master, or <code>null</code> if a company master with the primary key could not be found
	*/
	public CompanyMaster fetchByPrimaryKey(int companyMasterSid);

	@Override
	public java.util.Map<java.io.Serializable, CompanyMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the company masters.
	*
	* @return the company masters
	*/
	public java.util.List<CompanyMaster> findAll();

	/**
	* Returns a range of all the company masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of company masters
	* @param end the upper bound of the range of company masters (not inclusive)
	* @return the range of company masters
	*/
	public java.util.List<CompanyMaster> findAll(int start, int end);

	/**
	* Returns an ordered range of all the company masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of company masters
	* @param end the upper bound of the range of company masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of company masters
	*/
	public java.util.List<CompanyMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyMaster> orderByComparator);

	/**
	* Returns an ordered range of all the company masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of company masters
	* @param end the upper bound of the range of company masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of company masters
	*/
	public java.util.List<CompanyMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the company masters from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of company masters.
	*
	* @return the number of company masters
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}