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

import com.stpl.app.exception.NoSuchIvldDemandActualException;
import com.stpl.app.model.IvldDemandActual;

/**
 * The persistence interface for the ivld demand actual service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.IvldDemandActualPersistenceImpl
 * @see IvldDemandActualUtil
 * @generated
 */
@ProviderType
public interface IvldDemandActualPersistence extends BasePersistence<IvldDemandActual> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link IvldDemandActualUtil} to access the ivld demand actual persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the ivld demand actual in the entity cache if it is enabled.
	*
	* @param ivldDemandActual the ivld demand actual
	*/
	public void cacheResult(IvldDemandActual ivldDemandActual);

	/**
	* Caches the ivld demand actuals in the entity cache if it is enabled.
	*
	* @param ivldDemandActuals the ivld demand actuals
	*/
	public void cacheResult(java.util.List<IvldDemandActual> ivldDemandActuals);

	/**
	* Creates a new ivld demand actual with the primary key. Does not add the ivld demand actual to the database.
	*
	* @param ivldDemandActualSid the primary key for the new ivld demand actual
	* @return the new ivld demand actual
	*/
	public IvldDemandActual create(int ivldDemandActualSid);

	/**
	* Removes the ivld demand actual with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldDemandActualSid the primary key of the ivld demand actual
	* @return the ivld demand actual that was removed
	* @throws NoSuchIvldDemandActualException if a ivld demand actual with the primary key could not be found
	*/
	public IvldDemandActual remove(int ivldDemandActualSid)
		throws NoSuchIvldDemandActualException;

	public IvldDemandActual updateImpl(IvldDemandActual ivldDemandActual);

	/**
	* Returns the ivld demand actual with the primary key or throws a {@link NoSuchIvldDemandActualException} if it could not be found.
	*
	* @param ivldDemandActualSid the primary key of the ivld demand actual
	* @return the ivld demand actual
	* @throws NoSuchIvldDemandActualException if a ivld demand actual with the primary key could not be found
	*/
	public IvldDemandActual findByPrimaryKey(int ivldDemandActualSid)
		throws NoSuchIvldDemandActualException;

	/**
	* Returns the ivld demand actual with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ivldDemandActualSid the primary key of the ivld demand actual
	* @return the ivld demand actual, or <code>null</code> if a ivld demand actual with the primary key could not be found
	*/
	public IvldDemandActual fetchByPrimaryKey(int ivldDemandActualSid);

	@Override
	public java.util.Map<java.io.Serializable, IvldDemandActual> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the ivld demand actuals.
	*
	* @return the ivld demand actuals
	*/
	public java.util.List<IvldDemandActual> findAll();

	/**
	* Returns a range of all the ivld demand actuals.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldDemandActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld demand actuals
	* @param end the upper bound of the range of ivld demand actuals (not inclusive)
	* @return the range of ivld demand actuals
	*/
	public java.util.List<IvldDemandActual> findAll(int start, int end);

	/**
	* Returns an ordered range of all the ivld demand actuals.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldDemandActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld demand actuals
	* @param end the upper bound of the range of ivld demand actuals (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ivld demand actuals
	*/
	public java.util.List<IvldDemandActual> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IvldDemandActual> orderByComparator);

	/**
	* Returns an ordered range of all the ivld demand actuals.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldDemandActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld demand actuals
	* @param end the upper bound of the range of ivld demand actuals (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ivld demand actuals
	*/
	public java.util.List<IvldDemandActual> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IvldDemandActual> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the ivld demand actuals from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of ivld demand actuals.
	*
	* @return the number of ivld demand actuals
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}