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

import com.stpl.app.exception.NoSuchHistItemGroupException;
import com.stpl.app.model.HistItemGroup;
import com.stpl.app.model.impl.HistItemGroupImpl;
import com.stpl.app.model.impl.HistItemGroupModelImpl;
import com.stpl.app.service.persistence.HistItemGroupPK;
import com.stpl.app.service.persistence.HistItemGroupPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the hist item group service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see HistItemGroupPersistence
 * @see com.stpl.app.service.persistence.HistItemGroupUtil
 * @generated
 */
@ProviderType
public class HistItemGroupPersistenceImpl extends BasePersistenceImpl<HistItemGroup>
	implements HistItemGroupPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link HistItemGroupUtil} to access the hist item group persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = HistItemGroupImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(HistItemGroupModelImpl.ENTITY_CACHE_ENABLED,
			HistItemGroupModelImpl.FINDER_CACHE_ENABLED,
			HistItemGroupImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(HistItemGroupModelImpl.ENTITY_CACHE_ENABLED,
			HistItemGroupModelImpl.FINDER_CACHE_ENABLED,
			HistItemGroupImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HistItemGroupModelImpl.ENTITY_CACHE_ENABLED,
			HistItemGroupModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public HistItemGroupPersistenceImpl() {
		setModelClass(HistItemGroup.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("lastModifiedDate", "LAST_MODIFIED_DATE");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("actionFlag", "ACTION_FLAG");
			dbColumnNames.put("itemGroupNo", "ITEM_GROUP_NO");
			dbColumnNames.put("versionNo", "VERSION_NO");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("itemGroupDescription", "ITEM_GROUP_DESCRIPTION");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("itemGroupName", "ITEM_GROUP_NAME");
			dbColumnNames.put("itemGroupSid", "ITEM_GROUP_SID");
			dbColumnNames.put("companyMasterSid", "COMPANY_MASTER_SID");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the hist item group in the entity cache if it is enabled.
	 *
	 * @param histItemGroup the hist item group
	 */
	@Override
	public void cacheResult(HistItemGroup histItemGroup) {
		entityCache.putResult(HistItemGroupModelImpl.ENTITY_CACHE_ENABLED,
			HistItemGroupImpl.class, histItemGroup.getPrimaryKey(),
			histItemGroup);

		histItemGroup.resetOriginalValues();
	}

	/**
	 * Caches the hist item groups in the entity cache if it is enabled.
	 *
	 * @param histItemGroups the hist item groups
	 */
	@Override
	public void cacheResult(List<HistItemGroup> histItemGroups) {
		for (HistItemGroup histItemGroup : histItemGroups) {
			if (entityCache.getResult(
						HistItemGroupModelImpl.ENTITY_CACHE_ENABLED,
						HistItemGroupImpl.class, histItemGroup.getPrimaryKey()) == null) {
				cacheResult(histItemGroup);
			}
			else {
				histItemGroup.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all hist item groups.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(HistItemGroupImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the hist item group.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(HistItemGroup histItemGroup) {
		entityCache.removeResult(HistItemGroupModelImpl.ENTITY_CACHE_ENABLED,
			HistItemGroupImpl.class, histItemGroup.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<HistItemGroup> histItemGroups) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (HistItemGroup histItemGroup : histItemGroups) {
			entityCache.removeResult(HistItemGroupModelImpl.ENTITY_CACHE_ENABLED,
				HistItemGroupImpl.class, histItemGroup.getPrimaryKey());
		}
	}

	/**
	 * Creates a new hist item group with the primary key. Does not add the hist item group to the database.
	 *
	 * @param histItemGroupPK the primary key for the new hist item group
	 * @return the new hist item group
	 */
	@Override
	public HistItemGroup create(HistItemGroupPK histItemGroupPK) {
		HistItemGroup histItemGroup = new HistItemGroupImpl();

		histItemGroup.setNew(true);
		histItemGroup.setPrimaryKey(histItemGroupPK);

		return histItemGroup;
	}

	/**
	 * Removes the hist item group with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param histItemGroupPK the primary key of the hist item group
	 * @return the hist item group that was removed
	 * @throws NoSuchHistItemGroupException if a hist item group with the primary key could not be found
	 */
	@Override
	public HistItemGroup remove(HistItemGroupPK histItemGroupPK)
		throws NoSuchHistItemGroupException {
		return remove((Serializable)histItemGroupPK);
	}

	/**
	 * Removes the hist item group with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the hist item group
	 * @return the hist item group that was removed
	 * @throws NoSuchHistItemGroupException if a hist item group with the primary key could not be found
	 */
	@Override
	public HistItemGroup remove(Serializable primaryKey)
		throws NoSuchHistItemGroupException {
		Session session = null;

		try {
			session = openSession();

			HistItemGroup histItemGroup = (HistItemGroup)session.get(HistItemGroupImpl.class,
					primaryKey);

			if (histItemGroup == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchHistItemGroupException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(histItemGroup);
		}
		catch (NoSuchHistItemGroupException nsee) {
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
	protected HistItemGroup removeImpl(HistItemGroup histItemGroup) {
		histItemGroup = toUnwrappedModel(histItemGroup);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(histItemGroup)) {
				histItemGroup = (HistItemGroup)session.get(HistItemGroupImpl.class,
						histItemGroup.getPrimaryKeyObj());
			}

			if (histItemGroup != null) {
				session.delete(histItemGroup);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (histItemGroup != null) {
			clearCache(histItemGroup);
		}

		return histItemGroup;
	}

	@Override
	public HistItemGroup updateImpl(HistItemGroup histItemGroup) {
		histItemGroup = toUnwrappedModel(histItemGroup);

		boolean isNew = histItemGroup.isNew();

		Session session = null;

		try {
			session = openSession();

			if (histItemGroup.isNew()) {
				session.save(histItemGroup);

				histItemGroup.setNew(false);
			}
			else {
				histItemGroup = (HistItemGroup)session.merge(histItemGroup);
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

		entityCache.putResult(HistItemGroupModelImpl.ENTITY_CACHE_ENABLED,
			HistItemGroupImpl.class, histItemGroup.getPrimaryKey(),
			histItemGroup, false);

		histItemGroup.resetOriginalValues();

		return histItemGroup;
	}

	protected HistItemGroup toUnwrappedModel(HistItemGroup histItemGroup) {
		if (histItemGroup instanceof HistItemGroupImpl) {
			return histItemGroup;
		}

		HistItemGroupImpl histItemGroupImpl = new HistItemGroupImpl();

		histItemGroupImpl.setNew(histItemGroup.isNew());
		histItemGroupImpl.setPrimaryKey(histItemGroup.getPrimaryKey());

		histItemGroupImpl.setLastModifiedDate(histItemGroup.getLastModifiedDate());
		histItemGroupImpl.setCreatedDate(histItemGroup.getCreatedDate());
		histItemGroupImpl.setCreatedBy(histItemGroup.getCreatedBy());
		histItemGroupImpl.setActionFlag(histItemGroup.getActionFlag());
		histItemGroupImpl.setItemGroupNo(histItemGroup.getItemGroupNo());
		histItemGroupImpl.setVersionNo(histItemGroup.getVersionNo());
		histItemGroupImpl.setModifiedBy(histItemGroup.getModifiedBy());
		histItemGroupImpl.setItemGroupDescription(histItemGroup.getItemGroupDescription());
		histItemGroupImpl.setModifiedDate(histItemGroup.getModifiedDate());
		histItemGroupImpl.setItemGroupName(histItemGroup.getItemGroupName());
		histItemGroupImpl.setItemGroupSid(histItemGroup.getItemGroupSid());
		histItemGroupImpl.setCompanyMasterSid(histItemGroup.getCompanyMasterSid());

		return histItemGroupImpl;
	}

	/**
	 * Returns the hist item group with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the hist item group
	 * @return the hist item group
	 * @throws NoSuchHistItemGroupException if a hist item group with the primary key could not be found
	 */
	@Override
	public HistItemGroup findByPrimaryKey(Serializable primaryKey)
		throws NoSuchHistItemGroupException {
		HistItemGroup histItemGroup = fetchByPrimaryKey(primaryKey);

		if (histItemGroup == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchHistItemGroupException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return histItemGroup;
	}

	/**
	 * Returns the hist item group with the primary key or throws a {@link NoSuchHistItemGroupException} if it could not be found.
	 *
	 * @param histItemGroupPK the primary key of the hist item group
	 * @return the hist item group
	 * @throws NoSuchHistItemGroupException if a hist item group with the primary key could not be found
	 */
	@Override
	public HistItemGroup findByPrimaryKey(HistItemGroupPK histItemGroupPK)
		throws NoSuchHistItemGroupException {
		return findByPrimaryKey((Serializable)histItemGroupPK);
	}

	/**
	 * Returns the hist item group with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the hist item group
	 * @return the hist item group, or <code>null</code> if a hist item group with the primary key could not be found
	 */
	@Override
	public HistItemGroup fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(HistItemGroupModelImpl.ENTITY_CACHE_ENABLED,
				HistItemGroupImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		HistItemGroup histItemGroup = (HistItemGroup)serializable;

		if (histItemGroup == null) {
			Session session = null;

			try {
				session = openSession();

				histItemGroup = (HistItemGroup)session.get(HistItemGroupImpl.class,
						primaryKey);

				if (histItemGroup != null) {
					cacheResult(histItemGroup);
				}
				else {
					entityCache.putResult(HistItemGroupModelImpl.ENTITY_CACHE_ENABLED,
						HistItemGroupImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(HistItemGroupModelImpl.ENTITY_CACHE_ENABLED,
					HistItemGroupImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return histItemGroup;
	}

	/**
	 * Returns the hist item group with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param histItemGroupPK the primary key of the hist item group
	 * @return the hist item group, or <code>null</code> if a hist item group with the primary key could not be found
	 */
	@Override
	public HistItemGroup fetchByPrimaryKey(HistItemGroupPK histItemGroupPK) {
		return fetchByPrimaryKey((Serializable)histItemGroupPK);
	}

	@Override
	public Map<Serializable, HistItemGroup> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, HistItemGroup> map = new HashMap<Serializable, HistItemGroup>();

		for (Serializable primaryKey : primaryKeys) {
			HistItemGroup histItemGroup = fetchByPrimaryKey(primaryKey);

			if (histItemGroup != null) {
				map.put(primaryKey, histItemGroup);
			}
		}

		return map;
	}

	/**
	 * Returns all the hist item groups.
	 *
	 * @return the hist item groups
	 */
	@Override
	public List<HistItemGroup> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the hist item groups.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of hist item groups
	 * @param end the upper bound of the range of hist item groups (not inclusive)
	 * @return the range of hist item groups
	 */
	@Override
	public List<HistItemGroup> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the hist item groups.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of hist item groups
	 * @param end the upper bound of the range of hist item groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of hist item groups
	 */
	@Override
	public List<HistItemGroup> findAll(int start, int end,
		OrderByComparator<HistItemGroup> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the hist item groups.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of hist item groups
	 * @param end the upper bound of the range of hist item groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of hist item groups
	 */
	@Override
	public List<HistItemGroup> findAll(int start, int end,
		OrderByComparator<HistItemGroup> orderByComparator,
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

		List<HistItemGroup> list = null;

		if (retrieveFromCache) {
			list = (List<HistItemGroup>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_HISTITEMGROUP);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_HISTITEMGROUP;

				if (pagination) {
					sql = sql.concat(HistItemGroupModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<HistItemGroup>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<HistItemGroup>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the hist item groups from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (HistItemGroup histItemGroup : findAll()) {
			remove(histItemGroup);
		}
	}

	/**
	 * Returns the number of hist item groups.
	 *
	 * @return the number of hist item groups
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_HISTITEMGROUP);

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
		return HistItemGroupModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the hist item group persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(HistItemGroupImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_HISTITEMGROUP = "SELECT histItemGroup FROM HistItemGroup histItemGroup";
	private static final String _SQL_COUNT_HISTITEMGROUP = "SELECT COUNT(histItemGroup) FROM HistItemGroup histItemGroup";
	private static final String _ORDER_BY_ENTITY_ALIAS = "histItemGroup.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HistItemGroup exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(HistItemGroupPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"lastModifiedDate", "createdDate", "createdBy", "actionFlag",
				"itemGroupNo", "versionNo", "modifiedBy", "itemGroupDescription",
				"modifiedDate", "itemGroupName", "itemGroupSid",
				"companyMasterSid"
			});
}