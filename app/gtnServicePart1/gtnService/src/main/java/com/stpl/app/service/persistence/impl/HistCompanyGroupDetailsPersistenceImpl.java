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

package com.stpl.app.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReflectionUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.spring.extender.service.ServiceReference;

import com.stpl.app.exception.NoSuchHistCompanyGroupDetailsException;
import com.stpl.app.model.HistCompanyGroupDetails;
import com.stpl.app.model.impl.HistCompanyGroupDetailsImpl;
import com.stpl.app.model.impl.HistCompanyGroupDetailsModelImpl;
import com.stpl.app.service.persistence.HistCompanyGroupDetailsPK;
import com.stpl.app.service.persistence.HistCompanyGroupDetailsPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the hist company group details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see HistCompanyGroupDetailsPersistence
 * @see com.stpl.app.service.persistence.HistCompanyGroupDetailsUtil
 * @generated
 */
@ProviderType
public class HistCompanyGroupDetailsPersistenceImpl extends BasePersistenceImpl<HistCompanyGroupDetails>
	implements HistCompanyGroupDetailsPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link HistCompanyGroupDetailsUtil} to access the hist company group details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = HistCompanyGroupDetailsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(HistCompanyGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
			HistCompanyGroupDetailsModelImpl.FINDER_CACHE_ENABLED,
			HistCompanyGroupDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(HistCompanyGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
			HistCompanyGroupDetailsModelImpl.FINDER_CACHE_ENABLED,
			HistCompanyGroupDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HistCompanyGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
			HistCompanyGroupDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public HistCompanyGroupDetailsPersistenceImpl() {
		setModelClass(HistCompanyGroupDetails.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("actionDate", "ACTION_DATE");
			dbColumnNames.put("companyParentDetailsSid",
				"COMPANY_PARENT_DETAILS_SID");
			dbColumnNames.put("companyTradeclassSid", "COMPANY_TRADECLASS_SID");
			dbColumnNames.put("actionFlag", "ACTION_FLAG");
			dbColumnNames.put("companyGroupSid", "COMPANY_GROUP_SID");
			dbColumnNames.put("versionNo", "VERSION_NO");
			dbColumnNames.put("companyGroupDetailsSid",
				"COMPANY_GROUP_DETAILS_SID");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("companyMasterSid", "COMPANY_MASTER_SID");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the hist company group details in the entity cache if it is enabled.
	 *
	 * @param histCompanyGroupDetails the hist company group details
	 */
	@Override
	public void cacheResult(HistCompanyGroupDetails histCompanyGroupDetails) {
		entityCache.putResult(HistCompanyGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
			HistCompanyGroupDetailsImpl.class,
			histCompanyGroupDetails.getPrimaryKey(), histCompanyGroupDetails);

		histCompanyGroupDetails.resetOriginalValues();
	}

	/**
	 * Caches the hist company group detailses in the entity cache if it is enabled.
	 *
	 * @param histCompanyGroupDetailses the hist company group detailses
	 */
	@Override
	public void cacheResult(
		List<HistCompanyGroupDetails> histCompanyGroupDetailses) {
		for (HistCompanyGroupDetails histCompanyGroupDetails : histCompanyGroupDetailses) {
			if (entityCache.getResult(
						HistCompanyGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
						HistCompanyGroupDetailsImpl.class,
						histCompanyGroupDetails.getPrimaryKey()) == null) {
				cacheResult(histCompanyGroupDetails);
			}
			else {
				histCompanyGroupDetails.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all hist company group detailses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(HistCompanyGroupDetailsImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the hist company group details.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(HistCompanyGroupDetails histCompanyGroupDetails) {
		entityCache.removeResult(HistCompanyGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
			HistCompanyGroupDetailsImpl.class,
			histCompanyGroupDetails.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<HistCompanyGroupDetails> histCompanyGroupDetailses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (HistCompanyGroupDetails histCompanyGroupDetails : histCompanyGroupDetailses) {
			entityCache.removeResult(HistCompanyGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
				HistCompanyGroupDetailsImpl.class,
				histCompanyGroupDetails.getPrimaryKey());
		}
	}

	/**
	 * Creates a new hist company group details with the primary key. Does not add the hist company group details to the database.
	 *
	 * @param histCompanyGroupDetailsPK the primary key for the new hist company group details
	 * @return the new hist company group details
	 */
	@Override
	public HistCompanyGroupDetails create(
		HistCompanyGroupDetailsPK histCompanyGroupDetailsPK) {
		HistCompanyGroupDetails histCompanyGroupDetails = new HistCompanyGroupDetailsImpl();

		histCompanyGroupDetails.setNew(true);
		histCompanyGroupDetails.setPrimaryKey(histCompanyGroupDetailsPK);

		return histCompanyGroupDetails;
	}

	/**
	 * Removes the hist company group details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param histCompanyGroupDetailsPK the primary key of the hist company group details
	 * @return the hist company group details that was removed
	 * @throws NoSuchHistCompanyGroupDetailsException if a hist company group details with the primary key could not be found
	 */
	@Override
	public HistCompanyGroupDetails remove(
		HistCompanyGroupDetailsPK histCompanyGroupDetailsPK)
		throws NoSuchHistCompanyGroupDetailsException {
		return remove((Serializable)histCompanyGroupDetailsPK);
	}

	/**
	 * Removes the hist company group details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the hist company group details
	 * @return the hist company group details that was removed
	 * @throws NoSuchHistCompanyGroupDetailsException if a hist company group details with the primary key could not be found
	 */
	@Override
	public HistCompanyGroupDetails remove(Serializable primaryKey)
		throws NoSuchHistCompanyGroupDetailsException {
		Session session = null;

		try {
			session = openSession();

			HistCompanyGroupDetails histCompanyGroupDetails = (HistCompanyGroupDetails)session.get(HistCompanyGroupDetailsImpl.class,
					primaryKey);

			if (histCompanyGroupDetails == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchHistCompanyGroupDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(histCompanyGroupDetails);
		}
		catch (NoSuchHistCompanyGroupDetailsException nsee) {
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
	protected HistCompanyGroupDetails removeImpl(
		HistCompanyGroupDetails histCompanyGroupDetails) {
		histCompanyGroupDetails = toUnwrappedModel(histCompanyGroupDetails);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(histCompanyGroupDetails)) {
				histCompanyGroupDetails = (HistCompanyGroupDetails)session.get(HistCompanyGroupDetailsImpl.class,
						histCompanyGroupDetails.getPrimaryKeyObj());
			}

			if (histCompanyGroupDetails != null) {
				session.delete(histCompanyGroupDetails);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (histCompanyGroupDetails != null) {
			clearCache(histCompanyGroupDetails);
		}

		return histCompanyGroupDetails;
	}

	@Override
	public HistCompanyGroupDetails updateImpl(
		HistCompanyGroupDetails histCompanyGroupDetails) {
		histCompanyGroupDetails = toUnwrappedModel(histCompanyGroupDetails);

		boolean isNew = histCompanyGroupDetails.isNew();

		Session session = null;

		try {
			session = openSession();

			if (histCompanyGroupDetails.isNew()) {
				session.save(histCompanyGroupDetails);

				histCompanyGroupDetails.setNew(false);
			}
			else {
				histCompanyGroupDetails = (HistCompanyGroupDetails)session.merge(histCompanyGroupDetails);
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

		entityCache.putResult(HistCompanyGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
			HistCompanyGroupDetailsImpl.class,
			histCompanyGroupDetails.getPrimaryKey(), histCompanyGroupDetails,
			false);

		histCompanyGroupDetails.resetOriginalValues();

		return histCompanyGroupDetails;
	}

	protected HistCompanyGroupDetails toUnwrappedModel(
		HistCompanyGroupDetails histCompanyGroupDetails) {
		if (histCompanyGroupDetails instanceof HistCompanyGroupDetailsImpl) {
			return histCompanyGroupDetails;
		}

		HistCompanyGroupDetailsImpl histCompanyGroupDetailsImpl = new HistCompanyGroupDetailsImpl();

		histCompanyGroupDetailsImpl.setNew(histCompanyGroupDetails.isNew());
		histCompanyGroupDetailsImpl.setPrimaryKey(histCompanyGroupDetails.getPrimaryKey());

		histCompanyGroupDetailsImpl.setCreatedDate(histCompanyGroupDetails.getCreatedDate());
		histCompanyGroupDetailsImpl.setCreatedBy(histCompanyGroupDetails.getCreatedBy());
		histCompanyGroupDetailsImpl.setActionDate(histCompanyGroupDetails.getActionDate());
		histCompanyGroupDetailsImpl.setCompanyParentDetailsSid(histCompanyGroupDetails.getCompanyParentDetailsSid());
		histCompanyGroupDetailsImpl.setCompanyTradeclassSid(histCompanyGroupDetails.getCompanyTradeclassSid());
		histCompanyGroupDetailsImpl.setActionFlag(histCompanyGroupDetails.getActionFlag());
		histCompanyGroupDetailsImpl.setCompanyGroupSid(histCompanyGroupDetails.getCompanyGroupSid());
		histCompanyGroupDetailsImpl.setVersionNo(histCompanyGroupDetails.getVersionNo());
		histCompanyGroupDetailsImpl.setCompanyGroupDetailsSid(histCompanyGroupDetails.getCompanyGroupDetailsSid());
		histCompanyGroupDetailsImpl.setModifiedBy(histCompanyGroupDetails.getModifiedBy());
		histCompanyGroupDetailsImpl.setModifiedDate(histCompanyGroupDetails.getModifiedDate());
		histCompanyGroupDetailsImpl.setCompanyMasterSid(histCompanyGroupDetails.getCompanyMasterSid());

		return histCompanyGroupDetailsImpl;
	}

	/**
	 * Returns the hist company group details with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the hist company group details
	 * @return the hist company group details
	 * @throws NoSuchHistCompanyGroupDetailsException if a hist company group details with the primary key could not be found
	 */
	@Override
	public HistCompanyGroupDetails findByPrimaryKey(Serializable primaryKey)
		throws NoSuchHistCompanyGroupDetailsException {
		HistCompanyGroupDetails histCompanyGroupDetails = fetchByPrimaryKey(primaryKey);

		if (histCompanyGroupDetails == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchHistCompanyGroupDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return histCompanyGroupDetails;
	}

	/**
	 * Returns the hist company group details with the primary key or throws a {@link NoSuchHistCompanyGroupDetailsException} if it could not be found.
	 *
	 * @param histCompanyGroupDetailsPK the primary key of the hist company group details
	 * @return the hist company group details
	 * @throws NoSuchHistCompanyGroupDetailsException if a hist company group details with the primary key could not be found
	 */
	@Override
	public HistCompanyGroupDetails findByPrimaryKey(
		HistCompanyGroupDetailsPK histCompanyGroupDetailsPK)
		throws NoSuchHistCompanyGroupDetailsException {
		return findByPrimaryKey((Serializable)histCompanyGroupDetailsPK);
	}

	/**
	 * Returns the hist company group details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the hist company group details
	 * @return the hist company group details, or <code>null</code> if a hist company group details with the primary key could not be found
	 */
	@Override
	public HistCompanyGroupDetails fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(HistCompanyGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
				HistCompanyGroupDetailsImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		HistCompanyGroupDetails histCompanyGroupDetails = (HistCompanyGroupDetails)serializable;

		if (histCompanyGroupDetails == null) {
			Session session = null;

			try {
				session = openSession();

				histCompanyGroupDetails = (HistCompanyGroupDetails)session.get(HistCompanyGroupDetailsImpl.class,
						primaryKey);

				if (histCompanyGroupDetails != null) {
					cacheResult(histCompanyGroupDetails);
				}
				else {
					entityCache.putResult(HistCompanyGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
						HistCompanyGroupDetailsImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(HistCompanyGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
					HistCompanyGroupDetailsImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return histCompanyGroupDetails;
	}

	/**
	 * Returns the hist company group details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param histCompanyGroupDetailsPK the primary key of the hist company group details
	 * @return the hist company group details, or <code>null</code> if a hist company group details with the primary key could not be found
	 */
	@Override
	public HistCompanyGroupDetails fetchByPrimaryKey(
		HistCompanyGroupDetailsPK histCompanyGroupDetailsPK) {
		return fetchByPrimaryKey((Serializable)histCompanyGroupDetailsPK);
	}

	@Override
	public Map<Serializable, HistCompanyGroupDetails> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, HistCompanyGroupDetails> map = new HashMap<Serializable, HistCompanyGroupDetails>();

		for (Serializable primaryKey : primaryKeys) {
			HistCompanyGroupDetails histCompanyGroupDetails = fetchByPrimaryKey(primaryKey);

			if (histCompanyGroupDetails != null) {
				map.put(primaryKey, histCompanyGroupDetails);
			}
		}

		return map;
	}

	/**
	 * Returns all the hist company group detailses.
	 *
	 * @return the hist company group detailses
	 */
	@Override
	public List<HistCompanyGroupDetails> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the hist company group detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistCompanyGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of hist company group detailses
	 * @param end the upper bound of the range of hist company group detailses (not inclusive)
	 * @return the range of hist company group detailses
	 */
	@Override
	public List<HistCompanyGroupDetails> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the hist company group detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistCompanyGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of hist company group detailses
	 * @param end the upper bound of the range of hist company group detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of hist company group detailses
	 */
	@Override
	public List<HistCompanyGroupDetails> findAll(int start, int end,
		OrderByComparator<HistCompanyGroupDetails> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the hist company group detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistCompanyGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of hist company group detailses
	 * @param end the upper bound of the range of hist company group detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of hist company group detailses
	 */
	@Override
	public List<HistCompanyGroupDetails> findAll(int start, int end,
		OrderByComparator<HistCompanyGroupDetails> orderByComparator,
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

		List<HistCompanyGroupDetails> list = null;

		if (retrieveFromCache) {
			list = (List<HistCompanyGroupDetails>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_HISTCOMPANYGROUPDETAILS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_HISTCOMPANYGROUPDETAILS;

				if (pagination) {
					sql = sql.concat(HistCompanyGroupDetailsModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<HistCompanyGroupDetails>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<HistCompanyGroupDetails>)QueryUtil.list(q,
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
	 * Removes all the hist company group detailses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (HistCompanyGroupDetails histCompanyGroupDetails : findAll()) {
			remove(histCompanyGroupDetails);
		}
	}

	/**
	 * Returns the number of hist company group detailses.
	 *
	 * @return the number of hist company group detailses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_HISTCOMPANYGROUPDETAILS);

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
		return HistCompanyGroupDetailsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the hist company group details persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(HistCompanyGroupDetailsImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_HISTCOMPANYGROUPDETAILS = "SELECT histCompanyGroupDetails FROM HistCompanyGroupDetails histCompanyGroupDetails";
	private static final String _SQL_COUNT_HISTCOMPANYGROUPDETAILS = "SELECT COUNT(histCompanyGroupDetails) FROM HistCompanyGroupDetails histCompanyGroupDetails";
	private static final String _ORDER_BY_ENTITY_ALIAS = "histCompanyGroupDetails.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HistCompanyGroupDetails exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(HistCompanyGroupDetailsPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"createdDate", "createdBy", "actionDate",
				"companyParentDetailsSid", "companyTradeclassSid", "actionFlag",
				"companyGroupSid", "versionNo", "companyGroupDetailsSid",
				"modifiedBy", "modifiedDate", "companyMasterSid"
			});
}