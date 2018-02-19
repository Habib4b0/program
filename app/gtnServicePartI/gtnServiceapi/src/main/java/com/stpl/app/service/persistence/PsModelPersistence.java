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

import com.stpl.app.exception.NoSuchPsModelException;
import com.stpl.app.model.PsModel;

/**
 * The persistence interface for the ps model service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.PsModelPersistenceImpl
 * @see PsModelUtil
 * @generated
 */
@ProviderType
public interface PsModelPersistence extends BasePersistence<PsModel> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link PsModelUtil} to access the ps model persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the ps models where psId = &#63;.
	*
	* @param psId the ps ID
	* @return the matching ps models
	*/
	public java.util.List<PsModel> findBypsId(java.lang.String psId);

	/**
	* Returns a range of all the ps models where psId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param psId the ps ID
	* @param start the lower bound of the range of ps models
	* @param end the upper bound of the range of ps models (not inclusive)
	* @return the range of matching ps models
	*/
	public java.util.List<PsModel> findBypsId(java.lang.String psId, int start,
		int end);

	/**
	* Returns an ordered range of all the ps models where psId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param psId the ps ID
	* @param start the lower bound of the range of ps models
	* @param end the upper bound of the range of ps models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ps models
	*/
	public java.util.List<PsModel> findBypsId(java.lang.String psId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<PsModel> orderByComparator);

	/**
	* Returns an ordered range of all the ps models where psId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param psId the ps ID
	* @param start the lower bound of the range of ps models
	* @param end the upper bound of the range of ps models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ps models
	*/
	public java.util.List<PsModel> findBypsId(java.lang.String psId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<PsModel> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first ps model in the ordered set where psId = &#63;.
	*
	* @param psId the ps ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ps model
	* @throws NoSuchPsModelException if a matching ps model could not be found
	*/
	public PsModel findBypsId_First(java.lang.String psId,
		com.liferay.portal.kernel.util.OrderByComparator<PsModel> orderByComparator)
		throws NoSuchPsModelException;

	/**
	* Returns the first ps model in the ordered set where psId = &#63;.
	*
	* @param psId the ps ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ps model, or <code>null</code> if a matching ps model could not be found
	*/
	public PsModel fetchBypsId_First(java.lang.String psId,
		com.liferay.portal.kernel.util.OrderByComparator<PsModel> orderByComparator);

	/**
	* Returns the last ps model in the ordered set where psId = &#63;.
	*
	* @param psId the ps ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ps model
	* @throws NoSuchPsModelException if a matching ps model could not be found
	*/
	public PsModel findBypsId_Last(java.lang.String psId,
		com.liferay.portal.kernel.util.OrderByComparator<PsModel> orderByComparator)
		throws NoSuchPsModelException;

	/**
	* Returns the last ps model in the ordered set where psId = &#63;.
	*
	* @param psId the ps ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ps model, or <code>null</code> if a matching ps model could not be found
	*/
	public PsModel fetchBypsId_Last(java.lang.String psId,
		com.liferay.portal.kernel.util.OrderByComparator<PsModel> orderByComparator);

	/**
	* Returns the ps models before and after the current ps model in the ordered set where psId = &#63;.
	*
	* @param psModelSid the primary key of the current ps model
	* @param psId the ps ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ps model
	* @throws NoSuchPsModelException if a ps model with the primary key could not be found
	*/
	public PsModel[] findBypsId_PrevAndNext(int psModelSid,
		java.lang.String psId,
		com.liferay.portal.kernel.util.OrderByComparator<PsModel> orderByComparator)
		throws NoSuchPsModelException;

	/**
	* Removes all the ps models where psId = &#63; from the database.
	*
	* @param psId the ps ID
	*/
	public void removeBypsId(java.lang.String psId);

	/**
	* Returns the number of ps models where psId = &#63;.
	*
	* @param psId the ps ID
	* @return the number of matching ps models
	*/
	public int countBypsId(java.lang.String psId);

	/**
	* Returns all the ps models where psNo = &#63;.
	*
	* @param psNo the ps no
	* @return the matching ps models
	*/
	public java.util.List<PsModel> findBypsNo(java.lang.String psNo);

	/**
	* Returns a range of all the ps models where psNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param psNo the ps no
	* @param start the lower bound of the range of ps models
	* @param end the upper bound of the range of ps models (not inclusive)
	* @return the range of matching ps models
	*/
	public java.util.List<PsModel> findBypsNo(java.lang.String psNo, int start,
		int end);

	/**
	* Returns an ordered range of all the ps models where psNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param psNo the ps no
	* @param start the lower bound of the range of ps models
	* @param end the upper bound of the range of ps models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ps models
	*/
	public java.util.List<PsModel> findBypsNo(java.lang.String psNo, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<PsModel> orderByComparator);

	/**
	* Returns an ordered range of all the ps models where psNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param psNo the ps no
	* @param start the lower bound of the range of ps models
	* @param end the upper bound of the range of ps models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ps models
	*/
	public java.util.List<PsModel> findBypsNo(java.lang.String psNo, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<PsModel> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first ps model in the ordered set where psNo = &#63;.
	*
	* @param psNo the ps no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ps model
	* @throws NoSuchPsModelException if a matching ps model could not be found
	*/
	public PsModel findBypsNo_First(java.lang.String psNo,
		com.liferay.portal.kernel.util.OrderByComparator<PsModel> orderByComparator)
		throws NoSuchPsModelException;

	/**
	* Returns the first ps model in the ordered set where psNo = &#63;.
	*
	* @param psNo the ps no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ps model, or <code>null</code> if a matching ps model could not be found
	*/
	public PsModel fetchBypsNo_First(java.lang.String psNo,
		com.liferay.portal.kernel.util.OrderByComparator<PsModel> orderByComparator);

	/**
	* Returns the last ps model in the ordered set where psNo = &#63;.
	*
	* @param psNo the ps no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ps model
	* @throws NoSuchPsModelException if a matching ps model could not be found
	*/
	public PsModel findBypsNo_Last(java.lang.String psNo,
		com.liferay.portal.kernel.util.OrderByComparator<PsModel> orderByComparator)
		throws NoSuchPsModelException;

	/**
	* Returns the last ps model in the ordered set where psNo = &#63;.
	*
	* @param psNo the ps no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ps model, or <code>null</code> if a matching ps model could not be found
	*/
	public PsModel fetchBypsNo_Last(java.lang.String psNo,
		com.liferay.portal.kernel.util.OrderByComparator<PsModel> orderByComparator);

	/**
	* Returns the ps models before and after the current ps model in the ordered set where psNo = &#63;.
	*
	* @param psModelSid the primary key of the current ps model
	* @param psNo the ps no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ps model
	* @throws NoSuchPsModelException if a ps model with the primary key could not be found
	*/
	public PsModel[] findBypsNo_PrevAndNext(int psModelSid,
		java.lang.String psNo,
		com.liferay.portal.kernel.util.OrderByComparator<PsModel> orderByComparator)
		throws NoSuchPsModelException;

	/**
	* Removes all the ps models where psNo = &#63; from the database.
	*
	* @param psNo the ps no
	*/
	public void removeBypsNo(java.lang.String psNo);

	/**
	* Returns the number of ps models where psNo = &#63;.
	*
	* @param psNo the ps no
	* @return the number of matching ps models
	*/
	public int countBypsNo(java.lang.String psNo);

	/**
	* Returns all the ps models where psName = &#63;.
	*
	* @param psName the ps name
	* @return the matching ps models
	*/
	public java.util.List<PsModel> findBypsName(java.lang.String psName);

	/**
	* Returns a range of all the ps models where psName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param psName the ps name
	* @param start the lower bound of the range of ps models
	* @param end the upper bound of the range of ps models (not inclusive)
	* @return the range of matching ps models
	*/
	public java.util.List<PsModel> findBypsName(java.lang.String psName,
		int start, int end);

	/**
	* Returns an ordered range of all the ps models where psName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param psName the ps name
	* @param start the lower bound of the range of ps models
	* @param end the upper bound of the range of ps models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ps models
	*/
	public java.util.List<PsModel> findBypsName(java.lang.String psName,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PsModel> orderByComparator);

	/**
	* Returns an ordered range of all the ps models where psName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param psName the ps name
	* @param start the lower bound of the range of ps models
	* @param end the upper bound of the range of ps models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ps models
	*/
	public java.util.List<PsModel> findBypsName(java.lang.String psName,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PsModel> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first ps model in the ordered set where psName = &#63;.
	*
	* @param psName the ps name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ps model
	* @throws NoSuchPsModelException if a matching ps model could not be found
	*/
	public PsModel findBypsName_First(java.lang.String psName,
		com.liferay.portal.kernel.util.OrderByComparator<PsModel> orderByComparator)
		throws NoSuchPsModelException;

	/**
	* Returns the first ps model in the ordered set where psName = &#63;.
	*
	* @param psName the ps name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ps model, or <code>null</code> if a matching ps model could not be found
	*/
	public PsModel fetchBypsName_First(java.lang.String psName,
		com.liferay.portal.kernel.util.OrderByComparator<PsModel> orderByComparator);

	/**
	* Returns the last ps model in the ordered set where psName = &#63;.
	*
	* @param psName the ps name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ps model
	* @throws NoSuchPsModelException if a matching ps model could not be found
	*/
	public PsModel findBypsName_Last(java.lang.String psName,
		com.liferay.portal.kernel.util.OrderByComparator<PsModel> orderByComparator)
		throws NoSuchPsModelException;

	/**
	* Returns the last ps model in the ordered set where psName = &#63;.
	*
	* @param psName the ps name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ps model, or <code>null</code> if a matching ps model could not be found
	*/
	public PsModel fetchBypsName_Last(java.lang.String psName,
		com.liferay.portal.kernel.util.OrderByComparator<PsModel> orderByComparator);

	/**
	* Returns the ps models before and after the current ps model in the ordered set where psName = &#63;.
	*
	* @param psModelSid the primary key of the current ps model
	* @param psName the ps name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ps model
	* @throws NoSuchPsModelException if a ps model with the primary key could not be found
	*/
	public PsModel[] findBypsName_PrevAndNext(int psModelSid,
		java.lang.String psName,
		com.liferay.portal.kernel.util.OrderByComparator<PsModel> orderByComparator)
		throws NoSuchPsModelException;

	/**
	* Removes all the ps models where psName = &#63; from the database.
	*
	* @param psName the ps name
	*/
	public void removeBypsName(java.lang.String psName);

	/**
	* Returns the number of ps models where psName = &#63;.
	*
	* @param psName the ps name
	* @return the number of matching ps models
	*/
	public int countBypsName(java.lang.String psName);

	/**
	* Returns all the ps models where psType = &#63;.
	*
	* @param psType the ps type
	* @return the matching ps models
	*/
	public java.util.List<PsModel> findBypsType(int psType);

	/**
	* Returns a range of all the ps models where psType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param psType the ps type
	* @param start the lower bound of the range of ps models
	* @param end the upper bound of the range of ps models (not inclusive)
	* @return the range of matching ps models
	*/
	public java.util.List<PsModel> findBypsType(int psType, int start, int end);

	/**
	* Returns an ordered range of all the ps models where psType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param psType the ps type
	* @param start the lower bound of the range of ps models
	* @param end the upper bound of the range of ps models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ps models
	*/
	public java.util.List<PsModel> findBypsType(int psType, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PsModel> orderByComparator);

	/**
	* Returns an ordered range of all the ps models where psType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param psType the ps type
	* @param start the lower bound of the range of ps models
	* @param end the upper bound of the range of ps models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ps models
	*/
	public java.util.List<PsModel> findBypsType(int psType, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PsModel> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first ps model in the ordered set where psType = &#63;.
	*
	* @param psType the ps type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ps model
	* @throws NoSuchPsModelException if a matching ps model could not be found
	*/
	public PsModel findBypsType_First(int psType,
		com.liferay.portal.kernel.util.OrderByComparator<PsModel> orderByComparator)
		throws NoSuchPsModelException;

	/**
	* Returns the first ps model in the ordered set where psType = &#63;.
	*
	* @param psType the ps type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ps model, or <code>null</code> if a matching ps model could not be found
	*/
	public PsModel fetchBypsType_First(int psType,
		com.liferay.portal.kernel.util.OrderByComparator<PsModel> orderByComparator);

	/**
	* Returns the last ps model in the ordered set where psType = &#63;.
	*
	* @param psType the ps type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ps model
	* @throws NoSuchPsModelException if a matching ps model could not be found
	*/
	public PsModel findBypsType_Last(int psType,
		com.liferay.portal.kernel.util.OrderByComparator<PsModel> orderByComparator)
		throws NoSuchPsModelException;

	/**
	* Returns the last ps model in the ordered set where psType = &#63;.
	*
	* @param psType the ps type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ps model, or <code>null</code> if a matching ps model could not be found
	*/
	public PsModel fetchBypsType_Last(int psType,
		com.liferay.portal.kernel.util.OrderByComparator<PsModel> orderByComparator);

	/**
	* Returns the ps models before and after the current ps model in the ordered set where psType = &#63;.
	*
	* @param psModelSid the primary key of the current ps model
	* @param psType the ps type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ps model
	* @throws NoSuchPsModelException if a ps model with the primary key could not be found
	*/
	public PsModel[] findBypsType_PrevAndNext(int psModelSid, int psType,
		com.liferay.portal.kernel.util.OrderByComparator<PsModel> orderByComparator)
		throws NoSuchPsModelException;

	/**
	* Removes all the ps models where psType = &#63; from the database.
	*
	* @param psType the ps type
	*/
	public void removeBypsType(int psType);

	/**
	* Returns the number of ps models where psType = &#63;.
	*
	* @param psType the ps type
	* @return the number of matching ps models
	*/
	public int countBypsType(int psType);

	/**
	* Returns all the ps models where psStatus = &#63;.
	*
	* @param psStatus the ps status
	* @return the matching ps models
	*/
	public java.util.List<PsModel> findBypsStatus(int psStatus);

	/**
	* Returns a range of all the ps models where psStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param psStatus the ps status
	* @param start the lower bound of the range of ps models
	* @param end the upper bound of the range of ps models (not inclusive)
	* @return the range of matching ps models
	*/
	public java.util.List<PsModel> findBypsStatus(int psStatus, int start,
		int end);

	/**
	* Returns an ordered range of all the ps models where psStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param psStatus the ps status
	* @param start the lower bound of the range of ps models
	* @param end the upper bound of the range of ps models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ps models
	*/
	public java.util.List<PsModel> findBypsStatus(int psStatus, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<PsModel> orderByComparator);

	/**
	* Returns an ordered range of all the ps models where psStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param psStatus the ps status
	* @param start the lower bound of the range of ps models
	* @param end the upper bound of the range of ps models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ps models
	*/
	public java.util.List<PsModel> findBypsStatus(int psStatus, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<PsModel> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first ps model in the ordered set where psStatus = &#63;.
	*
	* @param psStatus the ps status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ps model
	* @throws NoSuchPsModelException if a matching ps model could not be found
	*/
	public PsModel findBypsStatus_First(int psStatus,
		com.liferay.portal.kernel.util.OrderByComparator<PsModel> orderByComparator)
		throws NoSuchPsModelException;

	/**
	* Returns the first ps model in the ordered set where psStatus = &#63;.
	*
	* @param psStatus the ps status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ps model, or <code>null</code> if a matching ps model could not be found
	*/
	public PsModel fetchBypsStatus_First(int psStatus,
		com.liferay.portal.kernel.util.OrderByComparator<PsModel> orderByComparator);

	/**
	* Returns the last ps model in the ordered set where psStatus = &#63;.
	*
	* @param psStatus the ps status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ps model
	* @throws NoSuchPsModelException if a matching ps model could not be found
	*/
	public PsModel findBypsStatus_Last(int psStatus,
		com.liferay.portal.kernel.util.OrderByComparator<PsModel> orderByComparator)
		throws NoSuchPsModelException;

	/**
	* Returns the last ps model in the ordered set where psStatus = &#63;.
	*
	* @param psStatus the ps status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ps model, or <code>null</code> if a matching ps model could not be found
	*/
	public PsModel fetchBypsStatus_Last(int psStatus,
		com.liferay.portal.kernel.util.OrderByComparator<PsModel> orderByComparator);

	/**
	* Returns the ps models before and after the current ps model in the ordered set where psStatus = &#63;.
	*
	* @param psModelSid the primary key of the current ps model
	* @param psStatus the ps status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ps model
	* @throws NoSuchPsModelException if a ps model with the primary key could not be found
	*/
	public PsModel[] findBypsStatus_PrevAndNext(int psModelSid, int psStatus,
		com.liferay.portal.kernel.util.OrderByComparator<PsModel> orderByComparator)
		throws NoSuchPsModelException;

	/**
	* Removes all the ps models where psStatus = &#63; from the database.
	*
	* @param psStatus the ps status
	*/
	public void removeBypsStatus(int psStatus);

	/**
	* Returns the number of ps models where psStatus = &#63;.
	*
	* @param psStatus the ps status
	* @return the number of matching ps models
	*/
	public int countBypsStatus(int psStatus);

	/**
	* Caches the ps model in the entity cache if it is enabled.
	*
	* @param psModel the ps model
	*/
	public void cacheResult(PsModel psModel);

	/**
	* Caches the ps models in the entity cache if it is enabled.
	*
	* @param psModels the ps models
	*/
	public void cacheResult(java.util.List<PsModel> psModels);

	/**
	* Creates a new ps model with the primary key. Does not add the ps model to the database.
	*
	* @param psModelSid the primary key for the new ps model
	* @return the new ps model
	*/
	public PsModel create(int psModelSid);

	/**
	* Removes the ps model with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param psModelSid the primary key of the ps model
	* @return the ps model that was removed
	* @throws NoSuchPsModelException if a ps model with the primary key could not be found
	*/
	public PsModel remove(int psModelSid) throws NoSuchPsModelException;

	public PsModel updateImpl(PsModel psModel);

	/**
	* Returns the ps model with the primary key or throws a {@link NoSuchPsModelException} if it could not be found.
	*
	* @param psModelSid the primary key of the ps model
	* @return the ps model
	* @throws NoSuchPsModelException if a ps model with the primary key could not be found
	*/
	public PsModel findByPrimaryKey(int psModelSid)
		throws NoSuchPsModelException;

	/**
	* Returns the ps model with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param psModelSid the primary key of the ps model
	* @return the ps model, or <code>null</code> if a ps model with the primary key could not be found
	*/
	public PsModel fetchByPrimaryKey(int psModelSid);

	@Override
	public java.util.Map<java.io.Serializable, PsModel> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the ps models.
	*
	* @return the ps models
	*/
	public java.util.List<PsModel> findAll();

	/**
	* Returns a range of all the ps models.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ps models
	* @param end the upper bound of the range of ps models (not inclusive)
	* @return the range of ps models
	*/
	public java.util.List<PsModel> findAll(int start, int end);

	/**
	* Returns an ordered range of all the ps models.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ps models
	* @param end the upper bound of the range of ps models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ps models
	*/
	public java.util.List<PsModel> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PsModel> orderByComparator);

	/**
	* Returns an ordered range of all the ps models.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ps models
	* @param end the upper bound of the range of ps models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ps models
	*/
	public java.util.List<PsModel> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PsModel> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the ps models from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of ps models.
	*
	* @return the number of ps models
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}