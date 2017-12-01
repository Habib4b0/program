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

import com.stpl.app.exception.NoSuchNationalAssumptionsProjException;
import com.stpl.app.model.NationalAssumptionsProj;
import com.stpl.app.model.impl.NationalAssumptionsProjImpl;
import com.stpl.app.model.impl.NationalAssumptionsProjModelImpl;
import com.stpl.app.service.persistence.NationalAssumptionsProjPK;
import com.stpl.app.service.persistence.NationalAssumptionsProjPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the national assumptions proj service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see NationalAssumptionsProjPersistence
 * @see com.stpl.app.service.persistence.NationalAssumptionsProjUtil
 * @generated
 */
@ProviderType
public class NationalAssumptionsProjPersistenceImpl extends BasePersistenceImpl<NationalAssumptionsProj>
	implements NationalAssumptionsProjPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link NationalAssumptionsProjUtil} to access the national assumptions proj persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = NationalAssumptionsProjImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(NationalAssumptionsProjModelImpl.ENTITY_CACHE_ENABLED,
			NationalAssumptionsProjModelImpl.FINDER_CACHE_ENABLED,
			NationalAssumptionsProjImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(NationalAssumptionsProjModelImpl.ENTITY_CACHE_ENABLED,
			NationalAssumptionsProjModelImpl.FINDER_CACHE_ENABLED,
			NationalAssumptionsProjImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(NationalAssumptionsProjModelImpl.ENTITY_CACHE_ENABLED,
			NationalAssumptionsProjModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public NationalAssumptionsProjPersistenceImpl() {
		setModelClass(NationalAssumptionsProj.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("periodSid", "PERIOD_SID");
			dbColumnNames.put("itemMasterSid", "ITEM_MASTER_SID");
			dbColumnNames.put("priceType", "PRICE_TYPE");
			dbColumnNames.put("projectionPrice", "PROJECTION_PRICE");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the national assumptions proj in the entity cache if it is enabled.
	 *
	 * @param nationalAssumptionsProj the national assumptions proj
	 */
	@Override
	public void cacheResult(NationalAssumptionsProj nationalAssumptionsProj) {
		entityCache.putResult(NationalAssumptionsProjModelImpl.ENTITY_CACHE_ENABLED,
			NationalAssumptionsProjImpl.class,
			nationalAssumptionsProj.getPrimaryKey(), nationalAssumptionsProj);

		nationalAssumptionsProj.resetOriginalValues();
	}

	/**
	 * Caches the national assumptions projs in the entity cache if it is enabled.
	 *
	 * @param nationalAssumptionsProjs the national assumptions projs
	 */
	@Override
	public void cacheResult(
		List<NationalAssumptionsProj> nationalAssumptionsProjs) {
		for (NationalAssumptionsProj nationalAssumptionsProj : nationalAssumptionsProjs) {
			if (entityCache.getResult(
						NationalAssumptionsProjModelImpl.ENTITY_CACHE_ENABLED,
						NationalAssumptionsProjImpl.class,
						nationalAssumptionsProj.getPrimaryKey()) == null) {
				cacheResult(nationalAssumptionsProj);
			}
			else {
				nationalAssumptionsProj.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all national assumptions projs.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(NationalAssumptionsProjImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the national assumptions proj.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(NationalAssumptionsProj nationalAssumptionsProj) {
		entityCache.removeResult(NationalAssumptionsProjModelImpl.ENTITY_CACHE_ENABLED,
			NationalAssumptionsProjImpl.class,
			nationalAssumptionsProj.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<NationalAssumptionsProj> nationalAssumptionsProjs) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (NationalAssumptionsProj nationalAssumptionsProj : nationalAssumptionsProjs) {
			entityCache.removeResult(NationalAssumptionsProjModelImpl.ENTITY_CACHE_ENABLED,
				NationalAssumptionsProjImpl.class,
				nationalAssumptionsProj.getPrimaryKey());
		}
	}

	/**
	 * Creates a new national assumptions proj with the primary key. Does not add the national assumptions proj to the database.
	 *
	 * @param nationalAssumptionsProjPK the primary key for the new national assumptions proj
	 * @return the new national assumptions proj
	 */
	@Override
	public NationalAssumptionsProj create(
		NationalAssumptionsProjPK nationalAssumptionsProjPK) {
		NationalAssumptionsProj nationalAssumptionsProj = new NationalAssumptionsProjImpl();

		nationalAssumptionsProj.setNew(true);
		nationalAssumptionsProj.setPrimaryKey(nationalAssumptionsProjPK);

		return nationalAssumptionsProj;
	}

	/**
	 * Removes the national assumptions proj with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param nationalAssumptionsProjPK the primary key of the national assumptions proj
	 * @return the national assumptions proj that was removed
	 * @throws NoSuchNationalAssumptionsProjException if a national assumptions proj with the primary key could not be found
	 */
	@Override
	public NationalAssumptionsProj remove(
		NationalAssumptionsProjPK nationalAssumptionsProjPK)
		throws NoSuchNationalAssumptionsProjException {
		return remove((Serializable)nationalAssumptionsProjPK);
	}

	/**
	 * Removes the national assumptions proj with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the national assumptions proj
	 * @return the national assumptions proj that was removed
	 * @throws NoSuchNationalAssumptionsProjException if a national assumptions proj with the primary key could not be found
	 */
	@Override
	public NationalAssumptionsProj remove(Serializable primaryKey)
		throws NoSuchNationalAssumptionsProjException {
		Session session = null;

		try {
			session = openSession();

			NationalAssumptionsProj nationalAssumptionsProj = (NationalAssumptionsProj)session.get(NationalAssumptionsProjImpl.class,
					primaryKey);

			if (nationalAssumptionsProj == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchNationalAssumptionsProjException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(nationalAssumptionsProj);
		}
		catch (NoSuchNationalAssumptionsProjException nsee) {
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
	protected NationalAssumptionsProj removeImpl(
		NationalAssumptionsProj nationalAssumptionsProj) {
		nationalAssumptionsProj = toUnwrappedModel(nationalAssumptionsProj);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(nationalAssumptionsProj)) {
				nationalAssumptionsProj = (NationalAssumptionsProj)session.get(NationalAssumptionsProjImpl.class,
						nationalAssumptionsProj.getPrimaryKeyObj());
			}

			if (nationalAssumptionsProj != null) {
				session.delete(nationalAssumptionsProj);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (nationalAssumptionsProj != null) {
			clearCache(nationalAssumptionsProj);
		}

		return nationalAssumptionsProj;
	}

	@Override
	public NationalAssumptionsProj updateImpl(
		NationalAssumptionsProj nationalAssumptionsProj) {
		nationalAssumptionsProj = toUnwrappedModel(nationalAssumptionsProj);

		boolean isNew = nationalAssumptionsProj.isNew();

		Session session = null;

		try {
			session = openSession();

			if (nationalAssumptionsProj.isNew()) {
				session.save(nationalAssumptionsProj);

				nationalAssumptionsProj.setNew(false);
			}
			else {
				nationalAssumptionsProj = (NationalAssumptionsProj)session.merge(nationalAssumptionsProj);
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

		entityCache.putResult(NationalAssumptionsProjModelImpl.ENTITY_CACHE_ENABLED,
			NationalAssumptionsProjImpl.class,
			nationalAssumptionsProj.getPrimaryKey(), nationalAssumptionsProj,
			false);

		nationalAssumptionsProj.resetOriginalValues();

		return nationalAssumptionsProj;
	}

	protected NationalAssumptionsProj toUnwrappedModel(
		NationalAssumptionsProj nationalAssumptionsProj) {
		if (nationalAssumptionsProj instanceof NationalAssumptionsProjImpl) {
			return nationalAssumptionsProj;
		}

		NationalAssumptionsProjImpl nationalAssumptionsProjImpl = new NationalAssumptionsProjImpl();

		nationalAssumptionsProjImpl.setNew(nationalAssumptionsProj.isNew());
		nationalAssumptionsProjImpl.setPrimaryKey(nationalAssumptionsProj.getPrimaryKey());

		nationalAssumptionsProjImpl.setPeriodSid(nationalAssumptionsProj.getPeriodSid());
		nationalAssumptionsProjImpl.setItemMasterSid(nationalAssumptionsProj.getItemMasterSid());
		nationalAssumptionsProjImpl.setPriceType(nationalAssumptionsProj.getPriceType());
		nationalAssumptionsProjImpl.setProjectionPrice(nationalAssumptionsProj.getProjectionPrice());

		return nationalAssumptionsProjImpl;
	}

	/**
	 * Returns the national assumptions proj with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the national assumptions proj
	 * @return the national assumptions proj
	 * @throws NoSuchNationalAssumptionsProjException if a national assumptions proj with the primary key could not be found
	 */
	@Override
	public NationalAssumptionsProj findByPrimaryKey(Serializable primaryKey)
		throws NoSuchNationalAssumptionsProjException {
		NationalAssumptionsProj nationalAssumptionsProj = fetchByPrimaryKey(primaryKey);

		if (nationalAssumptionsProj == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchNationalAssumptionsProjException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return nationalAssumptionsProj;
	}

	/**
	 * Returns the national assumptions proj with the primary key or throws a {@link NoSuchNationalAssumptionsProjException} if it could not be found.
	 *
	 * @param nationalAssumptionsProjPK the primary key of the national assumptions proj
	 * @return the national assumptions proj
	 * @throws NoSuchNationalAssumptionsProjException if a national assumptions proj with the primary key could not be found
	 */
	@Override
	public NationalAssumptionsProj findByPrimaryKey(
		NationalAssumptionsProjPK nationalAssumptionsProjPK)
		throws NoSuchNationalAssumptionsProjException {
		return findByPrimaryKey((Serializable)nationalAssumptionsProjPK);
	}

	/**
	 * Returns the national assumptions proj with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the national assumptions proj
	 * @return the national assumptions proj, or <code>null</code> if a national assumptions proj with the primary key could not be found
	 */
	@Override
	public NationalAssumptionsProj fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(NationalAssumptionsProjModelImpl.ENTITY_CACHE_ENABLED,
				NationalAssumptionsProjImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		NationalAssumptionsProj nationalAssumptionsProj = (NationalAssumptionsProj)serializable;

		if (nationalAssumptionsProj == null) {
			Session session = null;

			try {
				session = openSession();

				nationalAssumptionsProj = (NationalAssumptionsProj)session.get(NationalAssumptionsProjImpl.class,
						primaryKey);

				if (nationalAssumptionsProj != null) {
					cacheResult(nationalAssumptionsProj);
				}
				else {
					entityCache.putResult(NationalAssumptionsProjModelImpl.ENTITY_CACHE_ENABLED,
						NationalAssumptionsProjImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(NationalAssumptionsProjModelImpl.ENTITY_CACHE_ENABLED,
					NationalAssumptionsProjImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return nationalAssumptionsProj;
	}

	/**
	 * Returns the national assumptions proj with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param nationalAssumptionsProjPK the primary key of the national assumptions proj
	 * @return the national assumptions proj, or <code>null</code> if a national assumptions proj with the primary key could not be found
	 */
	@Override
	public NationalAssumptionsProj fetchByPrimaryKey(
		NationalAssumptionsProjPK nationalAssumptionsProjPK) {
		return fetchByPrimaryKey((Serializable)nationalAssumptionsProjPK);
	}

	@Override
	public Map<Serializable, NationalAssumptionsProj> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, NationalAssumptionsProj> map = new HashMap<Serializable, NationalAssumptionsProj>();

		for (Serializable primaryKey : primaryKeys) {
			NationalAssumptionsProj nationalAssumptionsProj = fetchByPrimaryKey(primaryKey);

			if (nationalAssumptionsProj != null) {
				map.put(primaryKey, nationalAssumptionsProj);
			}
		}

		return map;
	}

	/**
	 * Returns all the national assumptions projs.
	 *
	 * @return the national assumptions projs
	 */
	@Override
	public List<NationalAssumptionsProj> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the national assumptions projs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NationalAssumptionsProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of national assumptions projs
	 * @param end the upper bound of the range of national assumptions projs (not inclusive)
	 * @return the range of national assumptions projs
	 */
	@Override
	public List<NationalAssumptionsProj> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the national assumptions projs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NationalAssumptionsProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of national assumptions projs
	 * @param end the upper bound of the range of national assumptions projs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of national assumptions projs
	 */
	@Override
	public List<NationalAssumptionsProj> findAll(int start, int end,
		OrderByComparator<NationalAssumptionsProj> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the national assumptions projs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NationalAssumptionsProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of national assumptions projs
	 * @param end the upper bound of the range of national assumptions projs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of national assumptions projs
	 */
	@Override
	public List<NationalAssumptionsProj> findAll(int start, int end,
		OrderByComparator<NationalAssumptionsProj> orderByComparator,
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

		List<NationalAssumptionsProj> list = null;

		if (retrieveFromCache) {
			list = (List<NationalAssumptionsProj>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_NATIONALASSUMPTIONSPROJ);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_NATIONALASSUMPTIONSPROJ;

				if (pagination) {
					sql = sql.concat(NationalAssumptionsProjModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<NationalAssumptionsProj>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<NationalAssumptionsProj>)QueryUtil.list(q,
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
	 * Removes all the national assumptions projs from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (NationalAssumptionsProj nationalAssumptionsProj : findAll()) {
			remove(nationalAssumptionsProj);
		}
	}

	/**
	 * Returns the number of national assumptions projs.
	 *
	 * @return the number of national assumptions projs
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_NATIONALASSUMPTIONSPROJ);

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
		return NationalAssumptionsProjModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the national assumptions proj persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(NationalAssumptionsProjImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_NATIONALASSUMPTIONSPROJ = "SELECT nationalAssumptionsProj FROM NationalAssumptionsProj nationalAssumptionsProj";
	private static final String _SQL_COUNT_NATIONALASSUMPTIONSPROJ = "SELECT COUNT(nationalAssumptionsProj) FROM NationalAssumptionsProj nationalAssumptionsProj";
	private static final String _ORDER_BY_ENTITY_ALIAS = "nationalAssumptionsProj.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No NationalAssumptionsProj exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(NationalAssumptionsProjPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"periodSid", "itemMasterSid", "priceType", "projectionPrice"
			});
}