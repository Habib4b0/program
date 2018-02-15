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

import com.stpl.app.exception.NoSuchIvldMasterDataAttributeException;
import com.stpl.app.model.IvldMasterDataAttribute;

/**
 * The persistence interface for the ivld master data attribute service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.IvldMasterDataAttributePersistenceImpl
 * @see IvldMasterDataAttributeUtil
 * @generated
 */
@ProviderType
public interface IvldMasterDataAttributePersistence extends BasePersistence<IvldMasterDataAttribute> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link IvldMasterDataAttributeUtil} to access the ivld master data attribute persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the ivld master data attribute in the entity cache if it is enabled.
	*
	* @param ivldMasterDataAttribute the ivld master data attribute
	*/
	public void cacheResult(IvldMasterDataAttribute ivldMasterDataAttribute);

	/**
	* Caches the ivld master data attributes in the entity cache if it is enabled.
	*
	* @param ivldMasterDataAttributes the ivld master data attributes
	*/
	public void cacheResult(
		java.util.List<IvldMasterDataAttribute> ivldMasterDataAttributes);

	/**
	* Creates a new ivld master data attribute with the primary key. Does not add the ivld master data attribute to the database.
	*
	* @param ivldMasterDataAtbteSid the primary key for the new ivld master data attribute
	* @return the new ivld master data attribute
	*/
	public IvldMasterDataAttribute create(int ivldMasterDataAtbteSid);

	/**
	* Removes the ivld master data attribute with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldMasterDataAtbteSid the primary key of the ivld master data attribute
	* @return the ivld master data attribute that was removed
	* @throws NoSuchIvldMasterDataAttributeException if a ivld master data attribute with the primary key could not be found
	*/
	public IvldMasterDataAttribute remove(int ivldMasterDataAtbteSid)
		throws NoSuchIvldMasterDataAttributeException;

	public IvldMasterDataAttribute updateImpl(
		IvldMasterDataAttribute ivldMasterDataAttribute);

	/**
	* Returns the ivld master data attribute with the primary key or throws a {@link NoSuchIvldMasterDataAttributeException} if it could not be found.
	*
	* @param ivldMasterDataAtbteSid the primary key of the ivld master data attribute
	* @return the ivld master data attribute
	* @throws NoSuchIvldMasterDataAttributeException if a ivld master data attribute with the primary key could not be found
	*/
	public IvldMasterDataAttribute findByPrimaryKey(int ivldMasterDataAtbteSid)
		throws NoSuchIvldMasterDataAttributeException;

	/**
	* Returns the ivld master data attribute with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ivldMasterDataAtbteSid the primary key of the ivld master data attribute
	* @return the ivld master data attribute, or <code>null</code> if a ivld master data attribute with the primary key could not be found
	*/
	public IvldMasterDataAttribute fetchByPrimaryKey(int ivldMasterDataAtbteSid);

	@Override
	public java.util.Map<java.io.Serializable, IvldMasterDataAttribute> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the ivld master data attributes.
	*
	* @return the ivld master data attributes
	*/
	public java.util.List<IvldMasterDataAttribute> findAll();

	/**
	* Returns a range of all the ivld master data attributes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldMasterDataAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld master data attributes
	* @param end the upper bound of the range of ivld master data attributes (not inclusive)
	* @return the range of ivld master data attributes
	*/
	public java.util.List<IvldMasterDataAttribute> findAll(int start, int end);

	/**
	* Returns an ordered range of all the ivld master data attributes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldMasterDataAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld master data attributes
	* @param end the upper bound of the range of ivld master data attributes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ivld master data attributes
	*/
	public java.util.List<IvldMasterDataAttribute> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IvldMasterDataAttribute> orderByComparator);

	/**
	* Returns an ordered range of all the ivld master data attributes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldMasterDataAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld master data attributes
	* @param end the upper bound of the range of ivld master data attributes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ivld master data attributes
	*/
	public java.util.List<IvldMasterDataAttribute> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IvldMasterDataAttribute> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the ivld master data attributes from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of ivld master data attributes.
	*
	* @return the number of ivld master data attributes
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}