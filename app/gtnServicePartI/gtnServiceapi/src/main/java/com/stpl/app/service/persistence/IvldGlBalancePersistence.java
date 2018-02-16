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

import com.stpl.app.exception.NoSuchIvldGlBalanceException;
import com.stpl.app.model.IvldGlBalance;

/**
 * The persistence interface for the ivld gl balance service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.IvldGlBalancePersistenceImpl
 * @see IvldGlBalanceUtil
 * @generated
 */
@ProviderType
public interface IvldGlBalancePersistence extends BasePersistence<IvldGlBalance> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link IvldGlBalanceUtil} to access the ivld gl balance persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the ivld gl balance in the entity cache if it is enabled.
	*
	* @param ivldGlBalance the ivld gl balance
	*/
	public void cacheResult(IvldGlBalance ivldGlBalance);

	/**
	* Caches the ivld gl balances in the entity cache if it is enabled.
	*
	* @param ivldGlBalances the ivld gl balances
	*/
	public void cacheResult(java.util.List<IvldGlBalance> ivldGlBalances);

	/**
	* Creates a new ivld gl balance with the primary key. Does not add the ivld gl balance to the database.
	*
	* @param ivldGlBalanceSid the primary key for the new ivld gl balance
	* @return the new ivld gl balance
	*/
	public IvldGlBalance create(int ivldGlBalanceSid);

	/**
	* Removes the ivld gl balance with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldGlBalanceSid the primary key of the ivld gl balance
	* @return the ivld gl balance that was removed
	* @throws NoSuchIvldGlBalanceException if a ivld gl balance with the primary key could not be found
	*/
	public IvldGlBalance remove(int ivldGlBalanceSid)
		throws NoSuchIvldGlBalanceException;

	public IvldGlBalance updateImpl(IvldGlBalance ivldGlBalance);

	/**
	* Returns the ivld gl balance with the primary key or throws a {@link NoSuchIvldGlBalanceException} if it could not be found.
	*
	* @param ivldGlBalanceSid the primary key of the ivld gl balance
	* @return the ivld gl balance
	* @throws NoSuchIvldGlBalanceException if a ivld gl balance with the primary key could not be found
	*/
	public IvldGlBalance findByPrimaryKey(int ivldGlBalanceSid)
		throws NoSuchIvldGlBalanceException;

	/**
	* Returns the ivld gl balance with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ivldGlBalanceSid the primary key of the ivld gl balance
	* @return the ivld gl balance, or <code>null</code> if a ivld gl balance with the primary key could not be found
	*/
	public IvldGlBalance fetchByPrimaryKey(int ivldGlBalanceSid);

	@Override
	public java.util.Map<java.io.Serializable, IvldGlBalance> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the ivld gl balances.
	*
	* @return the ivld gl balances
	*/
	public java.util.List<IvldGlBalance> findAll();

	/**
	* Returns a range of all the ivld gl balances.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldGlBalanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld gl balances
	* @param end the upper bound of the range of ivld gl balances (not inclusive)
	* @return the range of ivld gl balances
	*/
	public java.util.List<IvldGlBalance> findAll(int start, int end);

	/**
	* Returns an ordered range of all the ivld gl balances.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldGlBalanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld gl balances
	* @param end the upper bound of the range of ivld gl balances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ivld gl balances
	*/
	public java.util.List<IvldGlBalance> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IvldGlBalance> orderByComparator);

	/**
	* Returns an ordered range of all the ivld gl balances.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldGlBalanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld gl balances
	* @param end the upper bound of the range of ivld gl balances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ivld gl balances
	*/
	public java.util.List<IvldGlBalance> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IvldGlBalance> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the ivld gl balances from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of ivld gl balances.
	*
	* @return the number of ivld gl balances
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}