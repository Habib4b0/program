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

import com.stpl.app.exception.NoSuchGlCostCenterMasterException;
import com.stpl.app.model.GlCostCenterMaster;

/**
 * The persistence interface for the gl cost center master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.GlCostCenterMasterPersistenceImpl
 * @see GlCostCenterMasterUtil
 * @generated
 */
@ProviderType
public interface GlCostCenterMasterPersistence extends BasePersistence<GlCostCenterMaster> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link GlCostCenterMasterUtil} to access the gl cost center master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the gl cost center masters where companyCostCenter = &#63;.
	*
	* @param companyCostCenter the company cost center
	* @return the matching gl cost center masters
	*/
	public java.util.List<GlCostCenterMaster> findByCompanyCostCenter(
		java.lang.String companyCostCenter);

	/**
	* Returns a range of all the gl cost center masters where companyCostCenter = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlCostCenterMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyCostCenter the company cost center
	* @param start the lower bound of the range of gl cost center masters
	* @param end the upper bound of the range of gl cost center masters (not inclusive)
	* @return the range of matching gl cost center masters
	*/
	public java.util.List<GlCostCenterMaster> findByCompanyCostCenter(
		java.lang.String companyCostCenter, int start, int end);

	/**
	* Returns an ordered range of all the gl cost center masters where companyCostCenter = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlCostCenterMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyCostCenter the company cost center
	* @param start the lower bound of the range of gl cost center masters
	* @param end the upper bound of the range of gl cost center masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching gl cost center masters
	*/
	public java.util.List<GlCostCenterMaster> findByCompanyCostCenter(
		java.lang.String companyCostCenter, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<GlCostCenterMaster> orderByComparator);

	/**
	* Returns an ordered range of all the gl cost center masters where companyCostCenter = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlCostCenterMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyCostCenter the company cost center
	* @param start the lower bound of the range of gl cost center masters
	* @param end the upper bound of the range of gl cost center masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching gl cost center masters
	*/
	public java.util.List<GlCostCenterMaster> findByCompanyCostCenter(
		java.lang.String companyCostCenter, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<GlCostCenterMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first gl cost center master in the ordered set where companyCostCenter = &#63;.
	*
	* @param companyCostCenter the company cost center
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching gl cost center master
	* @throws NoSuchGlCostCenterMasterException if a matching gl cost center master could not be found
	*/
	public GlCostCenterMaster findByCompanyCostCenter_First(
		java.lang.String companyCostCenter,
		com.liferay.portal.kernel.util.OrderByComparator<GlCostCenterMaster> orderByComparator)
		throws NoSuchGlCostCenterMasterException;

	/**
	* Returns the first gl cost center master in the ordered set where companyCostCenter = &#63;.
	*
	* @param companyCostCenter the company cost center
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching gl cost center master, or <code>null</code> if a matching gl cost center master could not be found
	*/
	public GlCostCenterMaster fetchByCompanyCostCenter_First(
		java.lang.String companyCostCenter,
		com.liferay.portal.kernel.util.OrderByComparator<GlCostCenterMaster> orderByComparator);

	/**
	* Returns the last gl cost center master in the ordered set where companyCostCenter = &#63;.
	*
	* @param companyCostCenter the company cost center
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching gl cost center master
	* @throws NoSuchGlCostCenterMasterException if a matching gl cost center master could not be found
	*/
	public GlCostCenterMaster findByCompanyCostCenter_Last(
		java.lang.String companyCostCenter,
		com.liferay.portal.kernel.util.OrderByComparator<GlCostCenterMaster> orderByComparator)
		throws NoSuchGlCostCenterMasterException;

	/**
	* Returns the last gl cost center master in the ordered set where companyCostCenter = &#63;.
	*
	* @param companyCostCenter the company cost center
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching gl cost center master, or <code>null</code> if a matching gl cost center master could not be found
	*/
	public GlCostCenterMaster fetchByCompanyCostCenter_Last(
		java.lang.String companyCostCenter,
		com.liferay.portal.kernel.util.OrderByComparator<GlCostCenterMaster> orderByComparator);

	/**
	* Returns the gl cost center masters before and after the current gl cost center master in the ordered set where companyCostCenter = &#63;.
	*
	* @param glCostCenterMasterSid the primary key of the current gl cost center master
	* @param companyCostCenter the company cost center
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next gl cost center master
	* @throws NoSuchGlCostCenterMasterException if a gl cost center master with the primary key could not be found
	*/
	public GlCostCenterMaster[] findByCompanyCostCenter_PrevAndNext(
		int glCostCenterMasterSid, java.lang.String companyCostCenter,
		com.liferay.portal.kernel.util.OrderByComparator<GlCostCenterMaster> orderByComparator)
		throws NoSuchGlCostCenterMasterException;

	/**
	* Removes all the gl cost center masters where companyCostCenter = &#63; from the database.
	*
	* @param companyCostCenter the company cost center
	*/
	public void removeByCompanyCostCenter(java.lang.String companyCostCenter);

	/**
	* Returns the number of gl cost center masters where companyCostCenter = &#63;.
	*
	* @param companyCostCenter the company cost center
	* @return the number of matching gl cost center masters
	*/
	public int countByCompanyCostCenter(java.lang.String companyCostCenter);

	/**
	* Returns all the gl cost center masters where ndc8 = &#63;.
	*
	* @param ndc8 the ndc8
	* @return the matching gl cost center masters
	*/
	public java.util.List<GlCostCenterMaster> findByNdc8(java.lang.String ndc8);

	/**
	* Returns a range of all the gl cost center masters where ndc8 = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlCostCenterMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ndc8 the ndc8
	* @param start the lower bound of the range of gl cost center masters
	* @param end the upper bound of the range of gl cost center masters (not inclusive)
	* @return the range of matching gl cost center masters
	*/
	public java.util.List<GlCostCenterMaster> findByNdc8(
		java.lang.String ndc8, int start, int end);

	/**
	* Returns an ordered range of all the gl cost center masters where ndc8 = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlCostCenterMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ndc8 the ndc8
	* @param start the lower bound of the range of gl cost center masters
	* @param end the upper bound of the range of gl cost center masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching gl cost center masters
	*/
	public java.util.List<GlCostCenterMaster> findByNdc8(
		java.lang.String ndc8, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<GlCostCenterMaster> orderByComparator);

	/**
	* Returns an ordered range of all the gl cost center masters where ndc8 = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlCostCenterMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ndc8 the ndc8
	* @param start the lower bound of the range of gl cost center masters
	* @param end the upper bound of the range of gl cost center masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching gl cost center masters
	*/
	public java.util.List<GlCostCenterMaster> findByNdc8(
		java.lang.String ndc8, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<GlCostCenterMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first gl cost center master in the ordered set where ndc8 = &#63;.
	*
	* @param ndc8 the ndc8
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching gl cost center master
	* @throws NoSuchGlCostCenterMasterException if a matching gl cost center master could not be found
	*/
	public GlCostCenterMaster findByNdc8_First(java.lang.String ndc8,
		com.liferay.portal.kernel.util.OrderByComparator<GlCostCenterMaster> orderByComparator)
		throws NoSuchGlCostCenterMasterException;

	/**
	* Returns the first gl cost center master in the ordered set where ndc8 = &#63;.
	*
	* @param ndc8 the ndc8
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching gl cost center master, or <code>null</code> if a matching gl cost center master could not be found
	*/
	public GlCostCenterMaster fetchByNdc8_First(java.lang.String ndc8,
		com.liferay.portal.kernel.util.OrderByComparator<GlCostCenterMaster> orderByComparator);

	/**
	* Returns the last gl cost center master in the ordered set where ndc8 = &#63;.
	*
	* @param ndc8 the ndc8
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching gl cost center master
	* @throws NoSuchGlCostCenterMasterException if a matching gl cost center master could not be found
	*/
	public GlCostCenterMaster findByNdc8_Last(java.lang.String ndc8,
		com.liferay.portal.kernel.util.OrderByComparator<GlCostCenterMaster> orderByComparator)
		throws NoSuchGlCostCenterMasterException;

	/**
	* Returns the last gl cost center master in the ordered set where ndc8 = &#63;.
	*
	* @param ndc8 the ndc8
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching gl cost center master, or <code>null</code> if a matching gl cost center master could not be found
	*/
	public GlCostCenterMaster fetchByNdc8_Last(java.lang.String ndc8,
		com.liferay.portal.kernel.util.OrderByComparator<GlCostCenterMaster> orderByComparator);

	/**
	* Returns the gl cost center masters before and after the current gl cost center master in the ordered set where ndc8 = &#63;.
	*
	* @param glCostCenterMasterSid the primary key of the current gl cost center master
	* @param ndc8 the ndc8
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next gl cost center master
	* @throws NoSuchGlCostCenterMasterException if a gl cost center master with the primary key could not be found
	*/
	public GlCostCenterMaster[] findByNdc8_PrevAndNext(
		int glCostCenterMasterSid, java.lang.String ndc8,
		com.liferay.portal.kernel.util.OrderByComparator<GlCostCenterMaster> orderByComparator)
		throws NoSuchGlCostCenterMasterException;

	/**
	* Removes all the gl cost center masters where ndc8 = &#63; from the database.
	*
	* @param ndc8 the ndc8
	*/
	public void removeByNdc8(java.lang.String ndc8);

	/**
	* Returns the number of gl cost center masters where ndc8 = &#63;.
	*
	* @param ndc8 the ndc8
	* @return the number of matching gl cost center masters
	*/
	public int countByNdc8(java.lang.String ndc8);

	/**
	* Returns all the gl cost center masters where companyCode = &#63;.
	*
	* @param companyCode the company code
	* @return the matching gl cost center masters
	*/
	public java.util.List<GlCostCenterMaster> findByCompanyCode(
		java.lang.String companyCode);

	/**
	* Returns a range of all the gl cost center masters where companyCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlCostCenterMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyCode the company code
	* @param start the lower bound of the range of gl cost center masters
	* @param end the upper bound of the range of gl cost center masters (not inclusive)
	* @return the range of matching gl cost center masters
	*/
	public java.util.List<GlCostCenterMaster> findByCompanyCode(
		java.lang.String companyCode, int start, int end);

	/**
	* Returns an ordered range of all the gl cost center masters where companyCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlCostCenterMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyCode the company code
	* @param start the lower bound of the range of gl cost center masters
	* @param end the upper bound of the range of gl cost center masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching gl cost center masters
	*/
	public java.util.List<GlCostCenterMaster> findByCompanyCode(
		java.lang.String companyCode, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<GlCostCenterMaster> orderByComparator);

	/**
	* Returns an ordered range of all the gl cost center masters where companyCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlCostCenterMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyCode the company code
	* @param start the lower bound of the range of gl cost center masters
	* @param end the upper bound of the range of gl cost center masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching gl cost center masters
	*/
	public java.util.List<GlCostCenterMaster> findByCompanyCode(
		java.lang.String companyCode, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<GlCostCenterMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first gl cost center master in the ordered set where companyCode = &#63;.
	*
	* @param companyCode the company code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching gl cost center master
	* @throws NoSuchGlCostCenterMasterException if a matching gl cost center master could not be found
	*/
	public GlCostCenterMaster findByCompanyCode_First(
		java.lang.String companyCode,
		com.liferay.portal.kernel.util.OrderByComparator<GlCostCenterMaster> orderByComparator)
		throws NoSuchGlCostCenterMasterException;

	/**
	* Returns the first gl cost center master in the ordered set where companyCode = &#63;.
	*
	* @param companyCode the company code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching gl cost center master, or <code>null</code> if a matching gl cost center master could not be found
	*/
	public GlCostCenterMaster fetchByCompanyCode_First(
		java.lang.String companyCode,
		com.liferay.portal.kernel.util.OrderByComparator<GlCostCenterMaster> orderByComparator);

	/**
	* Returns the last gl cost center master in the ordered set where companyCode = &#63;.
	*
	* @param companyCode the company code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching gl cost center master
	* @throws NoSuchGlCostCenterMasterException if a matching gl cost center master could not be found
	*/
	public GlCostCenterMaster findByCompanyCode_Last(
		java.lang.String companyCode,
		com.liferay.portal.kernel.util.OrderByComparator<GlCostCenterMaster> orderByComparator)
		throws NoSuchGlCostCenterMasterException;

	/**
	* Returns the last gl cost center master in the ordered set where companyCode = &#63;.
	*
	* @param companyCode the company code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching gl cost center master, or <code>null</code> if a matching gl cost center master could not be found
	*/
	public GlCostCenterMaster fetchByCompanyCode_Last(
		java.lang.String companyCode,
		com.liferay.portal.kernel.util.OrderByComparator<GlCostCenterMaster> orderByComparator);

	/**
	* Returns the gl cost center masters before and after the current gl cost center master in the ordered set where companyCode = &#63;.
	*
	* @param glCostCenterMasterSid the primary key of the current gl cost center master
	* @param companyCode the company code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next gl cost center master
	* @throws NoSuchGlCostCenterMasterException if a gl cost center master with the primary key could not be found
	*/
	public GlCostCenterMaster[] findByCompanyCode_PrevAndNext(
		int glCostCenterMasterSid, java.lang.String companyCode,
		com.liferay.portal.kernel.util.OrderByComparator<GlCostCenterMaster> orderByComparator)
		throws NoSuchGlCostCenterMasterException;

	/**
	* Removes all the gl cost center masters where companyCode = &#63; from the database.
	*
	* @param companyCode the company code
	*/
	public void removeByCompanyCode(java.lang.String companyCode);

	/**
	* Returns the number of gl cost center masters where companyCode = &#63;.
	*
	* @param companyCode the company code
	* @return the number of matching gl cost center masters
	*/
	public int countByCompanyCode(java.lang.String companyCode);

	/**
	* Returns all the gl cost center masters where companyCostCenter = &#63; and ndc8 = &#63; and companyCode = &#63;.
	*
	* @param companyCostCenter the company cost center
	* @param ndc8 the ndc8
	* @param companyCode the company code
	* @return the matching gl cost center masters
	*/
	public java.util.List<GlCostCenterMaster> findByGlCostCenterUnique(
		java.lang.String companyCostCenter, java.lang.String ndc8,
		java.lang.String companyCode);

	/**
	* Returns a range of all the gl cost center masters where companyCostCenter = &#63; and ndc8 = &#63; and companyCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlCostCenterMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyCostCenter the company cost center
	* @param ndc8 the ndc8
	* @param companyCode the company code
	* @param start the lower bound of the range of gl cost center masters
	* @param end the upper bound of the range of gl cost center masters (not inclusive)
	* @return the range of matching gl cost center masters
	*/
	public java.util.List<GlCostCenterMaster> findByGlCostCenterUnique(
		java.lang.String companyCostCenter, java.lang.String ndc8,
		java.lang.String companyCode, int start, int end);

	/**
	* Returns an ordered range of all the gl cost center masters where companyCostCenter = &#63; and ndc8 = &#63; and companyCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlCostCenterMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyCostCenter the company cost center
	* @param ndc8 the ndc8
	* @param companyCode the company code
	* @param start the lower bound of the range of gl cost center masters
	* @param end the upper bound of the range of gl cost center masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching gl cost center masters
	*/
	public java.util.List<GlCostCenterMaster> findByGlCostCenterUnique(
		java.lang.String companyCostCenter, java.lang.String ndc8,
		java.lang.String companyCode, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<GlCostCenterMaster> orderByComparator);

	/**
	* Returns an ordered range of all the gl cost center masters where companyCostCenter = &#63; and ndc8 = &#63; and companyCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlCostCenterMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyCostCenter the company cost center
	* @param ndc8 the ndc8
	* @param companyCode the company code
	* @param start the lower bound of the range of gl cost center masters
	* @param end the upper bound of the range of gl cost center masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching gl cost center masters
	*/
	public java.util.List<GlCostCenterMaster> findByGlCostCenterUnique(
		java.lang.String companyCostCenter, java.lang.String ndc8,
		java.lang.String companyCode, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<GlCostCenterMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first gl cost center master in the ordered set where companyCostCenter = &#63; and ndc8 = &#63; and companyCode = &#63;.
	*
	* @param companyCostCenter the company cost center
	* @param ndc8 the ndc8
	* @param companyCode the company code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching gl cost center master
	* @throws NoSuchGlCostCenterMasterException if a matching gl cost center master could not be found
	*/
	public GlCostCenterMaster findByGlCostCenterUnique_First(
		java.lang.String companyCostCenter, java.lang.String ndc8,
		java.lang.String companyCode,
		com.liferay.portal.kernel.util.OrderByComparator<GlCostCenterMaster> orderByComparator)
		throws NoSuchGlCostCenterMasterException;

	/**
	* Returns the first gl cost center master in the ordered set where companyCostCenter = &#63; and ndc8 = &#63; and companyCode = &#63;.
	*
	* @param companyCostCenter the company cost center
	* @param ndc8 the ndc8
	* @param companyCode the company code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching gl cost center master, or <code>null</code> if a matching gl cost center master could not be found
	*/
	public GlCostCenterMaster fetchByGlCostCenterUnique_First(
		java.lang.String companyCostCenter, java.lang.String ndc8,
		java.lang.String companyCode,
		com.liferay.portal.kernel.util.OrderByComparator<GlCostCenterMaster> orderByComparator);

	/**
	* Returns the last gl cost center master in the ordered set where companyCostCenter = &#63; and ndc8 = &#63; and companyCode = &#63;.
	*
	* @param companyCostCenter the company cost center
	* @param ndc8 the ndc8
	* @param companyCode the company code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching gl cost center master
	* @throws NoSuchGlCostCenterMasterException if a matching gl cost center master could not be found
	*/
	public GlCostCenterMaster findByGlCostCenterUnique_Last(
		java.lang.String companyCostCenter, java.lang.String ndc8,
		java.lang.String companyCode,
		com.liferay.portal.kernel.util.OrderByComparator<GlCostCenterMaster> orderByComparator)
		throws NoSuchGlCostCenterMasterException;

	/**
	* Returns the last gl cost center master in the ordered set where companyCostCenter = &#63; and ndc8 = &#63; and companyCode = &#63;.
	*
	* @param companyCostCenter the company cost center
	* @param ndc8 the ndc8
	* @param companyCode the company code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching gl cost center master, or <code>null</code> if a matching gl cost center master could not be found
	*/
	public GlCostCenterMaster fetchByGlCostCenterUnique_Last(
		java.lang.String companyCostCenter, java.lang.String ndc8,
		java.lang.String companyCode,
		com.liferay.portal.kernel.util.OrderByComparator<GlCostCenterMaster> orderByComparator);

	/**
	* Returns the gl cost center masters before and after the current gl cost center master in the ordered set where companyCostCenter = &#63; and ndc8 = &#63; and companyCode = &#63;.
	*
	* @param glCostCenterMasterSid the primary key of the current gl cost center master
	* @param companyCostCenter the company cost center
	* @param ndc8 the ndc8
	* @param companyCode the company code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next gl cost center master
	* @throws NoSuchGlCostCenterMasterException if a gl cost center master with the primary key could not be found
	*/
	public GlCostCenterMaster[] findByGlCostCenterUnique_PrevAndNext(
		int glCostCenterMasterSid, java.lang.String companyCostCenter,
		java.lang.String ndc8, java.lang.String companyCode,
		com.liferay.portal.kernel.util.OrderByComparator<GlCostCenterMaster> orderByComparator)
		throws NoSuchGlCostCenterMasterException;

	/**
	* Removes all the gl cost center masters where companyCostCenter = &#63; and ndc8 = &#63; and companyCode = &#63; from the database.
	*
	* @param companyCostCenter the company cost center
	* @param ndc8 the ndc8
	* @param companyCode the company code
	*/
	public void removeByGlCostCenterUnique(java.lang.String companyCostCenter,
		java.lang.String ndc8, java.lang.String companyCode);

	/**
	* Returns the number of gl cost center masters where companyCostCenter = &#63; and ndc8 = &#63; and companyCode = &#63;.
	*
	* @param companyCostCenter the company cost center
	* @param ndc8 the ndc8
	* @param companyCode the company code
	* @return the number of matching gl cost center masters
	*/
	public int countByGlCostCenterUnique(java.lang.String companyCostCenter,
		java.lang.String ndc8, java.lang.String companyCode);

	/**
	* Caches the gl cost center master in the entity cache if it is enabled.
	*
	* @param glCostCenterMaster the gl cost center master
	*/
	public void cacheResult(GlCostCenterMaster glCostCenterMaster);

	/**
	* Caches the gl cost center masters in the entity cache if it is enabled.
	*
	* @param glCostCenterMasters the gl cost center masters
	*/
	public void cacheResult(
		java.util.List<GlCostCenterMaster> glCostCenterMasters);

	/**
	* Creates a new gl cost center master with the primary key. Does not add the gl cost center master to the database.
	*
	* @param glCostCenterMasterSid the primary key for the new gl cost center master
	* @return the new gl cost center master
	*/
	public GlCostCenterMaster create(int glCostCenterMasterSid);

	/**
	* Removes the gl cost center master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param glCostCenterMasterSid the primary key of the gl cost center master
	* @return the gl cost center master that was removed
	* @throws NoSuchGlCostCenterMasterException if a gl cost center master with the primary key could not be found
	*/
	public GlCostCenterMaster remove(int glCostCenterMasterSid)
		throws NoSuchGlCostCenterMasterException;

	public GlCostCenterMaster updateImpl(GlCostCenterMaster glCostCenterMaster);

	/**
	* Returns the gl cost center master with the primary key or throws a {@link NoSuchGlCostCenterMasterException} if it could not be found.
	*
	* @param glCostCenterMasterSid the primary key of the gl cost center master
	* @return the gl cost center master
	* @throws NoSuchGlCostCenterMasterException if a gl cost center master with the primary key could not be found
	*/
	public GlCostCenterMaster findByPrimaryKey(int glCostCenterMasterSid)
		throws NoSuchGlCostCenterMasterException;

	/**
	* Returns the gl cost center master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param glCostCenterMasterSid the primary key of the gl cost center master
	* @return the gl cost center master, or <code>null</code> if a gl cost center master with the primary key could not be found
	*/
	public GlCostCenterMaster fetchByPrimaryKey(int glCostCenterMasterSid);

	@Override
	public java.util.Map<java.io.Serializable, GlCostCenterMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the gl cost center masters.
	*
	* @return the gl cost center masters
	*/
	public java.util.List<GlCostCenterMaster> findAll();

	/**
	* Returns a range of all the gl cost center masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlCostCenterMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of gl cost center masters
	* @param end the upper bound of the range of gl cost center masters (not inclusive)
	* @return the range of gl cost center masters
	*/
	public java.util.List<GlCostCenterMaster> findAll(int start, int end);

	/**
	* Returns an ordered range of all the gl cost center masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlCostCenterMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of gl cost center masters
	* @param end the upper bound of the range of gl cost center masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of gl cost center masters
	*/
	public java.util.List<GlCostCenterMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<GlCostCenterMaster> orderByComparator);

	/**
	* Returns an ordered range of all the gl cost center masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlCostCenterMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of gl cost center masters
	* @param end the upper bound of the range of gl cost center masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of gl cost center masters
	*/
	public java.util.List<GlCostCenterMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<GlCostCenterMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the gl cost center masters from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of gl cost center masters.
	*
	* @return the number of gl cost center masters
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}