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

import com.stpl.app.exception.NoSuchCompanyGroupException;
import com.stpl.app.model.CompanyGroup;
import com.stpl.app.model.impl.CompanyGroupImpl;
import com.stpl.app.model.impl.CompanyGroupModelImpl;
import com.stpl.app.service.persistence.CompanyGroupPersistence;

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
 * The persistence implementation for the company group service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CompanyGroupPersistence
 * @see com.stpl.app.service.persistence.CompanyGroupUtil
 * @generated
 */
@ProviderType
public class CompanyGroupPersistenceImpl extends BasePersistenceImpl<CompanyGroup>
	implements CompanyGroupPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CompanyGroupUtil} to access the company group persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CompanyGroupImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CompanyGroupModelImpl.ENTITY_CACHE_ENABLED,
			CompanyGroupModelImpl.FINDER_CACHE_ENABLED, CompanyGroupImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CompanyGroupModelImpl.ENTITY_CACHE_ENABLED,
			CompanyGroupModelImpl.FINDER_CACHE_ENABLED, CompanyGroupImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CompanyGroupModelImpl.ENTITY_CACHE_ENABLED,
			CompanyGroupModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public CompanyGroupPersistenceImpl() {
		setModelClass(CompanyGroup.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("companyGroupNo", "COMPANY_GROUP_NO");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("companyFilter", "COMPANY_FILTER");
			dbColumnNames.put("companyGroupSid", "COMPANY_GROUP_SID");
			dbColumnNames.put("companyGroupDescription",
				"COMPANY_GROUP_DESCRIPTION");
			dbColumnNames.put("versionNo", "VERSION_NO");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("companyGroupName", "COMPANY_GROUP_NAME");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the company group in the entity cache if it is enabled.
	 *
	 * @param companyGroup the company group
	 */
	@Override
	public void cacheResult(CompanyGroup companyGroup) {
		entityCache.putResult(CompanyGroupModelImpl.ENTITY_CACHE_ENABLED,
			CompanyGroupImpl.class, companyGroup.getPrimaryKey(), companyGroup);

		companyGroup.resetOriginalValues();
	}

	/**
	 * Caches the company groups in the entity cache if it is enabled.
	 *
	 * @param companyGroups the company groups
	 */
	@Override
	public void cacheResult(List<CompanyGroup> companyGroups) {
		for (CompanyGroup companyGroup : companyGroups) {
			if (entityCache.getResult(
						CompanyGroupModelImpl.ENTITY_CACHE_ENABLED,
						CompanyGroupImpl.class, companyGroup.getPrimaryKey()) == null) {
				cacheResult(companyGroup);
			}
			else {
				companyGroup.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all company groups.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CompanyGroupImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the company group.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CompanyGroup companyGroup) {
		entityCache.removeResult(CompanyGroupModelImpl.ENTITY_CACHE_ENABLED,
			CompanyGroupImpl.class, companyGroup.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<CompanyGroup> companyGroups) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CompanyGroup companyGroup : companyGroups) {
			entityCache.removeResult(CompanyGroupModelImpl.ENTITY_CACHE_ENABLED,
				CompanyGroupImpl.class, companyGroup.getPrimaryKey());
		}
	}

	/**
	 * Creates a new company group with the primary key. Does not add the company group to the database.
	 *
	 * @param companyGroupSid the primary key for the new company group
	 * @return the new company group
	 */
	@Override
	public CompanyGroup create(int companyGroupSid) {
		CompanyGroup companyGroup = new CompanyGroupImpl();

		companyGroup.setNew(true);
		companyGroup.setPrimaryKey(companyGroupSid);

		return companyGroup;
	}

	/**
	 * Removes the company group with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param companyGroupSid the primary key of the company group
	 * @return the company group that was removed
	 * @throws NoSuchCompanyGroupException if a company group with the primary key could not be found
	 */
	@Override
	public CompanyGroup remove(int companyGroupSid)
		throws NoSuchCompanyGroupException {
		return remove((Serializable)companyGroupSid);
	}

	/**
	 * Removes the company group with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the company group
	 * @return the company group that was removed
	 * @throws NoSuchCompanyGroupException if a company group with the primary key could not be found
	 */
	@Override
	public CompanyGroup remove(Serializable primaryKey)
		throws NoSuchCompanyGroupException {
		Session session = null;

		try {
			session = openSession();

			CompanyGroup companyGroup = (CompanyGroup)session.get(CompanyGroupImpl.class,
					primaryKey);

			if (companyGroup == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCompanyGroupException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(companyGroup);
		}
		catch (NoSuchCompanyGroupException nsee) {
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
	protected CompanyGroup removeImpl(CompanyGroup companyGroup) {
		companyGroup = toUnwrappedModel(companyGroup);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(companyGroup)) {
				companyGroup = (CompanyGroup)session.get(CompanyGroupImpl.class,
						companyGroup.getPrimaryKeyObj());
			}

			if (companyGroup != null) {
				session.delete(companyGroup);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (companyGroup != null) {
			clearCache(companyGroup);
		}

		return companyGroup;
	}

	@Override
	public CompanyGroup updateImpl(CompanyGroup companyGroup) {
		companyGroup = toUnwrappedModel(companyGroup);

		boolean isNew = companyGroup.isNew();

		Session session = null;

		try {
			session = openSession();

			if (companyGroup.isNew()) {
				session.save(companyGroup);

				companyGroup.setNew(false);
			}
			else {
				companyGroup = (CompanyGroup)session.merge(companyGroup);
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

		entityCache.putResult(CompanyGroupModelImpl.ENTITY_CACHE_ENABLED,
			CompanyGroupImpl.class, companyGroup.getPrimaryKey(), companyGroup,
			false);

		companyGroup.resetOriginalValues();

		return companyGroup;
	}

	protected CompanyGroup toUnwrappedModel(CompanyGroup companyGroup) {
		if (companyGroup instanceof CompanyGroupImpl) {
			return companyGroup;
		}

		CompanyGroupImpl companyGroupImpl = new CompanyGroupImpl();

		companyGroupImpl.setNew(companyGroup.isNew());
		companyGroupImpl.setPrimaryKey(companyGroup.getPrimaryKey());

		companyGroupImpl.setCompanyGroupNo(companyGroup.getCompanyGroupNo());
		companyGroupImpl.setCreatedDate(companyGroup.getCreatedDate());
		companyGroupImpl.setCreatedBy(companyGroup.getCreatedBy());
		companyGroupImpl.setCompanyFilter(companyGroup.getCompanyFilter());
		companyGroupImpl.setCompanyGroupSid(companyGroup.getCompanyGroupSid());
		companyGroupImpl.setCompanyGroupDescription(companyGroup.getCompanyGroupDescription());
		companyGroupImpl.setVersionNo(companyGroup.getVersionNo());
		companyGroupImpl.setModifiedBy(companyGroup.getModifiedBy());
		companyGroupImpl.setModifiedDate(companyGroup.getModifiedDate());
		companyGroupImpl.setCompanyGroupName(companyGroup.getCompanyGroupName());

		return companyGroupImpl;
	}

	/**
	 * Returns the company group with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the company group
	 * @return the company group
	 * @throws NoSuchCompanyGroupException if a company group with the primary key could not be found
	 */
	@Override
	public CompanyGroup findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCompanyGroupException {
		CompanyGroup companyGroup = fetchByPrimaryKey(primaryKey);

		if (companyGroup == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCompanyGroupException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return companyGroup;
	}

	/**
	 * Returns the company group with the primary key or throws a {@link NoSuchCompanyGroupException} if it could not be found.
	 *
	 * @param companyGroupSid the primary key of the company group
	 * @return the company group
	 * @throws NoSuchCompanyGroupException if a company group with the primary key could not be found
	 */
	@Override
	public CompanyGroup findByPrimaryKey(int companyGroupSid)
		throws NoSuchCompanyGroupException {
		return findByPrimaryKey((Serializable)companyGroupSid);
	}

	/**
	 * Returns the company group with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the company group
	 * @return the company group, or <code>null</code> if a company group with the primary key could not be found
	 */
	@Override
	public CompanyGroup fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(CompanyGroupModelImpl.ENTITY_CACHE_ENABLED,
				CompanyGroupImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CompanyGroup companyGroup = (CompanyGroup)serializable;

		if (companyGroup == null) {
			Session session = null;

			try {
				session = openSession();

				companyGroup = (CompanyGroup)session.get(CompanyGroupImpl.class,
						primaryKey);

				if (companyGroup != null) {
					cacheResult(companyGroup);
				}
				else {
					entityCache.putResult(CompanyGroupModelImpl.ENTITY_CACHE_ENABLED,
						CompanyGroupImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(CompanyGroupModelImpl.ENTITY_CACHE_ENABLED,
					CompanyGroupImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return companyGroup;
	}

	/**
	 * Returns the company group with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param companyGroupSid the primary key of the company group
	 * @return the company group, or <code>null</code> if a company group with the primary key could not be found
	 */
	@Override
	public CompanyGroup fetchByPrimaryKey(int companyGroupSid) {
		return fetchByPrimaryKey((Serializable)companyGroupSid);
	}

	@Override
	public Map<Serializable, CompanyGroup> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CompanyGroup> map = new HashMap<Serializable, CompanyGroup>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CompanyGroup companyGroup = fetchByPrimaryKey(primaryKey);

			if (companyGroup != null) {
				map.put(primaryKey, companyGroup);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(CompanyGroupModelImpl.ENTITY_CACHE_ENABLED,
					CompanyGroupImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CompanyGroup)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_COMPANYGROUP_WHERE_PKS_IN);

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

			for (CompanyGroup companyGroup : (List<CompanyGroup>)q.list()) {
				map.put(companyGroup.getPrimaryKeyObj(), companyGroup);

				cacheResult(companyGroup);

				uncachedPrimaryKeys.remove(companyGroup.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(CompanyGroupModelImpl.ENTITY_CACHE_ENABLED,
					CompanyGroupImpl.class, primaryKey, nullModel);
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
	 * Returns all the company groups.
	 *
	 * @return the company groups
	 */
	@Override
	public List<CompanyGroup> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the company groups.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of company groups
	 * @param end the upper bound of the range of company groups (not inclusive)
	 * @return the range of company groups
	 */
	@Override
	public List<CompanyGroup> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the company groups.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of company groups
	 * @param end the upper bound of the range of company groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of company groups
	 */
	@Override
	public List<CompanyGroup> findAll(int start, int end,
		OrderByComparator<CompanyGroup> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the company groups.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of company groups
	 * @param end the upper bound of the range of company groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of company groups
	 */
	@Override
	public List<CompanyGroup> findAll(int start, int end,
		OrderByComparator<CompanyGroup> orderByComparator,
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

		List<CompanyGroup> list = null;

		if (retrieveFromCache) {
			list = (List<CompanyGroup>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_COMPANYGROUP);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_COMPANYGROUP;

				if (pagination) {
					sql = sql.concat(CompanyGroupModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CompanyGroup>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CompanyGroup>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the company groups from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CompanyGroup companyGroup : findAll()) {
			remove(companyGroup);
		}
	}

	/**
	 * Returns the number of company groups.
	 *
	 * @return the number of company groups
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_COMPANYGROUP);

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
		return CompanyGroupModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the company group persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(CompanyGroupImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_COMPANYGROUP = "SELECT companyGroup FROM CompanyGroup companyGroup";
	private static final String _SQL_SELECT_COMPANYGROUP_WHERE_PKS_IN = "SELECT companyGroup FROM CompanyGroup companyGroup WHERE COMPANY_GROUP_SID IN (";
	private static final String _SQL_COUNT_COMPANYGROUP = "SELECT COUNT(companyGroup) FROM CompanyGroup companyGroup";
	private static final String _ORDER_BY_ENTITY_ALIAS = "companyGroup.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CompanyGroup exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(CompanyGroupPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"companyGroupNo", "createdDate", "createdBy", "companyFilter",
				"companyGroupSid", "companyGroupDescription", "versionNo",
				"modifiedBy", "modifiedDate", "companyGroupName"
			});
}