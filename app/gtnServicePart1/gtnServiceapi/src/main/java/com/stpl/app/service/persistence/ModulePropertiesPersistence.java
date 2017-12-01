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

import com.stpl.app.exception.NoSuchModulePropertiesException;
import com.stpl.app.model.ModuleProperties;

/**
 * The persistence interface for the module properties service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.ModulePropertiesPersistenceImpl
 * @see ModulePropertiesUtil
 * @generated
 */
@ProviderType
public interface ModulePropertiesPersistence extends BasePersistence<ModuleProperties> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ModulePropertiesUtil} to access the module properties persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the module properties in the entity cache if it is enabled.
	*
	* @param moduleProperties the module properties
	*/
	public void cacheResult(ModuleProperties moduleProperties);

	/**
	* Caches the module propertieses in the entity cache if it is enabled.
	*
	* @param modulePropertieses the module propertieses
	*/
	public void cacheResult(java.util.List<ModuleProperties> modulePropertieses);

	/**
	* Creates a new module properties with the primary key. Does not add the module properties to the database.
	*
	* @param modulePropertySid the primary key for the new module properties
	* @return the new module properties
	*/
	public ModuleProperties create(int modulePropertySid);

	/**
	* Removes the module properties with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param modulePropertySid the primary key of the module properties
	* @return the module properties that was removed
	* @throws NoSuchModulePropertiesException if a module properties with the primary key could not be found
	*/
	public ModuleProperties remove(int modulePropertySid)
		throws NoSuchModulePropertiesException;

	public ModuleProperties updateImpl(ModuleProperties moduleProperties);

	/**
	* Returns the module properties with the primary key or throws a {@link NoSuchModulePropertiesException} if it could not be found.
	*
	* @param modulePropertySid the primary key of the module properties
	* @return the module properties
	* @throws NoSuchModulePropertiesException if a module properties with the primary key could not be found
	*/
	public ModuleProperties findByPrimaryKey(int modulePropertySid)
		throws NoSuchModulePropertiesException;

	/**
	* Returns the module properties with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param modulePropertySid the primary key of the module properties
	* @return the module properties, or <code>null</code> if a module properties with the primary key could not be found
	*/
	public ModuleProperties fetchByPrimaryKey(int modulePropertySid);

	@Override
	public java.util.Map<java.io.Serializable, ModuleProperties> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the module propertieses.
	*
	* @return the module propertieses
	*/
	public java.util.List<ModuleProperties> findAll();

	/**
	* Returns a range of all the module propertieses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModulePropertiesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of module propertieses
	* @param end the upper bound of the range of module propertieses (not inclusive)
	* @return the range of module propertieses
	*/
	public java.util.List<ModuleProperties> findAll(int start, int end);

	/**
	* Returns an ordered range of all the module propertieses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModulePropertiesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of module propertieses
	* @param end the upper bound of the range of module propertieses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of module propertieses
	*/
	public java.util.List<ModuleProperties> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ModuleProperties> orderByComparator);

	/**
	* Returns an ordered range of all the module propertieses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModulePropertiesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of module propertieses
	* @param end the upper bound of the range of module propertieses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of module propertieses
	*/
	public java.util.List<ModuleProperties> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ModuleProperties> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the module propertieses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of module propertieses.
	*
	* @return the number of module propertieses
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}