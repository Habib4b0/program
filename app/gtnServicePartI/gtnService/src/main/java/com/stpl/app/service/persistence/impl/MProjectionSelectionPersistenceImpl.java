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

import com.stpl.app.exception.NoSuchMProjectionSelectionException;
import com.stpl.app.model.MProjectionSelection;
import com.stpl.app.model.impl.MProjectionSelectionImpl;
import com.stpl.app.model.impl.MProjectionSelectionModelImpl;
import com.stpl.app.service.persistence.MProjectionSelectionPersistence;

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
 * The persistence implementation for the m projection selection service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see MProjectionSelectionPersistence
 * @see com.stpl.app.service.persistence.MProjectionSelectionUtil
 * @generated
 */
@ProviderType
public class MProjectionSelectionPersistenceImpl extends BasePersistenceImpl<MProjectionSelection>
	implements MProjectionSelectionPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link MProjectionSelectionUtil} to access the m projection selection persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = MProjectionSelectionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(MProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
			MProjectionSelectionModelImpl.FINDER_CACHE_ENABLED,
			MProjectionSelectionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(MProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
			MProjectionSelectionModelImpl.FINDER_CACHE_ENABLED,
			MProjectionSelectionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
			MProjectionSelectionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public MProjectionSelectionPersistenceImpl() {
		setModelClass(MProjectionSelection.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("mProjectionSelectionSid",
				"M_PROJECTION_SELECTION_SID");
			dbColumnNames.put("projectionMasterSid", "PROJECTION_MASTER_SID");
			dbColumnNames.put("fieldValues", "FIELD_VALUES");
			dbColumnNames.put("fieldName", "FIELD_NAME");
			dbColumnNames.put("screenName", "SCREEN_NAME");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the m projection selection in the entity cache if it is enabled.
	 *
	 * @param mProjectionSelection the m projection selection
	 */
	@Override
	public void cacheResult(MProjectionSelection mProjectionSelection) {
		entityCache.putResult(MProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
			MProjectionSelectionImpl.class,
			mProjectionSelection.getPrimaryKey(), mProjectionSelection);

		mProjectionSelection.resetOriginalValues();
	}

	/**
	 * Caches the m projection selections in the entity cache if it is enabled.
	 *
	 * @param mProjectionSelections the m projection selections
	 */
	@Override
	public void cacheResult(List<MProjectionSelection> mProjectionSelections) {
		for (MProjectionSelection mProjectionSelection : mProjectionSelections) {
			if (entityCache.getResult(
						MProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
						MProjectionSelectionImpl.class,
						mProjectionSelection.getPrimaryKey()) == null) {
				cacheResult(mProjectionSelection);
			}
			else {
				mProjectionSelection.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all m projection selections.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(MProjectionSelectionImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the m projection selection.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(MProjectionSelection mProjectionSelection) {
		entityCache.removeResult(MProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
			MProjectionSelectionImpl.class, mProjectionSelection.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<MProjectionSelection> mProjectionSelections) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (MProjectionSelection mProjectionSelection : mProjectionSelections) {
			entityCache.removeResult(MProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
				MProjectionSelectionImpl.class,
				mProjectionSelection.getPrimaryKey());
		}
	}

	/**
	 * Creates a new m projection selection with the primary key. Does not add the m projection selection to the database.
	 *
	 * @param mProjectionSelectionSid the primary key for the new m projection selection
	 * @return the new m projection selection
	 */
	@Override
	public MProjectionSelection create(int mProjectionSelectionSid) {
		MProjectionSelection mProjectionSelection = new MProjectionSelectionImpl();

		mProjectionSelection.setNew(true);
		mProjectionSelection.setPrimaryKey(mProjectionSelectionSid);

		return mProjectionSelection;
	}

	/**
	 * Removes the m projection selection with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param mProjectionSelectionSid the primary key of the m projection selection
	 * @return the m projection selection that was removed
	 * @throws NoSuchMProjectionSelectionException if a m projection selection with the primary key could not be found
	 */
	@Override
	public MProjectionSelection remove(int mProjectionSelectionSid)
		throws NoSuchMProjectionSelectionException {
		return remove((Serializable)mProjectionSelectionSid);
	}

	/**
	 * Removes the m projection selection with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the m projection selection
	 * @return the m projection selection that was removed
	 * @throws NoSuchMProjectionSelectionException if a m projection selection with the primary key could not be found
	 */
	@Override
	public MProjectionSelection remove(Serializable primaryKey)
		throws NoSuchMProjectionSelectionException {
		Session session = null;

		try {
			session = openSession();

			MProjectionSelection mProjectionSelection = (MProjectionSelection)session.get(MProjectionSelectionImpl.class,
					primaryKey);

			if (mProjectionSelection == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchMProjectionSelectionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(mProjectionSelection);
		}
		catch (NoSuchMProjectionSelectionException nsee) {
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
	protected MProjectionSelection removeImpl(
		MProjectionSelection mProjectionSelection) {
		mProjectionSelection = toUnwrappedModel(mProjectionSelection);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(mProjectionSelection)) {
				mProjectionSelection = (MProjectionSelection)session.get(MProjectionSelectionImpl.class,
						mProjectionSelection.getPrimaryKeyObj());
			}

			if (mProjectionSelection != null) {
				session.delete(mProjectionSelection);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (mProjectionSelection != null) {
			clearCache(mProjectionSelection);
		}

		return mProjectionSelection;
	}

	@Override
	public MProjectionSelection updateImpl(
		MProjectionSelection mProjectionSelection) {
		mProjectionSelection = toUnwrappedModel(mProjectionSelection);

		boolean isNew = mProjectionSelection.isNew();

		Session session = null;

		try {
			session = openSession();

			if (mProjectionSelection.isNew()) {
				session.save(mProjectionSelection);

				mProjectionSelection.setNew(false);
			}
			else {
				mProjectionSelection = (MProjectionSelection)session.merge(mProjectionSelection);
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

		entityCache.putResult(MProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
			MProjectionSelectionImpl.class,
			mProjectionSelection.getPrimaryKey(), mProjectionSelection, false);

		mProjectionSelection.resetOriginalValues();

		return mProjectionSelection;
	}

	protected MProjectionSelection toUnwrappedModel(
		MProjectionSelection mProjectionSelection) {
		if (mProjectionSelection instanceof MProjectionSelectionImpl) {
			return mProjectionSelection;
		}

		MProjectionSelectionImpl mProjectionSelectionImpl = new MProjectionSelectionImpl();

		mProjectionSelectionImpl.setNew(mProjectionSelection.isNew());
		mProjectionSelectionImpl.setPrimaryKey(mProjectionSelection.getPrimaryKey());

		mProjectionSelectionImpl.setMProjectionSelectionSid(mProjectionSelection.getMProjectionSelectionSid());
		mProjectionSelectionImpl.setProjectionMasterSid(mProjectionSelection.getProjectionMasterSid());
		mProjectionSelectionImpl.setFieldValues(mProjectionSelection.getFieldValues());
		mProjectionSelectionImpl.setFieldName(mProjectionSelection.getFieldName());
		mProjectionSelectionImpl.setScreenName(mProjectionSelection.getScreenName());

		return mProjectionSelectionImpl;
	}

	/**
	 * Returns the m projection selection with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the m projection selection
	 * @return the m projection selection
	 * @throws NoSuchMProjectionSelectionException if a m projection selection with the primary key could not be found
	 */
	@Override
	public MProjectionSelection findByPrimaryKey(Serializable primaryKey)
		throws NoSuchMProjectionSelectionException {
		MProjectionSelection mProjectionSelection = fetchByPrimaryKey(primaryKey);

		if (mProjectionSelection == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchMProjectionSelectionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return mProjectionSelection;
	}

	/**
	 * Returns the m projection selection with the primary key or throws a {@link NoSuchMProjectionSelectionException} if it could not be found.
	 *
	 * @param mProjectionSelectionSid the primary key of the m projection selection
	 * @return the m projection selection
	 * @throws NoSuchMProjectionSelectionException if a m projection selection with the primary key could not be found
	 */
	@Override
	public MProjectionSelection findByPrimaryKey(int mProjectionSelectionSid)
		throws NoSuchMProjectionSelectionException {
		return findByPrimaryKey((Serializable)mProjectionSelectionSid);
	}

	/**
	 * Returns the m projection selection with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the m projection selection
	 * @return the m projection selection, or <code>null</code> if a m projection selection with the primary key could not be found
	 */
	@Override
	public MProjectionSelection fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(MProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
				MProjectionSelectionImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		MProjectionSelection mProjectionSelection = (MProjectionSelection)serializable;

		if (mProjectionSelection == null) {
			Session session = null;

			try {
				session = openSession();

				mProjectionSelection = (MProjectionSelection)session.get(MProjectionSelectionImpl.class,
						primaryKey);

				if (mProjectionSelection != null) {
					cacheResult(mProjectionSelection);
				}
				else {
					entityCache.putResult(MProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
						MProjectionSelectionImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(MProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
					MProjectionSelectionImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return mProjectionSelection;
	}

	/**
	 * Returns the m projection selection with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param mProjectionSelectionSid the primary key of the m projection selection
	 * @return the m projection selection, or <code>null</code> if a m projection selection with the primary key could not be found
	 */
	@Override
	public MProjectionSelection fetchByPrimaryKey(int mProjectionSelectionSid) {
		return fetchByPrimaryKey((Serializable)mProjectionSelectionSid);
	}

	@Override
	public Map<Serializable, MProjectionSelection> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, MProjectionSelection> map = new HashMap<Serializable, MProjectionSelection>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			MProjectionSelection mProjectionSelection = fetchByPrimaryKey(primaryKey);

			if (mProjectionSelection != null) {
				map.put(primaryKey, mProjectionSelection);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(MProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
					MProjectionSelectionImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (MProjectionSelection)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_MPROJECTIONSELECTION_WHERE_PKS_IN);

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

			for (MProjectionSelection mProjectionSelection : (List<MProjectionSelection>)q.list()) {
				map.put(mProjectionSelection.getPrimaryKeyObj(),
					mProjectionSelection);

				cacheResult(mProjectionSelection);

				uncachedPrimaryKeys.remove(mProjectionSelection.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(MProjectionSelectionModelImpl.ENTITY_CACHE_ENABLED,
					MProjectionSelectionImpl.class, primaryKey, nullModel);
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
	 * Returns all the m projection selections.
	 *
	 * @return the m projection selections
	 */
	@Override
	public List<MProjectionSelection> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the m projection selections.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of m projection selections
	 * @param end the upper bound of the range of m projection selections (not inclusive)
	 * @return the range of m projection selections
	 */
	@Override
	public List<MProjectionSelection> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the m projection selections.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of m projection selections
	 * @param end the upper bound of the range of m projection selections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of m projection selections
	 */
	@Override
	public List<MProjectionSelection> findAll(int start, int end,
		OrderByComparator<MProjectionSelection> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the m projection selections.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of m projection selections
	 * @param end the upper bound of the range of m projection selections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of m projection selections
	 */
	@Override
	public List<MProjectionSelection> findAll(int start, int end,
		OrderByComparator<MProjectionSelection> orderByComparator,
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

		List<MProjectionSelection> list = null;

		if (retrieveFromCache) {
			list = (List<MProjectionSelection>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_MPROJECTIONSELECTION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_MPROJECTIONSELECTION;

				if (pagination) {
					sql = sql.concat(MProjectionSelectionModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<MProjectionSelection>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<MProjectionSelection>)QueryUtil.list(q,
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
	 * Removes all the m projection selections from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (MProjectionSelection mProjectionSelection : findAll()) {
			remove(mProjectionSelection);
		}
	}

	/**
	 * Returns the number of m projection selections.
	 *
	 * @return the number of m projection selections
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_MPROJECTIONSELECTION);

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
		return MProjectionSelectionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the m projection selection persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(MProjectionSelectionImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_MPROJECTIONSELECTION = "SELECT mProjectionSelection FROM MProjectionSelection mProjectionSelection";
	private static final String _SQL_SELECT_MPROJECTIONSELECTION_WHERE_PKS_IN = "SELECT mProjectionSelection FROM MProjectionSelection mProjectionSelection WHERE M_PROJECTION_SELECTION_SID IN (";
	private static final String _SQL_COUNT_MPROJECTIONSELECTION = "SELECT COUNT(mProjectionSelection) FROM MProjectionSelection mProjectionSelection";
	private static final String _ORDER_BY_ENTITY_ALIAS = "mProjectionSelection.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No MProjectionSelection exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(MProjectionSelectionPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"mProjectionSelectionSid", "projectionMasterSid", "fieldValues",
				"fieldName", "screenName"
			});
}