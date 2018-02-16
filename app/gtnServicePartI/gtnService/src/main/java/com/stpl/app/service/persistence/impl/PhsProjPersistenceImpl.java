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

import com.stpl.app.exception.NoSuchPhsProjException;
import com.stpl.app.model.PhsProj;
import com.stpl.app.model.impl.PhsProjImpl;
import com.stpl.app.model.impl.PhsProjModelImpl;
import com.stpl.app.service.persistence.PhsProjPK;
import com.stpl.app.service.persistence.PhsProjPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the phs proj service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see PhsProjPersistence
 * @see com.stpl.app.service.persistence.PhsProjUtil
 * @generated
 */
@ProviderType
public class PhsProjPersistenceImpl extends BasePersistenceImpl<PhsProj>
	implements PhsProjPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link PhsProjUtil} to access the phs proj persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = PhsProjImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PhsProjModelImpl.ENTITY_CACHE_ENABLED,
			PhsProjModelImpl.FINDER_CACHE_ENABLED, PhsProjImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PhsProjModelImpl.ENTITY_CACHE_ENABLED,
			PhsProjModelImpl.FINDER_CACHE_ENABLED, PhsProjImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PhsProjModelImpl.ENTITY_CACHE_ENABLED,
			PhsProjModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public PhsProjPersistenceImpl() {
		setModelClass(PhsProj.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("adjustment", "ADJUSTMENT");
			dbColumnNames.put("periodSid", "PERIOD_SID");
			dbColumnNames.put("priceType", "PRICE_TYPE");
			dbColumnNames.put("projectionPrice", "PROJECTION_PRICE");
			dbColumnNames.put("notes", "NOTES");
			dbColumnNames.put("naProjDetailsSid", "NA_PROJ_DETAILS_SID");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the phs proj in the entity cache if it is enabled.
	 *
	 * @param phsProj the phs proj
	 */
	@Override
	public void cacheResult(PhsProj phsProj) {
		entityCache.putResult(PhsProjModelImpl.ENTITY_CACHE_ENABLED,
			PhsProjImpl.class, phsProj.getPrimaryKey(), phsProj);

		phsProj.resetOriginalValues();
	}

	/**
	 * Caches the phs projs in the entity cache if it is enabled.
	 *
	 * @param phsProjs the phs projs
	 */
	@Override
	public void cacheResult(List<PhsProj> phsProjs) {
		for (PhsProj phsProj : phsProjs) {
			if (entityCache.getResult(PhsProjModelImpl.ENTITY_CACHE_ENABLED,
						PhsProjImpl.class, phsProj.getPrimaryKey()) == null) {
				cacheResult(phsProj);
			}
			else {
				phsProj.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all phs projs.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(PhsProjImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the phs proj.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(PhsProj phsProj) {
		entityCache.removeResult(PhsProjModelImpl.ENTITY_CACHE_ENABLED,
			PhsProjImpl.class, phsProj.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<PhsProj> phsProjs) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (PhsProj phsProj : phsProjs) {
			entityCache.removeResult(PhsProjModelImpl.ENTITY_CACHE_ENABLED,
				PhsProjImpl.class, phsProj.getPrimaryKey());
		}
	}

	/**
	 * Creates a new phs proj with the primary key. Does not add the phs proj to the database.
	 *
	 * @param phsProjPK the primary key for the new phs proj
	 * @return the new phs proj
	 */
	@Override
	public PhsProj create(PhsProjPK phsProjPK) {
		PhsProj phsProj = new PhsProjImpl();

		phsProj.setNew(true);
		phsProj.setPrimaryKey(phsProjPK);

		return phsProj;
	}

	/**
	 * Removes the phs proj with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param phsProjPK the primary key of the phs proj
	 * @return the phs proj that was removed
	 * @throws NoSuchPhsProjException if a phs proj with the primary key could not be found
	 */
	@Override
	public PhsProj remove(PhsProjPK phsProjPK) throws NoSuchPhsProjException {
		return remove((Serializable)phsProjPK);
	}

	/**
	 * Removes the phs proj with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the phs proj
	 * @return the phs proj that was removed
	 * @throws NoSuchPhsProjException if a phs proj with the primary key could not be found
	 */
	@Override
	public PhsProj remove(Serializable primaryKey)
		throws NoSuchPhsProjException {
		Session session = null;

		try {
			session = openSession();

			PhsProj phsProj = (PhsProj)session.get(PhsProjImpl.class, primaryKey);

			if (phsProj == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchPhsProjException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(phsProj);
		}
		catch (NoSuchPhsProjException nsee) {
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
	protected PhsProj removeImpl(PhsProj phsProj) {
		phsProj = toUnwrappedModel(phsProj);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(phsProj)) {
				phsProj = (PhsProj)session.get(PhsProjImpl.class,
						phsProj.getPrimaryKeyObj());
			}

			if (phsProj != null) {
				session.delete(phsProj);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (phsProj != null) {
			clearCache(phsProj);
		}

		return phsProj;
	}

	@Override
	public PhsProj updateImpl(PhsProj phsProj) {
		phsProj = toUnwrappedModel(phsProj);

		boolean isNew = phsProj.isNew();

		Session session = null;

		try {
			session = openSession();

			if (phsProj.isNew()) {
				session.save(phsProj);

				phsProj.setNew(false);
			}
			else {
				phsProj = (PhsProj)session.merge(phsProj);
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

		entityCache.putResult(PhsProjModelImpl.ENTITY_CACHE_ENABLED,
			PhsProjImpl.class, phsProj.getPrimaryKey(), phsProj, false);

		phsProj.resetOriginalValues();

		return phsProj;
	}

	protected PhsProj toUnwrappedModel(PhsProj phsProj) {
		if (phsProj instanceof PhsProjImpl) {
			return phsProj;
		}

		PhsProjImpl phsProjImpl = new PhsProjImpl();

		phsProjImpl.setNew(phsProj.isNew());
		phsProjImpl.setPrimaryKey(phsProj.getPrimaryKey());

		phsProjImpl.setAdjustment(phsProj.getAdjustment());
		phsProjImpl.setPeriodSid(phsProj.getPeriodSid());
		phsProjImpl.setPriceType(phsProj.getPriceType());
		phsProjImpl.setProjectionPrice(phsProj.getProjectionPrice());
		phsProjImpl.setNotes(phsProj.getNotes());
		phsProjImpl.setNaProjDetailsSid(phsProj.getNaProjDetailsSid());

		return phsProjImpl;
	}

	/**
	 * Returns the phs proj with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the phs proj
	 * @return the phs proj
	 * @throws NoSuchPhsProjException if a phs proj with the primary key could not be found
	 */
	@Override
	public PhsProj findByPrimaryKey(Serializable primaryKey)
		throws NoSuchPhsProjException {
		PhsProj phsProj = fetchByPrimaryKey(primaryKey);

		if (phsProj == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchPhsProjException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return phsProj;
	}

	/**
	 * Returns the phs proj with the primary key or throws a {@link NoSuchPhsProjException} if it could not be found.
	 *
	 * @param phsProjPK the primary key of the phs proj
	 * @return the phs proj
	 * @throws NoSuchPhsProjException if a phs proj with the primary key could not be found
	 */
	@Override
	public PhsProj findByPrimaryKey(PhsProjPK phsProjPK)
		throws NoSuchPhsProjException {
		return findByPrimaryKey((Serializable)phsProjPK);
	}

	/**
	 * Returns the phs proj with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the phs proj
	 * @return the phs proj, or <code>null</code> if a phs proj with the primary key could not be found
	 */
	@Override
	public PhsProj fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(PhsProjModelImpl.ENTITY_CACHE_ENABLED,
				PhsProjImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		PhsProj phsProj = (PhsProj)serializable;

		if (phsProj == null) {
			Session session = null;

			try {
				session = openSession();

				phsProj = (PhsProj)session.get(PhsProjImpl.class, primaryKey);

				if (phsProj != null) {
					cacheResult(phsProj);
				}
				else {
					entityCache.putResult(PhsProjModelImpl.ENTITY_CACHE_ENABLED,
						PhsProjImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(PhsProjModelImpl.ENTITY_CACHE_ENABLED,
					PhsProjImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return phsProj;
	}

	/**
	 * Returns the phs proj with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param phsProjPK the primary key of the phs proj
	 * @return the phs proj, or <code>null</code> if a phs proj with the primary key could not be found
	 */
	@Override
	public PhsProj fetchByPrimaryKey(PhsProjPK phsProjPK) {
		return fetchByPrimaryKey((Serializable)phsProjPK);
	}

	@Override
	public Map<Serializable, PhsProj> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, PhsProj> map = new HashMap<Serializable, PhsProj>();

		for (Serializable primaryKey : primaryKeys) {
			PhsProj phsProj = fetchByPrimaryKey(primaryKey);

			if (phsProj != null) {
				map.put(primaryKey, phsProj);
			}
		}

		return map;
	}

	/**
	 * Returns all the phs projs.
	 *
	 * @return the phs projs
	 */
	@Override
	public List<PhsProj> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the phs projs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PhsProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of phs projs
	 * @param end the upper bound of the range of phs projs (not inclusive)
	 * @return the range of phs projs
	 */
	@Override
	public List<PhsProj> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the phs projs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PhsProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of phs projs
	 * @param end the upper bound of the range of phs projs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of phs projs
	 */
	@Override
	public List<PhsProj> findAll(int start, int end,
		OrderByComparator<PhsProj> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the phs projs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PhsProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of phs projs
	 * @param end the upper bound of the range of phs projs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of phs projs
	 */
	@Override
	public List<PhsProj> findAll(int start, int end,
		OrderByComparator<PhsProj> orderByComparator, boolean retrieveFromCache) {
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

		List<PhsProj> list = null;

		if (retrieveFromCache) {
			list = (List<PhsProj>)finderCache.getResult(finderPath, finderArgs,
					this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_PHSPROJ);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_PHSPROJ;

				if (pagination) {
					sql = sql.concat(PhsProjModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<PhsProj>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PhsProj>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Removes all the phs projs from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (PhsProj phsProj : findAll()) {
			remove(phsProj);
		}
	}

	/**
	 * Returns the number of phs projs.
	 *
	 * @return the number of phs projs
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_PHSPROJ);

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
		return PhsProjModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the phs proj persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(PhsProjImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_PHSPROJ = "SELECT phsProj FROM PhsProj phsProj";
	private static final String _SQL_COUNT_PHSPROJ = "SELECT COUNT(phsProj) FROM PhsProj phsProj";
	private static final String _ORDER_BY_ENTITY_ALIAS = "phsProj.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PhsProj exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(PhsProjPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"adjustment", "periodSid", "priceType", "projectionPrice",
				"notes", "naProjDetailsSid"
			});
}