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

import com.stpl.app.exception.NoSuchItemGroupException;
import com.stpl.app.model.ItemGroup;
import com.stpl.app.model.impl.ItemGroupImpl;
import com.stpl.app.model.impl.ItemGroupModelImpl;
import com.stpl.app.service.persistence.ItemGroupPersistence;

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
 * The persistence implementation for the item group service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ItemGroupPersistence
 * @see com.stpl.app.service.persistence.ItemGroupUtil
 * @generated
 */
@ProviderType
public class ItemGroupPersistenceImpl extends BasePersistenceImpl<ItemGroup>
	implements ItemGroupPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ItemGroupUtil} to access the item group persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ItemGroupImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ItemGroupModelImpl.ENTITY_CACHE_ENABLED,
			ItemGroupModelImpl.FINDER_CACHE_ENABLED, ItemGroupImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ItemGroupModelImpl.ENTITY_CACHE_ENABLED,
			ItemGroupModelImpl.FINDER_CACHE_ENABLED, ItemGroupImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ItemGroupModelImpl.ENTITY_CACHE_ENABLED,
			ItemGroupModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public ItemGroupPersistenceImpl() {
		setModelClass(ItemGroup.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("itemGroupNo", "ITEM_GROUP_NO");
			dbColumnNames.put("versionNo", "VERSION_NO");
			dbColumnNames.put("itemFilter", "ITEM_FILTER");
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
	 * Caches the item group in the entity cache if it is enabled.
	 *
	 * @param itemGroup the item group
	 */
	@Override
	public void cacheResult(ItemGroup itemGroup) {
		entityCache.putResult(ItemGroupModelImpl.ENTITY_CACHE_ENABLED,
			ItemGroupImpl.class, itemGroup.getPrimaryKey(), itemGroup);

		itemGroup.resetOriginalValues();
	}

	/**
	 * Caches the item groups in the entity cache if it is enabled.
	 *
	 * @param itemGroups the item groups
	 */
	@Override
	public void cacheResult(List<ItemGroup> itemGroups) {
		for (ItemGroup itemGroup : itemGroups) {
			if (entityCache.getResult(ItemGroupModelImpl.ENTITY_CACHE_ENABLED,
						ItemGroupImpl.class, itemGroup.getPrimaryKey()) == null) {
				cacheResult(itemGroup);
			}
			else {
				itemGroup.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all item groups.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ItemGroupImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the item group.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ItemGroup itemGroup) {
		entityCache.removeResult(ItemGroupModelImpl.ENTITY_CACHE_ENABLED,
			ItemGroupImpl.class, itemGroup.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ItemGroup> itemGroups) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ItemGroup itemGroup : itemGroups) {
			entityCache.removeResult(ItemGroupModelImpl.ENTITY_CACHE_ENABLED,
				ItemGroupImpl.class, itemGroup.getPrimaryKey());
		}
	}

	/**
	 * Creates a new item group with the primary key. Does not add the item group to the database.
	 *
	 * @param itemGroupSid the primary key for the new item group
	 * @return the new item group
	 */
	@Override
	public ItemGroup create(int itemGroupSid) {
		ItemGroup itemGroup = new ItemGroupImpl();

		itemGroup.setNew(true);
		itemGroup.setPrimaryKey(itemGroupSid);

		return itemGroup;
	}

	/**
	 * Removes the item group with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param itemGroupSid the primary key of the item group
	 * @return the item group that was removed
	 * @throws NoSuchItemGroupException if a item group with the primary key could not be found
	 */
	@Override
	public ItemGroup remove(int itemGroupSid) throws NoSuchItemGroupException {
		return remove((Serializable)itemGroupSid);
	}

	/**
	 * Removes the item group with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the item group
	 * @return the item group that was removed
	 * @throws NoSuchItemGroupException if a item group with the primary key could not be found
	 */
	@Override
	public ItemGroup remove(Serializable primaryKey)
		throws NoSuchItemGroupException {
		Session session = null;

		try {
			session = openSession();

			ItemGroup itemGroup = (ItemGroup)session.get(ItemGroupImpl.class,
					primaryKey);

			if (itemGroup == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchItemGroupException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(itemGroup);
		}
		catch (NoSuchItemGroupException nsee) {
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
	protected ItemGroup removeImpl(ItemGroup itemGroup) {
		itemGroup = toUnwrappedModel(itemGroup);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(itemGroup)) {
				itemGroup = (ItemGroup)session.get(ItemGroupImpl.class,
						itemGroup.getPrimaryKeyObj());
			}

			if (itemGroup != null) {
				session.delete(itemGroup);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (itemGroup != null) {
			clearCache(itemGroup);
		}

		return itemGroup;
	}

	@Override
	public ItemGroup updateImpl(ItemGroup itemGroup) {
		itemGroup = toUnwrappedModel(itemGroup);

		boolean isNew = itemGroup.isNew();

		Session session = null;

		try {
			session = openSession();

			if (itemGroup.isNew()) {
				session.save(itemGroup);

				itemGroup.setNew(false);
			}
			else {
				itemGroup = (ItemGroup)session.merge(itemGroup);
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

		entityCache.putResult(ItemGroupModelImpl.ENTITY_CACHE_ENABLED,
			ItemGroupImpl.class, itemGroup.getPrimaryKey(), itemGroup, false);

		itemGroup.resetOriginalValues();

		return itemGroup;
	}

	protected ItemGroup toUnwrappedModel(ItemGroup itemGroup) {
		if (itemGroup instanceof ItemGroupImpl) {
			return itemGroup;
		}

		ItemGroupImpl itemGroupImpl = new ItemGroupImpl();

		itemGroupImpl.setNew(itemGroup.isNew());
		itemGroupImpl.setPrimaryKey(itemGroup.getPrimaryKey());

		itemGroupImpl.setCreatedDate(itemGroup.getCreatedDate());
		itemGroupImpl.setCreatedBy(itemGroup.getCreatedBy());
		itemGroupImpl.setItemGroupNo(itemGroup.getItemGroupNo());
		itemGroupImpl.setVersionNo(itemGroup.getVersionNo());
		itemGroupImpl.setItemFilter(itemGroup.getItemFilter());
		itemGroupImpl.setModifiedBy(itemGroup.getModifiedBy());
		itemGroupImpl.setItemGroupDescription(itemGroup.getItemGroupDescription());
		itemGroupImpl.setModifiedDate(itemGroup.getModifiedDate());
		itemGroupImpl.setItemGroupName(itemGroup.getItemGroupName());
		itemGroupImpl.setItemGroupSid(itemGroup.getItemGroupSid());
		itemGroupImpl.setCompanyMasterSid(itemGroup.getCompanyMasterSid());

		return itemGroupImpl;
	}

	/**
	 * Returns the item group with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the item group
	 * @return the item group
	 * @throws NoSuchItemGroupException if a item group with the primary key could not be found
	 */
	@Override
	public ItemGroup findByPrimaryKey(Serializable primaryKey)
		throws NoSuchItemGroupException {
		ItemGroup itemGroup = fetchByPrimaryKey(primaryKey);

		if (itemGroup == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchItemGroupException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return itemGroup;
	}

	/**
	 * Returns the item group with the primary key or throws a {@link NoSuchItemGroupException} if it could not be found.
	 *
	 * @param itemGroupSid the primary key of the item group
	 * @return the item group
	 * @throws NoSuchItemGroupException if a item group with the primary key could not be found
	 */
	@Override
	public ItemGroup findByPrimaryKey(int itemGroupSid)
		throws NoSuchItemGroupException {
		return findByPrimaryKey((Serializable)itemGroupSid);
	}

	/**
	 * Returns the item group with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the item group
	 * @return the item group, or <code>null</code> if a item group with the primary key could not be found
	 */
	@Override
	public ItemGroup fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ItemGroupModelImpl.ENTITY_CACHE_ENABLED,
				ItemGroupImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ItemGroup itemGroup = (ItemGroup)serializable;

		if (itemGroup == null) {
			Session session = null;

			try {
				session = openSession();

				itemGroup = (ItemGroup)session.get(ItemGroupImpl.class,
						primaryKey);

				if (itemGroup != null) {
					cacheResult(itemGroup);
				}
				else {
					entityCache.putResult(ItemGroupModelImpl.ENTITY_CACHE_ENABLED,
						ItemGroupImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ItemGroupModelImpl.ENTITY_CACHE_ENABLED,
					ItemGroupImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return itemGroup;
	}

	/**
	 * Returns the item group with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param itemGroupSid the primary key of the item group
	 * @return the item group, or <code>null</code> if a item group with the primary key could not be found
	 */
	@Override
	public ItemGroup fetchByPrimaryKey(int itemGroupSid) {
		return fetchByPrimaryKey((Serializable)itemGroupSid);
	}

	@Override
	public Map<Serializable, ItemGroup> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ItemGroup> map = new HashMap<Serializable, ItemGroup>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ItemGroup itemGroup = fetchByPrimaryKey(primaryKey);

			if (itemGroup != null) {
				map.put(primaryKey, itemGroup);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ItemGroupModelImpl.ENTITY_CACHE_ENABLED,
					ItemGroupImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ItemGroup)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_ITEMGROUP_WHERE_PKS_IN);

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

			for (ItemGroup itemGroup : (List<ItemGroup>)q.list()) {
				map.put(itemGroup.getPrimaryKeyObj(), itemGroup);

				cacheResult(itemGroup);

				uncachedPrimaryKeys.remove(itemGroup.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ItemGroupModelImpl.ENTITY_CACHE_ENABLED,
					ItemGroupImpl.class, primaryKey, nullModel);
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
	 * Returns all the item groups.
	 *
	 * @return the item groups
	 */
	@Override
	public List<ItemGroup> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the item groups.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of item groups
	 * @param end the upper bound of the range of item groups (not inclusive)
	 * @return the range of item groups
	 */
	@Override
	public List<ItemGroup> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the item groups.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of item groups
	 * @param end the upper bound of the range of item groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of item groups
	 */
	@Override
	public List<ItemGroup> findAll(int start, int end,
		OrderByComparator<ItemGroup> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the item groups.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of item groups
	 * @param end the upper bound of the range of item groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of item groups
	 */
	@Override
	public List<ItemGroup> findAll(int start, int end,
		OrderByComparator<ItemGroup> orderByComparator,
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

		List<ItemGroup> list = null;

		if (retrieveFromCache) {
			list = (List<ItemGroup>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_ITEMGROUP);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ITEMGROUP;

				if (pagination) {
					sql = sql.concat(ItemGroupModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ItemGroup>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ItemGroup>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the item groups from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ItemGroup itemGroup : findAll()) {
			remove(itemGroup);
		}
	}

	/**
	 * Returns the number of item groups.
	 *
	 * @return the number of item groups
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ITEMGROUP);

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
		return ItemGroupModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the item group persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ItemGroupImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_ITEMGROUP = "SELECT itemGroup FROM ItemGroup itemGroup";
	private static final String _SQL_SELECT_ITEMGROUP_WHERE_PKS_IN = "SELECT itemGroup FROM ItemGroup itemGroup WHERE ITEM_GROUP_SID IN (";
	private static final String _SQL_COUNT_ITEMGROUP = "SELECT COUNT(itemGroup) FROM ItemGroup itemGroup";
	private static final String _ORDER_BY_ENTITY_ALIAS = "itemGroup.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ItemGroup exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(ItemGroupPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"createdDate", "createdBy", "itemGroupNo", "versionNo",
				"itemFilter", "modifiedBy", "itemGroupDescription",
				"modifiedDate", "itemGroupName", "itemGroupSid",
				"companyMasterSid"
			});
}