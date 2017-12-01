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

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.stpl.app.model.CfpModel;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the cfp model service. This utility wraps {@link com.stpl.app.service.persistence.impl.CfpModelPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CfpModelPersistence
 * @see com.stpl.app.service.persistence.impl.CfpModelPersistenceImpl
 * @generated
 */
@ProviderType
public class CfpModelUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(CfpModel cfpModel) {
		getPersistence().clearCache(cfpModel);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<CfpModel> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CfpModel> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CfpModel> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CfpModel> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CfpModel update(CfpModel cfpModel) {
		return getPersistence().update(cfpModel);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CfpModel update(CfpModel cfpModel,
		ServiceContext serviceContext) {
		return getPersistence().update(cfpModel, serviceContext);
	}

	/**
	* Returns all the cfp models where cfpId = &#63;.
	*
	* @param cfpId the cfp ID
	* @return the matching cfp models
	*/
	public static List<CfpModel> findByCfpId(java.lang.String cfpId) {
		return getPersistence().findByCfpId(cfpId);
	}

	/**
	* Returns a range of all the cfp models where cfpId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param cfpId the cfp ID
	* @param start the lower bound of the range of cfp models
	* @param end the upper bound of the range of cfp models (not inclusive)
	* @return the range of matching cfp models
	*/
	public static List<CfpModel> findByCfpId(java.lang.String cfpId, int start,
		int end) {
		return getPersistence().findByCfpId(cfpId, start, end);
	}

	/**
	* Returns an ordered range of all the cfp models where cfpId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param cfpId the cfp ID
	* @param start the lower bound of the range of cfp models
	* @param end the upper bound of the range of cfp models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching cfp models
	*/
	public static List<CfpModel> findByCfpId(java.lang.String cfpId, int start,
		int end, OrderByComparator<CfpModel> orderByComparator) {
		return getPersistence().findByCfpId(cfpId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the cfp models where cfpId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param cfpId the cfp ID
	* @param start the lower bound of the range of cfp models
	* @param end the upper bound of the range of cfp models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching cfp models
	*/
	public static List<CfpModel> findByCfpId(java.lang.String cfpId, int start,
		int end, OrderByComparator<CfpModel> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCfpId(cfpId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first cfp model in the ordered set where cfpId = &#63;.
	*
	* @param cfpId the cfp ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cfp model
	* @throws NoSuchCfpModelException if a matching cfp model could not be found
	*/
	public static CfpModel findByCfpId_First(java.lang.String cfpId,
		OrderByComparator<CfpModel> orderByComparator)
		throws com.stpl.app.exception.NoSuchCfpModelException {
		return getPersistence().findByCfpId_First(cfpId, orderByComparator);
	}

	/**
	* Returns the first cfp model in the ordered set where cfpId = &#63;.
	*
	* @param cfpId the cfp ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cfp model, or <code>null</code> if a matching cfp model could not be found
	*/
	public static CfpModel fetchByCfpId_First(java.lang.String cfpId,
		OrderByComparator<CfpModel> orderByComparator) {
		return getPersistence().fetchByCfpId_First(cfpId, orderByComparator);
	}

	/**
	* Returns the last cfp model in the ordered set where cfpId = &#63;.
	*
	* @param cfpId the cfp ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cfp model
	* @throws NoSuchCfpModelException if a matching cfp model could not be found
	*/
	public static CfpModel findByCfpId_Last(java.lang.String cfpId,
		OrderByComparator<CfpModel> orderByComparator)
		throws com.stpl.app.exception.NoSuchCfpModelException {
		return getPersistence().findByCfpId_Last(cfpId, orderByComparator);
	}

	/**
	* Returns the last cfp model in the ordered set where cfpId = &#63;.
	*
	* @param cfpId the cfp ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cfp model, or <code>null</code> if a matching cfp model could not be found
	*/
	public static CfpModel fetchByCfpId_Last(java.lang.String cfpId,
		OrderByComparator<CfpModel> orderByComparator) {
		return getPersistence().fetchByCfpId_Last(cfpId, orderByComparator);
	}

	/**
	* Returns the cfp models before and after the current cfp model in the ordered set where cfpId = &#63;.
	*
	* @param cfpModelSid the primary key of the current cfp model
	* @param cfpId the cfp ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next cfp model
	* @throws NoSuchCfpModelException if a cfp model with the primary key could not be found
	*/
	public static CfpModel[] findByCfpId_PrevAndNext(int cfpModelSid,
		java.lang.String cfpId, OrderByComparator<CfpModel> orderByComparator)
		throws com.stpl.app.exception.NoSuchCfpModelException {
		return getPersistence()
				   .findByCfpId_PrevAndNext(cfpModelSid, cfpId,
			orderByComparator);
	}

	/**
	* Removes all the cfp models where cfpId = &#63; from the database.
	*
	* @param cfpId the cfp ID
	*/
	public static void removeByCfpId(java.lang.String cfpId) {
		getPersistence().removeByCfpId(cfpId);
	}

	/**
	* Returns the number of cfp models where cfpId = &#63;.
	*
	* @param cfpId the cfp ID
	* @return the number of matching cfp models
	*/
	public static int countByCfpId(java.lang.String cfpId) {
		return getPersistence().countByCfpId(cfpId);
	}

	/**
	* Returns all the cfp models where cfpNo = &#63;.
	*
	* @param cfpNo the cfp no
	* @return the matching cfp models
	*/
	public static List<CfpModel> findByCfpNo(java.lang.String cfpNo) {
		return getPersistence().findByCfpNo(cfpNo);
	}

	/**
	* Returns a range of all the cfp models where cfpNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param cfpNo the cfp no
	* @param start the lower bound of the range of cfp models
	* @param end the upper bound of the range of cfp models (not inclusive)
	* @return the range of matching cfp models
	*/
	public static List<CfpModel> findByCfpNo(java.lang.String cfpNo, int start,
		int end) {
		return getPersistence().findByCfpNo(cfpNo, start, end);
	}

	/**
	* Returns an ordered range of all the cfp models where cfpNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param cfpNo the cfp no
	* @param start the lower bound of the range of cfp models
	* @param end the upper bound of the range of cfp models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching cfp models
	*/
	public static List<CfpModel> findByCfpNo(java.lang.String cfpNo, int start,
		int end, OrderByComparator<CfpModel> orderByComparator) {
		return getPersistence().findByCfpNo(cfpNo, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the cfp models where cfpNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param cfpNo the cfp no
	* @param start the lower bound of the range of cfp models
	* @param end the upper bound of the range of cfp models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching cfp models
	*/
	public static List<CfpModel> findByCfpNo(java.lang.String cfpNo, int start,
		int end, OrderByComparator<CfpModel> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCfpNo(cfpNo, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first cfp model in the ordered set where cfpNo = &#63;.
	*
	* @param cfpNo the cfp no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cfp model
	* @throws NoSuchCfpModelException if a matching cfp model could not be found
	*/
	public static CfpModel findByCfpNo_First(java.lang.String cfpNo,
		OrderByComparator<CfpModel> orderByComparator)
		throws com.stpl.app.exception.NoSuchCfpModelException {
		return getPersistence().findByCfpNo_First(cfpNo, orderByComparator);
	}

	/**
	* Returns the first cfp model in the ordered set where cfpNo = &#63;.
	*
	* @param cfpNo the cfp no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cfp model, or <code>null</code> if a matching cfp model could not be found
	*/
	public static CfpModel fetchByCfpNo_First(java.lang.String cfpNo,
		OrderByComparator<CfpModel> orderByComparator) {
		return getPersistence().fetchByCfpNo_First(cfpNo, orderByComparator);
	}

	/**
	* Returns the last cfp model in the ordered set where cfpNo = &#63;.
	*
	* @param cfpNo the cfp no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cfp model
	* @throws NoSuchCfpModelException if a matching cfp model could not be found
	*/
	public static CfpModel findByCfpNo_Last(java.lang.String cfpNo,
		OrderByComparator<CfpModel> orderByComparator)
		throws com.stpl.app.exception.NoSuchCfpModelException {
		return getPersistence().findByCfpNo_Last(cfpNo, orderByComparator);
	}

	/**
	* Returns the last cfp model in the ordered set where cfpNo = &#63;.
	*
	* @param cfpNo the cfp no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cfp model, or <code>null</code> if a matching cfp model could not be found
	*/
	public static CfpModel fetchByCfpNo_Last(java.lang.String cfpNo,
		OrderByComparator<CfpModel> orderByComparator) {
		return getPersistence().fetchByCfpNo_Last(cfpNo, orderByComparator);
	}

	/**
	* Returns the cfp models before and after the current cfp model in the ordered set where cfpNo = &#63;.
	*
	* @param cfpModelSid the primary key of the current cfp model
	* @param cfpNo the cfp no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next cfp model
	* @throws NoSuchCfpModelException if a cfp model with the primary key could not be found
	*/
	public static CfpModel[] findByCfpNo_PrevAndNext(int cfpModelSid,
		java.lang.String cfpNo, OrderByComparator<CfpModel> orderByComparator)
		throws com.stpl.app.exception.NoSuchCfpModelException {
		return getPersistence()
				   .findByCfpNo_PrevAndNext(cfpModelSid, cfpNo,
			orderByComparator);
	}

	/**
	* Removes all the cfp models where cfpNo = &#63; from the database.
	*
	* @param cfpNo the cfp no
	*/
	public static void removeByCfpNo(java.lang.String cfpNo) {
		getPersistence().removeByCfpNo(cfpNo);
	}

	/**
	* Returns the number of cfp models where cfpNo = &#63;.
	*
	* @param cfpNo the cfp no
	* @return the number of matching cfp models
	*/
	public static int countByCfpNo(java.lang.String cfpNo) {
		return getPersistence().countByCfpNo(cfpNo);
	}

	/**
	* Returns all the cfp models where cfpName = &#63;.
	*
	* @param cfpName the cfp name
	* @return the matching cfp models
	*/
	public static List<CfpModel> findByCfpName(java.lang.String cfpName) {
		return getPersistence().findByCfpName(cfpName);
	}

	/**
	* Returns a range of all the cfp models where cfpName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param cfpName the cfp name
	* @param start the lower bound of the range of cfp models
	* @param end the upper bound of the range of cfp models (not inclusive)
	* @return the range of matching cfp models
	*/
	public static List<CfpModel> findByCfpName(java.lang.String cfpName,
		int start, int end) {
		return getPersistence().findByCfpName(cfpName, start, end);
	}

	/**
	* Returns an ordered range of all the cfp models where cfpName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param cfpName the cfp name
	* @param start the lower bound of the range of cfp models
	* @param end the upper bound of the range of cfp models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching cfp models
	*/
	public static List<CfpModel> findByCfpName(java.lang.String cfpName,
		int start, int end, OrderByComparator<CfpModel> orderByComparator) {
		return getPersistence()
				   .findByCfpName(cfpName, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the cfp models where cfpName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param cfpName the cfp name
	* @param start the lower bound of the range of cfp models
	* @param end the upper bound of the range of cfp models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching cfp models
	*/
	public static List<CfpModel> findByCfpName(java.lang.String cfpName,
		int start, int end, OrderByComparator<CfpModel> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCfpName(cfpName, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first cfp model in the ordered set where cfpName = &#63;.
	*
	* @param cfpName the cfp name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cfp model
	* @throws NoSuchCfpModelException if a matching cfp model could not be found
	*/
	public static CfpModel findByCfpName_First(java.lang.String cfpName,
		OrderByComparator<CfpModel> orderByComparator)
		throws com.stpl.app.exception.NoSuchCfpModelException {
		return getPersistence().findByCfpName_First(cfpName, orderByComparator);
	}

	/**
	* Returns the first cfp model in the ordered set where cfpName = &#63;.
	*
	* @param cfpName the cfp name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cfp model, or <code>null</code> if a matching cfp model could not be found
	*/
	public static CfpModel fetchByCfpName_First(java.lang.String cfpName,
		OrderByComparator<CfpModel> orderByComparator) {
		return getPersistence().fetchByCfpName_First(cfpName, orderByComparator);
	}

	/**
	* Returns the last cfp model in the ordered set where cfpName = &#63;.
	*
	* @param cfpName the cfp name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cfp model
	* @throws NoSuchCfpModelException if a matching cfp model could not be found
	*/
	public static CfpModel findByCfpName_Last(java.lang.String cfpName,
		OrderByComparator<CfpModel> orderByComparator)
		throws com.stpl.app.exception.NoSuchCfpModelException {
		return getPersistence().findByCfpName_Last(cfpName, orderByComparator);
	}

	/**
	* Returns the last cfp model in the ordered set where cfpName = &#63;.
	*
	* @param cfpName the cfp name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cfp model, or <code>null</code> if a matching cfp model could not be found
	*/
	public static CfpModel fetchByCfpName_Last(java.lang.String cfpName,
		OrderByComparator<CfpModel> orderByComparator) {
		return getPersistence().fetchByCfpName_Last(cfpName, orderByComparator);
	}

	/**
	* Returns the cfp models before and after the current cfp model in the ordered set where cfpName = &#63;.
	*
	* @param cfpModelSid the primary key of the current cfp model
	* @param cfpName the cfp name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next cfp model
	* @throws NoSuchCfpModelException if a cfp model with the primary key could not be found
	*/
	public static CfpModel[] findByCfpName_PrevAndNext(int cfpModelSid,
		java.lang.String cfpName, OrderByComparator<CfpModel> orderByComparator)
		throws com.stpl.app.exception.NoSuchCfpModelException {
		return getPersistence()
				   .findByCfpName_PrevAndNext(cfpModelSid, cfpName,
			orderByComparator);
	}

	/**
	* Removes all the cfp models where cfpName = &#63; from the database.
	*
	* @param cfpName the cfp name
	*/
	public static void removeByCfpName(java.lang.String cfpName) {
		getPersistence().removeByCfpName(cfpName);
	}

	/**
	* Returns the number of cfp models where cfpName = &#63;.
	*
	* @param cfpName the cfp name
	* @return the number of matching cfp models
	*/
	public static int countByCfpName(java.lang.String cfpName) {
		return getPersistence().countByCfpName(cfpName);
	}

	/**
	* Returns all the cfp models where cfpType = &#63;.
	*
	* @param cfpType the cfp type
	* @return the matching cfp models
	*/
	public static List<CfpModel> findByCfpType(int cfpType) {
		return getPersistence().findByCfpType(cfpType);
	}

	/**
	* Returns a range of all the cfp models where cfpType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param cfpType the cfp type
	* @param start the lower bound of the range of cfp models
	* @param end the upper bound of the range of cfp models (not inclusive)
	* @return the range of matching cfp models
	*/
	public static List<CfpModel> findByCfpType(int cfpType, int start, int end) {
		return getPersistence().findByCfpType(cfpType, start, end);
	}

	/**
	* Returns an ordered range of all the cfp models where cfpType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param cfpType the cfp type
	* @param start the lower bound of the range of cfp models
	* @param end the upper bound of the range of cfp models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching cfp models
	*/
	public static List<CfpModel> findByCfpType(int cfpType, int start, int end,
		OrderByComparator<CfpModel> orderByComparator) {
		return getPersistence()
				   .findByCfpType(cfpType, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the cfp models where cfpType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param cfpType the cfp type
	* @param start the lower bound of the range of cfp models
	* @param end the upper bound of the range of cfp models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching cfp models
	*/
	public static List<CfpModel> findByCfpType(int cfpType, int start, int end,
		OrderByComparator<CfpModel> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByCfpType(cfpType, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first cfp model in the ordered set where cfpType = &#63;.
	*
	* @param cfpType the cfp type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cfp model
	* @throws NoSuchCfpModelException if a matching cfp model could not be found
	*/
	public static CfpModel findByCfpType_First(int cfpType,
		OrderByComparator<CfpModel> orderByComparator)
		throws com.stpl.app.exception.NoSuchCfpModelException {
		return getPersistence().findByCfpType_First(cfpType, orderByComparator);
	}

	/**
	* Returns the first cfp model in the ordered set where cfpType = &#63;.
	*
	* @param cfpType the cfp type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cfp model, or <code>null</code> if a matching cfp model could not be found
	*/
	public static CfpModel fetchByCfpType_First(int cfpType,
		OrderByComparator<CfpModel> orderByComparator) {
		return getPersistence().fetchByCfpType_First(cfpType, orderByComparator);
	}

	/**
	* Returns the last cfp model in the ordered set where cfpType = &#63;.
	*
	* @param cfpType the cfp type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cfp model
	* @throws NoSuchCfpModelException if a matching cfp model could not be found
	*/
	public static CfpModel findByCfpType_Last(int cfpType,
		OrderByComparator<CfpModel> orderByComparator)
		throws com.stpl.app.exception.NoSuchCfpModelException {
		return getPersistence().findByCfpType_Last(cfpType, orderByComparator);
	}

	/**
	* Returns the last cfp model in the ordered set where cfpType = &#63;.
	*
	* @param cfpType the cfp type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cfp model, or <code>null</code> if a matching cfp model could not be found
	*/
	public static CfpModel fetchByCfpType_Last(int cfpType,
		OrderByComparator<CfpModel> orderByComparator) {
		return getPersistence().fetchByCfpType_Last(cfpType, orderByComparator);
	}

	/**
	* Returns the cfp models before and after the current cfp model in the ordered set where cfpType = &#63;.
	*
	* @param cfpModelSid the primary key of the current cfp model
	* @param cfpType the cfp type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next cfp model
	* @throws NoSuchCfpModelException if a cfp model with the primary key could not be found
	*/
	public static CfpModel[] findByCfpType_PrevAndNext(int cfpModelSid,
		int cfpType, OrderByComparator<CfpModel> orderByComparator)
		throws com.stpl.app.exception.NoSuchCfpModelException {
		return getPersistence()
				   .findByCfpType_PrevAndNext(cfpModelSid, cfpType,
			orderByComparator);
	}

	/**
	* Removes all the cfp models where cfpType = &#63; from the database.
	*
	* @param cfpType the cfp type
	*/
	public static void removeByCfpType(int cfpType) {
		getPersistence().removeByCfpType(cfpType);
	}

	/**
	* Returns the number of cfp models where cfpType = &#63;.
	*
	* @param cfpType the cfp type
	* @return the number of matching cfp models
	*/
	public static int countByCfpType(int cfpType) {
		return getPersistence().countByCfpType(cfpType);
	}

	/**
	* Returns all the cfp models where cfpStatus = &#63;.
	*
	* @param cfpStatus the cfp status
	* @return the matching cfp models
	*/
	public static List<CfpModel> findByCfpStatus(int cfpStatus) {
		return getPersistence().findByCfpStatus(cfpStatus);
	}

	/**
	* Returns a range of all the cfp models where cfpStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param cfpStatus the cfp status
	* @param start the lower bound of the range of cfp models
	* @param end the upper bound of the range of cfp models (not inclusive)
	* @return the range of matching cfp models
	*/
	public static List<CfpModel> findByCfpStatus(int cfpStatus, int start,
		int end) {
		return getPersistence().findByCfpStatus(cfpStatus, start, end);
	}

	/**
	* Returns an ordered range of all the cfp models where cfpStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param cfpStatus the cfp status
	* @param start the lower bound of the range of cfp models
	* @param end the upper bound of the range of cfp models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching cfp models
	*/
	public static List<CfpModel> findByCfpStatus(int cfpStatus, int start,
		int end, OrderByComparator<CfpModel> orderByComparator) {
		return getPersistence()
				   .findByCfpStatus(cfpStatus, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the cfp models where cfpStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param cfpStatus the cfp status
	* @param start the lower bound of the range of cfp models
	* @param end the upper bound of the range of cfp models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching cfp models
	*/
	public static List<CfpModel> findByCfpStatus(int cfpStatus, int start,
		int end, OrderByComparator<CfpModel> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCfpStatus(cfpStatus, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first cfp model in the ordered set where cfpStatus = &#63;.
	*
	* @param cfpStatus the cfp status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cfp model
	* @throws NoSuchCfpModelException if a matching cfp model could not be found
	*/
	public static CfpModel findByCfpStatus_First(int cfpStatus,
		OrderByComparator<CfpModel> orderByComparator)
		throws com.stpl.app.exception.NoSuchCfpModelException {
		return getPersistence()
				   .findByCfpStatus_First(cfpStatus, orderByComparator);
	}

	/**
	* Returns the first cfp model in the ordered set where cfpStatus = &#63;.
	*
	* @param cfpStatus the cfp status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cfp model, or <code>null</code> if a matching cfp model could not be found
	*/
	public static CfpModel fetchByCfpStatus_First(int cfpStatus,
		OrderByComparator<CfpModel> orderByComparator) {
		return getPersistence()
				   .fetchByCfpStatus_First(cfpStatus, orderByComparator);
	}

	/**
	* Returns the last cfp model in the ordered set where cfpStatus = &#63;.
	*
	* @param cfpStatus the cfp status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cfp model
	* @throws NoSuchCfpModelException if a matching cfp model could not be found
	*/
	public static CfpModel findByCfpStatus_Last(int cfpStatus,
		OrderByComparator<CfpModel> orderByComparator)
		throws com.stpl.app.exception.NoSuchCfpModelException {
		return getPersistence()
				   .findByCfpStatus_Last(cfpStatus, orderByComparator);
	}

	/**
	* Returns the last cfp model in the ordered set where cfpStatus = &#63;.
	*
	* @param cfpStatus the cfp status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cfp model, or <code>null</code> if a matching cfp model could not be found
	*/
	public static CfpModel fetchByCfpStatus_Last(int cfpStatus,
		OrderByComparator<CfpModel> orderByComparator) {
		return getPersistence()
				   .fetchByCfpStatus_Last(cfpStatus, orderByComparator);
	}

	/**
	* Returns the cfp models before and after the current cfp model in the ordered set where cfpStatus = &#63;.
	*
	* @param cfpModelSid the primary key of the current cfp model
	* @param cfpStatus the cfp status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next cfp model
	* @throws NoSuchCfpModelException if a cfp model with the primary key could not be found
	*/
	public static CfpModel[] findByCfpStatus_PrevAndNext(int cfpModelSid,
		int cfpStatus, OrderByComparator<CfpModel> orderByComparator)
		throws com.stpl.app.exception.NoSuchCfpModelException {
		return getPersistence()
				   .findByCfpStatus_PrevAndNext(cfpModelSid, cfpStatus,
			orderByComparator);
	}

	/**
	* Removes all the cfp models where cfpStatus = &#63; from the database.
	*
	* @param cfpStatus the cfp status
	*/
	public static void removeByCfpStatus(int cfpStatus) {
		getPersistence().removeByCfpStatus(cfpStatus);
	}

	/**
	* Returns the number of cfp models where cfpStatus = &#63;.
	*
	* @param cfpStatus the cfp status
	* @return the number of matching cfp models
	*/
	public static int countByCfpStatus(int cfpStatus) {
		return getPersistence().countByCfpStatus(cfpStatus);
	}

	/**
	* Returns all the cfp models where cfpTradeClass = &#63;.
	*
	* @param cfpTradeClass the cfp trade class
	* @return the matching cfp models
	*/
	public static List<CfpModel> findByCfpTradeClass(int cfpTradeClass) {
		return getPersistence().findByCfpTradeClass(cfpTradeClass);
	}

	/**
	* Returns a range of all the cfp models where cfpTradeClass = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param cfpTradeClass the cfp trade class
	* @param start the lower bound of the range of cfp models
	* @param end the upper bound of the range of cfp models (not inclusive)
	* @return the range of matching cfp models
	*/
	public static List<CfpModel> findByCfpTradeClass(int cfpTradeClass,
		int start, int end) {
		return getPersistence().findByCfpTradeClass(cfpTradeClass, start, end);
	}

	/**
	* Returns an ordered range of all the cfp models where cfpTradeClass = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param cfpTradeClass the cfp trade class
	* @param start the lower bound of the range of cfp models
	* @param end the upper bound of the range of cfp models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching cfp models
	*/
	public static List<CfpModel> findByCfpTradeClass(int cfpTradeClass,
		int start, int end, OrderByComparator<CfpModel> orderByComparator) {
		return getPersistence()
				   .findByCfpTradeClass(cfpTradeClass, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the cfp models where cfpTradeClass = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param cfpTradeClass the cfp trade class
	* @param start the lower bound of the range of cfp models
	* @param end the upper bound of the range of cfp models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching cfp models
	*/
	public static List<CfpModel> findByCfpTradeClass(int cfpTradeClass,
		int start, int end, OrderByComparator<CfpModel> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCfpTradeClass(cfpTradeClass, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first cfp model in the ordered set where cfpTradeClass = &#63;.
	*
	* @param cfpTradeClass the cfp trade class
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cfp model
	* @throws NoSuchCfpModelException if a matching cfp model could not be found
	*/
	public static CfpModel findByCfpTradeClass_First(int cfpTradeClass,
		OrderByComparator<CfpModel> orderByComparator)
		throws com.stpl.app.exception.NoSuchCfpModelException {
		return getPersistence()
				   .findByCfpTradeClass_First(cfpTradeClass, orderByComparator);
	}

	/**
	* Returns the first cfp model in the ordered set where cfpTradeClass = &#63;.
	*
	* @param cfpTradeClass the cfp trade class
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cfp model, or <code>null</code> if a matching cfp model could not be found
	*/
	public static CfpModel fetchByCfpTradeClass_First(int cfpTradeClass,
		OrderByComparator<CfpModel> orderByComparator) {
		return getPersistence()
				   .fetchByCfpTradeClass_First(cfpTradeClass, orderByComparator);
	}

	/**
	* Returns the last cfp model in the ordered set where cfpTradeClass = &#63;.
	*
	* @param cfpTradeClass the cfp trade class
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cfp model
	* @throws NoSuchCfpModelException if a matching cfp model could not be found
	*/
	public static CfpModel findByCfpTradeClass_Last(int cfpTradeClass,
		OrderByComparator<CfpModel> orderByComparator)
		throws com.stpl.app.exception.NoSuchCfpModelException {
		return getPersistence()
				   .findByCfpTradeClass_Last(cfpTradeClass, orderByComparator);
	}

	/**
	* Returns the last cfp model in the ordered set where cfpTradeClass = &#63;.
	*
	* @param cfpTradeClass the cfp trade class
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cfp model, or <code>null</code> if a matching cfp model could not be found
	*/
	public static CfpModel fetchByCfpTradeClass_Last(int cfpTradeClass,
		OrderByComparator<CfpModel> orderByComparator) {
		return getPersistence()
				   .fetchByCfpTradeClass_Last(cfpTradeClass, orderByComparator);
	}

	/**
	* Returns the cfp models before and after the current cfp model in the ordered set where cfpTradeClass = &#63;.
	*
	* @param cfpModelSid the primary key of the current cfp model
	* @param cfpTradeClass the cfp trade class
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next cfp model
	* @throws NoSuchCfpModelException if a cfp model with the primary key could not be found
	*/
	public static CfpModel[] findByCfpTradeClass_PrevAndNext(int cfpModelSid,
		int cfpTradeClass, OrderByComparator<CfpModel> orderByComparator)
		throws com.stpl.app.exception.NoSuchCfpModelException {
		return getPersistence()
				   .findByCfpTradeClass_PrevAndNext(cfpModelSid, cfpTradeClass,
			orderByComparator);
	}

	/**
	* Removes all the cfp models where cfpTradeClass = &#63; from the database.
	*
	* @param cfpTradeClass the cfp trade class
	*/
	public static void removeByCfpTradeClass(int cfpTradeClass) {
		getPersistence().removeByCfpTradeClass(cfpTradeClass);
	}

	/**
	* Returns the number of cfp models where cfpTradeClass = &#63;.
	*
	* @param cfpTradeClass the cfp trade class
	* @return the number of matching cfp models
	*/
	public static int countByCfpTradeClass(int cfpTradeClass) {
		return getPersistence().countByCfpTradeClass(cfpTradeClass);
	}

	/**
	* Caches the cfp model in the entity cache if it is enabled.
	*
	* @param cfpModel the cfp model
	*/
	public static void cacheResult(CfpModel cfpModel) {
		getPersistence().cacheResult(cfpModel);
	}

	/**
	* Caches the cfp models in the entity cache if it is enabled.
	*
	* @param cfpModels the cfp models
	*/
	public static void cacheResult(List<CfpModel> cfpModels) {
		getPersistence().cacheResult(cfpModels);
	}

	/**
	* Creates a new cfp model with the primary key. Does not add the cfp model to the database.
	*
	* @param cfpModelSid the primary key for the new cfp model
	* @return the new cfp model
	*/
	public static CfpModel create(int cfpModelSid) {
		return getPersistence().create(cfpModelSid);
	}

	/**
	* Removes the cfp model with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param cfpModelSid the primary key of the cfp model
	* @return the cfp model that was removed
	* @throws NoSuchCfpModelException if a cfp model with the primary key could not be found
	*/
	public static CfpModel remove(int cfpModelSid)
		throws com.stpl.app.exception.NoSuchCfpModelException {
		return getPersistence().remove(cfpModelSid);
	}

	public static CfpModel updateImpl(CfpModel cfpModel) {
		return getPersistence().updateImpl(cfpModel);
	}

	/**
	* Returns the cfp model with the primary key or throws a {@link NoSuchCfpModelException} if it could not be found.
	*
	* @param cfpModelSid the primary key of the cfp model
	* @return the cfp model
	* @throws NoSuchCfpModelException if a cfp model with the primary key could not be found
	*/
	public static CfpModel findByPrimaryKey(int cfpModelSid)
		throws com.stpl.app.exception.NoSuchCfpModelException {
		return getPersistence().findByPrimaryKey(cfpModelSid);
	}

	/**
	* Returns the cfp model with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param cfpModelSid the primary key of the cfp model
	* @return the cfp model, or <code>null</code> if a cfp model with the primary key could not be found
	*/
	public static CfpModel fetchByPrimaryKey(int cfpModelSid) {
		return getPersistence().fetchByPrimaryKey(cfpModelSid);
	}

	public static java.util.Map<java.io.Serializable, CfpModel> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the cfp models.
	*
	* @return the cfp models
	*/
	public static List<CfpModel> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the cfp models.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cfp models
	* @param end the upper bound of the range of cfp models (not inclusive)
	* @return the range of cfp models
	*/
	public static List<CfpModel> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the cfp models.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cfp models
	* @param end the upper bound of the range of cfp models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of cfp models
	*/
	public static List<CfpModel> findAll(int start, int end,
		OrderByComparator<CfpModel> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the cfp models.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cfp models
	* @param end the upper bound of the range of cfp models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of cfp models
	*/
	public static List<CfpModel> findAll(int start, int end,
		OrderByComparator<CfpModel> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the cfp models from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of cfp models.
	*
	* @return the number of cfp models
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static CfpModelPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CfpModelPersistence, CfpModelPersistence> _serviceTracker =
		ServiceTrackerFactory.open(CfpModelPersistence.class);
}