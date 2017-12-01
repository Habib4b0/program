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

import com.stpl.app.exception.NoSuchBusinessroleModuleException;
import com.stpl.app.model.BusinessroleModule;

/**
 * The persistence interface for the businessrole module service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.BusinessroleModulePersistenceImpl
 * @see BusinessroleModuleUtil
 * @generated
 */
@ProviderType
public interface BusinessroleModulePersistence extends BasePersistence<BusinessroleModule> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link BusinessroleModuleUtil} to access the businessrole module persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the businessrole module in the entity cache if it is enabled.
	*
	* @param businessroleModule the businessrole module
	*/
	public void cacheResult(BusinessroleModule businessroleModule);

	/**
	* Caches the businessrole modules in the entity cache if it is enabled.
	*
	* @param businessroleModules the businessrole modules
	*/
	public void cacheResult(
		java.util.List<BusinessroleModule> businessroleModules);

	/**
	* Creates a new businessrole module with the primary key. Does not add the businessrole module to the database.
	*
	* @param businessroleModuleSid the primary key for the new businessrole module
	* @return the new businessrole module
	*/
	public BusinessroleModule create(int businessroleModuleSid);

	/**
	* Removes the businessrole module with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param businessroleModuleSid the primary key of the businessrole module
	* @return the businessrole module that was removed
	* @throws NoSuchBusinessroleModuleException if a businessrole module with the primary key could not be found
	*/
	public BusinessroleModule remove(int businessroleModuleSid)
		throws NoSuchBusinessroleModuleException;

	public BusinessroleModule updateImpl(BusinessroleModule businessroleModule);

	/**
	* Returns the businessrole module with the primary key or throws a {@link NoSuchBusinessroleModuleException} if it could not be found.
	*
	* @param businessroleModuleSid the primary key of the businessrole module
	* @return the businessrole module
	* @throws NoSuchBusinessroleModuleException if a businessrole module with the primary key could not be found
	*/
	public BusinessroleModule findByPrimaryKey(int businessroleModuleSid)
		throws NoSuchBusinessroleModuleException;

	/**
	* Returns the businessrole module with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param businessroleModuleSid the primary key of the businessrole module
	* @return the businessrole module, or <code>null</code> if a businessrole module with the primary key could not be found
	*/
	public BusinessroleModule fetchByPrimaryKey(int businessroleModuleSid);

	@Override
	public java.util.Map<java.io.Serializable, BusinessroleModule> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the businessrole modules.
	*
	* @return the businessrole modules
	*/
	public java.util.List<BusinessroleModule> findAll();

	/**
	* Returns a range of all the businessrole modules.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BusinessroleModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of businessrole modules
	* @param end the upper bound of the range of businessrole modules (not inclusive)
	* @return the range of businessrole modules
	*/
	public java.util.List<BusinessroleModule> findAll(int start, int end);

	/**
	* Returns an ordered range of all the businessrole modules.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BusinessroleModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of businessrole modules
	* @param end the upper bound of the range of businessrole modules (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of businessrole modules
	*/
	public java.util.List<BusinessroleModule> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BusinessroleModule> orderByComparator);

	/**
	* Returns an ordered range of all the businessrole modules.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BusinessroleModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of businessrole modules
	* @param end the upper bound of the range of businessrole modules (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of businessrole modules
	*/
	public java.util.List<BusinessroleModule> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BusinessroleModule> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the businessrole modules from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of businessrole modules.
	*
	* @return the number of businessrole modules
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}