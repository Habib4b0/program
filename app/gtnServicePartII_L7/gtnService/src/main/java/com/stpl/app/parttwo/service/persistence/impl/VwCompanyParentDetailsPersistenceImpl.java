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

package com.stpl.app.parttwo.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReflectionUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.spring.extender.service.ServiceReference;

import com.stpl.app.parttwo.exception.NoSuchVwCompanyParentDetailsException;
import com.stpl.app.parttwo.model.VwCompanyParentDetails;
import com.stpl.app.parttwo.model.impl.VwCompanyParentDetailsImpl;
import com.stpl.app.parttwo.model.impl.VwCompanyParentDetailsModelImpl;
import com.stpl.app.parttwo.service.persistence.VwCompanyParentDetailsPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the vw company parent details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see VwCompanyParentDetailsPersistence
 * @see com.stpl.app.parttwo.service.persistence.VwCompanyParentDetailsUtil
 * @generated
 */
@ProviderType
public class VwCompanyParentDetailsPersistenceImpl extends BasePersistenceImpl<VwCompanyParentDetails>
	implements VwCompanyParentDetailsPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link VwCompanyParentDetailsUtil} to access the vw company parent details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = VwCompanyParentDetailsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(VwCompanyParentDetailsModelImpl.ENTITY_CACHE_ENABLED,
			VwCompanyParentDetailsModelImpl.FINDER_CACHE_ENABLED,
			VwCompanyParentDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(VwCompanyParentDetailsModelImpl.ENTITY_CACHE_ENABLED,
			VwCompanyParentDetailsModelImpl.FINDER_CACHE_ENABLED,
			VwCompanyParentDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(VwCompanyParentDetailsModelImpl.ENTITY_CACHE_ENABLED,
			VwCompanyParentDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public VwCompanyParentDetailsPersistenceImpl() {
		setModelClass(VwCompanyParentDetails.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("parentcompanyId", "PARENT_COMPANY_ID");
			dbColumnNames.put("priorParentcompanyId", "PRIOR_PARENT_COMPANY_ID");
			dbColumnNames.put("companyId", "COMPANY_ID");
			dbColumnNames.put("lastUpdatedDate", "LAST_UPDATED_DATE");
			dbColumnNames.put("parentEndDate", "PARENT_END_DATE");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("priorParentStartDate", "PRIOR_PARENT_START_DATE");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("companyParentDetailsSid",
				"COMPANY_PARENT_DETAILS_SID");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("addChgDelIndicator", "ADD_CHG_DEL_INDICATOR");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("parentStartDate", "PARENT_START_DATE");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the vw company parent details in the entity cache if it is enabled.
	 *
	 * @param vwCompanyParentDetails the vw company parent details
	 */
	@Override
	public void cacheResult(VwCompanyParentDetails vwCompanyParentDetails) {
		entityCache.putResult(VwCompanyParentDetailsModelImpl.ENTITY_CACHE_ENABLED,
			VwCompanyParentDetailsImpl.class,
			vwCompanyParentDetails.getPrimaryKey(), vwCompanyParentDetails);

		vwCompanyParentDetails.resetOriginalValues();
	}

	/**
	 * Caches the vw company parent detailses in the entity cache if it is enabled.
	 *
	 * @param vwCompanyParentDetailses the vw company parent detailses
	 */
	@Override
	public void cacheResult(
		List<VwCompanyParentDetails> vwCompanyParentDetailses) {
		for (VwCompanyParentDetails vwCompanyParentDetails : vwCompanyParentDetailses) {
			if (entityCache.getResult(
						VwCompanyParentDetailsModelImpl.ENTITY_CACHE_ENABLED,
						VwCompanyParentDetailsImpl.class,
						vwCompanyParentDetails.getPrimaryKey()) == null) {
				cacheResult(vwCompanyParentDetails);
			}
			else {
				vwCompanyParentDetails.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all vw company parent detailses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(VwCompanyParentDetailsImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the vw company parent details.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(VwCompanyParentDetails vwCompanyParentDetails) {
		entityCache.removeResult(VwCompanyParentDetailsModelImpl.ENTITY_CACHE_ENABLED,
			VwCompanyParentDetailsImpl.class,
			vwCompanyParentDetails.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<VwCompanyParentDetails> vwCompanyParentDetailses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (VwCompanyParentDetails vwCompanyParentDetails : vwCompanyParentDetailses) {
			entityCache.removeResult(VwCompanyParentDetailsModelImpl.ENTITY_CACHE_ENABLED,
				VwCompanyParentDetailsImpl.class,
				vwCompanyParentDetails.getPrimaryKey());
		}
	}

	/**
	 * Creates a new vw company parent details with the primary key. Does not add the vw company parent details to the database.
	 *
	 * @param companyParentDetailsSid the primary key for the new vw company parent details
	 * @return the new vw company parent details
	 */
	@Override
	public VwCompanyParentDetails create(int companyParentDetailsSid) {
		VwCompanyParentDetails vwCompanyParentDetails = new VwCompanyParentDetailsImpl();

		vwCompanyParentDetails.setNew(true);
		vwCompanyParentDetails.setPrimaryKey(companyParentDetailsSid);

		vwCompanyParentDetails.setCompanyId(companyProvider.getCompanyId());

		return vwCompanyParentDetails;
	}

	/**
	 * Removes the vw company parent details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param companyParentDetailsSid the primary key of the vw company parent details
	 * @return the vw company parent details that was removed
	 * @throws NoSuchVwCompanyParentDetailsException if a vw company parent details with the primary key could not be found
	 */
	@Override
	public VwCompanyParentDetails remove(int companyParentDetailsSid)
		throws NoSuchVwCompanyParentDetailsException {
		return remove((Serializable)companyParentDetailsSid);
	}

	/**
	 * Removes the vw company parent details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the vw company parent details
	 * @return the vw company parent details that was removed
	 * @throws NoSuchVwCompanyParentDetailsException if a vw company parent details with the primary key could not be found
	 */
	@Override
	public VwCompanyParentDetails remove(Serializable primaryKey)
		throws NoSuchVwCompanyParentDetailsException {
		Session session = null;

		try {
			session = openSession();

			VwCompanyParentDetails vwCompanyParentDetails = (VwCompanyParentDetails)session.get(VwCompanyParentDetailsImpl.class,
					primaryKey);

			if (vwCompanyParentDetails == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchVwCompanyParentDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(vwCompanyParentDetails);
		}
		catch (NoSuchVwCompanyParentDetailsException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected VwCompanyParentDetails removeImpl(
		VwCompanyParentDetails vwCompanyParentDetails) {
		vwCompanyParentDetails = toUnwrappedModel(vwCompanyParentDetails);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(vwCompanyParentDetails)) {
				vwCompanyParentDetails = (VwCompanyParentDetails)session.get(VwCompanyParentDetailsImpl.class,
						vwCompanyParentDetails.getPrimaryKeyObj());
			}

			if (vwCompanyParentDetails != null) {
				session.delete(vwCompanyParentDetails);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (vwCompanyParentDetails != null) {
			clearCache(vwCompanyParentDetails);
		}

		return vwCompanyParentDetails;
	}

	@Override
	public VwCompanyParentDetails updateImpl(
		VwCompanyParentDetails vwCompanyParentDetails) {
		vwCompanyParentDetails = toUnwrappedModel(vwCompanyParentDetails);

		boolean isNew = vwCompanyParentDetails.isNew();

		Session session = null;

		try {
			session = openSession();

			if (vwCompanyParentDetails.isNew()) {
				session.save(vwCompanyParentDetails);

				vwCompanyParentDetails.setNew(false);
			}
			else {
				vwCompanyParentDetails = (VwCompanyParentDetails)session.merge(vwCompanyParentDetails);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew) {
			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		entityCache.putResult(VwCompanyParentDetailsModelImpl.ENTITY_CACHE_ENABLED,
			VwCompanyParentDetailsImpl.class,
			vwCompanyParentDetails.getPrimaryKey(), vwCompanyParentDetails,
			false);

		vwCompanyParentDetails.resetOriginalValues();

		return vwCompanyParentDetails;
	}

	protected VwCompanyParentDetails toUnwrappedModel(
		VwCompanyParentDetails vwCompanyParentDetails) {
		if (vwCompanyParentDetails instanceof VwCompanyParentDetailsImpl) {
			return vwCompanyParentDetails;
		}

		VwCompanyParentDetailsImpl vwCompanyParentDetailsImpl = new VwCompanyParentDetailsImpl();

		vwCompanyParentDetailsImpl.setNew(vwCompanyParentDetails.isNew());
		vwCompanyParentDetailsImpl.setPrimaryKey(vwCompanyParentDetails.getPrimaryKey());

		vwCompanyParentDetailsImpl.setParentcompanyId(vwCompanyParentDetails.getParentcompanyId());
		vwCompanyParentDetailsImpl.setPriorParentcompanyId(vwCompanyParentDetails.getPriorParentcompanyId());
		vwCompanyParentDetailsImpl.setCompanyId(vwCompanyParentDetails.getCompanyId());
		vwCompanyParentDetailsImpl.setLastUpdatedDate(vwCompanyParentDetails.getLastUpdatedDate());
		vwCompanyParentDetailsImpl.setParentEndDate(vwCompanyParentDetails.getParentEndDate());
		vwCompanyParentDetailsImpl.setModifiedDate(vwCompanyParentDetails.getModifiedDate());
		vwCompanyParentDetailsImpl.setPriorParentStartDate(vwCompanyParentDetails.getPriorParentStartDate());
		vwCompanyParentDetailsImpl.setSource(vwCompanyParentDetails.getSource());
		vwCompanyParentDetailsImpl.setCreatedBy(vwCompanyParentDetails.getCreatedBy());
		vwCompanyParentDetailsImpl.setCreatedDate(vwCompanyParentDetails.getCreatedDate());
		vwCompanyParentDetailsImpl.setCompanyParentDetailsSid(vwCompanyParentDetails.getCompanyParentDetailsSid());
		vwCompanyParentDetailsImpl.setBatchId(vwCompanyParentDetails.getBatchId());
		vwCompanyParentDetailsImpl.setAddChgDelIndicator(vwCompanyParentDetails.getAddChgDelIndicator());
		vwCompanyParentDetailsImpl.setModifiedBy(vwCompanyParentDetails.getModifiedBy());
		vwCompanyParentDetailsImpl.setParentStartDate(vwCompanyParentDetails.getParentStartDate());

		return vwCompanyParentDetailsImpl;
	}

	/**
	 * Returns the vw company parent details with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the vw company parent details
	 * @return the vw company parent details
	 * @throws NoSuchVwCompanyParentDetailsException if a vw company parent details with the primary key could not be found
	 */
	@Override
	public VwCompanyParentDetails findByPrimaryKey(Serializable primaryKey)
		throws NoSuchVwCompanyParentDetailsException {
		VwCompanyParentDetails vwCompanyParentDetails = fetchByPrimaryKey(primaryKey);

		if (vwCompanyParentDetails == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchVwCompanyParentDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return vwCompanyParentDetails;
	}

	/**
	 * Returns the vw company parent details with the primary key or throws a {@link NoSuchVwCompanyParentDetailsException} if it could not be found.
	 *
	 * @param companyParentDetailsSid the primary key of the vw company parent details
	 * @return the vw company parent details
	 * @throws NoSuchVwCompanyParentDetailsException if a vw company parent details with the primary key could not be found
	 */
	@Override
	public VwCompanyParentDetails findByPrimaryKey(int companyParentDetailsSid)
		throws NoSuchVwCompanyParentDetailsException {
		return findByPrimaryKey((Serializable)companyParentDetailsSid);
	}

	/**
	 * Returns the vw company parent details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the vw company parent details
	 * @return the vw company parent details, or <code>null</code> if a vw company parent details with the primary key could not be found
	 */
	@Override
	public VwCompanyParentDetails fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(VwCompanyParentDetailsModelImpl.ENTITY_CACHE_ENABLED,
				VwCompanyParentDetailsImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		VwCompanyParentDetails vwCompanyParentDetails = (VwCompanyParentDetails)serializable;

		if (vwCompanyParentDetails == null) {
			Session session = null;

			try {
				session = openSession();

				vwCompanyParentDetails = (VwCompanyParentDetails)session.get(VwCompanyParentDetailsImpl.class,
						primaryKey);

				if (vwCompanyParentDetails != null) {
					cacheResult(vwCompanyParentDetails);
				}
				else {
					entityCache.putResult(VwCompanyParentDetailsModelImpl.ENTITY_CACHE_ENABLED,
						VwCompanyParentDetailsImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(VwCompanyParentDetailsModelImpl.ENTITY_CACHE_ENABLED,
					VwCompanyParentDetailsImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return vwCompanyParentDetails;
	}

	/**
	 * Returns the vw company parent details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param companyParentDetailsSid the primary key of the vw company parent details
	 * @return the vw company parent details, or <code>null</code> if a vw company parent details with the primary key could not be found
	 */
	@Override
	public VwCompanyParentDetails fetchByPrimaryKey(int companyParentDetailsSid) {
		return fetchByPrimaryKey((Serializable)companyParentDetailsSid);
	}

	@Override
	public Map<Serializable, VwCompanyParentDetails> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, VwCompanyParentDetails> map = new HashMap<Serializable, VwCompanyParentDetails>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			VwCompanyParentDetails vwCompanyParentDetails = fetchByPrimaryKey(primaryKey);

			if (vwCompanyParentDetails != null) {
				map.put(primaryKey, vwCompanyParentDetails);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(VwCompanyParentDetailsModelImpl.ENTITY_CACHE_ENABLED,
					VwCompanyParentDetailsImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (VwCompanyParentDetails)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_VWCOMPANYPARENTDETAILS_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append((int)primaryKey);

			query.append(StringPool.COMMA);
		}

		query.setIndex(query.index() - 1);

		query.append(StringPool.CLOSE_PARENTHESIS);

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (VwCompanyParentDetails vwCompanyParentDetails : (List<VwCompanyParentDetails>)q.list()) {
				map.put(vwCompanyParentDetails.getPrimaryKeyObj(),
					vwCompanyParentDetails);

				cacheResult(vwCompanyParentDetails);

				uncachedPrimaryKeys.remove(vwCompanyParentDetails.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(VwCompanyParentDetailsModelImpl.ENTITY_CACHE_ENABLED,
					VwCompanyParentDetailsImpl.class, primaryKey, nullModel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the vw company parent detailses.
	 *
	 * @return the vw company parent detailses
	 */
	@Override
	public List<VwCompanyParentDetails> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vw company parent detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwCompanyParentDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of vw company parent detailses
	 * @param end the upper bound of the range of vw company parent detailses (not inclusive)
	 * @return the range of vw company parent detailses
	 */
	@Override
	public List<VwCompanyParentDetails> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the vw company parent detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwCompanyParentDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of vw company parent detailses
	 * @param end the upper bound of the range of vw company parent detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of vw company parent detailses
	 */
	@Override
	public List<VwCompanyParentDetails> findAll(int start, int end,
		OrderByComparator<VwCompanyParentDetails> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the vw company parent detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwCompanyParentDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of vw company parent detailses
	 * @param end the upper bound of the range of vw company parent detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of vw company parent detailses
	 */
	@Override
	public List<VwCompanyParentDetails> findAll(int start, int end,
		OrderByComparator<VwCompanyParentDetails> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<VwCompanyParentDetails> list = null;

		if (retrieveFromCache) {
			list = (List<VwCompanyParentDetails>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_VWCOMPANYPARENTDETAILS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_VWCOMPANYPARENTDETAILS;

				if (pagination) {
					sql = sql.concat(VwCompanyParentDetailsModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<VwCompanyParentDetails>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<VwCompanyParentDetails>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the vw company parent detailses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (VwCompanyParentDetails vwCompanyParentDetails : findAll()) {
			remove(vwCompanyParentDetails);
		}
	}

	/**
	 * Returns the number of vw company parent detailses.
	 *
	 * @return the number of vw company parent detailses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_VWCOMPANYPARENTDETAILS);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY,
					count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return VwCompanyParentDetailsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the vw company parent details persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(VwCompanyParentDetailsImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_VWCOMPANYPARENTDETAILS = "SELECT vwCompanyParentDetails FROM VwCompanyParentDetails vwCompanyParentDetails";
	private static final String _SQL_SELECT_VWCOMPANYPARENTDETAILS_WHERE_PKS_IN = "SELECT vwCompanyParentDetails FROM VwCompanyParentDetails vwCompanyParentDetails WHERE COMPANY_PARENT_DETAILS_SID IN (";
	private static final String _SQL_COUNT_VWCOMPANYPARENTDETAILS = "SELECT COUNT(vwCompanyParentDetails) FROM VwCompanyParentDetails vwCompanyParentDetails";
	private static final String _ORDER_BY_ENTITY_ALIAS = "vwCompanyParentDetails.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VwCompanyParentDetails exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(VwCompanyParentDetailsPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"parentcompanyId", "priorParentcompanyId", "companyId",
				"lastUpdatedDate", "parentEndDate", "modifiedDate",
				"priorParentStartDate", "source", "createdBy", "createdDate",
				"companyParentDetailsSid", "batchId", "addChgDelIndicator",
				"modifiedBy", "parentStartDate"
			});
}