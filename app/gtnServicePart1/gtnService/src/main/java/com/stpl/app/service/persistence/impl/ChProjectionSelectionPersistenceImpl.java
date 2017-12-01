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

import com.stpl.app.exception.NoSuchChProjectionSelectionException;
import com.stpl.app.model.ChProjectionSelection;
import com.stpl.app.model.impl.ChProjectionSelectionImpl;
import com.stpl.app.model.impl.ChProjectionSelectionModelImpl;
import com.stpl.app.service.persistence.ChProjectionSelectionPersistence;

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
 * The persistence implementation for the ch projection selection service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ChProjectionSelectionPersistence
 * @see com.stpl.app.service.persistence.ChProjectionSelectionUtil
 * @generated
 */
@ProviderType
public class ChProjectionSelectionPersistenceImpl extends BasePersistenceImpl<ChProjectionSelection>
	implements ChProjectionSelectionPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ChProjectionSelectionUtil} to access the ch projection selection persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ChProjectionSelectionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ChProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
			ChProjectionSelectionModelImpl.FINDER_CACHE_ENABLED,
			ChProjectionSelectionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ChProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
			ChProjectionSelectionModelImpl.FINDER_CACHE_ENABLED,
			ChProjectionSelectionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ChProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
			ChProjectionSelectionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public ChProjectionSelectionPersistenceImpl() {
		setModelClass(ChProjectionSelection.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("screenName", "SCREEN_NAME");
			dbColumnNames.put("fieldName", "FIELD_NAME");
			dbColumnNames.put("chProjectionSelectionSid",
				"CH_PROJECTION_SELECTION_SID");
			dbColumnNames.put("fieldValues", "FIELD_VALUES");
			dbColumnNames.put("projectionMasterSid", "PROJECTION_MASTER_SID");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the ch projection selection in the entity cache if it is enabled.
	 *
	 * @param chProjectionSelection the ch projection selection
	 */
	@Override
	public void cacheResult(ChProjectionSelection chProjectionSelection) {
		entityCache.putResult(ChProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
			ChProjectionSelectionImpl.class,
			chProjectionSelection.getPrimaryKey(), chProjectionSelection);

		chProjectionSelection.resetOriginalValues();
	}

	/**
	 * Caches the ch projection selections in the entity cache if it is enabled.
	 *
	 * @param chProjectionSelections the ch projection selections
	 */
	@Override
	public void cacheResult(List<ChProjectionSelection> chProjectionSelections) {
		for (ChProjectionSelection chProjectionSelection : chProjectionSelections) {
			if (entityCache.getResult(
						ChProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
						ChProjectionSelectionImpl.class,
						chProjectionSelection.getPrimaryKey()) == null) {
				cacheResult(chProjectionSelection);
			}
			else {
				chProjectionSelection.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all ch projection selections.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ChProjectionSelectionImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the ch projection selection.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ChProjectionSelection chProjectionSelection) {
		entityCache.removeResult(ChProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
			ChProjectionSelectionImpl.class,
			chProjectionSelection.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ChProjectionSelection> chProjectionSelections) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ChProjectionSelection chProjectionSelection : chProjectionSelections) {
			entityCache.removeResult(ChProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
				ChProjectionSelectionImpl.class,
				chProjectionSelection.getPrimaryKey());
		}
	}

	/**
	 * Creates a new ch projection selection with the primary key. Does not add the ch projection selection to the database.
	 *
	 * @param chProjectionSelectionSid the primary key for the new ch projection selection
	 * @return the new ch projection selection
	 */
	@Override
	public ChProjectionSelection create(int chProjectionSelectionSid) {
		ChProjectionSelection chProjectionSelection = new ChProjectionSelectionImpl();

		chProjectionSelection.setNew(true);
		chProjectionSelection.setPrimaryKey(chProjectionSelectionSid);

		return chProjectionSelection;
	}

	/**
	 * Removes the ch projection selection with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param chProjectionSelectionSid the primary key of the ch projection selection
	 * @return the ch projection selection that was removed
	 * @throws NoSuchChProjectionSelectionException if a ch projection selection with the primary key could not be found
	 */
	@Override
	public ChProjectionSelection remove(int chProjectionSelectionSid)
		throws NoSuchChProjectionSelectionException {
		return remove((Serializable)chProjectionSelectionSid);
	}

	/**
	 * Removes the ch projection selection with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ch projection selection
	 * @return the ch projection selection that was removed
	 * @throws NoSuchChProjectionSelectionException if a ch projection selection with the primary key could not be found
	 */
	@Override
	public ChProjectionSelection remove(Serializable primaryKey)
		throws NoSuchChProjectionSelectionException {
		Session session = null;

		try {
			session = openSession();

			ChProjectionSelection chProjectionSelection = (ChProjectionSelection)session.get(ChProjectionSelectionImpl.class,
					primaryKey);

			if (chProjectionSelection == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchChProjectionSelectionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(chProjectionSelection);
		}
		catch (NoSuchChProjectionSelectionException nsee) {
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
	protected ChProjectionSelection removeImpl(
		ChProjectionSelection chProjectionSelection) {
		chProjectionSelection = toUnwrappedModel(chProjectionSelection);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(chProjectionSelection)) {
				chProjectionSelection = (ChProjectionSelection)session.get(ChProjectionSelectionImpl.class,
						chProjectionSelection.getPrimaryKeyObj());
			}

			if (chProjectionSelection != null) {
				session.delete(chProjectionSelection);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (chProjectionSelection != null) {
			clearCache(chProjectionSelection);
		}

		return chProjectionSelection;
	}

	@Override
	public ChProjectionSelection updateImpl(
		ChProjectionSelection chProjectionSelection) {
		chProjectionSelection = toUnwrappedModel(chProjectionSelection);

		boolean isNew = chProjectionSelection.isNew();

		Session session = null;

		try {
			session = openSession();

			if (chProjectionSelection.isNew()) {
				session.save(chProjectionSelection);

				chProjectionSelection.setNew(false);
			}
			else {
				chProjectionSelection = (ChProjectionSelection)session.merge(chProjectionSelection);
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

		entityCache.putResult(ChProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
			ChProjectionSelectionImpl.class,
			chProjectionSelection.getPrimaryKey(), chProjectionSelection, false);

		chProjectionSelection.resetOriginalValues();

		return chProjectionSelection;
	}

	protected ChProjectionSelection toUnwrappedModel(
		ChProjectionSelection chProjectionSelection) {
		if (chProjectionSelection instanceof ChProjectionSelectionImpl) {
			return chProjectionSelection;
		}

		ChProjectionSelectionImpl chProjectionSelectionImpl = new ChProjectionSelectionImpl();

		chProjectionSelectionImpl.setNew(chProjectionSelection.isNew());
		chProjectionSelectionImpl.setPrimaryKey(chProjectionSelection.getPrimaryKey());

		chProjectionSelectionImpl.setScreenName(chProjectionSelection.getScreenName());
		chProjectionSelectionImpl.setFieldName(chProjectionSelection.getFieldName());
		chProjectionSelectionImpl.setChProjectionSelectionSid(chProjectionSelection.getChProjectionSelectionSid());
		chProjectionSelectionImpl.setFieldValues(chProjectionSelection.getFieldValues());
		chProjectionSelectionImpl.setProjectionMasterSid(chProjectionSelection.getProjectionMasterSid());

		return chProjectionSelectionImpl;
	}

	/**
	 * Returns the ch projection selection with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the ch projection selection
	 * @return the ch projection selection
	 * @throws NoSuchChProjectionSelectionException if a ch projection selection with the primary key could not be found
	 */
	@Override
	public ChProjectionSelection findByPrimaryKey(Serializable primaryKey)
		throws NoSuchChProjectionSelectionException {
		ChProjectionSelection chProjectionSelection = fetchByPrimaryKey(primaryKey);

		if (chProjectionSelection == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchChProjectionSelectionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return chProjectionSelection;
	}

	/**
	 * Returns the ch projection selection with the primary key or throws a {@link NoSuchChProjectionSelectionException} if it could not be found.
	 *
	 * @param chProjectionSelectionSid the primary key of the ch projection selection
	 * @return the ch projection selection
	 * @throws NoSuchChProjectionSelectionException if a ch projection selection with the primary key could not be found
	 */
	@Override
	public ChProjectionSelection findByPrimaryKey(int chProjectionSelectionSid)
		throws NoSuchChProjectionSelectionException {
		return findByPrimaryKey((Serializable)chProjectionSelectionSid);
	}

	/**
	 * Returns the ch projection selection with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ch projection selection
	 * @return the ch projection selection, or <code>null</code> if a ch projection selection with the primary key could not be found
	 */
	@Override
	public ChProjectionSelection fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ChProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
				ChProjectionSelectionImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ChProjectionSelection chProjectionSelection = (ChProjectionSelection)serializable;

		if (chProjectionSelection == null) {
			Session session = null;

			try {
				session = openSession();

				chProjectionSelection = (ChProjectionSelection)session.get(ChProjectionSelectionImpl.class,
						primaryKey);

				if (chProjectionSelection != null) {
					cacheResult(chProjectionSelection);
				}
				else {
					entityCache.putResult(ChProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
						ChProjectionSelectionImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ChProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
					ChProjectionSelectionImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return chProjectionSelection;
	}

	/**
	 * Returns the ch projection selection with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param chProjectionSelectionSid the primary key of the ch projection selection
	 * @return the ch projection selection, or <code>null</code> if a ch projection selection with the primary key could not be found
	 */
	@Override
	public ChProjectionSelection fetchByPrimaryKey(int chProjectionSelectionSid) {
		return fetchByPrimaryKey((Serializable)chProjectionSelectionSid);
	}

	@Override
	public Map<Serializable, ChProjectionSelection> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ChProjectionSelection> map = new HashMap<Serializable, ChProjectionSelection>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ChProjectionSelection chProjectionSelection = fetchByPrimaryKey(primaryKey);

			if (chProjectionSelection != null) {
				map.put(primaryKey, chProjectionSelection);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ChProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
					ChProjectionSelectionImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ChProjectionSelection)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_CHPROJECTIONSELECTION_WHERE_PKS_IN);

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

			for (ChProjectionSelection chProjectionSelection : (List<ChProjectionSelection>)q.list()) {
				map.put(chProjectionSelection.getPrimaryKeyObj(),
					chProjectionSelection);

				cacheResult(chProjectionSelection);

				uncachedPrimaryKeys.remove(chProjectionSelection.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ChProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
					ChProjectionSelectionImpl.class, primaryKey, nullModel);
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
	 * Returns all the ch projection selections.
	 *
	 * @return the ch projection selections
	 */
	@Override
	public List<ChProjectionSelection> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ch projection selections.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ChProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ch projection selections
	 * @param end the upper bound of the range of ch projection selections (not inclusive)
	 * @return the range of ch projection selections
	 */
	@Override
	public List<ChProjectionSelection> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ch projection selections.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ChProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ch projection selections
	 * @param end the upper bound of the range of ch projection selections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ch projection selections
	 */
	@Override
	public List<ChProjectionSelection> findAll(int start, int end,
		OrderByComparator<ChProjectionSelection> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ch projection selections.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ChProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ch projection selections
	 * @param end the upper bound of the range of ch projection selections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of ch projection selections
	 */
	@Override
	public List<ChProjectionSelection> findAll(int start, int end,
		OrderByComparator<ChProjectionSelection> orderByComparator,
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

		List<ChProjectionSelection> list = null;

		if (retrieveFromCache) {
			list = (List<ChProjectionSelection>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CHPROJECTIONSELECTION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CHPROJECTIONSELECTION;

				if (pagination) {
					sql = sql.concat(ChProjectionSelectionModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ChProjectionSelection>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ChProjectionSelection>)QueryUtil.list(q,
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
	 * Removes all the ch projection selections from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ChProjectionSelection chProjectionSelection : findAll()) {
			remove(chProjectionSelection);
		}
	}

	/**
	 * Returns the number of ch projection selections.
	 *
	 * @return the number of ch projection selections
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CHPROJECTIONSELECTION);

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
		return ChProjectionSelectionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ch projection selection persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ChProjectionSelectionImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_CHPROJECTIONSELECTION = "SELECT chProjectionSelection FROM ChProjectionSelection chProjectionSelection";
	private static final String _SQL_SELECT_CHPROJECTIONSELECTION_WHERE_PKS_IN = "SELECT chProjectionSelection FROM ChProjectionSelection chProjectionSelection WHERE CH_PROJECTION_SELECTION_SID IN (";
	private static final String _SQL_COUNT_CHPROJECTIONSELECTION = "SELECT COUNT(chProjectionSelection) FROM ChProjectionSelection chProjectionSelection";
	private static final String _ORDER_BY_ENTITY_ALIAS = "chProjectionSelection.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ChProjectionSelection exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(ChProjectionSelectionPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"screenName", "fieldName", "chProjectionSelectionSid",
				"fieldValues", "projectionMasterSid"
			});
}