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

import com.stpl.app.exception.NoSuchHistCompanyGroupException;
import com.stpl.app.model.HistCompanyGroup;
import com.stpl.app.model.impl.HistCompanyGroupImpl;
import com.stpl.app.model.impl.HistCompanyGroupModelImpl;
import com.stpl.app.service.persistence.HistCompanyGroupPK;
import com.stpl.app.service.persistence.HistCompanyGroupPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the hist company group service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see HistCompanyGroupPersistence
 * @see com.stpl.app.service.persistence.HistCompanyGroupUtil
 * @generated
 */
@ProviderType
public class HistCompanyGroupPersistenceImpl extends BasePersistenceImpl<HistCompanyGroup>
	implements HistCompanyGroupPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link HistCompanyGroupUtil} to access the hist company group persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = HistCompanyGroupImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(HistCompanyGroupModelImpl.ENTITY_CACHE_ENABLED,
			HistCompanyGroupModelImpl.FINDER_CACHE_ENABLED,
			HistCompanyGroupImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(HistCompanyGroupModelImpl.ENTITY_CACHE_ENABLED,
			HistCompanyGroupModelImpl.FINDER_CACHE_ENABLED,
			HistCompanyGroupImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HistCompanyGroupModelImpl.ENTITY_CACHE_ENABLED,
			HistCompanyGroupModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public HistCompanyGroupPersistenceImpl() {
		setModelClass(HistCompanyGroup.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("companyGroupNo", "COMPANY_GROUP_NO");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("actionDate", "ACTION_DATE");
			dbColumnNames.put("actionFlag", "ACTION_FLAG");
			dbColumnNames.put("companyGroupSid", "COMPANY_GROUP_SID");
			dbColumnNames.put("companyGroupDescription",
				"COMPANY_GROUP_DESCRIPTION");
			dbColumnNames.put("versionNo", "VERSION_NO");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("companyGroupName", "COMPANY_GROUP_NAME");
			dbColumnNames.put("companyFilter", "COMPANY_FILTER");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the hist company group in the entity cache if it is enabled.
	 *
	 * @param histCompanyGroup the hist company group
	 */
	@Override
	public void cacheResult(HistCompanyGroup histCompanyGroup) {
		entityCache.putResult(HistCompanyGroupModelImpl.ENTITY_CACHE_ENABLED,
			HistCompanyGroupImpl.class, histCompanyGroup.getPrimaryKey(),
			histCompanyGroup);

		histCompanyGroup.resetOriginalValues();
	}

	/**
	 * Caches the hist company groups in the entity cache if it is enabled.
	 *
	 * @param histCompanyGroups the hist company groups
	 */
	@Override
	public void cacheResult(List<HistCompanyGroup> histCompanyGroups) {
		for (HistCompanyGroup histCompanyGroup : histCompanyGroups) {
			if (entityCache.getResult(
						HistCompanyGroupModelImpl.ENTITY_CACHE_ENABLED,
						HistCompanyGroupImpl.class,
						histCompanyGroup.getPrimaryKey()) == null) {
				cacheResult(histCompanyGroup);
			}
			else {
				histCompanyGroup.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all hist company groups.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(HistCompanyGroupImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the hist company group.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(HistCompanyGroup histCompanyGroup) {
		entityCache.removeResult(HistCompanyGroupModelImpl.ENTITY_CACHE_ENABLED,
			HistCompanyGroupImpl.class, histCompanyGroup.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<HistCompanyGroup> histCompanyGroups) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (HistCompanyGroup histCompanyGroup : histCompanyGroups) {
			entityCache.removeResult(HistCompanyGroupModelImpl.ENTITY_CACHE_ENABLED,
				HistCompanyGroupImpl.class, histCompanyGroup.getPrimaryKey());
		}
	}

	/**
	 * Creates a new hist company group with the primary key. Does not add the hist company group to the database.
	 *
	 * @param histCompanyGroupPK the primary key for the new hist company group
	 * @return the new hist company group
	 */
	@Override
	public HistCompanyGroup create(HistCompanyGroupPK histCompanyGroupPK) {
		HistCompanyGroup histCompanyGroup = new HistCompanyGroupImpl();

		histCompanyGroup.setNew(true);
		histCompanyGroup.setPrimaryKey(histCompanyGroupPK);

		return histCompanyGroup;
	}

	/**
	 * Removes the hist company group with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param histCompanyGroupPK the primary key of the hist company group
	 * @return the hist company group that was removed
	 * @throws NoSuchHistCompanyGroupException if a hist company group with the primary key could not be found
	 */
	@Override
	public HistCompanyGroup remove(HistCompanyGroupPK histCompanyGroupPK)
		throws NoSuchHistCompanyGroupException {
		return remove((Serializable)histCompanyGroupPK);
	}

	/**
	 * Removes the hist company group with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the hist company group
	 * @return the hist company group that was removed
	 * @throws NoSuchHistCompanyGroupException if a hist company group with the primary key could not be found
	 */
	@Override
	public HistCompanyGroup remove(Serializable primaryKey)
		throws NoSuchHistCompanyGroupException {
		Session session = null;

		try {
			session = openSession();

			HistCompanyGroup histCompanyGroup = (HistCompanyGroup)session.get(HistCompanyGroupImpl.class,
					primaryKey);

			if (histCompanyGroup == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchHistCompanyGroupException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(histCompanyGroup);
		}
		catch (NoSuchHistCompanyGroupException nsee) {
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
	protected HistCompanyGroup removeImpl(HistCompanyGroup histCompanyGroup) {
		histCompanyGroup = toUnwrappedModel(histCompanyGroup);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(histCompanyGroup)) {
				histCompanyGroup = (HistCompanyGroup)session.get(HistCompanyGroupImpl.class,
						histCompanyGroup.getPrimaryKeyObj());
			}

			if (histCompanyGroup != null) {
				session.delete(histCompanyGroup);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (histCompanyGroup != null) {
			clearCache(histCompanyGroup);
		}

		return histCompanyGroup;
	}

	@Override
	public HistCompanyGroup updateImpl(HistCompanyGroup histCompanyGroup) {
		histCompanyGroup = toUnwrappedModel(histCompanyGroup);

		boolean isNew = histCompanyGroup.isNew();

		Session session = null;

		try {
			session = openSession();

			if (histCompanyGroup.isNew()) {
				session.save(histCompanyGroup);

				histCompanyGroup.setNew(false);
			}
			else {
				histCompanyGroup = (HistCompanyGroup)session.merge(histCompanyGroup);
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

		entityCache.putResult(HistCompanyGroupModelImpl.ENTITY_CACHE_ENABLED,
			HistCompanyGroupImpl.class, histCompanyGroup.getPrimaryKey(),
			histCompanyGroup, false);

		histCompanyGroup.resetOriginalValues();

		return histCompanyGroup;
	}

	protected HistCompanyGroup toUnwrappedModel(
		HistCompanyGroup histCompanyGroup) {
		if (histCompanyGroup instanceof HistCompanyGroupImpl) {
			return histCompanyGroup;
		}

		HistCompanyGroupImpl histCompanyGroupImpl = new HistCompanyGroupImpl();

		histCompanyGroupImpl.setNew(histCompanyGroup.isNew());
		histCompanyGroupImpl.setPrimaryKey(histCompanyGroup.getPrimaryKey());

		histCompanyGroupImpl.setCompanyGroupNo(histCompanyGroup.getCompanyGroupNo());
		histCompanyGroupImpl.setCreatedDate(histCompanyGroup.getCreatedDate());
		histCompanyGroupImpl.setCreatedBy(histCompanyGroup.getCreatedBy());
		histCompanyGroupImpl.setActionDate(histCompanyGroup.getActionDate());
		histCompanyGroupImpl.setActionFlag(histCompanyGroup.getActionFlag());
		histCompanyGroupImpl.setCompanyGroupSid(histCompanyGroup.getCompanyGroupSid());
		histCompanyGroupImpl.setCompanyGroupDescription(histCompanyGroup.getCompanyGroupDescription());
		histCompanyGroupImpl.setVersionNo(histCompanyGroup.getVersionNo());
		histCompanyGroupImpl.setModifiedBy(histCompanyGroup.getModifiedBy());
		histCompanyGroupImpl.setModifiedDate(histCompanyGroup.getModifiedDate());
		histCompanyGroupImpl.setCompanyGroupName(histCompanyGroup.getCompanyGroupName());
		histCompanyGroupImpl.setCompanyFilter(histCompanyGroup.getCompanyFilter());

		return histCompanyGroupImpl;
	}

	/**
	 * Returns the hist company group with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the hist company group
	 * @return the hist company group
	 * @throws NoSuchHistCompanyGroupException if a hist company group with the primary key could not be found
	 */
	@Override
	public HistCompanyGroup findByPrimaryKey(Serializable primaryKey)
		throws NoSuchHistCompanyGroupException {
		HistCompanyGroup histCompanyGroup = fetchByPrimaryKey(primaryKey);

		if (histCompanyGroup == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchHistCompanyGroupException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return histCompanyGroup;
	}

	/**
	 * Returns the hist company group with the primary key or throws a {@link NoSuchHistCompanyGroupException} if it could not be found.
	 *
	 * @param histCompanyGroupPK the primary key of the hist company group
	 * @return the hist company group
	 * @throws NoSuchHistCompanyGroupException if a hist company group with the primary key could not be found
	 */
	@Override
	public HistCompanyGroup findByPrimaryKey(
		HistCompanyGroupPK histCompanyGroupPK)
		throws NoSuchHistCompanyGroupException {
		return findByPrimaryKey((Serializable)histCompanyGroupPK);
	}

	/**
	 * Returns the hist company group with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the hist company group
	 * @return the hist company group, or <code>null</code> if a hist company group with the primary key could not be found
	 */
	@Override
	public HistCompanyGroup fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(HistCompanyGroupModelImpl.ENTITY_CACHE_ENABLED,
				HistCompanyGroupImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		HistCompanyGroup histCompanyGroup = (HistCompanyGroup)serializable;

		if (histCompanyGroup == null) {
			Session session = null;

			try {
				session = openSession();

				histCompanyGroup = (HistCompanyGroup)session.get(HistCompanyGroupImpl.class,
						primaryKey);

				if (histCompanyGroup != null) {
					cacheResult(histCompanyGroup);
				}
				else {
					entityCache.putResult(HistCompanyGroupModelImpl.ENTITY_CACHE_ENABLED,
						HistCompanyGroupImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(HistCompanyGroupModelImpl.ENTITY_CACHE_ENABLED,
					HistCompanyGroupImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return histCompanyGroup;
	}

	/**
	 * Returns the hist company group with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param histCompanyGroupPK the primary key of the hist company group
	 * @return the hist company group, or <code>null</code> if a hist company group with the primary key could not be found
	 */
	@Override
	public HistCompanyGroup fetchByPrimaryKey(
		HistCompanyGroupPK histCompanyGroupPK) {
		return fetchByPrimaryKey((Serializable)histCompanyGroupPK);
	}

	@Override
	public Map<Serializable, HistCompanyGroup> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, HistCompanyGroup> map = new HashMap<Serializable, HistCompanyGroup>();

		for (Serializable primaryKey : primaryKeys) {
			HistCompanyGroup histCompanyGroup = fetchByPrimaryKey(primaryKey);

			if (histCompanyGroup != null) {
				map.put(primaryKey, histCompanyGroup);
			}
		}

		return map;
	}

	/**
	 * Returns all the hist company groups.
	 *
	 * @return the hist company groups
	 */
	@Override
	public List<HistCompanyGroup> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the hist company groups.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistCompanyGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of hist company groups
	 * @param end the upper bound of the range of hist company groups (not inclusive)
	 * @return the range of hist company groups
	 */
	@Override
	public List<HistCompanyGroup> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the hist company groups.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistCompanyGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of hist company groups
	 * @param end the upper bound of the range of hist company groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of hist company groups
	 */
	@Override
	public List<HistCompanyGroup> findAll(int start, int end,
		OrderByComparator<HistCompanyGroup> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the hist company groups.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistCompanyGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of hist company groups
	 * @param end the upper bound of the range of hist company groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of hist company groups
	 */
	@Override
	public List<HistCompanyGroup> findAll(int start, int end,
		OrderByComparator<HistCompanyGroup> orderByComparator,
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

		List<HistCompanyGroup> list = null;

		if (retrieveFromCache) {
			list = (List<HistCompanyGroup>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_HISTCOMPANYGROUP);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_HISTCOMPANYGROUP;

				if (pagination) {
					sql = sql.concat(HistCompanyGroupModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<HistCompanyGroup>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<HistCompanyGroup>)QueryUtil.list(q,
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
	 * Removes all the hist company groups from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (HistCompanyGroup histCompanyGroup : findAll()) {
			remove(histCompanyGroup);
		}
	}

	/**
	 * Returns the number of hist company groups.
	 *
	 * @return the number of hist company groups
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_HISTCOMPANYGROUP);

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
		return HistCompanyGroupModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the hist company group persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(HistCompanyGroupImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_HISTCOMPANYGROUP = "SELECT histCompanyGroup FROM HistCompanyGroup histCompanyGroup";
	private static final String _SQL_COUNT_HISTCOMPANYGROUP = "SELECT COUNT(histCompanyGroup) FROM HistCompanyGroup histCompanyGroup";
	private static final String _ORDER_BY_ENTITY_ALIAS = "histCompanyGroup.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HistCompanyGroup exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(HistCompanyGroupPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"companyGroupNo", "createdDate", "createdBy", "actionDate",
				"actionFlag", "companyGroupSid", "companyGroupDescription",
				"versionNo", "modifiedBy", "modifiedDate", "companyGroupName",
				"companyFilter"
			});
}