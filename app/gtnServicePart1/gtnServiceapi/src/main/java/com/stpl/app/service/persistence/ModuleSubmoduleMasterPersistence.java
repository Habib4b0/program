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

import com.stpl.app.exception.NoSuchModuleSubmoduleMasterException;
import com.stpl.app.model.ModuleSubmoduleMaster;

/**
 * The persistence interface for the module submodule master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.ModuleSubmoduleMasterPersistenceImpl
 * @see ModuleSubmoduleMasterUtil
 * @generated
 */
@ProviderType
public interface ModuleSubmoduleMasterPersistence extends BasePersistence<ModuleSubmoduleMaster> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ModuleSubmoduleMasterUtil} to access the module submodule master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the module submodule masters where moduleName = &#63;.
	*
	* @param moduleName the module name
	* @return the matching module submodule masters
	*/
	public java.util.List<ModuleSubmoduleMaster> findByModuleName(
		java.lang.String moduleName);

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
	public java.util.List<ModuleSubmoduleMaster> findByModuleName(
		java.lang.String moduleName, int start, int end);

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
	public java.util.List<ModuleSubmoduleMaster> findByModuleName(
		java.lang.String moduleName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ModuleSubmoduleMaster> orderByComparator);

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
	public java.util.List<ModuleSubmoduleMaster> findByModuleName(
		java.lang.String moduleName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ModuleSubmoduleMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first module submodule master in the ordered set where moduleName = &#63;.
	*
	* @param moduleName the module name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching module submodule master
	* @throws NoSuchModuleSubmoduleMasterException if a matching module submodule master could not be found
	*/
	public ModuleSubmoduleMaster findByModuleName_First(
		java.lang.String moduleName,
		com.liferay.portal.kernel.util.OrderByComparator<ModuleSubmoduleMaster> orderByComparator)
		throws NoSuchModuleSubmoduleMasterException;

	/**
	* Returns the first module submodule master in the ordered set where moduleName = &#63;.
	*
	* @param moduleName the module name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching module submodule master, or <code>null</code> if a matching module submodule master could not be found
	*/
	public ModuleSubmoduleMaster fetchByModuleName_First(
		java.lang.String moduleName,
		com.liferay.portal.kernel.util.OrderByComparator<ModuleSubmoduleMaster> orderByComparator);

	/**
	* Returns the last module submodule master in the ordered set where moduleName = &#63;.
	*
	* @param moduleName the module name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching module submodule master
	* @throws NoSuchModuleSubmoduleMasterException if a matching module submodule master could not be found
	*/
	public ModuleSubmoduleMaster findByModuleName_Last(
		java.lang.String moduleName,
		com.liferay.portal.kernel.util.OrderByComparator<ModuleSubmoduleMaster> orderByComparator)
		throws NoSuchModuleSubmoduleMasterException;

	/**
	* Returns the last module submodule master in the ordered set where moduleName = &#63;.
	*
	* @param moduleName the module name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching module submodule master, or <code>null</code> if a matching module submodule master could not be found
	*/
	public ModuleSubmoduleMaster fetchByModuleName_Last(
		java.lang.String moduleName,
		com.liferay.portal.kernel.util.OrderByComparator<ModuleSubmoduleMaster> orderByComparator);

	/**
	* Returns the module submodule masters before and after the current module submodule master in the ordered set where moduleName = &#63;.
	*
	* @param moduleSubmoduleSid the primary key of the current module submodule master
	* @param moduleName the module name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next module submodule master
	* @throws NoSuchModuleSubmoduleMasterException if a module submodule master with the primary key could not be found
	*/
	public ModuleSubmoduleMaster[] findByModuleName_PrevAndNext(
		int moduleSubmoduleSid, java.lang.String moduleName,
		com.liferay.portal.kernel.util.OrderByComparator<ModuleSubmoduleMaster> orderByComparator)
		throws NoSuchModuleSubmoduleMasterException;

	/**
	* Removes all the module submodule masters where moduleName = &#63; from the database.
	*
	* @param moduleName the module name
	*/
	public void removeByModuleName(java.lang.String moduleName);

	/**
	* Returns the number of module submodule masters where moduleName = &#63;.
	*
	* @param moduleName the module name
	* @return the number of matching module submodule masters
	*/
	public int countByModuleName(java.lang.String moduleName);

	/**
	* Returns all the module submodule masters where moduleName = &#63;.
	*
	* @param moduleName the module name
	* @return the matching module submodule masters
	*/
	public java.util.List<ModuleSubmoduleMaster> findBySubmoduleName(
		java.lang.String moduleName);

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
	public java.util.List<ModuleSubmoduleMaster> findBySubmoduleName(
		java.lang.String moduleName, int start, int end);

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
	public java.util.List<ModuleSubmoduleMaster> findBySubmoduleName(
		java.lang.String moduleName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ModuleSubmoduleMaster> orderByComparator);

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
	public java.util.List<ModuleSubmoduleMaster> findBySubmoduleName(
		java.lang.String moduleName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ModuleSubmoduleMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first module submodule master in the ordered set where moduleName = &#63;.
	*
	* @param moduleName the module name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching module submodule master
	* @throws NoSuchModuleSubmoduleMasterException if a matching module submodule master could not be found
	*/
	public ModuleSubmoduleMaster findBySubmoduleName_First(
		java.lang.String moduleName,
		com.liferay.portal.kernel.util.OrderByComparator<ModuleSubmoduleMaster> orderByComparator)
		throws NoSuchModuleSubmoduleMasterException;

	/**
	* Returns the first module submodule master in the ordered set where moduleName = &#63;.
	*
	* @param moduleName the module name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching module submodule master, or <code>null</code> if a matching module submodule master could not be found
	*/
	public ModuleSubmoduleMaster fetchBySubmoduleName_First(
		java.lang.String moduleName,
		com.liferay.portal.kernel.util.OrderByComparator<ModuleSubmoduleMaster> orderByComparator);

	/**
	* Returns the last module submodule master in the ordered set where moduleName = &#63;.
	*
	* @param moduleName the module name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching module submodule master
	* @throws NoSuchModuleSubmoduleMasterException if a matching module submodule master could not be found
	*/
	public ModuleSubmoduleMaster findBySubmoduleName_Last(
		java.lang.String moduleName,
		com.liferay.portal.kernel.util.OrderByComparator<ModuleSubmoduleMaster> orderByComparator)
		throws NoSuchModuleSubmoduleMasterException;

	/**
	* Returns the last module submodule master in the ordered set where moduleName = &#63;.
	*
	* @param moduleName the module name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching module submodule master, or <code>null</code> if a matching module submodule master could not be found
	*/
	public ModuleSubmoduleMaster fetchBySubmoduleName_Last(
		java.lang.String moduleName,
		com.liferay.portal.kernel.util.OrderByComparator<ModuleSubmoduleMaster> orderByComparator);

	/**
	* Returns the module submodule masters before and after the current module submodule master in the ordered set where moduleName = &#63;.
	*
	* @param moduleSubmoduleSid the primary key of the current module submodule master
	* @param moduleName the module name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next module submodule master
	* @throws NoSuchModuleSubmoduleMasterException if a module submodule master with the primary key could not be found
	*/
	public ModuleSubmoduleMaster[] findBySubmoduleName_PrevAndNext(
		int moduleSubmoduleSid, java.lang.String moduleName,
		com.liferay.portal.kernel.util.OrderByComparator<ModuleSubmoduleMaster> orderByComparator)
		throws NoSuchModuleSubmoduleMasterException;

	/**
	* Removes all the module submodule masters where moduleName = &#63; from the database.
	*
	* @param moduleName the module name
	*/
	public void removeBySubmoduleName(java.lang.String moduleName);

	/**
	* Returns the number of module submodule masters where moduleName = &#63;.
	*
	* @param moduleName the module name
	* @return the number of matching module submodule masters
	*/
	public int countBySubmoduleName(java.lang.String moduleName);

	/**
	* Caches the module submodule master in the entity cache if it is enabled.
	*
	* @param moduleSubmoduleMaster the module submodule master
	*/
	public void cacheResult(ModuleSubmoduleMaster moduleSubmoduleMaster);

	/**
	* Caches the module submodule masters in the entity cache if it is enabled.
	*
	* @param moduleSubmoduleMasters the module submodule masters
	*/
	public void cacheResult(
		java.util.List<ModuleSubmoduleMaster> moduleSubmoduleMasters);

	/**
	* Creates a new module submodule master with the primary key. Does not add the module submodule master to the database.
	*
	* @param moduleSubmoduleSid the primary key for the new module submodule master
	* @return the new module submodule master
	*/
	public ModuleSubmoduleMaster create(int moduleSubmoduleSid);

	/**
	* Removes the module submodule master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param moduleSubmoduleSid the primary key of the module submodule master
	* @return the module submodule master that was removed
	* @throws NoSuchModuleSubmoduleMasterException if a module submodule master with the primary key could not be found
	*/
	public ModuleSubmoduleMaster remove(int moduleSubmoduleSid)
		throws NoSuchModuleSubmoduleMasterException;

	public ModuleSubmoduleMaster updateImpl(
		ModuleSubmoduleMaster moduleSubmoduleMaster);

	/**
	* Returns the module submodule master with the primary key or throws a {@link NoSuchModuleSubmoduleMasterException} if it could not be found.
	*
	* @param moduleSubmoduleSid the primary key of the module submodule master
	* @return the module submodule master
	* @throws NoSuchModuleSubmoduleMasterException if a module submodule master with the primary key could not be found
	*/
	public ModuleSubmoduleMaster findByPrimaryKey(int moduleSubmoduleSid)
		throws NoSuchModuleSubmoduleMasterException;

	/**
	* Returns the module submodule master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param moduleSubmoduleSid the primary key of the module submodule master
	* @return the module submodule master, or <code>null</code> if a module submodule master with the primary key could not be found
	*/
	public ModuleSubmoduleMaster fetchByPrimaryKey(int moduleSubmoduleSid);

	@Override
	public java.util.Map<java.io.Serializable, ModuleSubmoduleMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the module submodule masters.
	*
	* @return the module submodule masters
	*/
	public java.util.List<ModuleSubmoduleMaster> findAll();

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
	public java.util.List<ModuleSubmoduleMaster> findAll(int start, int end);

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
	public java.util.List<ModuleSubmoduleMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ModuleSubmoduleMaster> orderByComparator);

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
	public java.util.List<ModuleSubmoduleMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ModuleSubmoduleMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the module submodule masters from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of module submodule masters.
	*
	* @return the number of module submodule masters
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}