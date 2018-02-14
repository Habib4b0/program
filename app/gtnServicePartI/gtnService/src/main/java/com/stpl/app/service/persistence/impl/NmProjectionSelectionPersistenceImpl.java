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

import com.stpl.app.exception.NoSuchNmProjectionSelectionException;
import com.stpl.app.model.NmProjectionSelection;
import com.stpl.app.model.impl.NmProjectionSelectionImpl;
import com.stpl.app.model.impl.NmProjectionSelectionModelImpl;
import com.stpl.app.service.persistence.NmProjectionSelectionPersistence;

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
 * The persistence implementation for the nm projection selection service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see NmProjectionSelectionPersistence
 * @see com.stpl.app.service.persistence.NmProjectionSelectionUtil
 * @generated
 */
@ProviderType
public class NmProjectionSelectionPersistenceImpl extends BasePersistenceImpl<NmProjectionSelection>
	implements NmProjectionSelectionPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link NmProjectionSelectionUtil} to access the nm projection selection persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = NmProjectionSelectionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(NmProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
			NmProjectionSelectionModelImpl.FINDER_CACHE_ENABLED,
			NmProjectionSelectionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(NmProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
			NmProjectionSelectionModelImpl.FINDER_CACHE_ENABLED,
			NmProjectionSelectionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(NmProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
			NmProjectionSelectionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public NmProjectionSelectionPersistenceImpl() {
		setModelClass(NmProjectionSelection.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("screenName", "SCREEN_NAME");
			dbColumnNames.put("nmProjectionSelectionSid",
				"NM_PROJECTION_SELECTION_SID");
			dbColumnNames.put("fieldName", "FIELD_NAME");
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
	 * Caches the nm projection selection in the entity cache if it is enabled.
	 *
	 * @param nmProjectionSelection the nm projection selection
	 */
	@Override
	public void cacheResult(NmProjectionSelection nmProjectionSelection) {
		entityCache.putResult(NmProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
			NmProjectionSelectionImpl.class,
			nmProjectionSelection.getPrimaryKey(), nmProjectionSelection);

		nmProjectionSelection.resetOriginalValues();
	}

	/**
	 * Caches the nm projection selections in the entity cache if it is enabled.
	 *
	 * @param nmProjectionSelections the nm projection selections
	 */
	@Override
	public void cacheResult(List<NmProjectionSelection> nmProjectionSelections) {
		for (NmProjectionSelection nmProjectionSelection : nmProjectionSelections) {
			if (entityCache.getResult(
						NmProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
						NmProjectionSelectionImpl.class,
						nmProjectionSelection.getPrimaryKey()) == null) {
				cacheResult(nmProjectionSelection);
			}
			else {
				nmProjectionSelection.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all nm projection selections.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(NmProjectionSelectionImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the nm projection selection.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(NmProjectionSelection nmProjectionSelection) {
		entityCache.removeResult(NmProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
			NmProjectionSelectionImpl.class,
			nmProjectionSelection.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<NmProjectionSelection> nmProjectionSelections) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (NmProjectionSelection nmProjectionSelection : nmProjectionSelections) {
			entityCache.removeResult(NmProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
				NmProjectionSelectionImpl.class,
				nmProjectionSelection.getPrimaryKey());
		}
	}

	/**
	 * Creates a new nm projection selection with the primary key. Does not add the nm projection selection to the database.
	 *
	 * @param nmProjectionSelectionSid the primary key for the new nm projection selection
	 * @return the new nm projection selection
	 */
	@Override
	public NmProjectionSelection create(int nmProjectionSelectionSid) {
		NmProjectionSelection nmProjectionSelection = new NmProjectionSelectionImpl();

		nmProjectionSelection.setNew(true);
		nmProjectionSelection.setPrimaryKey(nmProjectionSelectionSid);

		return nmProjectionSelection;
	}

	/**
	 * Removes the nm projection selection with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param nmProjectionSelectionSid the primary key of the nm projection selection
	 * @return the nm projection selection that was removed
	 * @throws NoSuchNmProjectionSelectionException if a nm projection selection with the primary key could not be found
	 */
	@Override
	public NmProjectionSelection remove(int nmProjectionSelectionSid)
		throws NoSuchNmProjectionSelectionException {
		return remove((Serializable)nmProjectionSelectionSid);
	}

	/**
	 * Removes the nm projection selection with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the nm projection selection
	 * @return the nm projection selection that was removed
	 * @throws NoSuchNmProjectionSelectionException if a nm projection selection with the primary key could not be found
	 */
	@Override
	public NmProjectionSelection remove(Serializable primaryKey)
		throws NoSuchNmProjectionSelectionException {
		Session session = null;

		try {
			session = openSession();

			NmProjectionSelection nmProjectionSelection = (NmProjectionSelection)session.get(NmProjectionSelectionImpl.class,
					primaryKey);

			if (nmProjectionSelection == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchNmProjectionSelectionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(nmProjectionSelection);
		}
		catch (NoSuchNmProjectionSelectionException nsee) {
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
	protected NmProjectionSelection removeImpl(
		NmProjectionSelection nmProjectionSelection) {
		nmProjectionSelection = toUnwrappedModel(nmProjectionSelection);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(nmProjectionSelection)) {
				nmProjectionSelection = (NmProjectionSelection)session.get(NmProjectionSelectionImpl.class,
						nmProjectionSelection.getPrimaryKeyObj());
			}

			if (nmProjectionSelection != null) {
				session.delete(nmProjectionSelection);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (nmProjectionSelection != null) {
			clearCache(nmProjectionSelection);
		}

		return nmProjectionSelection;
	}

	@Override
	public NmProjectionSelection updateImpl(
		NmProjectionSelection nmProjectionSelection) {
		nmProjectionSelection = toUnwrappedModel(nmProjectionSelection);

		boolean isNew = nmProjectionSelection.isNew();

		Session session = null;

		try {
			session = openSession();

			if (nmProjectionSelection.isNew()) {
				session.save(nmProjectionSelection);

				nmProjectionSelection.setNew(false);
			}
			else {
				nmProjectionSelection = (NmProjectionSelection)session.merge(nmProjectionSelection);
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

		entityCache.putResult(NmProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
			NmProjectionSelectionImpl.class,
			nmProjectionSelection.getPrimaryKey(), nmProjectionSelection, false);

		nmProjectionSelection.resetOriginalValues();

		return nmProjectionSelection;
	}

	protected NmProjectionSelection toUnwrappedModel(
		NmProjectionSelection nmProjectionSelection) {
		if (nmProjectionSelection instanceof NmProjectionSelectionImpl) {
			return nmProjectionSelection;
		}

		NmProjectionSelectionImpl nmProjectionSelectionImpl = new NmProjectionSelectionImpl();

		nmProjectionSelectionImpl.setNew(nmProjectionSelection.isNew());
		nmProjectionSelectionImpl.setPrimaryKey(nmProjectionSelection.getPrimaryKey());

		nmProjectionSelectionImpl.setScreenName(nmProjectionSelection.getScreenName());
		nmProjectionSelectionImpl.setNmProjectionSelectionSid(nmProjectionSelection.getNmProjectionSelectionSid());
		nmProjectionSelectionImpl.setFieldName(nmProjectionSelection.getFieldName());
		nmProjectionSelectionImpl.setFieldValues(nmProjectionSelection.getFieldValues());
		nmProjectionSelectionImpl.setProjectionMasterSid(nmProjectionSelection.getProjectionMasterSid());

		return nmProjectionSelectionImpl;
	}

	/**
	 * Returns the nm projection selection with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the nm projection selection
	 * @return the nm projection selection
	 * @throws NoSuchNmProjectionSelectionException if a nm projection selection with the primary key could not be found
	 */
	@Override
	public NmProjectionSelection findByPrimaryKey(Serializable primaryKey)
		throws NoSuchNmProjectionSelectionException {
		NmProjectionSelection nmProjectionSelection = fetchByPrimaryKey(primaryKey);

		if (nmProjectionSelection == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchNmProjectionSelectionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return nmProjectionSelection;
	}

	/**
	 * Returns the nm projection selection with the primary key or throws a {@link NoSuchNmProjectionSelectionException} if it could not be found.
	 *
	 * @param nmProjectionSelectionSid the primary key of the nm projection selection
	 * @return the nm projection selection
	 * @throws NoSuchNmProjectionSelectionException if a nm projection selection with the primary key could not be found
	 */
	@Override
	public NmProjectionSelection findByPrimaryKey(int nmProjectionSelectionSid)
		throws NoSuchNmProjectionSelectionException {
		return findByPrimaryKey((Serializable)nmProjectionSelectionSid);
	}

	/**
	 * Returns the nm projection selection with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the nm projection selection
	 * @return the nm projection selection, or <code>null</code> if a nm projection selection with the primary key could not be found
	 */
	@Override
	public NmProjectionSelection fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(NmProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
				NmProjectionSelectionImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		NmProjectionSelection nmProjectionSelection = (NmProjectionSelection)serializable;

		if (nmProjectionSelection == null) {
			Session session = null;

			try {
				session = openSession();

				nmProjectionSelection = (NmProjectionSelection)session.get(NmProjectionSelectionImpl.class,
						primaryKey);

				if (nmProjectionSelection != null) {
					cacheResult(nmProjectionSelection);
				}
				else {
					entityCache.putResult(NmProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
						NmProjectionSelectionImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(NmProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
					NmProjectionSelectionImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return nmProjectionSelection;
	}

	/**
	 * Returns the nm projection selection with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param nmProjectionSelectionSid the primary key of the nm projection selection
	 * @return the nm projection selection, or <code>null</code> if a nm projection selection with the primary key could not be found
	 */
	@Override
	public NmProjectionSelection fetchByPrimaryKey(int nmProjectionSelectionSid) {
		return fetchByPrimaryKey((Serializable)nmProjectionSelectionSid);
	}

	@Override
	public Map<Serializable, NmProjectionSelection> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, NmProjectionSelection> map = new HashMap<Serializable, NmProjectionSelection>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			NmProjectionSelection nmProjectionSelection = fetchByPrimaryKey(primaryKey);

			if (nmProjectionSelection != null) {
				map.put(primaryKey, nmProjectionSelection);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(NmProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
					NmProjectionSelectionImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (NmProjectionSelection)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_NMPROJECTIONSELECTION_WHERE_PKS_IN);

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

			for (NmProjectionSelection nmProjectionSelection : (List<NmProjectionSelection>)q.list()) {
				map.put(nmProjectionSelection.getPrimaryKeyObj(),
					nmProjectionSelection);

				cacheResult(nmProjectionSelection);

				uncachedPrimaryKeys.remove(nmProjectionSelection.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(NmProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
					NmProjectionSelectionImpl.class, primaryKey, nullModel);
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
	 * Returns all the nm projection selections.
	 *
	 * @return the nm projection selections
	 */
	@Override
	public List<NmProjectionSelection> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the nm projection selections.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NmProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of nm projection selections
	 * @param end the upper bound of the range of nm projection selections (not inclusive)
	 * @return the range of nm projection selections
	 */
	@Override
	public List<NmProjectionSelection> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the nm projection selections.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NmProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of nm projection selections
	 * @param end the upper bound of the range of nm projection selections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of nm projection selections
	 */
	@Override
	public List<NmProjectionSelection> findAll(int start, int end,
		OrderByComparator<NmProjectionSelection> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the nm projection selections.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NmProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of nm projection selections
	 * @param end the upper bound of the range of nm projection selections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of nm projection selections
	 */
	@Override
	public List<NmProjectionSelection> findAll(int start, int end,
		OrderByComparator<NmProjectionSelection> orderByComparator,
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

		List<NmProjectionSelection> list = null;

		if (retrieveFromCache) {
			list = (List<NmProjectionSelection>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_NMPROJECTIONSELECTION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_NMPROJECTIONSELECTION;

				if (pagination) {
					sql = sql.concat(NmProjectionSelectionModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<NmProjectionSelection>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<NmProjectionSelection>)QueryUtil.list(q,
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
	 * Removes all the nm projection selections from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (NmProjectionSelection nmProjectionSelection : findAll()) {
			remove(nmProjectionSelection);
		}
	}

	/**
	 * Returns the number of nm projection selections.
	 *
	 * @return the number of nm projection selections
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_NMPROJECTIONSELECTION);

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
		return NmProjectionSelectionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the nm projection selection persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(NmProjectionSelectionImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_NMPROJECTIONSELECTION = "SELECT nmProjectionSelection FROM NmProjectionSelection nmProjectionSelection";
	private static final String _SQL_SELECT_NMPROJECTIONSELECTION_WHERE_PKS_IN = "SELECT nmProjectionSelection FROM NmProjectionSelection nmProjectionSelection WHERE NM_PROJECTION_SELECTION_SID IN (";
	private static final String _SQL_COUNT_NMPROJECTIONSELECTION = "SELECT COUNT(nmProjectionSelection) FROM NmProjectionSelection nmProjectionSelection";
	private static final String _ORDER_BY_ENTITY_ALIAS = "nmProjectionSelection.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No NmProjectionSelection exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(NmProjectionSelectionPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"screenName", "nmProjectionSelectionSid", "fieldName",
				"fieldValues", "projectionMasterSid"
			});
}