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

import com.stpl.app.model.ModuleSubmoduleMaster;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the module submodule master service. This utility wraps {@link com.stpl.app.service.persistence.impl.ModuleSubmoduleMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ModuleSubmoduleMasterPersistence
 * @see com.stpl.app.service.persistence.impl.ModuleSubmoduleMasterPersistenceImpl
 * @generated
 */
@ProviderType
public class ModuleSubmoduleMasterUtil {
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
	public static void clearCache(ModuleSubmoduleMaster moduleSubmoduleMaster) {
		getPersistence().clearCache(moduleSubmoduleMaster);
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
	public static List<ModuleSubmoduleMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ModuleSubmoduleMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ModuleSubmoduleMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ModuleSubmoduleMaster> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ModuleSubmoduleMaster update(
		ModuleSubmoduleMaster moduleSubmoduleMaster) {
		return getPersistence().update(moduleSubmoduleMaster);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ModuleSubmoduleMaster update(
		ModuleSubmoduleMaster moduleSubmoduleMaster,
		ServiceContext serviceContext) {
		return getPersistence().update(moduleSubmoduleMaster, serviceContext);
	}

	/**
	* Returns all the module submodule masters where moduleName = &#63;.
	*
	* @param moduleName the module name
	* @return the matching module submodule masters
	*/
	public static List<ModuleSubmoduleMaster> findByModuleName(
		java.lang.String moduleName) {
		return getPersistence().findByModuleName(moduleName);
	}

	/**
	* Returns a range of all the module submodule masters where moduleName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleSubmoduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param moduleName the module name
	* @param start the lower bound of the range of module submodule masters
	* @param end the upper bound of the range of module submodule masters (not inclusive)
	* @return the range of matching module submodule masters
	*/
	public static List<ModuleSubmoduleMaster> findByModuleName(
		java.lang.String moduleName, int start, int end) {
		return getPersistence().findByModuleName(moduleName, start, end);
	}

	/**
	* Returns an ordered range of all the module submodule masters where moduleName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleSubmoduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param moduleName the module name
	* @param start the lower bound of the range of module submodule masters
	* @param end the upper bound of the range of module submodule masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching module submodule masters
	*/
	public static List<ModuleSubmoduleMaster> findByModuleName(
		java.lang.String moduleName, int start, int end,
		OrderByComparator<ModuleSubmoduleMaster> orderByComparator) {
		return getPersistence()
				   .findByModuleName(moduleName, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the module submodule masters where moduleName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleSubmoduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param moduleName the module name
	* @param start the lower bound of the range of module submodule masters
	* @param end the upper bound of the range of module submodule masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching module submodule masters
	*/
	public static List<ModuleSubmoduleMaster> findByModuleName(
		java.lang.String moduleName, int start, int end,
		OrderByComparator<ModuleSubmoduleMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByModuleName(moduleName, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first module submodule master in the ordered set where moduleName = &#63;.
	*
	* @param moduleName the module name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching module submodule master
	* @throws NoSuchModuleSubmoduleMasterException if a matching module submodule master could not be found
	*/
	public static ModuleSubmoduleMaster findByModuleName_First(
		java.lang.String moduleName,
		OrderByComparator<ModuleSubmoduleMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchModuleSubmoduleMasterException {
		return getPersistence()
				   .findByModuleName_First(moduleName, orderByComparator);
	}

	/**
	* Returns the first module submodule master in the ordered set where moduleName = &#63;.
	*
	* @param moduleName the module name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching module submodule master, or <code>null</code> if a matching module submodule master could not be found
	*/
	public static ModuleSubmoduleMaster fetchByModuleName_First(
		java.lang.String moduleName,
		OrderByComparator<ModuleSubmoduleMaster> orderByComparator) {
		return getPersistence()
				   .fetchByModuleName_First(moduleName, orderByComparator);
	}

	/**
	* Returns the last module submodule master in the ordered set where moduleName = &#63;.
	*
	* @param moduleName the module name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching module submodule master
	* @throws NoSuchModuleSubmoduleMasterException if a matching module submodule master could not be found
	*/
	public static ModuleSubmoduleMaster findByModuleName_Last(
		java.lang.String moduleName,
		OrderByComparator<ModuleSubmoduleMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchModuleSubmoduleMasterException {
		return getPersistence()
				   .findByModuleName_Last(moduleName, orderByComparator);
	}

	/**
	* Returns the last module submodule master in the ordered set where moduleName = &#63;.
	*
	* @param moduleName the module name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching module submodule master, or <code>null</code> if a matching module submodule master could not be found
	*/
	public static ModuleSubmoduleMaster fetchByModuleName_Last(
		java.lang.String moduleName,
		OrderByComparator<ModuleSubmoduleMaster> orderByComparator) {
		return getPersistence()
				   .fetchByModuleName_Last(moduleName, orderByComparator);
	}

	/**
	* Returns the module submodule masters before and after the current module submodule master in the ordered set where moduleName = &#63;.
	*
	* @param moduleSubmoduleSid the primary key of the current module submodule master
	* @param moduleName the module name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next module submodule master
	* @throws NoSuchModuleSubmoduleMasterException if a module submodule master with the primary key could not be found
	*/
	public static ModuleSubmoduleMaster[] findByModuleName_PrevAndNext(
		int moduleSubmoduleSid, java.lang.String moduleName,
		OrderByComparator<ModuleSubmoduleMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchModuleSubmoduleMasterException {
		return getPersistence()
				   .findByModuleName_PrevAndNext(moduleSubmoduleSid,
			moduleName, orderByComparator);
	}

	/**
	* Removes all the module submodule masters where moduleName = &#63; from the database.
	*
	* @param moduleName the module name
	*/
	public static void removeByModuleName(java.lang.String moduleName) {
		getPersistence().removeByModuleName(moduleName);
	}

	/**
	* Returns the number of module submodule masters where moduleName = &#63;.
	*
	* @param moduleName the module name
	* @return the number of matching module submodule masters
	*/
	public static int countByModuleName(java.lang.String moduleName) {
		return getPersistence().countByModuleName(moduleName);
	}

	/**
	* Returns all the module submodule masters where moduleName = &#63;.
	*
	* @param moduleName the module name
	* @return the matching module submodule masters
	*/
	public static List<ModuleSubmoduleMaster> findBySubmoduleName(
		java.lang.String moduleName) {
		return getPersistence().findBySubmoduleName(moduleName);
	}

	/**
	* Returns a range of all the module submodule masters where moduleName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleSubmoduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param moduleName the module name
	* @param start the lower bound of the range of module submodule masters
	* @param end the upper bound of the range of module submodule masters (not inclusive)
	* @return the range of matching module submodule masters
	*/
	public static List<ModuleSubmoduleMaster> findBySubmoduleName(
		java.lang.String moduleName, int start, int end) {
		return getPersistence().findBySubmoduleName(moduleName, start, end);
	}

	/**
	* Returns an ordered range of all the module submodule masters where moduleName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleSubmoduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param moduleName the module name
	* @param start the lower bound of the range of module submodule masters
	* @param end the upper bound of the range of module submodule masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching module submodule masters
	*/
	public static List<ModuleSubmoduleMaster> findBySubmoduleName(
		java.lang.String moduleName, int start, int end,
		OrderByComparator<ModuleSubmoduleMaster> orderByComparator) {
		return getPersistence()
				   .findBySubmoduleName(moduleName, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the module submodule masters where moduleName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleSubmoduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param moduleName the module name
	* @param start the lower bound of the range of module submodule masters
	* @param end the upper bound of the range of module submodule masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching module submodule masters
	*/
	public static List<ModuleSubmoduleMaster> findBySubmoduleName(
		java.lang.String moduleName, int start, int end,
		OrderByComparator<ModuleSubmoduleMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findBySubmoduleName(moduleName, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first module submodule master in the ordered set where moduleName = &#63;.
	*
	* @param moduleName the module name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching module submodule master
	* @throws NoSuchModuleSubmoduleMasterException if a matching module submodule master could not be found
	*/
	public static ModuleSubmoduleMaster findBySubmoduleName_First(
		java.lang.String moduleName,
		OrderByComparator<ModuleSubmoduleMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchModuleSubmoduleMasterException {
		return getPersistence()
				   .findBySubmoduleName_First(moduleName, orderByComparator);
	}

	/**
	* Returns the first module submodule master in the ordered set where moduleName = &#63;.
	*
	* @param moduleName the module name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching module submodule master, or <code>null</code> if a matching module submodule master could not be found
	*/
	public static ModuleSubmoduleMaster fetchBySubmoduleName_First(
		java.lang.String moduleName,
		OrderByComparator<ModuleSubmoduleMaster> orderByComparator) {
		return getPersistence()
				   .fetchBySubmoduleName_First(moduleName, orderByComparator);
	}

	/**
	* Returns the last module submodule master in the ordered set where moduleName = &#63;.
	*
	* @param moduleName the module name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching module submodule master
	* @throws NoSuchModuleSubmoduleMasterException if a matching module submodule master could not be found
	*/
	public static ModuleSubmoduleMaster findBySubmoduleName_Last(
		java.lang.String moduleName,
		OrderByComparator<ModuleSubmoduleMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchModuleSubmoduleMasterException {
		return getPersistence()
				   .findBySubmoduleName_Last(moduleName, orderByComparator);
	}

	/**
	* Returns the last module submodule master in the ordered set where moduleName = &#63;.
	*
	* @param moduleName the module name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching module submodule master, or <code>null</code> if a matching module submodule master could not be found
	*/
	public static ModuleSubmoduleMaster fetchBySubmoduleName_Last(
		java.lang.String moduleName,
		OrderByComparator<ModuleSubmoduleMaster> orderByComparator) {
		return getPersistence()
				   .fetchBySubmoduleName_Last(moduleName, orderByComparator);
	}

	/**
	* Returns the module submodule masters before and after the current module submodule master in the ordered set where moduleName = &#63;.
	*
	* @param moduleSubmoduleSid the primary key of the current module submodule master
	* @param moduleName the module name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next module submodule master
	* @throws NoSuchModuleSubmoduleMasterException if a module submodule master with the primary key could not be found
	*/
	public static ModuleSubmoduleMaster[] findBySubmoduleName_PrevAndNext(
		int moduleSubmoduleSid, java.lang.String moduleName,
		OrderByComparator<ModuleSubmoduleMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchModuleSubmoduleMasterException {
		return getPersistence()
				   .findBySubmoduleName_PrevAndNext(moduleSubmoduleSid,
			moduleName, orderByComparator);
	}

	/**
	* Removes all the module submodule masters where moduleName = &#63; from the database.
	*
	* @param moduleName the module name
	*/
	public static void removeBySubmoduleName(java.lang.String moduleName) {
		getPersistence().removeBySubmoduleName(moduleName);
	}

	/**
	* Returns the number of module submodule masters where moduleName = &#63;.
	*
	* @param moduleName the module name
	* @return the number of matching module submodule masters
	*/
	public static int countBySubmoduleName(java.lang.String moduleName) {
		return getPersistence().countBySubmoduleName(moduleName);
	}

	/**
	* Caches the module submodule master in the entity cache if it is enabled.
	*
	* @param moduleSubmoduleMaster the module submodule master
	*/
	public static void cacheResult(ModuleSubmoduleMaster moduleSubmoduleMaster) {
		getPersistence().cacheResult(moduleSubmoduleMaster);
	}

	/**
	* Caches the module submodule masters in the entity cache if it is enabled.
	*
	* @param moduleSubmoduleMasters the module submodule masters
	*/
	public static void cacheResult(
		List<ModuleSubmoduleMaster> moduleSubmoduleMasters) {
		getPersistence().cacheResult(moduleSubmoduleMasters);
	}

	/**
	* Creates a new module submodule master with the primary key. Does not add the module submodule master to the database.
	*
	* @param moduleSubmoduleSid the primary key for the new module submodule master
	* @return the new module submodule master
	*/
	public static ModuleSubmoduleMaster create(int moduleSubmoduleSid) {
		return getPersistence().create(moduleSubmoduleSid);
	}

	/**
	* Removes the module submodule master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param moduleSubmoduleSid the primary key of the module submodule master
	* @return the module submodule master that was removed
	* @throws NoSuchModuleSubmoduleMasterException if a module submodule master with the primary key could not be found
	*/
	public static ModuleSubmoduleMaster remove(int moduleSubmoduleSid)
		throws com.stpl.app.exception.NoSuchModuleSubmoduleMasterException {
		return getPersistence().remove(moduleSubmoduleSid);
	}

	public static ModuleSubmoduleMaster updateImpl(
		ModuleSubmoduleMaster moduleSubmoduleMaster) {
		return getPersistence().updateImpl(moduleSubmoduleMaster);
	}

	/**
	* Returns the module submodule master with the primary key or throws a {@link NoSuchModuleSubmoduleMasterException} if it could not be found.
	*
	* @param moduleSubmoduleSid the primary key of the module submodule master
	* @return the module submodule master
	* @throws NoSuchModuleSubmoduleMasterException if a module submodule master with the primary key could not be found
	*/
	public static ModuleSubmoduleMaster findByPrimaryKey(int moduleSubmoduleSid)
		throws com.stpl.app.exception.NoSuchModuleSubmoduleMasterException {
		return getPersistence().findByPrimaryKey(moduleSubmoduleSid);
	}

	/**
	* Returns the module submodule master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param moduleSubmoduleSid the primary key of the module submodule master
	* @return the module submodule master, or <code>null</code> if a module submodule master with the primary key could not be found
	*/
	public static ModuleSubmoduleMaster fetchByPrimaryKey(
		int moduleSubmoduleSid) {
		return getPersistence().fetchByPrimaryKey(moduleSubmoduleSid);
	}

	public static java.util.Map<java.io.Serializable, ModuleSubmoduleMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the module submodule masters.
	*
	* @return the module submodule masters
	*/
	public static List<ModuleSubmoduleMaster> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the module submodule masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleSubmoduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of module submodule masters
	* @param end the upper bound of the range of module submodule masters (not inclusive)
	* @return the range of module submodule masters
	*/
	public static List<ModuleSubmoduleMaster> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the module submodule masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleSubmoduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of module submodule masters
	* @param end the upper bound of the range of module submodule masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of module submodule masters
	*/
	public static List<ModuleSubmoduleMaster> findAll(int start, int end,
		OrderByComparator<ModuleSubmoduleMaster> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the module submodule masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleSubmoduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of module submodule masters
	* @param end the upper bound of the range of module submodule masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of module submodule masters
	*/
	public static List<ModuleSubmoduleMaster> findAll(int start, int end,
		OrderByComparator<ModuleSubmoduleMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the module submodule masters from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of module submodule masters.
	*
	* @return the number of module submodule masters
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ModuleSubmoduleMasterPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ModuleSubmoduleMasterPersistence, ModuleSubmoduleMasterPersistence> _serviceTracker =
		ServiceTrackerFactory.open(ModuleSubmoduleMasterPersistence.class);
}