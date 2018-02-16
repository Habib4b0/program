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
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.spring.extender.service.ServiceReference;

import com.stpl.app.exception.NoSuchNaProjectionSelectionException;
import com.stpl.app.model.NaProjectionSelection;
import com.stpl.app.model.impl.NaProjectionSelectionImpl;
import com.stpl.app.model.impl.NaProjectionSelectionModelImpl;
import com.stpl.app.service.persistence.NaProjectionSelectionPersistence;

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
 * The persistence implementation for the na projection selection service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see NaProjectionSelectionPersistence
 * @see com.stpl.app.service.persistence.NaProjectionSelectionUtil
 * @generated
 */
@ProviderType
public class NaProjectionSelectionPersistenceImpl extends BasePersistenceImpl<NaProjectionSelection>
	implements NaProjectionSelectionPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link NaProjectionSelectionUtil} to access the na projection selection persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = NaProjectionSelectionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(NaProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
			NaProjectionSelectionModelImpl.FINDER_CACHE_ENABLED,
			NaProjectionSelectionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(NaProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
			NaProjectionSelectionModelImpl.FINDER_CACHE_ENABLED,
			NaProjectionSelectionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(NaProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
			NaProjectionSelectionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public NaProjectionSelectionPersistenceImpl() {
		setModelClass(NaProjectionSelection.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("screenName", "SCREEN_NAME");
			dbColumnNames.put("fieldName", "FIELD_NAME");
			dbColumnNames.put("fieldValues", "FIELD_VALUES");
			dbColumnNames.put("naProjectionSelectionSid",
				"NA_PROJECTION_SELECTION_SID");
			dbColumnNames.put("naProjMasterSid", "NA_PROJ_MASTER_SID");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the na projection selection in the entity cache if it is enabled.
	 *
	 * @param naProjectionSelection the na projection selection
	 */
	@Override
	public void cacheResult(NaProjectionSelection naProjectionSelection) {
		entityCache.putResult(NaProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
			NaProjectionSelectionImpl.class,
			naProjectionSelection.getPrimaryKey(), naProjectionSelection);

		naProjectionSelection.resetOriginalValues();
	}

	/**
	 * Caches the na projection selections in the entity cache if it is enabled.
	 *
	 * @param naProjectionSelections the na projection selections
	 */
	@Override
	public void cacheResult(List<NaProjectionSelection> naProjectionSelections) {
		for (NaProjectionSelection naProjectionSelection : naProjectionSelections) {
			if (entityCache.getResult(
						NaProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
						NaProjectionSelectionImpl.class,
						naProjectionSelection.getPrimaryKey()) == null) {
				cacheResult(naProjectionSelection);
			}
			else {
				naProjectionSelection.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all na projection selections.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(NaProjectionSelectionImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the na projection selection.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(NaProjectionSelection naProjectionSelection) {
		entityCache.removeResult(NaProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
			NaProjectionSelectionImpl.class,
			naProjectionSelection.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<NaProjectionSelection> naProjectionSelections) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (NaProjectionSelection naProjectionSelection : naProjectionSelections) {
			entityCache.removeResult(NaProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
				NaProjectionSelectionImpl.class,
				naProjectionSelection.getPrimaryKey());
		}
	}

	/**
	 * Creates a new na projection selection with the primary key. Does not add the na projection selection to the database.
	 *
	 * @param naProjectionSelectionSid the primary key for the new na projection selection
	 * @return the new na projection selection
	 */
	@Override
	public NaProjectionSelection create(int naProjectionSelectionSid) {
		NaProjectionSelection naProjectionSelection = new NaProjectionSelectionImpl();

		naProjectionSelection.setNew(true);
		naProjectionSelection.setPrimaryKey(naProjectionSelectionSid);

		return naProjectionSelection;
	}

	/**
	 * Removes the na projection selection with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param naProjectionSelectionSid the primary key of the na projection selection
	 * @return the na projection selection that was removed
	 * @throws NoSuchNaProjectionSelectionException if a na projection selection with the primary key could not be found
	 */
	@Override
	public NaProjectionSelection remove(int naProjectionSelectionSid)
		throws NoSuchNaProjectionSelectionException {
		return remove((Serializable)naProjectionSelectionSid);
	}

	/**
	 * Removes the na projection selection with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the na projection selection
	 * @return the na projection selection that was removed
	 * @throws NoSuchNaProjectionSelectionException if a na projection selection with the primary key could not be found
	 */
	@Override
	public NaProjectionSelection remove(Serializable primaryKey)
		throws NoSuchNaProjectionSelectionException {
		Session session = null;

		try {
			session = openSession();

			NaProjectionSelection naProjectionSelection = (NaProjectionSelection)session.get(NaProjectionSelectionImpl.class,
					primaryKey);

			if (naProjectionSelection == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchNaProjectionSelectionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(naProjectionSelection);
		}
		catch (NoSuchNaProjectionSelectionException nsee) {
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
	protected NaProjectionSelection removeImpl(
		NaProjectionSelection naProjectionSelection) {
		naProjectionSelection = toUnwrappedModel(naProjectionSelection);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(naProjectionSelection)) {
				naProjectionSelection = (NaProjectionSelection)session.get(NaProjectionSelectionImpl.class,
						naProjectionSelection.getPrimaryKeyObj());
			}

			if (naProjectionSelection != null) {
				session.delete(naProjectionSelection);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (naProjectionSelection != null) {
			clearCache(naProjectionSelection);
		}

		return naProjectionSelection;
	}

	@Override
	public NaProjectionSelection updateImpl(
		NaProjectionSelection naProjectionSelection) {
		naProjectionSelection = toUnwrappedModel(naProjectionSelection);

		boolean isNew = naProjectionSelection.isNew();

		Session session = null;

		try {
			session = openSession();

			if (naProjectionSelection.isNew()) {
				session.save(naProjectionSelection);

				naProjectionSelection.setNew(false);
			}
			else {
				naProjectionSelection = (NaProjectionSelection)session.merge(naProjectionSelection);
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

		entityCache.putResult(NaProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
			NaProjectionSelectionImpl.class,
			naProjectionSelection.getPrimaryKey(), naProjectionSelection, false);

		naProjectionSelection.resetOriginalValues();

		return naProjectionSelection;
	}

	protected NaProjectionSelection toUnwrappedModel(
		NaProjectionSelection naProjectionSelection) {
		if (naProjectionSelection instanceof NaProjectionSelectionImpl) {
			return naProjectionSelection;
		}

		NaProjectionSelectionImpl naProjectionSelectionImpl = new NaProjectionSelectionImpl();

		naProjectionSelectionImpl.setNew(naProjectionSelection.isNew());
		naProjectionSelectionImpl.setPrimaryKey(naProjectionSelection.getPrimaryKey());

		naProjectionSelectionImpl.setScreenName(naProjectionSelection.getScreenName());
		naProjectionSelectionImpl.setFieldName(naProjectionSelection.getFieldName());
		naProjectionSelectionImpl.setFieldValues(naProjectionSelection.getFieldValues());
		naProjectionSelectionImpl.setNaProjectionSelectionSid(naProjectionSelection.getNaProjectionSelectionSid());
		naProjectionSelectionImpl.setNaProjMasterSid(naProjectionSelection.getNaProjMasterSid());

		return naProjectionSelectionImpl;
	}

	/**
	 * Returns the na projection selection with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the na projection selection
	 * @return the na projection selection
	 * @throws NoSuchNaProjectionSelectionException if a na projection selection with the primary key could not be found
	 */
	@Override
	public NaProjectionSelection findByPrimaryKey(Serializable primaryKey)
		throws NoSuchNaProjectionSelectionException {
		NaProjectionSelection naProjectionSelection = fetchByPrimaryKey(primaryKey);

		if (naProjectionSelection == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchNaProjectionSelectionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return naProjectionSelection;
	}

	/**
	 * Returns the na projection selection with the primary key or throws a {@link NoSuchNaProjectionSelectionException} if it could not be found.
	 *
	 * @param naProjectionSelectionSid the primary key of the na projection selection
	 * @return the na projection selection
	 * @throws NoSuchNaProjectionSelectionException if a na projection selection with the primary key could not be found
	 */
	@Override
	public NaProjectionSelection findByPrimaryKey(int naProjectionSelectionSid)
		throws NoSuchNaProjectionSelectionException {
		return findByPrimaryKey((Serializable)naProjectionSelectionSid);
	}

	/**
	 * Returns the na projection selection with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the na projection selection
	 * @return the na projection selection, or <code>null</code> if a na projection selection with the primary key could not be found
	 */
	@Override
	public NaProjectionSelection fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(NaProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
				NaProjectionSelectionImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		NaProjectionSelection naProjectionSelection = (NaProjectionSelection)serializable;

		if (naProjectionSelection == null) {
			Session session = null;

			try {
				session = openSession();

				naProjectionSelection = (NaProjectionSelection)session.get(NaProjectionSelectionImpl.class,
						primaryKey);

				if (naProjectionSelection != null) {
					cacheResult(naProjectionSelection);
				}
				else {
					entityCache.putResult(NaProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
						NaProjectionSelectionImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(NaProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
					NaProjectionSelectionImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return naProjectionSelection;
	}

	/**
	 * Returns the na projection selection with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param naProjectionSelectionSid the primary key of the na projection selection
	 * @return the na projection selection, or <code>null</code> if a na projection selection with the primary key could not be found
	 */
	@Override
	public NaProjectionSelection fetchByPrimaryKey(int naProjectionSelectionSid) {
		return fetchByPrimaryKey((Serializable)naProjectionSelectionSid);
	}

	@Override
	public Map<Serializable, NaProjectionSelection> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, NaProjectionSelection> map = new HashMap<Serializable, NaProjectionSelection>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			NaProjectionSelection naProjectionSelection = fetchByPrimaryKey(primaryKey);

			if (naProjectionSelection != null) {
				map.put(primaryKey, naProjectionSelection);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(NaProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
					NaProjectionSelectionImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (NaProjectionSelection)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_NAPROJECTIONSELECTION_WHERE_PKS_IN);

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

			for (NaProjectionSelection naProjectionSelection : (List<NaProjectionSelection>)q.list()) {
				map.put(naProjectionSelection.getPrimaryKeyObj(),
					naProjectionSelection);

				cacheResult(naProjectionSelection);

				uncachedPrimaryKeys.remove(naProjectionSelection.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(NaProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
					NaProjectionSelectionImpl.class, primaryKey, nullModel);
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
	 * Returns all the na projection selections.
	 *
	 * @return the na projection selections
	 */
	@Override
	public List<NaProjectionSelection> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the na projection selections.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NaProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of na projection selections
	 * @param end the upper bound of the range of na projection selections (not inclusive)
	 * @return the range of na projection selections
	 */
	@Override
	public List<NaProjectionSelection> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the na projection selections.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NaProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of na projection selections
	 * @param end the upper bound of the range of na projection selections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of na projection selections
	 */
	@Override
	public List<NaProjectionSelection> findAll(int start, int end,
		OrderByComparator<NaProjectionSelection> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the na projection selections.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NaProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of na projection selections
	 * @param end the upper bound of the range of na projection selections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of na projection selections
	 */
	@Override
	public List<NaProjectionSelection> findAll(int start, int end,
		OrderByComparator<NaProjectionSelection> orderByComparator,
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

		List<NaProjectionSelection> list = null;

		if (retrieveFromCache) {
			list = (List<NaProjectionSelection>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_NAPROJECTIONSELECTION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_NAPROJECTIONSELECTION;

				if (pagination) {
					sql = sql.concat(NaProjectionSelectionModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<NaProjectionSelection>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<NaProjectionSelection>)QueryUtil.list(q,
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
	 * Removes all the na projection selections from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (NaProjectionSelection naProjectionSelection : findAll()) {
			remove(naProjectionSelection);
		}
	}

	/**
	 * Returns the number of na projection selections.
	 *
	 * @return the number of na projection selections
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_NAPROJECTIONSELECTION);

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
		return NaProjectionSelectionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the na projection selection persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(NaProjectionSelectionImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_NAPROJECTIONSELECTION = "SELECT naProjectionSelection FROM NaProjectionSelection naProjectionSelection";
	private static final String _SQL_SELECT_NAPROJECTIONSELECTION_WHERE_PKS_IN = "SELECT naProjectionSelection FROM NaProjectionSelection naProjectionSelection WHERE NA_PROJECTION_SELECTION_SID IN (";
	private static final String _SQL_COUNT_NAPROJECTIONSELECTION = "SELECT COUNT(naProjectionSelection) FROM NaProjectionSelection naProjectionSelection";
	private static final String _ORDER_BY_ENTITY_ALIAS = "naProjectionSelection.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No NaProjectionSelection exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(NaProjectionSelectionPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"screenName", "fieldName", "fieldValues",
				"naProjectionSelectionSid", "naProjMasterSid"
			});
}