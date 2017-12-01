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

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.stpl.app.model.AdditionalNotes;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the additional notes service. This utility wraps {@link com.stpl.app.service.persistence.impl.AdditionalNotesPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see AdditionalNotesPersistence
 * @see com.stpl.app.service.persistence.impl.AdditionalNotesPersistenceImpl
 * @generated
 */
@ProviderType
public class AdditionalNotesUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(AdditionalNotes additionalNotes) {
		getPersistence().clearCache(additionalNotes);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<AdditionalNotes> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AdditionalNotes> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AdditionalNotes> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<AdditionalNotes> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static AdditionalNotes update(AdditionalNotes additionalNotes) {
		return getPersistence().update(additionalNotes);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static AdditionalNotes update(AdditionalNotes additionalNotes,
		ServiceContext serviceContext) {
		return getPersistence().update(additionalNotes, serviceContext);
	}

	/**
	* Caches the additional notes in the entity cache if it is enabled.
	*
	* @param additionalNotes the additional notes
	*/
	public static void cacheResult(AdditionalNotes additionalNotes) {
		getPersistence().cacheResult(additionalNotes);
	}

	/**
	* Caches the additional noteses in the entity cache if it is enabled.
	*
	* @param additionalNoteses the additional noteses
	*/
	public static void cacheResult(List<AdditionalNotes> additionalNoteses) {
		getPersistence().cacheResult(additionalNoteses);
	}

	/**
	* Creates a new additional notes with the primary key. Does not add the additional notes to the database.
	*
	* @param additionalNotesId the primary key for the new additional notes
	* @return the new additional notes
	*/
	public static AdditionalNotes create(int additionalNotesId) {
		return getPersistence().create(additionalNotesId);
	}

	/**
	* Removes the additional notes with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param additionalNotesId the primary key of the additional notes
	* @return the additional notes that was removed
	* @throws NoSuchAdditionalNotesException if a additional notes with the primary key could not be found
	*/
	public static AdditionalNotes remove(int additionalNotesId)
		throws com.stpl.app.exception.NoSuchAdditionalNotesException {
		return getPersistence().remove(additionalNotesId);
	}

	public static AdditionalNotes updateImpl(AdditionalNotes additionalNotes) {
		return getPersistence().updateImpl(additionalNotes);
	}

	/**
	* Returns the additional notes with the primary key or throws a {@link NoSuchAdditionalNotesException} if it could not be found.
	*
	* @param additionalNotesId the primary key of the additional notes
	* @return the additional notes
	* @throws NoSuchAdditionalNotesException if a additional notes with the primary key could not be found
	*/
	public static AdditionalNotes findByPrimaryKey(int additionalNotesId)
		throws com.stpl.app.exception.NoSuchAdditionalNotesException {
		return getPersistence().findByPrimaryKey(additionalNotesId);
	}

	/**
	* Returns the additional notes with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param additionalNotesId the primary key of the additional notes
	* @return the additional notes, or <code>null</code> if a additional notes with the primary key could not be found
	*/
	public static AdditionalNotes fetchByPrimaryKey(int additionalNotesId) {
		return getPersistence().fetchByPrimaryKey(additionalNotesId);
	}

	public static java.util.Map<java.io.Serializable, AdditionalNotes> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the additional noteses.
	*
	* @return the additional noteses
	*/
	public static List<AdditionalNotes> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the additional noteses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AdditionalNotesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of additional noteses
	* @param end the upper bound of the range of additional noteses (not inclusive)
	* @return the range of additional noteses
	*/
	public static List<AdditionalNotes> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the additional noteses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AdditionalNotesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of additional noteses
	* @param end the upper bound of the range of additional noteses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of additional noteses
	*/
	public static List<AdditionalNotes> findAll(int start, int end,
		OrderByComparator<AdditionalNotes> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the additional noteses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AdditionalNotesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of additional noteses
	* @param end the upper bound of the range of additional noteses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of additional noteses
	*/
	public static List<AdditionalNotes> findAll(int start, int end,
		OrderByComparator<AdditionalNotes> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the additional noteses from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of additional noteses.
	*
	* @return the number of additional noteses
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static AdditionalNotesPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<AdditionalNotesPersistence, AdditionalNotesPersistence> _serviceTracker =
		ServiceTrackerFactory.open(AdditionalNotesPersistence.class);
}