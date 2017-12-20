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

import com.stpl.app.exception.NoSuchNmActualPpaException;
import com.stpl.app.model.NmActualPpa;
import com.stpl.app.model.impl.NmActualPpaImpl;
import com.stpl.app.model.impl.NmActualPpaModelImpl;
import com.stpl.app.service.persistence.NmActualPpaPK;
import com.stpl.app.service.persistence.NmActualPpaPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the nm actual ppa service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see NmActualPpaPersistence
 * @see com.stpl.app.service.persistence.NmActualPpaUtil
 * @generated
 */
@ProviderType
public class NmActualPpaPersistenceImpl extends BasePersistenceImpl<NmActualPpa>
	implements NmActualPpaPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link NmActualPpaUtil} to access the nm actual ppa persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = NmActualPpaImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(NmActualPpaModelImpl.ENTITY_CACHE_ENABLED,
			NmActualPpaModelImpl.FINDER_CACHE_ENABLED, NmActualPpaImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(NmActualPpaModelImpl.ENTITY_CACHE_ENABLED,
			NmActualPpaModelImpl.FINDER_CACHE_ENABLED, NmActualPpaImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(NmActualPpaModelImpl.ENTITY_CACHE_ENABLED,
			NmActualPpaModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public NmActualPpaPersistenceImpl() {
		setModelClass(NmActualPpa.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("actualRate", "ACTUAL_RATE");
			dbColumnNames.put("periodSid", "PERIOD_SID");
			dbColumnNames.put("projectionDetailsSid", "PROJECTION_DETAILS_SID");
			dbColumnNames.put("actualDiscountDollar", "ACTUAL_DISCOUNT_DOLLAR");
			dbColumnNames.put("actualDiscountUnits", "ACTUAL_DISCOUNT_UNITS");
			dbColumnNames.put("actualSales", "ACTUAL_SALES");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the nm actual ppa in the entity cache if it is enabled.
	 *
	 * @param nmActualPpa the nm actual ppa
	 */
	@Override
	public void cacheResult(NmActualPpa nmActualPpa) {
		entityCache.putResult(NmActualPpaModelImpl.ENTITY_CACHE_ENABLED,
			NmActualPpaImpl.class, nmActualPpa.getPrimaryKey(), nmActualPpa);

		nmActualPpa.resetOriginalValues();
	}

	/**
	 * Caches the nm actual ppas in the entity cache if it is enabled.
	 *
	 * @param nmActualPpas the nm actual ppas
	 */
	@Override
	public void cacheResult(List<NmActualPpa> nmActualPpas) {
		for (NmActualPpa nmActualPpa : nmActualPpas) {
			if (entityCache.getResult(
						NmActualPpaModelImpl.ENTITY_CACHE_ENABLED,
						NmActualPpaImpl.class, nmActualPpa.getPrimaryKey()) == null) {
				cacheResult(nmActualPpa);
			}
			else {
				nmActualPpa.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all nm actual ppas.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(NmActualPpaImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the nm actual ppa.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(NmActualPpa nmActualPpa) {
		entityCache.removeResult(NmActualPpaModelImpl.ENTITY_CACHE_ENABLED,
			NmActualPpaImpl.class, nmActualPpa.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<NmActualPpa> nmActualPpas) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (NmActualPpa nmActualPpa : nmActualPpas) {
			entityCache.removeResult(NmActualPpaModelImpl.ENTITY_CACHE_ENABLED,
				NmActualPpaImpl.class, nmActualPpa.getPrimaryKey());
		}
	}

	/**
	 * Creates a new nm actual ppa with the primary key. Does not add the nm actual ppa to the database.
	 *
	 * @param nmActualPpaPK the primary key for the new nm actual ppa
	 * @return the new nm actual ppa
	 */
	@Override
	public NmActualPpa create(NmActualPpaPK nmActualPpaPK) {
		NmActualPpa nmActualPpa = new NmActualPpaImpl();

		nmActualPpa.setNew(true);
		nmActualPpa.setPrimaryKey(nmActualPpaPK);

		return nmActualPpa;
	}

	/**
	 * Removes the nm actual ppa with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param nmActualPpaPK the primary key of the nm actual ppa
	 * @return the nm actual ppa that was removed
	 * @throws NoSuchNmActualPpaException if a nm actual ppa with the primary key could not be found
	 */
	@Override
	public NmActualPpa remove(NmActualPpaPK nmActualPpaPK)
		throws NoSuchNmActualPpaException {
		return remove((Serializable)nmActualPpaPK);
	}

	/**
	 * Removes the nm actual ppa with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the nm actual ppa
	 * @return the nm actual ppa that was removed
	 * @throws NoSuchNmActualPpaException if a nm actual ppa with the primary key could not be found
	 */
	@Override
	public NmActualPpa remove(Serializable primaryKey)
		throws NoSuchNmActualPpaException {
		Session session = null;

		try {
			session = openSession();

			NmActualPpa nmActualPpa = (NmActualPpa)session.get(NmActualPpaImpl.class,
					primaryKey);

			if (nmActualPpa == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchNmActualPpaException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(nmActualPpa);
		}
		catch (NoSuchNmActualPpaException nsee) {
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
	protected NmActualPpa removeImpl(NmActualPpa nmActualPpa) {
		nmActualPpa = toUnwrappedModel(nmActualPpa);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(nmActualPpa)) {
				nmActualPpa = (NmActualPpa)session.get(NmActualPpaImpl.class,
						nmActualPpa.getPrimaryKeyObj());
			}

			if (nmActualPpa != null) {
				session.delete(nmActualPpa);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (nmActualPpa != null) {
			clearCache(nmActualPpa);
		}

		return nmActualPpa;
	}

	@Override
	public NmActualPpa updateImpl(NmActualPpa nmActualPpa) {
		nmActualPpa = toUnwrappedModel(nmActualPpa);

		boolean isNew = nmActualPpa.isNew();

		Session session = null;

		try {
			session = openSession();

			if (nmActualPpa.isNew()) {
				session.save(nmActualPpa);

				nmActualPpa.setNew(false);
			}
			else {
				nmActualPpa = (NmActualPpa)session.merge(nmActualPpa);
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

		entityCache.putResult(NmActualPpaModelImpl.ENTITY_CACHE_ENABLED,
			NmActualPpaImpl.class, nmActualPpa.getPrimaryKey(), nmActualPpa,
			false);

		nmActualPpa.resetOriginalValues();

		return nmActualPpa;
	}

	protected NmActualPpa toUnwrappedModel(NmActualPpa nmActualPpa) {
		if (nmActualPpa instanceof NmActualPpaImpl) {
			return nmActualPpa;
		}

		NmActualPpaImpl nmActualPpaImpl = new NmActualPpaImpl();

		nmActualPpaImpl.setNew(nmActualPpa.isNew());
		nmActualPpaImpl.setPrimaryKey(nmActualPpa.getPrimaryKey());

		nmActualPpaImpl.setActualRate(nmActualPpa.getActualRate());
		nmActualPpaImpl.setPeriodSid(nmActualPpa.getPeriodSid());
		nmActualPpaImpl.setProjectionDetailsSid(nmActualPpa.getProjectionDetailsSid());
		nmActualPpaImpl.setActualDiscountDollar(nmActualPpa.getActualDiscountDollar());
		nmActualPpaImpl.setActualDiscountUnits(nmActualPpa.getActualDiscountUnits());
		nmActualPpaImpl.setActualSales(nmActualPpa.getActualSales());

		return nmActualPpaImpl;
	}

	/**
	 * Returns the nm actual ppa with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the nm actual ppa
	 * @return the nm actual ppa
	 * @throws NoSuchNmActualPpaException if a nm actual ppa with the primary key could not be found
	 */
	@Override
	public NmActualPpa findByPrimaryKey(Serializable primaryKey)
		throws NoSuchNmActualPpaException {
		NmActualPpa nmActualPpa = fetchByPrimaryKey(primaryKey);

		if (nmActualPpa == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchNmActualPpaException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return nmActualPpa;
	}

	/**
	 * Returns the nm actual ppa with the primary key or throws a {@link NoSuchNmActualPpaException} if it could not be found.
	 *
	 * @param nmActualPpaPK the primary key of the nm actual ppa
	 * @return the nm actual ppa
	 * @throws NoSuchNmActualPpaException if a nm actual ppa with the primary key could not be found
	 */
	@Override
	public NmActualPpa findByPrimaryKey(NmActualPpaPK nmActualPpaPK)
		throws NoSuchNmActualPpaException {
		return findByPrimaryKey((Serializable)nmActualPpaPK);
	}

	/**
	 * Returns the nm actual ppa with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the nm actual ppa
	 * @return the nm actual ppa, or <code>null</code> if a nm actual ppa with the primary key could not be found
	 */
	@Override
	public NmActualPpa fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(NmActualPpaModelImpl.ENTITY_CACHE_ENABLED,
				NmActualPpaImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		NmActualPpa nmActualPpa = (NmActualPpa)serializable;

		if (nmActualPpa == null) {
			Session session = null;

			try {
				session = openSession();

				nmActualPpa = (NmActualPpa)session.get(NmActualPpaImpl.class,
						primaryKey);

				if (nmActualPpa != null) {
					cacheResult(nmActualPpa);
				}
				else {
					entityCache.putResult(NmActualPpaModelImpl.ENTITY_CACHE_ENABLED,
						NmActualPpaImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(NmActualPpaModelImpl.ENTITY_CACHE_ENABLED,
					NmActualPpaImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return nmActualPpa;
	}

	/**
	 * Returns the nm actual ppa with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param nmActualPpaPK the primary key of the nm actual ppa
	 * @return the nm actual ppa, or <code>null</code> if a nm actual ppa with the primary key could not be found
	 */
	@Override
	public NmActualPpa fetchByPrimaryKey(NmActualPpaPK nmActualPpaPK) {
		return fetchByPrimaryKey((Serializable)nmActualPpaPK);
	}

	@Override
	public Map<Serializable, NmActualPpa> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, NmActualPpa> map = new HashMap<Serializable, NmActualPpa>();

		for (Serializable primaryKey : primaryKeys) {
			NmActualPpa nmActualPpa = fetchByPrimaryKey(primaryKey);

			if (nmActualPpa != null) {
				map.put(primaryKey, nmActualPpa);
			}
		}

		return map;
	}

	/**
	 * Returns all the nm actual ppas.
	 *
	 * @return the nm actual ppas
	 */
	@Override
	public List<NmActualPpa> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the nm actual ppas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NmActualPpaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of nm actual ppas
	 * @param end the upper bound of the range of nm actual ppas (not inclusive)
	 * @return the range of nm actual ppas
	 */
	@Override
	public List<NmActualPpa> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the nm actual ppas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NmActualPpaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of nm actual ppas
	 * @param end the upper bound of the range of nm actual ppas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of nm actual ppas
	 */
	@Override
	public List<NmActualPpa> findAll(int start, int end,
		OrderByComparator<NmActualPpa> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the nm actual ppas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NmActualPpaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of nm actual ppas
	 * @param end the upper bound of the range of nm actual ppas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of nm actual ppas
	 */
	@Override
	public List<NmActualPpa> findAll(int start, int end,
		OrderByComparator<NmActualPpa> orderByComparator,
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

		List<NmActualPpa> list = null;

		if (retrieveFromCache) {
			list = (List<NmActualPpa>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_NMACTUALPPA);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_NMACTUALPPA;

				if (pagination) {
					sql = sql.concat(NmActualPpaModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<NmActualPpa>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<NmActualPpa>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the nm actual ppas from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (NmActualPpa nmActualPpa : findAll()) {
			remove(nmActualPpa);
		}
	}

	/**
	 * Returns the number of nm actual ppas.
	 *
	 * @return the number of nm actual ppas
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_NMACTUALPPA);

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
		return NmActualPpaModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the nm actual ppa persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(NmActualPpaImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_NMACTUALPPA = "SELECT nmActualPpa FROM NmActualPpa nmActualPpa";
	private static final String _SQL_COUNT_NMACTUALPPA = "SELECT COUNT(nmActualPpa) FROM NmActualPpa nmActualPpa";
	private static final String _ORDER_BY_ENTITY_ALIAS = "nmActualPpa.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No NmActualPpa exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(NmActualPpaPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"actualRate", "periodSid", "projectionDetailsSid",
				"actualDiscountDollar", "actualDiscountUnits", "actualSales"
			});
}