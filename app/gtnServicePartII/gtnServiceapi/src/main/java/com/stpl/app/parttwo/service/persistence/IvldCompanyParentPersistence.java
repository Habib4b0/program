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

import com.stpl.app.parttwo.exception.NoSuchIvldCompanyParentException;
import com.stpl.app.parttwo.model.IvldCompanyParent;

/**
 * The persistence interface for the ivld company parent service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.parttwo.service.persistence.impl.IvldCompanyParentPersistenceImpl
 * @see IvldCompanyParentUtil
 * @generated
 */
@ProviderType
public interface IvldCompanyParentPersistence extends BasePersistence<IvldCompanyParent> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link IvldCompanyParentUtil} to access the ivld company parent persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the ivld company parent in the entity cache if it is enabled.
	*
	* @param ivldCompanyParent the ivld company parent
	*/
	public void cacheResult(IvldCompanyParent ivldCompanyParent);

	/**
	* Caches the ivld company parents in the entity cache if it is enabled.
	*
	* @param ivldCompanyParents the ivld company parents
	*/
	public void cacheResult(
		java.util.List<IvldCompanyParent> ivldCompanyParents);

	/**
	* Creates a new ivld company parent with the primary key. Does not add the ivld company parent to the database.
	*
	* @param ivldCompanyParentSid the primary key for the new ivld company parent
	* @return the new ivld company parent
	*/
	public IvldCompanyParent create(int ivldCompanyParentSid);

	/**
	* Removes the ivld company parent with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldCompanyParentSid the primary key of the ivld company parent
	* @return the ivld company parent that was removed
	* @throws NoSuchIvldCompanyParentException if a ivld company parent with the primary key could not be found
	*/
	public IvldCompanyParent remove(int ivldCompanyParentSid)
		throws NoSuchIvldCompanyParentException;

	public IvldCompanyParent updateImpl(IvldCompanyParent ivldCompanyParent);

	/**
	* Returns the ivld company parent with the primary key or throws a {@link NoSuchIvldCompanyParentException} if it could not be found.
	*
	* @param ivldCompanyParentSid the primary key of the ivld company parent
	* @return the ivld company parent
	* @throws NoSuchIvldCompanyParentException if a ivld company parent with the primary key could not be found
	*/
	public IvldCompanyParent findByPrimaryKey(int ivldCompanyParentSid)
		throws NoSuchIvldCompanyParentException;

	/**
	* Returns the ivld company parent with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ivldCompanyParentSid the primary key of the ivld company parent
	* @return the ivld company parent, or <code>null</code> if a ivld company parent with the primary key could not be found
	*/
	public IvldCompanyParent fetchByPrimaryKey(int ivldCompanyParentSid);

	@Override
	public java.util.Map<java.io.Serializable, IvldCompanyParent> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the ivld company parents.
	*
	* @return the ivld company parents
	*/
	public java.util.List<IvldCompanyParent> findAll();

	/**
	* Returns a range of all the ivld company parents.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldCompanyParentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld company parents
	* @param end the upper bound of the range of ivld company parents (not inclusive)
	* @return the range of ivld company parents
	*/
	public java.util.List<IvldCompanyParent> findAll(int start, int end);

	/**
	* Returns an ordered range of all the ivld company parents.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldCompanyParentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld company parents
	* @param end the upper bound of the range of ivld company parents (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ivld company parents
	*/
	public java.util.List<IvldCompanyParent> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IvldCompanyParent> orderByComparator);

	/**
	* Returns an ordered range of all the ivld company parents.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldCompanyParentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld company parents
	* @param end the upper bound of the range of ivld company parents (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ivld company parents
	*/
	public java.util.List<IvldCompanyParent> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IvldCompanyParent> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the ivld company parents from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of ivld company parents.
	*
	* @return the number of ivld company parents
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}