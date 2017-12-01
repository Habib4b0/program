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

import com.stpl.app.exception.NoSuchIfpModelException;
import com.stpl.app.model.IfpModel;

/**
 * The persistence interface for the ifp model service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.IfpModelPersistenceImpl
 * @see IfpModelUtil
 * @generated
 */
@ProviderType
public interface IfpModelPersistence extends BasePersistence<IfpModel> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link IfpModelUtil} to access the ifp model persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the ifp models where ifpId = &#63;.
	*
	* @param ifpId the ifp ID
	* @return the matching ifp models
	*/
	public java.util.List<IfpModel> findByItemFamilyPlanId(
		java.lang.String ifpId);

	/**
	* Returns a range of all the ifp models where ifpId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ifpId the ifp ID
	* @param start the lower bound of the range of ifp models
	* @param end the upper bound of the range of ifp models (not inclusive)
	* @return the range of matching ifp models
	*/
	public java.util.List<IfpModel> findByItemFamilyPlanId(
		java.lang.String ifpId, int start, int end);

	/**
	* Returns an ordered range of all the ifp models where ifpId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ifpId the ifp ID
	* @param start the lower bound of the range of ifp models
	* @param end the upper bound of the range of ifp models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ifp models
	*/
	public java.util.List<IfpModel> findByItemFamilyPlanId(
		java.lang.String ifpId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IfpModel> orderByComparator);

	/**
	* Returns an ordered range of all the ifp models where ifpId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ifpId the ifp ID
	* @param start the lower bound of the range of ifp models
	* @param end the upper bound of the range of ifp models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ifp models
	*/
	public java.util.List<IfpModel> findByItemFamilyPlanId(
		java.lang.String ifpId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IfpModel> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first ifp model in the ordered set where ifpId = &#63;.
	*
	* @param ifpId the ifp ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ifp model
	* @throws NoSuchIfpModelException if a matching ifp model could not be found
	*/
	public IfpModel findByItemFamilyPlanId_First(java.lang.String ifpId,
		com.liferay.portal.kernel.util.OrderByComparator<IfpModel> orderByComparator)
		throws NoSuchIfpModelException;

	/**
	* Returns the first ifp model in the ordered set where ifpId = &#63;.
	*
	* @param ifpId the ifp ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ifp model, or <code>null</code> if a matching ifp model could not be found
	*/
	public IfpModel fetchByItemFamilyPlanId_First(java.lang.String ifpId,
		com.liferay.portal.kernel.util.OrderByComparator<IfpModel> orderByComparator);

	/**
	* Returns the last ifp model in the ordered set where ifpId = &#63;.
	*
	* @param ifpId the ifp ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ifp model
	* @throws NoSuchIfpModelException if a matching ifp model could not be found
	*/
	public IfpModel findByItemFamilyPlanId_Last(java.lang.String ifpId,
		com.liferay.portal.kernel.util.OrderByComparator<IfpModel> orderByComparator)
		throws NoSuchIfpModelException;

	/**
	* Returns the last ifp model in the ordered set where ifpId = &#63;.
	*
	* @param ifpId the ifp ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ifp model, or <code>null</code> if a matching ifp model could not be found
	*/
	public IfpModel fetchByItemFamilyPlanId_Last(java.lang.String ifpId,
		com.liferay.portal.kernel.util.OrderByComparator<IfpModel> orderByComparator);

	/**
	* Returns the ifp models before and after the current ifp model in the ordered set where ifpId = &#63;.
	*
	* @param ifpModelSid the primary key of the current ifp model
	* @param ifpId the ifp ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ifp model
	* @throws NoSuchIfpModelException if a ifp model with the primary key could not be found
	*/
	public IfpModel[] findByItemFamilyPlanId_PrevAndNext(int ifpModelSid,
		java.lang.String ifpId,
		com.liferay.portal.kernel.util.OrderByComparator<IfpModel> orderByComparator)
		throws NoSuchIfpModelException;

	/**
	* Removes all the ifp models where ifpId = &#63; from the database.
	*
	* @param ifpId the ifp ID
	*/
	public void removeByItemFamilyPlanId(java.lang.String ifpId);

	/**
	* Returns the number of ifp models where ifpId = &#63;.
	*
	* @param ifpId the ifp ID
	* @return the number of matching ifp models
	*/
	public int countByItemFamilyPlanId(java.lang.String ifpId);

	/**
	* Returns all the ifp models where ifpNo = &#63;.
	*
	* @param ifpNo the ifp no
	* @return the matching ifp models
	*/
	public java.util.List<IfpModel> findByItemFamilyPlanNo(
		java.lang.String ifpNo);

	/**
	* Returns a range of all the ifp models where ifpNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ifpNo the ifp no
	* @param start the lower bound of the range of ifp models
	* @param end the upper bound of the range of ifp models (not inclusive)
	* @return the range of matching ifp models
	*/
	public java.util.List<IfpModel> findByItemFamilyPlanNo(
		java.lang.String ifpNo, int start, int end);

	/**
	* Returns an ordered range of all the ifp models where ifpNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ifpNo the ifp no
	* @param start the lower bound of the range of ifp models
	* @param end the upper bound of the range of ifp models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ifp models
	*/
	public java.util.List<IfpModel> findByItemFamilyPlanNo(
		java.lang.String ifpNo, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IfpModel> orderByComparator);

	/**
	* Returns an ordered range of all the ifp models where ifpNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ifpNo the ifp no
	* @param start the lower bound of the range of ifp models
	* @param end the upper bound of the range of ifp models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ifp models
	*/
	public java.util.List<IfpModel> findByItemFamilyPlanNo(
		java.lang.String ifpNo, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IfpModel> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first ifp model in the ordered set where ifpNo = &#63;.
	*
	* @param ifpNo the ifp no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ifp model
	* @throws NoSuchIfpModelException if a matching ifp model could not be found
	*/
	public IfpModel findByItemFamilyPlanNo_First(java.lang.String ifpNo,
		com.liferay.portal.kernel.util.OrderByComparator<IfpModel> orderByComparator)
		throws NoSuchIfpModelException;

	/**
	* Returns the first ifp model in the ordered set where ifpNo = &#63;.
	*
	* @param ifpNo the ifp no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ifp model, or <code>null</code> if a matching ifp model could not be found
	*/
	public IfpModel fetchByItemFamilyPlanNo_First(java.lang.String ifpNo,
		com.liferay.portal.kernel.util.OrderByComparator<IfpModel> orderByComparator);

	/**
	* Returns the last ifp model in the ordered set where ifpNo = &#63;.
	*
	* @param ifpNo the ifp no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ifp model
	* @throws NoSuchIfpModelException if a matching ifp model could not be found
	*/
	public IfpModel findByItemFamilyPlanNo_Last(java.lang.String ifpNo,
		com.liferay.portal.kernel.util.OrderByComparator<IfpModel> orderByComparator)
		throws NoSuchIfpModelException;

	/**
	* Returns the last ifp model in the ordered set where ifpNo = &#63;.
	*
	* @param ifpNo the ifp no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ifp model, or <code>null</code> if a matching ifp model could not be found
	*/
	public IfpModel fetchByItemFamilyPlanNo_Last(java.lang.String ifpNo,
		com.liferay.portal.kernel.util.OrderByComparator<IfpModel> orderByComparator);

	/**
	* Returns the ifp models before and after the current ifp model in the ordered set where ifpNo = &#63;.
	*
	* @param ifpModelSid the primary key of the current ifp model
	* @param ifpNo the ifp no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ifp model
	* @throws NoSuchIfpModelException if a ifp model with the primary key could not be found
	*/
	public IfpModel[] findByItemFamilyPlanNo_PrevAndNext(int ifpModelSid,
		java.lang.String ifpNo,
		com.liferay.portal.kernel.util.OrderByComparator<IfpModel> orderByComparator)
		throws NoSuchIfpModelException;

	/**
	* Removes all the ifp models where ifpNo = &#63; from the database.
	*
	* @param ifpNo the ifp no
	*/
	public void removeByItemFamilyPlanNo(java.lang.String ifpNo);

	/**
	* Returns the number of ifp models where ifpNo = &#63;.
	*
	* @param ifpNo the ifp no
	* @return the number of matching ifp models
	*/
	public int countByItemFamilyPlanNo(java.lang.String ifpNo);

	/**
	* Returns all the ifp models where ifpName = &#63;.
	*
	* @param ifpName the ifp name
	* @return the matching ifp models
	*/
	public java.util.List<IfpModel> findByItemFamilyPlanName(
		java.lang.String ifpName);

	/**
	* Returns a range of all the ifp models where ifpName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ifpName the ifp name
	* @param start the lower bound of the range of ifp models
	* @param end the upper bound of the range of ifp models (not inclusive)
	* @return the range of matching ifp models
	*/
	public java.util.List<IfpModel> findByItemFamilyPlanName(
		java.lang.String ifpName, int start, int end);

	/**
	* Returns an ordered range of all the ifp models where ifpName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ifpName the ifp name
	* @param start the lower bound of the range of ifp models
	* @param end the upper bound of the range of ifp models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ifp models
	*/
	public java.util.List<IfpModel> findByItemFamilyPlanName(
		java.lang.String ifpName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IfpModel> orderByComparator);

	/**
	* Returns an ordered range of all the ifp models where ifpName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ifpName the ifp name
	* @param start the lower bound of the range of ifp models
	* @param end the upper bound of the range of ifp models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ifp models
	*/
	public java.util.List<IfpModel> findByItemFamilyPlanName(
		java.lang.String ifpName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IfpModel> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first ifp model in the ordered set where ifpName = &#63;.
	*
	* @param ifpName the ifp name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ifp model
	* @throws NoSuchIfpModelException if a matching ifp model could not be found
	*/
	public IfpModel findByItemFamilyPlanName_First(java.lang.String ifpName,
		com.liferay.portal.kernel.util.OrderByComparator<IfpModel> orderByComparator)
		throws NoSuchIfpModelException;

	/**
	* Returns the first ifp model in the ordered set where ifpName = &#63;.
	*
	* @param ifpName the ifp name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ifp model, or <code>null</code> if a matching ifp model could not be found
	*/
	public IfpModel fetchByItemFamilyPlanName_First(java.lang.String ifpName,
		com.liferay.portal.kernel.util.OrderByComparator<IfpModel> orderByComparator);

	/**
	* Returns the last ifp model in the ordered set where ifpName = &#63;.
	*
	* @param ifpName the ifp name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ifp model
	* @throws NoSuchIfpModelException if a matching ifp model could not be found
	*/
	public IfpModel findByItemFamilyPlanName_Last(java.lang.String ifpName,
		com.liferay.portal.kernel.util.OrderByComparator<IfpModel> orderByComparator)
		throws NoSuchIfpModelException;

	/**
	* Returns the last ifp model in the ordered set where ifpName = &#63;.
	*
	* @param ifpName the ifp name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ifp model, or <code>null</code> if a matching ifp model could not be found
	*/
	public IfpModel fetchByItemFamilyPlanName_Last(java.lang.String ifpName,
		com.liferay.portal.kernel.util.OrderByComparator<IfpModel> orderByComparator);

	/**
	* Returns the ifp models before and after the current ifp model in the ordered set where ifpName = &#63;.
	*
	* @param ifpModelSid the primary key of the current ifp model
	* @param ifpName the ifp name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ifp model
	* @throws NoSuchIfpModelException if a ifp model with the primary key could not be found
	*/
	public IfpModel[] findByItemFamilyPlanName_PrevAndNext(int ifpModelSid,
		java.lang.String ifpName,
		com.liferay.portal.kernel.util.OrderByComparator<IfpModel> orderByComparator)
		throws NoSuchIfpModelException;

	/**
	* Removes all the ifp models where ifpName = &#63; from the database.
	*
	* @param ifpName the ifp name
	*/
	public void removeByItemFamilyPlanName(java.lang.String ifpName);

	/**
	* Returns the number of ifp models where ifpName = &#63;.
	*
	* @param ifpName the ifp name
	* @return the number of matching ifp models
	*/
	public int countByItemFamilyPlanName(java.lang.String ifpName);

	/**
	* Returns all the ifp models where ifpType = &#63;.
	*
	* @param ifpType the ifp type
	* @return the matching ifp models
	*/
	public java.util.List<IfpModel> findByItemFamilyPlanType(int ifpType);

	/**
	* Returns a range of all the ifp models where ifpType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ifpType the ifp type
	* @param start the lower bound of the range of ifp models
	* @param end the upper bound of the range of ifp models (not inclusive)
	* @return the range of matching ifp models
	*/
	public java.util.List<IfpModel> findByItemFamilyPlanType(int ifpType,
		int start, int end);

	/**
	* Returns an ordered range of all the ifp models where ifpType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ifpType the ifp type
	* @param start the lower bound of the range of ifp models
	* @param end the upper bound of the range of ifp models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ifp models
	*/
	public java.util.List<IfpModel> findByItemFamilyPlanType(int ifpType,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IfpModel> orderByComparator);

	/**
	* Returns an ordered range of all the ifp models where ifpType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ifpType the ifp type
	* @param start the lower bound of the range of ifp models
	* @param end the upper bound of the range of ifp models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ifp models
	*/
	public java.util.List<IfpModel> findByItemFamilyPlanType(int ifpType,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IfpModel> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first ifp model in the ordered set where ifpType = &#63;.
	*
	* @param ifpType the ifp type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ifp model
	* @throws NoSuchIfpModelException if a matching ifp model could not be found
	*/
	public IfpModel findByItemFamilyPlanType_First(int ifpType,
		com.liferay.portal.kernel.util.OrderByComparator<IfpModel> orderByComparator)
		throws NoSuchIfpModelException;

	/**
	* Returns the first ifp model in the ordered set where ifpType = &#63;.
	*
	* @param ifpType the ifp type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ifp model, or <code>null</code> if a matching ifp model could not be found
	*/
	public IfpModel fetchByItemFamilyPlanType_First(int ifpType,
		com.liferay.portal.kernel.util.OrderByComparator<IfpModel> orderByComparator);

	/**
	* Returns the last ifp model in the ordered set where ifpType = &#63;.
	*
	* @param ifpType the ifp type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ifp model
	* @throws NoSuchIfpModelException if a matching ifp model could not be found
	*/
	public IfpModel findByItemFamilyPlanType_Last(int ifpType,
		com.liferay.portal.kernel.util.OrderByComparator<IfpModel> orderByComparator)
		throws NoSuchIfpModelException;

	/**
	* Returns the last ifp model in the ordered set where ifpType = &#63;.
	*
	* @param ifpType the ifp type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ifp model, or <code>null</code> if a matching ifp model could not be found
	*/
	public IfpModel fetchByItemFamilyPlanType_Last(int ifpType,
		com.liferay.portal.kernel.util.OrderByComparator<IfpModel> orderByComparator);

	/**
	* Returns the ifp models before and after the current ifp model in the ordered set where ifpType = &#63;.
	*
	* @param ifpModelSid the primary key of the current ifp model
	* @param ifpType the ifp type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ifp model
	* @throws NoSuchIfpModelException if a ifp model with the primary key could not be found
	*/
	public IfpModel[] findByItemFamilyPlanType_PrevAndNext(int ifpModelSid,
		int ifpType,
		com.liferay.portal.kernel.util.OrderByComparator<IfpModel> orderByComparator)
		throws NoSuchIfpModelException;

	/**
	* Removes all the ifp models where ifpType = &#63; from the database.
	*
	* @param ifpType the ifp type
	*/
	public void removeByItemFamilyPlanType(int ifpType);

	/**
	* Returns the number of ifp models where ifpType = &#63;.
	*
	* @param ifpType the ifp type
	* @return the number of matching ifp models
	*/
	public int countByItemFamilyPlanType(int ifpType);

	/**
	* Returns all the ifp models where ifpStatus = &#63;.
	*
	* @param ifpStatus the ifp status
	* @return the matching ifp models
	*/
	public java.util.List<IfpModel> findByItemFamilyPlanStatus(int ifpStatus);

	/**
	* Returns a range of all the ifp models where ifpStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ifpStatus the ifp status
	* @param start the lower bound of the range of ifp models
	* @param end the upper bound of the range of ifp models (not inclusive)
	* @return the range of matching ifp models
	*/
	public java.util.List<IfpModel> findByItemFamilyPlanStatus(int ifpStatus,
		int start, int end);

	/**
	* Returns an ordered range of all the ifp models where ifpStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ifpStatus the ifp status
	* @param start the lower bound of the range of ifp models
	* @param end the upper bound of the range of ifp models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ifp models
	*/
	public java.util.List<IfpModel> findByItemFamilyPlanStatus(int ifpStatus,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IfpModel> orderByComparator);

	/**
	* Returns an ordered range of all the ifp models where ifpStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ifpStatus the ifp status
	* @param start the lower bound of the range of ifp models
	* @param end the upper bound of the range of ifp models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ifp models
	*/
	public java.util.List<IfpModel> findByItemFamilyPlanStatus(int ifpStatus,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IfpModel> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first ifp model in the ordered set where ifpStatus = &#63;.
	*
	* @param ifpStatus the ifp status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ifp model
	* @throws NoSuchIfpModelException if a matching ifp model could not be found
	*/
	public IfpModel findByItemFamilyPlanStatus_First(int ifpStatus,
		com.liferay.portal.kernel.util.OrderByComparator<IfpModel> orderByComparator)
		throws NoSuchIfpModelException;

	/**
	* Returns the first ifp model in the ordered set where ifpStatus = &#63;.
	*
	* @param ifpStatus the ifp status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ifp model, or <code>null</code> if a matching ifp model could not be found
	*/
	public IfpModel fetchByItemFamilyPlanStatus_First(int ifpStatus,
		com.liferay.portal.kernel.util.OrderByComparator<IfpModel> orderByComparator);

	/**
	* Returns the last ifp model in the ordered set where ifpStatus = &#63;.
	*
	* @param ifpStatus the ifp status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ifp model
	* @throws NoSuchIfpModelException if a matching ifp model could not be found
	*/
	public IfpModel findByItemFamilyPlanStatus_Last(int ifpStatus,
		com.liferay.portal.kernel.util.OrderByComparator<IfpModel> orderByComparator)
		throws NoSuchIfpModelException;

	/**
	* Returns the last ifp model in the ordered set where ifpStatus = &#63;.
	*
	* @param ifpStatus the ifp status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ifp model, or <code>null</code> if a matching ifp model could not be found
	*/
	public IfpModel fetchByItemFamilyPlanStatus_Last(int ifpStatus,
		com.liferay.portal.kernel.util.OrderByComparator<IfpModel> orderByComparator);

	/**
	* Returns the ifp models before and after the current ifp model in the ordered set where ifpStatus = &#63;.
	*
	* @param ifpModelSid the primary key of the current ifp model
	* @param ifpStatus the ifp status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ifp model
	* @throws NoSuchIfpModelException if a ifp model with the primary key could not be found
	*/
	public IfpModel[] findByItemFamilyPlanStatus_PrevAndNext(int ifpModelSid,
		int ifpStatus,
		com.liferay.portal.kernel.util.OrderByComparator<IfpModel> orderByComparator)
		throws NoSuchIfpModelException;

	/**
	* Removes all the ifp models where ifpStatus = &#63; from the database.
	*
	* @param ifpStatus the ifp status
	*/
	public void removeByItemFamilyPlanStatus(int ifpStatus);

	/**
	* Returns the number of ifp models where ifpStatus = &#63;.
	*
	* @param ifpStatus the ifp status
	* @return the number of matching ifp models
	*/
	public int countByItemFamilyPlanStatus(int ifpStatus);

	/**
	* Caches the ifp model in the entity cache if it is enabled.
	*
	* @param ifpModel the ifp model
	*/
	public void cacheResult(IfpModel ifpModel);

	/**
	* Caches the ifp models in the entity cache if it is enabled.
	*
	* @param ifpModels the ifp models
	*/
	public void cacheResult(java.util.List<IfpModel> ifpModels);

	/**
	* Creates a new ifp model with the primary key. Does not add the ifp model to the database.
	*
	* @param ifpModelSid the primary key for the new ifp model
	* @return the new ifp model
	*/
	public IfpModel create(int ifpModelSid);

	/**
	* Removes the ifp model with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ifpModelSid the primary key of the ifp model
	* @return the ifp model that was removed
	* @throws NoSuchIfpModelException if a ifp model with the primary key could not be found
	*/
	public IfpModel remove(int ifpModelSid) throws NoSuchIfpModelException;

	public IfpModel updateImpl(IfpModel ifpModel);

	/**
	* Returns the ifp model with the primary key or throws a {@link NoSuchIfpModelException} if it could not be found.
	*
	* @param ifpModelSid the primary key of the ifp model
	* @return the ifp model
	* @throws NoSuchIfpModelException if a ifp model with the primary key could not be found
	*/
	public IfpModel findByPrimaryKey(int ifpModelSid)
		throws NoSuchIfpModelException;

	/**
	* Returns the ifp model with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ifpModelSid the primary key of the ifp model
	* @return the ifp model, or <code>null</code> if a ifp model with the primary key could not be found
	*/
	public IfpModel fetchByPrimaryKey(int ifpModelSid);

	@Override
	public java.util.Map<java.io.Serializable, IfpModel> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the ifp models.
	*
	* @return the ifp models
	*/
	public java.util.List<IfpModel> findAll();

	/**
	* Returns a range of all the ifp models.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ifp models
	* @param end the upper bound of the range of ifp models (not inclusive)
	* @return the range of ifp models
	*/
	public java.util.List<IfpModel> findAll(int start, int end);

	/**
	* Returns an ordered range of all the ifp models.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ifp models
	* @param end the upper bound of the range of ifp models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ifp models
	*/
	public java.util.List<IfpModel> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IfpModel> orderByComparator);

	/**
	* Returns an ordered range of all the ifp models.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ifp models
	* @param end the upper bound of the range of ifp models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ifp models
	*/
	public java.util.List<IfpModel> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IfpModel> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the ifp models from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of ifp models.
	*
	* @return the number of ifp models
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}