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

import com.stpl.app.exception.NoSuchIvldAverageShelfLifeException;
import com.stpl.app.model.IvldAverageShelfLife;

/**
 * The persistence interface for the ivld average shelf life service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.IvldAverageShelfLifePersistenceImpl
 * @see IvldAverageShelfLifeUtil
 * @generated
 */
@ProviderType
public interface IvldAverageShelfLifePersistence extends BasePersistence<IvldAverageShelfLife> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link IvldAverageShelfLifeUtil} to access the ivld average shelf life persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the ivld average shelf life in the entity cache if it is enabled.
	*
	* @param ivldAverageShelfLife the ivld average shelf life
	*/
	public void cacheResult(IvldAverageShelfLife ivldAverageShelfLife);

	/**
	* Caches the ivld average shelf lifes in the entity cache if it is enabled.
	*
	* @param ivldAverageShelfLifes the ivld average shelf lifes
	*/
	public void cacheResult(
		java.util.List<IvldAverageShelfLife> ivldAverageShelfLifes);

	/**
	* Creates a new ivld average shelf life with the primary key. Does not add the ivld average shelf life to the database.
	*
	* @param ivldAverageShelfLifeSid the primary key for the new ivld average shelf life
	* @return the new ivld average shelf life
	*/
	public IvldAverageShelfLife create(int ivldAverageShelfLifeSid);

	/**
	* Removes the ivld average shelf life with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldAverageShelfLifeSid the primary key of the ivld average shelf life
	* @return the ivld average shelf life that was removed
	* @throws NoSuchIvldAverageShelfLifeException if a ivld average shelf life with the primary key could not be found
	*/
	public IvldAverageShelfLife remove(int ivldAverageShelfLifeSid)
		throws NoSuchIvldAverageShelfLifeException;

	public IvldAverageShelfLife updateImpl(
		IvldAverageShelfLife ivldAverageShelfLife);

	/**
	* Returns the ivld average shelf life with the primary key or throws a {@link NoSuchIvldAverageShelfLifeException} if it could not be found.
	*
	* @param ivldAverageShelfLifeSid the primary key of the ivld average shelf life
	* @return the ivld average shelf life
	* @throws NoSuchIvldAverageShelfLifeException if a ivld average shelf life with the primary key could not be found
	*/
	public IvldAverageShelfLife findByPrimaryKey(int ivldAverageShelfLifeSid)
		throws NoSuchIvldAverageShelfLifeException;

	/**
	* Returns the ivld average shelf life with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ivldAverageShelfLifeSid the primary key of the ivld average shelf life
	* @return the ivld average shelf life, or <code>null</code> if a ivld average shelf life with the primary key could not be found
	*/
	public IvldAverageShelfLife fetchByPrimaryKey(int ivldAverageShelfLifeSid);

	@Override
	public java.util.Map<java.io.Serializable, IvldAverageShelfLife> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the ivld average shelf lifes.
	*
	* @return the ivld average shelf lifes
	*/
	public java.util.List<IvldAverageShelfLife> findAll();

	/**
	* Returns a range of all the ivld average shelf lifes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldAverageShelfLifeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld average shelf lifes
	* @param end the upper bound of the range of ivld average shelf lifes (not inclusive)
	* @return the range of ivld average shelf lifes
	*/
	public java.util.List<IvldAverageShelfLife> findAll(int start, int end);

	/**
	* Returns an ordered range of all the ivld average shelf lifes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldAverageShelfLifeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld average shelf lifes
	* @param end the upper bound of the range of ivld average shelf lifes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ivld average shelf lifes
	*/
	public java.util.List<IvldAverageShelfLife> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IvldAverageShelfLife> orderByComparator);

	/**
	* Returns an ordered range of all the ivld average shelf lifes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldAverageShelfLifeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld average shelf lifes
	* @param end the upper bound of the range of ivld average shelf lifes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ivld average shelf lifes
	*/
	public java.util.List<IvldAverageShelfLife> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IvldAverageShelfLife> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the ivld average shelf lifes from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of ivld average shelf lifes.
	*
	* @return the number of ivld average shelf lifes
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}