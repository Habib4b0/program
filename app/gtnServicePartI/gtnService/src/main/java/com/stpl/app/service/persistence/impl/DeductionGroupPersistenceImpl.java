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

import com.stpl.app.exception.NoSuchDeductionGroupException;
import com.stpl.app.model.DeductionGroup;
import com.stpl.app.model.impl.DeductionGroupImpl;
import com.stpl.app.model.impl.DeductionGroupModelImpl;
import com.stpl.app.service.persistence.DeductionGroupPersistence;

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
 * The persistence implementation for the deduction group service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see DeductionGroupPersistence
 * @see com.stpl.app.service.persistence.DeductionGroupUtil
 * @generated
 */
@ProviderType
public class DeductionGroupPersistenceImpl extends BasePersistenceImpl<DeductionGroup>
	implements DeductionGroupPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DeductionGroupUtil} to access the deduction group persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = DeductionGroupImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(DeductionGroupModelImpl.ENTITY_CACHE_ENABLED,
			DeductionGroupModelImpl.FINDER_CACHE_ENABLED,
			DeductionGroupImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(DeductionGroupModelImpl.ENTITY_CACHE_ENABLED,
			DeductionGroupModelImpl.FINDER_CACHE_ENABLED,
			DeductionGroupImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DeductionGroupModelImpl.ENTITY_CACHE_ENABLED,
			DeductionGroupModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public DeductionGroupPersistenceImpl() {
		setModelClass(DeductionGroup.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("deductionFilter", "DEDUCTION_FILTER");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("deductionGroupSid", "DEDUCTION_GROUP_SID");
			dbColumnNames.put("deductionGroupName", "DEDUCTION_GROUP_NAME");
			dbColumnNames.put("versionNo", "VERSION_NO");
			dbColumnNames.put("deductionGroupDescription",
				"DEDUCTION_GROUP_DESCRIPTION");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("deductionGroupNo", "DEDUCTION_GROUP_NO");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the deduction group in the entity cache if it is enabled.
	 *
	 * @param deductionGroup the deduction group
	 */
	@Override
	public void cacheResult(DeductionGroup deductionGroup) {
		entityCache.putResult(DeductionGroupModelImpl.ENTITY_CACHE_ENABLED,
			DeductionGroupImpl.class, deductionGroup.getPrimaryKey(),
			deductionGroup);

		deductionGroup.resetOriginalValues();
	}

	/**
	 * Caches the deduction groups in the entity cache if it is enabled.
	 *
	 * @param deductionGroups the deduction groups
	 */
	@Override
	public void cacheResult(List<DeductionGroup> deductionGroups) {
		for (DeductionGroup deductionGroup : deductionGroups) {
			if (entityCache.getResult(
						DeductionGroupModelImpl.ENTITY_CACHE_ENABLED,
						DeductionGroupImpl.class, deductionGroup.getPrimaryKey()) == null) {
				cacheResult(deductionGroup);
			}
			else {
				deductionGroup.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all deduction groups.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(DeductionGroupImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the deduction group.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(DeductionGroup deductionGroup) {
		entityCache.removeResult(DeductionGroupModelImpl.ENTITY_CACHE_ENABLED,
			DeductionGroupImpl.class, deductionGroup.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<DeductionGroup> deductionGroups) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (DeductionGroup deductionGroup : deductionGroups) {
			entityCache.removeResult(DeductionGroupModelImpl.ENTITY_CACHE_ENABLED,
				DeductionGroupImpl.class, deductionGroup.getPrimaryKey());
		}
	}

	/**
	 * Creates a new deduction group with the primary key. Does not add the deduction group to the database.
	 *
	 * @param deductionGroupSid the primary key for the new deduction group
	 * @return the new deduction group
	 */
	@Override
	public DeductionGroup create(int deductionGroupSid) {
		DeductionGroup deductionGroup = new DeductionGroupImpl();

		deductionGroup.setNew(true);
		deductionGroup.setPrimaryKey(deductionGroupSid);

		return deductionGroup;
	}

	/**
	 * Removes the deduction group with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param deductionGroupSid the primary key of the deduction group
	 * @return the deduction group that was removed
	 * @throws NoSuchDeductionGroupException if a deduction group with the primary key could not be found
	 */
	@Override
	public DeductionGroup remove(int deductionGroupSid)
		throws NoSuchDeductionGroupException {
		return remove((Serializable)deductionGroupSid);
	}

	/**
	 * Removes the deduction group with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the deduction group
	 * @return the deduction group that was removed
	 * @throws NoSuchDeductionGroupException if a deduction group with the primary key could not be found
	 */
	@Override
	public DeductionGroup remove(Serializable primaryKey)
		throws NoSuchDeductionGroupException {
		Session session = null;

		try {
			session = openSession();

			DeductionGroup deductionGroup = (DeductionGroup)session.get(DeductionGroupImpl.class,
					primaryKey);

			if (deductionGroup == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDeductionGroupException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(deductionGroup);
		}
		catch (NoSuchDeductionGroupException nsee) {
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
	protected DeductionGroup removeImpl(DeductionGroup deductionGroup) {
		deductionGroup = toUnwrappedModel(deductionGroup);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(deductionGroup)) {
				deductionGroup = (DeductionGroup)session.get(DeductionGroupImpl.class,
						deductionGroup.getPrimaryKeyObj());
			}

			if (deductionGroup != null) {
				session.delete(deductionGroup);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (deductionGroup != null) {
			clearCache(deductionGroup);
		}

		return deductionGroup;
	}

	@Override
	public DeductionGroup updateImpl(DeductionGroup deductionGroup) {
		deductionGroup = toUnwrappedModel(deductionGroup);

		boolean isNew = deductionGroup.isNew();

		Session session = null;

		try {
			session = openSession();

			if (deductionGroup.isNew()) {
				session.save(deductionGroup);

				deductionGroup.setNew(false);
			}
			else {
				deductionGroup = (DeductionGroup)session.merge(deductionGroup);
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

		entityCache.putResult(DeductionGroupModelImpl.ENTITY_CACHE_ENABLED,
			DeductionGroupImpl.class, deductionGroup.getPrimaryKey(),
			deductionGroup, false);

		deductionGroup.resetOriginalValues();

		return deductionGroup;
	}

	protected DeductionGroup toUnwrappedModel(DeductionGroup deductionGroup) {
		if (deductionGroup instanceof DeductionGroupImpl) {
			return deductionGroup;
		}

		DeductionGroupImpl deductionGroupImpl = new DeductionGroupImpl();

		deductionGroupImpl.setNew(deductionGroup.isNew());
		deductionGroupImpl.setPrimaryKey(deductionGroup.getPrimaryKey());

		deductionGroupImpl.setDeductionFilter(deductionGroup.getDeductionFilter());
		deductionGroupImpl.setCreatedDate(deductionGroup.getCreatedDate());
		deductionGroupImpl.setCreatedBy(deductionGroup.getCreatedBy());
		deductionGroupImpl.setDeductionGroupSid(deductionGroup.getDeductionGroupSid());
		deductionGroupImpl.setDeductionGroupName(deductionGroup.getDeductionGroupName());
		deductionGroupImpl.setVersionNo(deductionGroup.getVersionNo());
		deductionGroupImpl.setDeductionGroupDescription(deductionGroup.getDeductionGroupDescription());
		deductionGroupImpl.setModifiedBy(deductionGroup.getModifiedBy());
		deductionGroupImpl.setDeductionGroupNo(deductionGroup.getDeductionGroupNo());
		deductionGroupImpl.setModifiedDate(deductionGroup.getModifiedDate());

		return deductionGroupImpl;
	}

	/**
	 * Returns the deduction group with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the deduction group
	 * @return the deduction group
	 * @throws NoSuchDeductionGroupException if a deduction group with the primary key could not be found
	 */
	@Override
	public DeductionGroup findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDeductionGroupException {
		DeductionGroup deductionGroup = fetchByPrimaryKey(primaryKey);

		if (deductionGroup == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDeductionGroupException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return deductionGroup;
	}

	/**
	 * Returns the deduction group with the primary key or throws a {@link NoSuchDeductionGroupException} if it could not be found.
	 *
	 * @param deductionGroupSid the primary key of the deduction group
	 * @return the deduction group
	 * @throws NoSuchDeductionGroupException if a deduction group with the primary key could not be found
	 */
	@Override
	public DeductionGroup findByPrimaryKey(int deductionGroupSid)
		throws NoSuchDeductionGroupException {
		return findByPrimaryKey((Serializable)deductionGroupSid);
	}

	/**
	 * Returns the deduction group with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the deduction group
	 * @return the deduction group, or <code>null</code> if a deduction group with the primary key could not be found
	 */
	@Override
	public DeductionGroup fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(DeductionGroupModelImpl.ENTITY_CACHE_ENABLED,
				DeductionGroupImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		DeductionGroup deductionGroup = (DeductionGroup)serializable;

		if (deductionGroup == null) {
			Session session = null;

			try {
				session = openSession();

				deductionGroup = (DeductionGroup)session.get(DeductionGroupImpl.class,
						primaryKey);

				if (deductionGroup != null) {
					cacheResult(deductionGroup);
				}
				else {
					entityCache.putResult(DeductionGroupModelImpl.ENTITY_CACHE_ENABLED,
						DeductionGroupImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(DeductionGroupModelImpl.ENTITY_CACHE_ENABLED,
					DeductionGroupImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return deductionGroup;
	}

	/**
	 * Returns the deduction group with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param deductionGroupSid the primary key of the deduction group
	 * @return the deduction group, or <code>null</code> if a deduction group with the primary key could not be found
	 */
	@Override
	public DeductionGroup fetchByPrimaryKey(int deductionGroupSid) {
		return fetchByPrimaryKey((Serializable)deductionGroupSid);
	}

	@Override
	public Map<Serializable, DeductionGroup> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, DeductionGroup> map = new HashMap<Serializable, DeductionGroup>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			DeductionGroup deductionGroup = fetchByPrimaryKey(primaryKey);

			if (deductionGroup != null) {
				map.put(primaryKey, deductionGroup);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(DeductionGroupModelImpl.ENTITY_CACHE_ENABLED,
					DeductionGroupImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (DeductionGroup)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_DEDUCTIONGROUP_WHERE_PKS_IN);

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

			for (DeductionGroup deductionGroup : (List<DeductionGroup>)q.list()) {
				map.put(deductionGroup.getPrimaryKeyObj(), deductionGroup);

				cacheResult(deductionGroup);

				uncachedPrimaryKeys.remove(deductionGroup.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(DeductionGroupModelImpl.ENTITY_CACHE_ENABLED,
					DeductionGroupImpl.class, primaryKey, nullModel);
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
	 * Returns all the deduction groups.
	 *
	 * @return the deduction groups
	 */
	@Override
	public List<DeductionGroup> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the deduction groups.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeductionGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of deduction groups
	 * @param end the upper bound of the range of deduction groups (not inclusive)
	 * @return the range of deduction groups
	 */
	@Override
	public List<DeductionGroup> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the deduction groups.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeductionGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of deduction groups
	 * @param end the upper bound of the range of deduction groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of deduction groups
	 */
	@Override
	public List<DeductionGroup> findAll(int start, int end,
		OrderByComparator<DeductionGroup> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the deduction groups.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeductionGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of deduction groups
	 * @param end the upper bound of the range of deduction groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of deduction groups
	 */
	@Override
	public List<DeductionGroup> findAll(int start, int end,
		OrderByComparator<DeductionGroup> orderByComparator,
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

		List<DeductionGroup> list = null;

		if (retrieveFromCache) {
			list = (List<DeductionGroup>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_DEDUCTIONGROUP);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DEDUCTIONGROUP;

				if (pagination) {
					sql = sql.concat(DeductionGroupModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<DeductionGroup>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DeductionGroup>)QueryUtil.list(q,
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
	 * Removes all the deduction groups from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (DeductionGroup deductionGroup : findAll()) {
			remove(deductionGroup);
		}
	}

	/**
	 * Returns the number of deduction groups.
	 *
	 * @return the number of deduction groups
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_DEDUCTIONGROUP);

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
		return DeductionGroupModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the deduction group persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(DeductionGroupImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_DEDUCTIONGROUP = "SELECT deductionGroup FROM DeductionGroup deductionGroup";
	private static final String _SQL_SELECT_DEDUCTIONGROUP_WHERE_PKS_IN = "SELECT deductionGroup FROM DeductionGroup deductionGroup WHERE DEDUCTION_GROUP_SID IN (";
	private static final String _SQL_COUNT_DEDUCTIONGROUP = "SELECT COUNT(deductionGroup) FROM DeductionGroup deductionGroup";
	private static final String _ORDER_BY_ENTITY_ALIAS = "deductionGroup.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DeductionGroup exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(DeductionGroupPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"deductionFilter", "createdDate", "createdBy",
				"deductionGroupSid", "deductionGroupName", "versionNo",
				"deductionGroupDescription", "modifiedBy", "deductionGroupNo",
				"modifiedDate"
			});
}