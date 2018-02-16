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

import com.stpl.app.exception.NoSuchMedicaidUraProjException;
import com.stpl.app.model.MedicaidUraProj;
import com.stpl.app.model.impl.MedicaidUraProjImpl;
import com.stpl.app.model.impl.MedicaidUraProjModelImpl;
import com.stpl.app.service.persistence.MedicaidUraProjPK;
import com.stpl.app.service.persistence.MedicaidUraProjPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the medicaid ura proj service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see MedicaidUraProjPersistence
 * @see com.stpl.app.service.persistence.MedicaidUraProjUtil
 * @generated
 */
@ProviderType
public class MedicaidUraProjPersistenceImpl extends BasePersistenceImpl<MedicaidUraProj>
	implements MedicaidUraProjPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link MedicaidUraProjUtil} to access the medicaid ura proj persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = MedicaidUraProjImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(MedicaidUraProjModelImpl.ENTITY_CACHE_ENABLED,
			MedicaidUraProjModelImpl.FINDER_CACHE_ENABLED,
			MedicaidUraProjImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(MedicaidUraProjModelImpl.ENTITY_CACHE_ENABLED,
			MedicaidUraProjModelImpl.FINDER_CACHE_ENABLED,
			MedicaidUraProjImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MedicaidUraProjModelImpl.ENTITY_CACHE_ENABLED,
			MedicaidUraProjModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public MedicaidUraProjPersistenceImpl() {
		setModelClass(MedicaidUraProj.class);

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
	 * Caches the medicaid ura proj in the entity cache if it is enabled.
	 *
	 * @param medicaidUraProj the medicaid ura proj
	 */
	@Override
	public void cacheResult(MedicaidUraProj medicaidUraProj) {
		entityCache.putResult(MedicaidUraProjModelImpl.ENTITY_CACHE_ENABLED,
			MedicaidUraProjImpl.class, medicaidUraProj.getPrimaryKey(),
			medicaidUraProj);

		medicaidUraProj.resetOriginalValues();
	}

	/**
	 * Caches the medicaid ura projs in the entity cache if it is enabled.
	 *
	 * @param medicaidUraProjs the medicaid ura projs
	 */
	@Override
	public void cacheResult(List<MedicaidUraProj> medicaidUraProjs) {
		for (MedicaidUraProj medicaidUraProj : medicaidUraProjs) {
			if (entityCache.getResult(
						MedicaidUraProjModelImpl.ENTITY_CACHE_ENABLED,
						MedicaidUraProjImpl.class,
						medicaidUraProj.getPrimaryKey()) == null) {
				cacheResult(medicaidUraProj);
			}
			else {
				medicaidUraProj.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all medicaid ura projs.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(MedicaidUraProjImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the medicaid ura proj.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(MedicaidUraProj medicaidUraProj) {
		entityCache.removeResult(MedicaidUraProjModelImpl.ENTITY_CACHE_ENABLED,
			MedicaidUraProjImpl.class, medicaidUraProj.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<MedicaidUraProj> medicaidUraProjs) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (MedicaidUraProj medicaidUraProj : medicaidUraProjs) {
			entityCache.removeResult(MedicaidUraProjModelImpl.ENTITY_CACHE_ENABLED,
				MedicaidUraProjImpl.class, medicaidUraProj.getPrimaryKey());
		}
	}

	/**
	 * Creates a new medicaid ura proj with the primary key. Does not add the medicaid ura proj to the database.
	 *
	 * @param medicaidUraProjPK the primary key for the new medicaid ura proj
	 * @return the new medicaid ura proj
	 */
	@Override
	public MedicaidUraProj create(MedicaidUraProjPK medicaidUraProjPK) {
		MedicaidUraProj medicaidUraProj = new MedicaidUraProjImpl();

		medicaidUraProj.setNew(true);
		medicaidUraProj.setPrimaryKey(medicaidUraProjPK);

		return medicaidUraProj;
	}

	/**
	 * Removes the medicaid ura proj with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param medicaidUraProjPK the primary key of the medicaid ura proj
	 * @return the medicaid ura proj that was removed
	 * @throws NoSuchMedicaidUraProjException if a medicaid ura proj with the primary key could not be found
	 */
	@Override
	public MedicaidUraProj remove(MedicaidUraProjPK medicaidUraProjPK)
		throws NoSuchMedicaidUraProjException {
		return remove((Serializable)medicaidUraProjPK);
	}

	/**
	 * Removes the medicaid ura proj with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the medicaid ura proj
	 * @return the medicaid ura proj that was removed
	 * @throws NoSuchMedicaidUraProjException if a medicaid ura proj with the primary key could not be found
	 */
	@Override
	public MedicaidUraProj remove(Serializable primaryKey)
		throws NoSuchMedicaidUraProjException {
		Session session = null;

		try {
			session = openSession();

			MedicaidUraProj medicaidUraProj = (MedicaidUraProj)session.get(MedicaidUraProjImpl.class,
					primaryKey);

			if (medicaidUraProj == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchMedicaidUraProjException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(medicaidUraProj);
		}
		catch (NoSuchMedicaidUraProjException nsee) {
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
	protected MedicaidUraProj removeImpl(MedicaidUraProj medicaidUraProj) {
		medicaidUraProj = toUnwrappedModel(medicaidUraProj);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(medicaidUraProj)) {
				medicaidUraProj = (MedicaidUraProj)session.get(MedicaidUraProjImpl.class,
						medicaidUraProj.getPrimaryKeyObj());
			}

			if (medicaidUraProj != null) {
				session.delete(medicaidUraProj);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (medicaidUraProj != null) {
			clearCache(medicaidUraProj);
		}

		return medicaidUraProj;
	}

	@Override
	public MedicaidUraProj updateImpl(MedicaidUraProj medicaidUraProj) {
		medicaidUraProj = toUnwrappedModel(medicaidUraProj);

		boolean isNew = medicaidUraProj.isNew();

		Session session = null;

		try {
			session = openSession();

			if (medicaidUraProj.isNew()) {
				session.save(medicaidUraProj);

				medicaidUraProj.setNew(false);
			}
			else {
				medicaidUraProj = (MedicaidUraProj)session.merge(medicaidUraProj);
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

		entityCache.putResult(MedicaidUraProjModelImpl.ENTITY_CACHE_ENABLED,
			MedicaidUraProjImpl.class, medicaidUraProj.getPrimaryKey(),
			medicaidUraProj, false);

		medicaidUraProj.resetOriginalValues();

		return medicaidUraProj;
	}

	protected MedicaidUraProj toUnwrappedModel(MedicaidUraProj medicaidUraProj) {
		if (medicaidUraProj instanceof MedicaidUraProjImpl) {
			return medicaidUraProj;
		}

		MedicaidUraProjImpl medicaidUraProjImpl = new MedicaidUraProjImpl();

		medicaidUraProjImpl.setNew(medicaidUraProj.isNew());
		medicaidUraProjImpl.setPrimaryKey(medicaidUraProj.getPrimaryKey());

		medicaidUraProjImpl.setAdjustment(medicaidUraProj.getAdjustment());
		medicaidUraProjImpl.setPeriodSid(medicaidUraProj.getPeriodSid());
		medicaidUraProjImpl.setPriceType(medicaidUraProj.getPriceType());
		medicaidUraProjImpl.setProjectionPrice(medicaidUraProj.getProjectionPrice());
		medicaidUraProjImpl.setNotes(medicaidUraProj.getNotes());
		medicaidUraProjImpl.setNaProjDetailsSid(medicaidUraProj.getNaProjDetailsSid());

		return medicaidUraProjImpl;
	}

	/**
	 * Returns the medicaid ura proj with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the medicaid ura proj
	 * @return the medicaid ura proj
	 * @throws NoSuchMedicaidUraProjException if a medicaid ura proj with the primary key could not be found
	 */
	@Override
	public MedicaidUraProj findByPrimaryKey(Serializable primaryKey)
		throws NoSuchMedicaidUraProjException {
		MedicaidUraProj medicaidUraProj = fetchByPrimaryKey(primaryKey);

		if (medicaidUraProj == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchMedicaidUraProjException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return medicaidUraProj;
	}

	/**
	 * Returns the medicaid ura proj with the primary key or throws a {@link NoSuchMedicaidUraProjException} if it could not be found.
	 *
	 * @param medicaidUraProjPK the primary key of the medicaid ura proj
	 * @return the medicaid ura proj
	 * @throws NoSuchMedicaidUraProjException if a medicaid ura proj with the primary key could not be found
	 */
	@Override
	public MedicaidUraProj findByPrimaryKey(MedicaidUraProjPK medicaidUraProjPK)
		throws NoSuchMedicaidUraProjException {
		return findByPrimaryKey((Serializable)medicaidUraProjPK);
	}

	/**
	 * Returns the medicaid ura proj with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the medicaid ura proj
	 * @return the medicaid ura proj, or <code>null</code> if a medicaid ura proj with the primary key could not be found
	 */
	@Override
	public MedicaidUraProj fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(MedicaidUraProjModelImpl.ENTITY_CACHE_ENABLED,
				MedicaidUraProjImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		MedicaidUraProj medicaidUraProj = (MedicaidUraProj)serializable;

		if (medicaidUraProj == null) {
			Session session = null;

			try {
				session = openSession();

				medicaidUraProj = (MedicaidUraProj)session.get(MedicaidUraProjImpl.class,
						primaryKey);

				if (medicaidUraProj != null) {
					cacheResult(medicaidUraProj);
				}
				else {
					entityCache.putResult(MedicaidUraProjModelImpl.ENTITY_CACHE_ENABLED,
						MedicaidUraProjImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(MedicaidUraProjModelImpl.ENTITY_CACHE_ENABLED,
					MedicaidUraProjImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return medicaidUraProj;
	}

	/**
	 * Returns the medicaid ura proj with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param medicaidUraProjPK the primary key of the medicaid ura proj
	 * @return the medicaid ura proj, or <code>null</code> if a medicaid ura proj with the primary key could not be found
	 */
	@Override
	public MedicaidUraProj fetchByPrimaryKey(
		MedicaidUraProjPK medicaidUraProjPK) {
		return fetchByPrimaryKey((Serializable)medicaidUraProjPK);
	}

	@Override
	public Map<Serializable, MedicaidUraProj> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, MedicaidUraProj> map = new HashMap<Serializable, MedicaidUraProj>();

		for (Serializable primaryKey : primaryKeys) {
			MedicaidUraProj medicaidUraProj = fetchByPrimaryKey(primaryKey);

			if (medicaidUraProj != null) {
				map.put(primaryKey, medicaidUraProj);
			}
		}

		return map;
	}

	/**
	 * Returns all the medicaid ura projs.
	 *
	 * @return the medicaid ura projs
	 */
	@Override
	public List<MedicaidUraProj> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the medicaid ura projs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MedicaidUraProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of medicaid ura projs
	 * @param end the upper bound of the range of medicaid ura projs (not inclusive)
	 * @return the range of medicaid ura projs
	 */
	@Override
	public List<MedicaidUraProj> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the medicaid ura projs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MedicaidUraProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of medicaid ura projs
	 * @param end the upper bound of the range of medicaid ura projs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of medicaid ura projs
	 */
	@Override
	public List<MedicaidUraProj> findAll(int start, int end,
		OrderByComparator<MedicaidUraProj> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the medicaid ura projs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MedicaidUraProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of medicaid ura projs
	 * @param end the upper bound of the range of medicaid ura projs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of medicaid ura projs
	 */
	@Override
	public List<MedicaidUraProj> findAll(int start, int end,
		OrderByComparator<MedicaidUraProj> orderByComparator,
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

		List<MedicaidUraProj> list = null;

		if (retrieveFromCache) {
			list = (List<MedicaidUraProj>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_MEDICAIDURAPROJ);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_MEDICAIDURAPROJ;

				if (pagination) {
					sql = sql.concat(MedicaidUraProjModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<MedicaidUraProj>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<MedicaidUraProj>)QueryUtil.list(q,
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
	 * Removes all the medicaid ura projs from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (MedicaidUraProj medicaidUraProj : findAll()) {
			remove(medicaidUraProj);
		}
	}

	/**
	 * Returns the number of medicaid ura projs.
	 *
	 * @return the number of medicaid ura projs
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_MEDICAIDURAPROJ);

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
		return MedicaidUraProjModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the medicaid ura proj persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(MedicaidUraProjImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_MEDICAIDURAPROJ = "SELECT medicaidUraProj FROM MedicaidUraProj medicaidUraProj";
	private static final String _SQL_COUNT_MEDICAIDURAPROJ = "SELECT COUNT(medicaidUraProj) FROM MedicaidUraProj medicaidUraProj";
	private static final String _ORDER_BY_ENTITY_ALIAS = "medicaidUraProj.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No MedicaidUraProj exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(MedicaidUraProjPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"adjustment", "periodSid", "priceType", "projectionPrice",
				"notes", "naProjDetailsSid"
			});
}