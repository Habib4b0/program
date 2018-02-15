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

import com.stpl.app.exception.NoSuchCompanyTradeClassException;
import com.stpl.app.model.CompanyTradeClass;

/**
 * The persistence interface for the company trade class service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.CompanyTradeClassPersistenceImpl
 * @see CompanyTradeClassUtil
 * @generated
 */
@ProviderType
public interface CompanyTradeClassPersistence extends BasePersistence<CompanyTradeClass> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CompanyTradeClassUtil} to access the company trade class persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the company trade class in the entity cache if it is enabled.
	*
	* @param companyTradeClass the company trade class
	*/
	public void cacheResult(CompanyTradeClass companyTradeClass);

	/**
	* Caches the company trade classes in the entity cache if it is enabled.
	*
	* @param companyTradeClasses the company trade classes
	*/
	public void cacheResult(
		java.util.List<CompanyTradeClass> companyTradeClasses);

	/**
	* Creates a new company trade class with the primary key. Does not add the company trade class to the database.
	*
	* @param companyTradeClassSid the primary key for the new company trade class
	* @return the new company trade class
	*/
	public CompanyTradeClass create(int companyTradeClassSid);

	/**
	* Removes the company trade class with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param companyTradeClassSid the primary key of the company trade class
	* @return the company trade class that was removed
	* @throws NoSuchCompanyTradeClassException if a company trade class with the primary key could not be found
	*/
	public CompanyTradeClass remove(int companyTradeClassSid)
		throws NoSuchCompanyTradeClassException;

	public CompanyTradeClass updateImpl(CompanyTradeClass companyTradeClass);

	/**
	* Returns the company trade class with the primary key or throws a {@link NoSuchCompanyTradeClassException} if it could not be found.
	*
	* @param companyTradeClassSid the primary key of the company trade class
	* @return the company trade class
	* @throws NoSuchCompanyTradeClassException if a company trade class with the primary key could not be found
	*/
	public CompanyTradeClass findByPrimaryKey(int companyTradeClassSid)
		throws NoSuchCompanyTradeClassException;

	/**
	* Returns the company trade class with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param companyTradeClassSid the primary key of the company trade class
	* @return the company trade class, or <code>null</code> if a company trade class with the primary key could not be found
	*/
	public CompanyTradeClass fetchByPrimaryKey(int companyTradeClassSid);

	@Override
	public java.util.Map<java.io.Serializable, CompanyTradeClass> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the company trade classes.
	*
	* @return the company trade classes
	*/
	public java.util.List<CompanyTradeClass> findAll();

	/**
	* Returns a range of all the company trade classes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyTradeClassModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of company trade classes
	* @param end the upper bound of the range of company trade classes (not inclusive)
	* @return the range of company trade classes
	*/
	public java.util.List<CompanyTradeClass> findAll(int start, int end);

	/**
	* Returns an ordered range of all the company trade classes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyTradeClassModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of company trade classes
	* @param end the upper bound of the range of company trade classes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of company trade classes
	*/
	public java.util.List<CompanyTradeClass> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyTradeClass> orderByComparator);

	/**
	* Returns an ordered range of all the company trade classes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyTradeClassModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of company trade classes
	* @param end the upper bound of the range of company trade classes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of company trade classes
	*/
	public java.util.List<CompanyTradeClass> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyTradeClass> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the company trade classes from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of company trade classes.
	*
	* @return the number of company trade classes
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}