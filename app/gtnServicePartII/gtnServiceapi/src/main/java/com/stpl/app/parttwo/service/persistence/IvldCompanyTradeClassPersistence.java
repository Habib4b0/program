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

import com.stpl.app.parttwo.exception.NoSuchIvldCompanyTradeClassException;
import com.stpl.app.parttwo.model.IvldCompanyTradeClass;

/**
 * The persistence interface for the ivld company trade class service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.parttwo.service.persistence.impl.IvldCompanyTradeClassPersistenceImpl
 * @see IvldCompanyTradeClassUtil
 * @generated
 */
@ProviderType
public interface IvldCompanyTradeClassPersistence extends BasePersistence<IvldCompanyTradeClass> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link IvldCompanyTradeClassUtil} to access the ivld company trade class persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the ivld company trade class in the entity cache if it is enabled.
	*
	* @param ivldCompanyTradeClass the ivld company trade class
	*/
	public void cacheResult(IvldCompanyTradeClass ivldCompanyTradeClass);

	/**
	* Caches the ivld company trade classes in the entity cache if it is enabled.
	*
	* @param ivldCompanyTradeClasses the ivld company trade classes
	*/
	public void cacheResult(
		java.util.List<IvldCompanyTradeClass> ivldCompanyTradeClasses);

	/**
	* Creates a new ivld company trade class with the primary key. Does not add the ivld company trade class to the database.
	*
	* @param ivldCompanyTradeClassSid the primary key for the new ivld company trade class
	* @return the new ivld company trade class
	*/
	public IvldCompanyTradeClass create(int ivldCompanyTradeClassSid);

	/**
	* Removes the ivld company trade class with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldCompanyTradeClassSid the primary key of the ivld company trade class
	* @return the ivld company trade class that was removed
	* @throws NoSuchIvldCompanyTradeClassException if a ivld company trade class with the primary key could not be found
	*/
	public IvldCompanyTradeClass remove(int ivldCompanyTradeClassSid)
		throws NoSuchIvldCompanyTradeClassException;

	public IvldCompanyTradeClass updateImpl(
		IvldCompanyTradeClass ivldCompanyTradeClass);

	/**
	* Returns the ivld company trade class with the primary key or throws a {@link NoSuchIvldCompanyTradeClassException} if it could not be found.
	*
	* @param ivldCompanyTradeClassSid the primary key of the ivld company trade class
	* @return the ivld company trade class
	* @throws NoSuchIvldCompanyTradeClassException if a ivld company trade class with the primary key could not be found
	*/
	public IvldCompanyTradeClass findByPrimaryKey(int ivldCompanyTradeClassSid)
		throws NoSuchIvldCompanyTradeClassException;

	/**
	* Returns the ivld company trade class with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ivldCompanyTradeClassSid the primary key of the ivld company trade class
	* @return the ivld company trade class, or <code>null</code> if a ivld company trade class with the primary key could not be found
	*/
	public IvldCompanyTradeClass fetchByPrimaryKey(int ivldCompanyTradeClassSid);

	@Override
	public java.util.Map<java.io.Serializable, IvldCompanyTradeClass> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the ivld company trade classes.
	*
	* @return the ivld company trade classes
	*/
	public java.util.List<IvldCompanyTradeClass> findAll();

	/**
	* Returns a range of all the ivld company trade classes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldCompanyTradeClassModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld company trade classes
	* @param end the upper bound of the range of ivld company trade classes (not inclusive)
	* @return the range of ivld company trade classes
	*/
	public java.util.List<IvldCompanyTradeClass> findAll(int start, int end);

	/**
	* Returns an ordered range of all the ivld company trade classes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldCompanyTradeClassModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld company trade classes
	* @param end the upper bound of the range of ivld company trade classes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ivld company trade classes
	*/
	public java.util.List<IvldCompanyTradeClass> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IvldCompanyTradeClass> orderByComparator);

	/**
	* Returns an ordered range of all the ivld company trade classes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldCompanyTradeClassModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld company trade classes
	* @param end the upper bound of the range of ivld company trade classes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ivld company trade classes
	*/
	public java.util.List<IvldCompanyTradeClass> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IvldCompanyTradeClass> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the ivld company trade classes from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of ivld company trade classes.
	*
	* @return the number of ivld company trade classes
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}