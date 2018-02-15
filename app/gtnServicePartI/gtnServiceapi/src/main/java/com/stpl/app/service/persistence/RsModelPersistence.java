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

import com.stpl.app.exception.NoSuchRsModelException;
import com.stpl.app.model.RsModel;

/**
 * The persistence interface for the rs model service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.RsModelPersistenceImpl
 * @see RsModelUtil
 * @generated
 */
@ProviderType
public interface RsModelPersistence extends BasePersistence<RsModel> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link RsModelUtil} to access the rs model persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the rs models where rsId = &#63;.
	*
	* @param rsId the rs ID
	* @return the matching rs models
	*/
	public java.util.List<RsModel> findByRebateScheduleId(java.lang.String rsId);

	/**
	* Returns a range of all the rs models where rsId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rsId the rs ID
	* @param start the lower bound of the range of rs models
	* @param end the upper bound of the range of rs models (not inclusive)
	* @return the range of matching rs models
	*/
	public java.util.List<RsModel> findByRebateScheduleId(
		java.lang.String rsId, int start, int end);

	/**
	* Returns an ordered range of all the rs models where rsId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rsId the rs ID
	* @param start the lower bound of the range of rs models
	* @param end the upper bound of the range of rs models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rs models
	*/
	public java.util.List<RsModel> findByRebateScheduleId(
		java.lang.String rsId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RsModel> orderByComparator);

	/**
	* Returns an ordered range of all the rs models where rsId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rsId the rs ID
	* @param start the lower bound of the range of rs models
	* @param end the upper bound of the range of rs models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching rs models
	*/
	public java.util.List<RsModel> findByRebateScheduleId(
		java.lang.String rsId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RsModel> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first rs model in the ordered set where rsId = &#63;.
	*
	* @param rsId the rs ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching rs model
	* @throws NoSuchRsModelException if a matching rs model could not be found
	*/
	public RsModel findByRebateScheduleId_First(java.lang.String rsId,
		com.liferay.portal.kernel.util.OrderByComparator<RsModel> orderByComparator)
		throws NoSuchRsModelException;

	/**
	* Returns the first rs model in the ordered set where rsId = &#63;.
	*
	* @param rsId the rs ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching rs model, or <code>null</code> if a matching rs model could not be found
	*/
	public RsModel fetchByRebateScheduleId_First(java.lang.String rsId,
		com.liferay.portal.kernel.util.OrderByComparator<RsModel> orderByComparator);

	/**
	* Returns the last rs model in the ordered set where rsId = &#63;.
	*
	* @param rsId the rs ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching rs model
	* @throws NoSuchRsModelException if a matching rs model could not be found
	*/
	public RsModel findByRebateScheduleId_Last(java.lang.String rsId,
		com.liferay.portal.kernel.util.OrderByComparator<RsModel> orderByComparator)
		throws NoSuchRsModelException;

	/**
	* Returns the last rs model in the ordered set where rsId = &#63;.
	*
	* @param rsId the rs ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching rs model, or <code>null</code> if a matching rs model could not be found
	*/
	public RsModel fetchByRebateScheduleId_Last(java.lang.String rsId,
		com.liferay.portal.kernel.util.OrderByComparator<RsModel> orderByComparator);

	/**
	* Returns the rs models before and after the current rs model in the ordered set where rsId = &#63;.
	*
	* @param rsModelSid the primary key of the current rs model
	* @param rsId the rs ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next rs model
	* @throws NoSuchRsModelException if a rs model with the primary key could not be found
	*/
	public RsModel[] findByRebateScheduleId_PrevAndNext(int rsModelSid,
		java.lang.String rsId,
		com.liferay.portal.kernel.util.OrderByComparator<RsModel> orderByComparator)
		throws NoSuchRsModelException;

	/**
	* Removes all the rs models where rsId = &#63; from the database.
	*
	* @param rsId the rs ID
	*/
	public void removeByRebateScheduleId(java.lang.String rsId);

	/**
	* Returns the number of rs models where rsId = &#63;.
	*
	* @param rsId the rs ID
	* @return the number of matching rs models
	*/
	public int countByRebateScheduleId(java.lang.String rsId);

	/**
	* Returns all the rs models where rsNo = &#63;.
	*
	* @param rsNo the rs no
	* @return the matching rs models
	*/
	public java.util.List<RsModel> findByRebateScheduleNo(java.lang.String rsNo);

	/**
	* Returns a range of all the rs models where rsNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rsNo the rs no
	* @param start the lower bound of the range of rs models
	* @param end the upper bound of the range of rs models (not inclusive)
	* @return the range of matching rs models
	*/
	public java.util.List<RsModel> findByRebateScheduleNo(
		java.lang.String rsNo, int start, int end);

	/**
	* Returns an ordered range of all the rs models where rsNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rsNo the rs no
	* @param start the lower bound of the range of rs models
	* @param end the upper bound of the range of rs models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rs models
	*/
	public java.util.List<RsModel> findByRebateScheduleNo(
		java.lang.String rsNo, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RsModel> orderByComparator);

	/**
	* Returns an ordered range of all the rs models where rsNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rsNo the rs no
	* @param start the lower bound of the range of rs models
	* @param end the upper bound of the range of rs models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching rs models
	*/
	public java.util.List<RsModel> findByRebateScheduleNo(
		java.lang.String rsNo, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RsModel> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first rs model in the ordered set where rsNo = &#63;.
	*
	* @param rsNo the rs no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching rs model
	* @throws NoSuchRsModelException if a matching rs model could not be found
	*/
	public RsModel findByRebateScheduleNo_First(java.lang.String rsNo,
		com.liferay.portal.kernel.util.OrderByComparator<RsModel> orderByComparator)
		throws NoSuchRsModelException;

	/**
	* Returns the first rs model in the ordered set where rsNo = &#63;.
	*
	* @param rsNo the rs no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching rs model, or <code>null</code> if a matching rs model could not be found
	*/
	public RsModel fetchByRebateScheduleNo_First(java.lang.String rsNo,
		com.liferay.portal.kernel.util.OrderByComparator<RsModel> orderByComparator);

	/**
	* Returns the last rs model in the ordered set where rsNo = &#63;.
	*
	* @param rsNo the rs no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching rs model
	* @throws NoSuchRsModelException if a matching rs model could not be found
	*/
	public RsModel findByRebateScheduleNo_Last(java.lang.String rsNo,
		com.liferay.portal.kernel.util.OrderByComparator<RsModel> orderByComparator)
		throws NoSuchRsModelException;

	/**
	* Returns the last rs model in the ordered set where rsNo = &#63;.
	*
	* @param rsNo the rs no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching rs model, or <code>null</code> if a matching rs model could not be found
	*/
	public RsModel fetchByRebateScheduleNo_Last(java.lang.String rsNo,
		com.liferay.portal.kernel.util.OrderByComparator<RsModel> orderByComparator);

	/**
	* Returns the rs models before and after the current rs model in the ordered set where rsNo = &#63;.
	*
	* @param rsModelSid the primary key of the current rs model
	* @param rsNo the rs no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next rs model
	* @throws NoSuchRsModelException if a rs model with the primary key could not be found
	*/
	public RsModel[] findByRebateScheduleNo_PrevAndNext(int rsModelSid,
		java.lang.String rsNo,
		com.liferay.portal.kernel.util.OrderByComparator<RsModel> orderByComparator)
		throws NoSuchRsModelException;

	/**
	* Removes all the rs models where rsNo = &#63; from the database.
	*
	* @param rsNo the rs no
	*/
	public void removeByRebateScheduleNo(java.lang.String rsNo);

	/**
	* Returns the number of rs models where rsNo = &#63;.
	*
	* @param rsNo the rs no
	* @return the number of matching rs models
	*/
	public int countByRebateScheduleNo(java.lang.String rsNo);

	/**
	* Returns all the rs models where rsName = &#63;.
	*
	* @param rsName the rs name
	* @return the matching rs models
	*/
	public java.util.List<RsModel> findByRebateScheduleName(
		java.lang.String rsName);

	/**
	* Returns a range of all the rs models where rsName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rsName the rs name
	* @param start the lower bound of the range of rs models
	* @param end the upper bound of the range of rs models (not inclusive)
	* @return the range of matching rs models
	*/
	public java.util.List<RsModel> findByRebateScheduleName(
		java.lang.String rsName, int start, int end);

	/**
	* Returns an ordered range of all the rs models where rsName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rsName the rs name
	* @param start the lower bound of the range of rs models
	* @param end the upper bound of the range of rs models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rs models
	*/
	public java.util.List<RsModel> findByRebateScheduleName(
		java.lang.String rsName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RsModel> orderByComparator);

	/**
	* Returns an ordered range of all the rs models where rsName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rsName the rs name
	* @param start the lower bound of the range of rs models
	* @param end the upper bound of the range of rs models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching rs models
	*/
	public java.util.List<RsModel> findByRebateScheduleName(
		java.lang.String rsName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RsModel> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first rs model in the ordered set where rsName = &#63;.
	*
	* @param rsName the rs name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching rs model
	* @throws NoSuchRsModelException if a matching rs model could not be found
	*/
	public RsModel findByRebateScheduleName_First(java.lang.String rsName,
		com.liferay.portal.kernel.util.OrderByComparator<RsModel> orderByComparator)
		throws NoSuchRsModelException;

	/**
	* Returns the first rs model in the ordered set where rsName = &#63;.
	*
	* @param rsName the rs name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching rs model, or <code>null</code> if a matching rs model could not be found
	*/
	public RsModel fetchByRebateScheduleName_First(java.lang.String rsName,
		com.liferay.portal.kernel.util.OrderByComparator<RsModel> orderByComparator);

	/**
	* Returns the last rs model in the ordered set where rsName = &#63;.
	*
	* @param rsName the rs name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching rs model
	* @throws NoSuchRsModelException if a matching rs model could not be found
	*/
	public RsModel findByRebateScheduleName_Last(java.lang.String rsName,
		com.liferay.portal.kernel.util.OrderByComparator<RsModel> orderByComparator)
		throws NoSuchRsModelException;

	/**
	* Returns the last rs model in the ordered set where rsName = &#63;.
	*
	* @param rsName the rs name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching rs model, or <code>null</code> if a matching rs model could not be found
	*/
	public RsModel fetchByRebateScheduleName_Last(java.lang.String rsName,
		com.liferay.portal.kernel.util.OrderByComparator<RsModel> orderByComparator);

	/**
	* Returns the rs models before and after the current rs model in the ordered set where rsName = &#63;.
	*
	* @param rsModelSid the primary key of the current rs model
	* @param rsName the rs name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next rs model
	* @throws NoSuchRsModelException if a rs model with the primary key could not be found
	*/
	public RsModel[] findByRebateScheduleName_PrevAndNext(int rsModelSid,
		java.lang.String rsName,
		com.liferay.portal.kernel.util.OrderByComparator<RsModel> orderByComparator)
		throws NoSuchRsModelException;

	/**
	* Removes all the rs models where rsName = &#63; from the database.
	*
	* @param rsName the rs name
	*/
	public void removeByRebateScheduleName(java.lang.String rsName);

	/**
	* Returns the number of rs models where rsName = &#63;.
	*
	* @param rsName the rs name
	* @return the number of matching rs models
	*/
	public int countByRebateScheduleName(java.lang.String rsName);

	/**
	* Returns all the rs models where rsType = &#63;.
	*
	* @param rsType the rs type
	* @return the matching rs models
	*/
	public java.util.List<RsModel> findByRebateScheduleType(int rsType);

	/**
	* Returns a range of all the rs models where rsType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rsType the rs type
	* @param start the lower bound of the range of rs models
	* @param end the upper bound of the range of rs models (not inclusive)
	* @return the range of matching rs models
	*/
	public java.util.List<RsModel> findByRebateScheduleType(int rsType,
		int start, int end);

	/**
	* Returns an ordered range of all the rs models where rsType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rsType the rs type
	* @param start the lower bound of the range of rs models
	* @param end the upper bound of the range of rs models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rs models
	*/
	public java.util.List<RsModel> findByRebateScheduleType(int rsType,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RsModel> orderByComparator);

	/**
	* Returns an ordered range of all the rs models where rsType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rsType the rs type
	* @param start the lower bound of the range of rs models
	* @param end the upper bound of the range of rs models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching rs models
	*/
	public java.util.List<RsModel> findByRebateScheduleType(int rsType,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RsModel> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first rs model in the ordered set where rsType = &#63;.
	*
	* @param rsType the rs type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching rs model
	* @throws NoSuchRsModelException if a matching rs model could not be found
	*/
	public RsModel findByRebateScheduleType_First(int rsType,
		com.liferay.portal.kernel.util.OrderByComparator<RsModel> orderByComparator)
		throws NoSuchRsModelException;

	/**
	* Returns the first rs model in the ordered set where rsType = &#63;.
	*
	* @param rsType the rs type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching rs model, or <code>null</code> if a matching rs model could not be found
	*/
	public RsModel fetchByRebateScheduleType_First(int rsType,
		com.liferay.portal.kernel.util.OrderByComparator<RsModel> orderByComparator);

	/**
	* Returns the last rs model in the ordered set where rsType = &#63;.
	*
	* @param rsType the rs type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching rs model
	* @throws NoSuchRsModelException if a matching rs model could not be found
	*/
	public RsModel findByRebateScheduleType_Last(int rsType,
		com.liferay.portal.kernel.util.OrderByComparator<RsModel> orderByComparator)
		throws NoSuchRsModelException;

	/**
	* Returns the last rs model in the ordered set where rsType = &#63;.
	*
	* @param rsType the rs type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching rs model, or <code>null</code> if a matching rs model could not be found
	*/
	public RsModel fetchByRebateScheduleType_Last(int rsType,
		com.liferay.portal.kernel.util.OrderByComparator<RsModel> orderByComparator);

	/**
	* Returns the rs models before and after the current rs model in the ordered set where rsType = &#63;.
	*
	* @param rsModelSid the primary key of the current rs model
	* @param rsType the rs type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next rs model
	* @throws NoSuchRsModelException if a rs model with the primary key could not be found
	*/
	public RsModel[] findByRebateScheduleType_PrevAndNext(int rsModelSid,
		int rsType,
		com.liferay.portal.kernel.util.OrderByComparator<RsModel> orderByComparator)
		throws NoSuchRsModelException;

	/**
	* Removes all the rs models where rsType = &#63; from the database.
	*
	* @param rsType the rs type
	*/
	public void removeByRebateScheduleType(int rsType);

	/**
	* Returns the number of rs models where rsType = &#63;.
	*
	* @param rsType the rs type
	* @return the number of matching rs models
	*/
	public int countByRebateScheduleType(int rsType);

	/**
	* Returns all the rs models where rsStatus = &#63;.
	*
	* @param rsStatus the rs status
	* @return the matching rs models
	*/
	public java.util.List<RsModel> findByRebateScheduleStatus(int rsStatus);

	/**
	* Returns a range of all the rs models where rsStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rsStatus the rs status
	* @param start the lower bound of the range of rs models
	* @param end the upper bound of the range of rs models (not inclusive)
	* @return the range of matching rs models
	*/
	public java.util.List<RsModel> findByRebateScheduleStatus(int rsStatus,
		int start, int end);

	/**
	* Returns an ordered range of all the rs models where rsStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rsStatus the rs status
	* @param start the lower bound of the range of rs models
	* @param end the upper bound of the range of rs models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rs models
	*/
	public java.util.List<RsModel> findByRebateScheduleStatus(int rsStatus,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RsModel> orderByComparator);

	/**
	* Returns an ordered range of all the rs models where rsStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rsStatus the rs status
	* @param start the lower bound of the range of rs models
	* @param end the upper bound of the range of rs models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching rs models
	*/
	public java.util.List<RsModel> findByRebateScheduleStatus(int rsStatus,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RsModel> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first rs model in the ordered set where rsStatus = &#63;.
	*
	* @param rsStatus the rs status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching rs model
	* @throws NoSuchRsModelException if a matching rs model could not be found
	*/
	public RsModel findByRebateScheduleStatus_First(int rsStatus,
		com.liferay.portal.kernel.util.OrderByComparator<RsModel> orderByComparator)
		throws NoSuchRsModelException;

	/**
	* Returns the first rs model in the ordered set where rsStatus = &#63;.
	*
	* @param rsStatus the rs status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching rs model, or <code>null</code> if a matching rs model could not be found
	*/
	public RsModel fetchByRebateScheduleStatus_First(int rsStatus,
		com.liferay.portal.kernel.util.OrderByComparator<RsModel> orderByComparator);

	/**
	* Returns the last rs model in the ordered set where rsStatus = &#63;.
	*
	* @param rsStatus the rs status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching rs model
	* @throws NoSuchRsModelException if a matching rs model could not be found
	*/
	public RsModel findByRebateScheduleStatus_Last(int rsStatus,
		com.liferay.portal.kernel.util.OrderByComparator<RsModel> orderByComparator)
		throws NoSuchRsModelException;

	/**
	* Returns the last rs model in the ordered set where rsStatus = &#63;.
	*
	* @param rsStatus the rs status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching rs model, or <code>null</code> if a matching rs model could not be found
	*/
	public RsModel fetchByRebateScheduleStatus_Last(int rsStatus,
		com.liferay.portal.kernel.util.OrderByComparator<RsModel> orderByComparator);

	/**
	* Returns the rs models before and after the current rs model in the ordered set where rsStatus = &#63;.
	*
	* @param rsModelSid the primary key of the current rs model
	* @param rsStatus the rs status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next rs model
	* @throws NoSuchRsModelException if a rs model with the primary key could not be found
	*/
	public RsModel[] findByRebateScheduleStatus_PrevAndNext(int rsModelSid,
		int rsStatus,
		com.liferay.portal.kernel.util.OrderByComparator<RsModel> orderByComparator)
		throws NoSuchRsModelException;

	/**
	* Removes all the rs models where rsStatus = &#63; from the database.
	*
	* @param rsStatus the rs status
	*/
	public void removeByRebateScheduleStatus(int rsStatus);

	/**
	* Returns the number of rs models where rsStatus = &#63;.
	*
	* @param rsStatus the rs status
	* @return the number of matching rs models
	*/
	public int countByRebateScheduleStatus(int rsStatus);

	/**
	* Returns all the rs models where rebateProgramType = &#63;.
	*
	* @param rebateProgramType the rebate program type
	* @return the matching rs models
	*/
	public java.util.List<RsModel> findByRebateProgramType(
		int rebateProgramType);

	/**
	* Returns a range of all the rs models where rebateProgramType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rebateProgramType the rebate program type
	* @param start the lower bound of the range of rs models
	* @param end the upper bound of the range of rs models (not inclusive)
	* @return the range of matching rs models
	*/
	public java.util.List<RsModel> findByRebateProgramType(
		int rebateProgramType, int start, int end);

	/**
	* Returns an ordered range of all the rs models where rebateProgramType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rebateProgramType the rebate program type
	* @param start the lower bound of the range of rs models
	* @param end the upper bound of the range of rs models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rs models
	*/
	public java.util.List<RsModel> findByRebateProgramType(
		int rebateProgramType, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RsModel> orderByComparator);

	/**
	* Returns an ordered range of all the rs models where rebateProgramType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rebateProgramType the rebate program type
	* @param start the lower bound of the range of rs models
	* @param end the upper bound of the range of rs models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching rs models
	*/
	public java.util.List<RsModel> findByRebateProgramType(
		int rebateProgramType, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RsModel> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first rs model in the ordered set where rebateProgramType = &#63;.
	*
	* @param rebateProgramType the rebate program type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching rs model
	* @throws NoSuchRsModelException if a matching rs model could not be found
	*/
	public RsModel findByRebateProgramType_First(int rebateProgramType,
		com.liferay.portal.kernel.util.OrderByComparator<RsModel> orderByComparator)
		throws NoSuchRsModelException;

	/**
	* Returns the first rs model in the ordered set where rebateProgramType = &#63;.
	*
	* @param rebateProgramType the rebate program type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching rs model, or <code>null</code> if a matching rs model could not be found
	*/
	public RsModel fetchByRebateProgramType_First(int rebateProgramType,
		com.liferay.portal.kernel.util.OrderByComparator<RsModel> orderByComparator);

	/**
	* Returns the last rs model in the ordered set where rebateProgramType = &#63;.
	*
	* @param rebateProgramType the rebate program type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching rs model
	* @throws NoSuchRsModelException if a matching rs model could not be found
	*/
	public RsModel findByRebateProgramType_Last(int rebateProgramType,
		com.liferay.portal.kernel.util.OrderByComparator<RsModel> orderByComparator)
		throws NoSuchRsModelException;

	/**
	* Returns the last rs model in the ordered set where rebateProgramType = &#63;.
	*
	* @param rebateProgramType the rebate program type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching rs model, or <code>null</code> if a matching rs model could not be found
	*/
	public RsModel fetchByRebateProgramType_Last(int rebateProgramType,
		com.liferay.portal.kernel.util.OrderByComparator<RsModel> orderByComparator);

	/**
	* Returns the rs models before and after the current rs model in the ordered set where rebateProgramType = &#63;.
	*
	* @param rsModelSid the primary key of the current rs model
	* @param rebateProgramType the rebate program type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next rs model
	* @throws NoSuchRsModelException if a rs model with the primary key could not be found
	*/
	public RsModel[] findByRebateProgramType_PrevAndNext(int rsModelSid,
		int rebateProgramType,
		com.liferay.portal.kernel.util.OrderByComparator<RsModel> orderByComparator)
		throws NoSuchRsModelException;

	/**
	* Removes all the rs models where rebateProgramType = &#63; from the database.
	*
	* @param rebateProgramType the rebate program type
	*/
	public void removeByRebateProgramType(int rebateProgramType);

	/**
	* Returns the number of rs models where rebateProgramType = &#63;.
	*
	* @param rebateProgramType the rebate program type
	* @return the number of matching rs models
	*/
	public int countByRebateProgramType(int rebateProgramType);

	/**
	* Caches the rs model in the entity cache if it is enabled.
	*
	* @param rsModel the rs model
	*/
	public void cacheResult(RsModel rsModel);

	/**
	* Caches the rs models in the entity cache if it is enabled.
	*
	* @param rsModels the rs models
	*/
	public void cacheResult(java.util.List<RsModel> rsModels);

	/**
	* Creates a new rs model with the primary key. Does not add the rs model to the database.
	*
	* @param rsModelSid the primary key for the new rs model
	* @return the new rs model
	*/
	public RsModel create(int rsModelSid);

	/**
	* Removes the rs model with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param rsModelSid the primary key of the rs model
	* @return the rs model that was removed
	* @throws NoSuchRsModelException if a rs model with the primary key could not be found
	*/
	public RsModel remove(int rsModelSid) throws NoSuchRsModelException;

	public RsModel updateImpl(RsModel rsModel);

	/**
	* Returns the rs model with the primary key or throws a {@link NoSuchRsModelException} if it could not be found.
	*
	* @param rsModelSid the primary key of the rs model
	* @return the rs model
	* @throws NoSuchRsModelException if a rs model with the primary key could not be found
	*/
	public RsModel findByPrimaryKey(int rsModelSid)
		throws NoSuchRsModelException;

	/**
	* Returns the rs model with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param rsModelSid the primary key of the rs model
	* @return the rs model, or <code>null</code> if a rs model with the primary key could not be found
	*/
	public RsModel fetchByPrimaryKey(int rsModelSid);

	@Override
	public java.util.Map<java.io.Serializable, RsModel> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the rs models.
	*
	* @return the rs models
	*/
	public java.util.List<RsModel> findAll();

	/**
	* Returns a range of all the rs models.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of rs models
	* @param end the upper bound of the range of rs models (not inclusive)
	* @return the range of rs models
	*/
	public java.util.List<RsModel> findAll(int start, int end);

	/**
	* Returns an ordered range of all the rs models.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of rs models
	* @param end the upper bound of the range of rs models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of rs models
	*/
	public java.util.List<RsModel> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RsModel> orderByComparator);

	/**
	* Returns an ordered range of all the rs models.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of rs models
	* @param end the upper bound of the range of rs models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of rs models
	*/
	public java.util.List<RsModel> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RsModel> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the rs models from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of rs models.
	*
	* @return the number of rs models
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}