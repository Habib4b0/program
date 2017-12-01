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

import com.stpl.app.model.CdrModel;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the cdr model service. This utility wraps {@link com.stpl.app.service.persistence.impl.CdrModelPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CdrModelPersistence
 * @see com.stpl.app.service.persistence.impl.CdrModelPersistenceImpl
 * @generated
 */
@ProviderType
public class CdrModelUtil {
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
	public static void clearCache(CdrModel cdrModel) {
		getPersistence().clearCache(cdrModel);
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
	public static List<CdrModel> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CdrModel> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CdrModel> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CdrModel> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CdrModel update(CdrModel cdrModel) {
		return getPersistence().update(cdrModel);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CdrModel update(CdrModel cdrModel,
		ServiceContext serviceContext) {
		return getPersistence().update(cdrModel, serviceContext);
	}

	/**
	* Caches the cdr model in the entity cache if it is enabled.
	*
	* @param cdrModel the cdr model
	*/
	public static void cacheResult(CdrModel cdrModel) {
		getPersistence().cacheResult(cdrModel);
	}

	/**
	* Caches the cdr models in the entity cache if it is enabled.
	*
	* @param cdrModels the cdr models
	*/
	public static void cacheResult(List<CdrModel> cdrModels) {
		getPersistence().cacheResult(cdrModels);
	}

	/**
	* Creates a new cdr model with the primary key. Does not add the cdr model to the database.
	*
	* @param cdrModelSid the primary key for the new cdr model
	* @return the new cdr model
	*/
	public static CdrModel create(int cdrModelSid) {
		return getPersistence().create(cdrModelSid);
	}

	/**
	* Removes the cdr model with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param cdrModelSid the primary key of the cdr model
	* @return the cdr model that was removed
	* @throws NoSuchCdrModelException if a cdr model with the primary key could not be found
	*/
	public static CdrModel remove(int cdrModelSid)
		throws com.stpl.app.exception.NoSuchCdrModelException {
		return getPersistence().remove(cdrModelSid);
	}

	public static CdrModel updateImpl(CdrModel cdrModel) {
		return getPersistence().updateImpl(cdrModel);
	}

	/**
	* Returns the cdr model with the primary key or throws a {@link NoSuchCdrModelException} if it could not be found.
	*
	* @param cdrModelSid the primary key of the cdr model
	* @return the cdr model
	* @throws NoSuchCdrModelException if a cdr model with the primary key could not be found
	*/
	public static CdrModel findByPrimaryKey(int cdrModelSid)
		throws com.stpl.app.exception.NoSuchCdrModelException {
		return getPersistence().findByPrimaryKey(cdrModelSid);
	}

	/**
	* Returns the cdr model with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param cdrModelSid the primary key of the cdr model
	* @return the cdr model, or <code>null</code> if a cdr model with the primary key could not be found
	*/
	public static CdrModel fetchByPrimaryKey(int cdrModelSid) {
		return getPersistence().fetchByPrimaryKey(cdrModelSid);
	}

	public static java.util.Map<java.io.Serializable, CdrModel> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the cdr models.
	*
	* @return the cdr models
	*/
	public static List<CdrModel> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the cdr models.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CdrModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cdr models
	* @param end the upper bound of the range of cdr models (not inclusive)
	* @return the range of cdr models
	*/
	public static List<CdrModel> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the cdr models.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CdrModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cdr models
	* @param end the upper bound of the range of cdr models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of cdr models
	*/
	public static List<CdrModel> findAll(int start, int end,
		OrderByComparator<CdrModel> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the cdr models.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CdrModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cdr models
	* @param end the upper bound of the range of cdr models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of cdr models
	*/
	public static List<CdrModel> findAll(int start, int end,
		OrderByComparator<CdrModel> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the cdr models from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of cdr models.
	*
	* @return the number of cdr models
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static CdrModelPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CdrModelPersistence, CdrModelPersistence> _serviceTracker =
		ServiceTrackerFactory.open(CdrModelPersistence.class);
}