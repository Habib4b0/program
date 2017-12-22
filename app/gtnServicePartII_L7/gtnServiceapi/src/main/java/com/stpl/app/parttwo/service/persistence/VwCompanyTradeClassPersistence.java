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

package com.stpl.app.parttwo.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.stpl.app.parttwo.exception.NoSuchVwCompanyTradeClassException;
import com.stpl.app.parttwo.model.VwCompanyTradeClass;

/**
 * The persistence interface for the vw company trade class service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.parttwo.service.persistence.impl.VwCompanyTradeClassPersistenceImpl
 * @see VwCompanyTradeClassUtil
 * @generated
 */
@ProviderType
public interface VwCompanyTradeClassPersistence extends BasePersistence<VwCompanyTradeClass> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link VwCompanyTradeClassUtil} to access the vw company trade class persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the vw company trade class in the entity cache if it is enabled.
	*
	* @param vwCompanyTradeClass the vw company trade class
	*/
	public void cacheResult(VwCompanyTradeClass vwCompanyTradeClass);

	/**
	* Caches the vw company trade classes in the entity cache if it is enabled.
	*
	* @param vwCompanyTradeClasses the vw company trade classes
	*/
	public void cacheResult(
		java.util.List<VwCompanyTradeClass> vwCompanyTradeClasses);

	/**
	* Creates a new vw company trade class with the primary key. Does not add the vw company trade class to the database.
	*
	* @param companyTradeClassSid the primary key for the new vw company trade class
	* @return the new vw company trade class
	*/
	public VwCompanyTradeClass create(int companyTradeClassSid);

	/**
	* Removes the vw company trade class with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param companyTradeClassSid the primary key of the vw company trade class
	* @return the vw company trade class that was removed
	* @throws NoSuchVwCompanyTradeClassException if a vw company trade class with the primary key could not be found
	*/
	public VwCompanyTradeClass remove(int companyTradeClassSid)
		throws NoSuchVwCompanyTradeClassException;

	public VwCompanyTradeClass updateImpl(
		VwCompanyTradeClass vwCompanyTradeClass);

	/**
	* Returns the vw company trade class with the primary key or throws a {@link NoSuchVwCompanyTradeClassException} if it could not be found.
	*
	* @param companyTradeClassSid the primary key of the vw company trade class
	* @return the vw company trade class
	* @throws NoSuchVwCompanyTradeClassException if a vw company trade class with the primary key could not be found
	*/
	public VwCompanyTradeClass findByPrimaryKey(int companyTradeClassSid)
		throws NoSuchVwCompanyTradeClassException;

	/**
	* Returns the vw company trade class with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param companyTradeClassSid the primary key of the vw company trade class
	* @return the vw company trade class, or <code>null</code> if a vw company trade class with the primary key could not be found
	*/
	public VwCompanyTradeClass fetchByPrimaryKey(int companyTradeClassSid);

	@Override
	public java.util.Map<java.io.Serializable, VwCompanyTradeClass> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the vw company trade classes.
	*
	* @return the vw company trade classes
	*/
	public java.util.List<VwCompanyTradeClass> findAll();

	/**
	* Returns a range of all the vw company trade classes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwCompanyTradeClassModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw company trade classes
	* @param end the upper bound of the range of vw company trade classes (not inclusive)
	* @return the range of vw company trade classes
	*/
	public java.util.List<VwCompanyTradeClass> findAll(int start, int end);

	/**
	* Returns an ordered range of all the vw company trade classes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwCompanyTradeClassModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw company trade classes
	* @param end the upper bound of the range of vw company trade classes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of vw company trade classes
	*/
	public java.util.List<VwCompanyTradeClass> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<VwCompanyTradeClass> orderByComparator);

	/**
	* Returns an ordered range of all the vw company trade classes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwCompanyTradeClassModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw company trade classes
	* @param end the upper bound of the range of vw company trade classes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of vw company trade classes
	*/
	public java.util.List<VwCompanyTradeClass> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<VwCompanyTradeClass> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the vw company trade classes from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of vw company trade classes.
	*
	* @return the number of vw company trade classes
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}