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

import com.stpl.app.exception.NoSuchCustomViewDetailsException;
import com.stpl.app.model.CustomViewDetails;
import com.stpl.app.model.impl.CustomViewDetailsImpl;
import com.stpl.app.model.impl.CustomViewDetailsModelImpl;
import com.stpl.app.service.persistence.CustomViewDetailsPersistence;

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
 * The persistence implementation for the custom view details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CustomViewDetailsPersistence
 * @see com.stpl.app.service.persistence.CustomViewDetailsUtil
 * @generated
 */
@ProviderType
public class CustomViewDetailsPersistenceImpl extends BasePersistenceImpl<CustomViewDetails>
	implements CustomViewDetailsPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CustomViewDetailsUtil} to access the custom view details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CustomViewDetailsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CustomViewDetailsModelImpl.ENTITY_CACHE_ENABLED,
			CustomViewDetailsModelImpl.FINDER_CACHE_ENABLED,
			CustomViewDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CustomViewDetailsModelImpl.ENTITY_CACHE_ENABLED,
			CustomViewDetailsModelImpl.FINDER_CACHE_ENABLED,
			CustomViewDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CustomViewDetailsModelImpl.ENTITY_CACHE_ENABLED,
			CustomViewDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public CustomViewDetailsPersistenceImpl() {
		setModelClass(CustomViewDetails.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("hierarchyId", "HIERARCHY_ID");
			dbColumnNames.put("hierarchyIndicator", "HIERARCHY_INDICATOR");
			dbColumnNames.put("customViewMasterSid", "CUSTOM_VIEW_MASTER_SID");
			dbColumnNames.put("customViewDetailsSid", "CUSTOM_VIEW_DETAILS_SID");
			dbColumnNames.put("levelNo", "LEVEL_NO");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the custom view details in the entity cache if it is enabled.
	 *
	 * @param customViewDetails the custom view details
	 */
	@Override
	public void cacheResult(CustomViewDetails customViewDetails) {
		entityCache.putResult(CustomViewDetailsModelImpl.ENTITY_CACHE_ENABLED,
			CustomViewDetailsImpl.class, customViewDetails.getPrimaryKey(),
			customViewDetails);

		customViewDetails.resetOriginalValues();
	}

	/**
	 * Caches the custom view detailses in the entity cache if it is enabled.
	 *
	 * @param customViewDetailses the custom view detailses
	 */
	@Override
	public void cacheResult(List<CustomViewDetails> customViewDetailses) {
		for (CustomViewDetails customViewDetails : customViewDetailses) {
			if (entityCache.getResult(
						CustomViewDetailsModelImpl.ENTITY_CACHE_ENABLED,
						CustomViewDetailsImpl.class,
						customViewDetails.getPrimaryKey()) == null) {
				cacheResult(customViewDetails);
			}
			else {
				customViewDetails.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all custom view detailses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CustomViewDetailsImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the custom view details.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CustomViewDetails customViewDetails) {
		entityCache.removeResult(CustomViewDetailsModelImpl.ENTITY_CACHE_ENABLED,
			CustomViewDetailsImpl.class, customViewDetails.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<CustomViewDetails> customViewDetailses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CustomViewDetails customViewDetails : customViewDetailses) {
			entityCache.removeResult(CustomViewDetailsModelImpl.ENTITY_CACHE_ENABLED,
				CustomViewDetailsImpl.class, customViewDetails.getPrimaryKey());
		}
	}

	/**
	 * Creates a new custom view details with the primary key. Does not add the custom view details to the database.
	 *
	 * @param customViewDetailsSid the primary key for the new custom view details
	 * @return the new custom view details
	 */
	@Override
	public CustomViewDetails create(int customViewDetailsSid) {
		CustomViewDetails customViewDetails = new CustomViewDetailsImpl();

		customViewDetails.setNew(true);
		customViewDetails.setPrimaryKey(customViewDetailsSid);

		return customViewDetails;
	}

	/**
	 * Removes the custom view details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param customViewDetailsSid the primary key of the custom view details
	 * @return the custom view details that was removed
	 * @throws NoSuchCustomViewDetailsException if a custom view details with the primary key could not be found
	 */
	@Override
	public CustomViewDetails remove(int customViewDetailsSid)
		throws NoSuchCustomViewDetailsException {
		return remove((Serializable)customViewDetailsSid);
	}

	/**
	 * Removes the custom view details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the custom view details
	 * @return the custom view details that was removed
	 * @throws NoSuchCustomViewDetailsException if a custom view details with the primary key could not be found
	 */
	@Override
	public CustomViewDetails remove(Serializable primaryKey)
		throws NoSuchCustomViewDetailsException {
		Session session = null;

		try {
			session = openSession();

			CustomViewDetails customViewDetails = (CustomViewDetails)session.get(CustomViewDetailsImpl.class,
					primaryKey);

			if (customViewDetails == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCustomViewDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(customViewDetails);
		}
		catch (NoSuchCustomViewDetailsException nsee) {
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
	protected CustomViewDetails removeImpl(CustomViewDetails customViewDetails) {
		customViewDetails = toUnwrappedModel(customViewDetails);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(customViewDetails)) {
				customViewDetails = (CustomViewDetails)session.get(CustomViewDetailsImpl.class,
						customViewDetails.getPrimaryKeyObj());
			}

			if (customViewDetails != null) {
				session.delete(customViewDetails);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (customViewDetails != null) {
			clearCache(customViewDetails);
		}

		return customViewDetails;
	}

	@Override
	public CustomViewDetails updateImpl(CustomViewDetails customViewDetails) {
		customViewDetails = toUnwrappedModel(customViewDetails);

		boolean isNew = customViewDetails.isNew();

		Session session = null;

		try {
			session = openSession();

			if (customViewDetails.isNew()) {
				session.save(customViewDetails);

				customViewDetails.setNew(false);
			}
			else {
				customViewDetails = (CustomViewDetails)session.merge(customViewDetails);
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

		entityCache.putResult(CustomViewDetailsModelImpl.ENTITY_CACHE_ENABLED,
			CustomViewDetailsImpl.class, customViewDetails.getPrimaryKey(),
			customViewDetails, false);

		customViewDetails.resetOriginalValues();

		return customViewDetails;
	}

	protected CustomViewDetails toUnwrappedModel(
		CustomViewDetails customViewDetails) {
		if (customViewDetails instanceof CustomViewDetailsImpl) {
			return customViewDetails;
		}

		CustomViewDetailsImpl customViewDetailsImpl = new CustomViewDetailsImpl();

		customViewDetailsImpl.setNew(customViewDetails.isNew());
		customViewDetailsImpl.setPrimaryKey(customViewDetails.getPrimaryKey());

		customViewDetailsImpl.setHierarchyId(customViewDetails.getHierarchyId());
		customViewDetailsImpl.setHierarchyIndicator(customViewDetails.getHierarchyIndicator());
		customViewDetailsImpl.setCustomViewMasterSid(customViewDetails.getCustomViewMasterSid());
		customViewDetailsImpl.setCustomViewDetailsSid(customViewDetails.getCustomViewDetailsSid());
		customViewDetailsImpl.setLevelNo(customViewDetails.getLevelNo());

		return customViewDetailsImpl;
	}

	/**
	 * Returns the custom view details with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the custom view details
	 * @return the custom view details
	 * @throws NoSuchCustomViewDetailsException if a custom view details with the primary key could not be found
	 */
	@Override
	public CustomViewDetails findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCustomViewDetailsException {
		CustomViewDetails customViewDetails = fetchByPrimaryKey(primaryKey);

		if (customViewDetails == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCustomViewDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return customViewDetails;
	}

	/**
	 * Returns the custom view details with the primary key or throws a {@link NoSuchCustomViewDetailsException} if it could not be found.
	 *
	 * @param customViewDetailsSid the primary key of the custom view details
	 * @return the custom view details
	 * @throws NoSuchCustomViewDetailsException if a custom view details with the primary key could not be found
	 */
	@Override
	public CustomViewDetails findByPrimaryKey(int customViewDetailsSid)
		throws NoSuchCustomViewDetailsException {
		return findByPrimaryKey((Serializable)customViewDetailsSid);
	}

	/**
	 * Returns the custom view details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the custom view details
	 * @return the custom view details, or <code>null</code> if a custom view details with the primary key could not be found
	 */
	@Override
	public CustomViewDetails fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(CustomViewDetailsModelImpl.ENTITY_CACHE_ENABLED,
				CustomViewDetailsImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CustomViewDetails customViewDetails = (CustomViewDetails)serializable;

		if (customViewDetails == null) {
			Session session = null;

			try {
				session = openSession();

				customViewDetails = (CustomViewDetails)session.get(CustomViewDetailsImpl.class,
						primaryKey);

				if (customViewDetails != null) {
					cacheResult(customViewDetails);
				}
				else {
					entityCache.putResult(CustomViewDetailsModelImpl.ENTITY_CACHE_ENABLED,
						CustomViewDetailsImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(CustomViewDetailsModelImpl.ENTITY_CACHE_ENABLED,
					CustomViewDetailsImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return customViewDetails;
	}

	/**
	 * Returns the custom view details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param customViewDetailsSid the primary key of the custom view details
	 * @return the custom view details, or <code>null</code> if a custom view details with the primary key could not be found
	 */
	@Override
	public CustomViewDetails fetchByPrimaryKey(int customViewDetailsSid) {
		return fetchByPrimaryKey((Serializable)customViewDetailsSid);
	}

	@Override
	public Map<Serializable, CustomViewDetails> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CustomViewDetails> map = new HashMap<Serializable, CustomViewDetails>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CustomViewDetails customViewDetails = fetchByPrimaryKey(primaryKey);

			if (customViewDetails != null) {
				map.put(primaryKey, customViewDetails);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(CustomViewDetailsModelImpl.ENTITY_CACHE_ENABLED,
					CustomViewDetailsImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CustomViewDetails)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_CUSTOMVIEWDETAILS_WHERE_PKS_IN);

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

			for (CustomViewDetails customViewDetails : (List<CustomViewDetails>)q.list()) {
				map.put(customViewDetails.getPrimaryKeyObj(), customViewDetails);

				cacheResult(customViewDetails);

				uncachedPrimaryKeys.remove(customViewDetails.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(CustomViewDetailsModelImpl.ENTITY_CACHE_ENABLED,
					CustomViewDetailsImpl.class, primaryKey, nullModel);
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
	 * Returns all the custom view detailses.
	 *
	 * @return the custom view detailses
	 */
	@Override
	public List<CustomViewDetails> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the custom view detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CustomViewDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of custom view detailses
	 * @param end the upper bound of the range of custom view detailses (not inclusive)
	 * @return the range of custom view detailses
	 */
	@Override
	public List<CustomViewDetails> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the custom view detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CustomViewDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of custom view detailses
	 * @param end the upper bound of the range of custom view detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of custom view detailses
	 */
	@Override
	public List<CustomViewDetails> findAll(int start, int end,
		OrderByComparator<CustomViewDetails> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the custom view detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CustomViewDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of custom view detailses
	 * @param end the upper bound of the range of custom view detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of custom view detailses
	 */
	@Override
	public List<CustomViewDetails> findAll(int start, int end,
		OrderByComparator<CustomViewDetails> orderByComparator,
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

		List<CustomViewDetails> list = null;

		if (retrieveFromCache) {
			list = (List<CustomViewDetails>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CUSTOMVIEWDETAILS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CUSTOMVIEWDETAILS;

				if (pagination) {
					sql = sql.concat(CustomViewDetailsModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CustomViewDetails>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CustomViewDetails>)QueryUtil.list(q,
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
	 * Removes all the custom view detailses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CustomViewDetails customViewDetails : findAll()) {
			remove(customViewDetails);
		}
	}

	/**
	 * Returns the number of custom view detailses.
	 *
	 * @return the number of custom view detailses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CUSTOMVIEWDETAILS);

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
		return CustomViewDetailsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the custom view details persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(CustomViewDetailsImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_CUSTOMVIEWDETAILS = "SELECT customViewDetails FROM CustomViewDetails customViewDetails";
	private static final String _SQL_SELECT_CUSTOMVIEWDETAILS_WHERE_PKS_IN = "SELECT customViewDetails FROM CustomViewDetails customViewDetails WHERE CUSTOM_VIEW_DETAILS_SID IN (";
	private static final String _SQL_COUNT_CUSTOMVIEWDETAILS = "SELECT COUNT(customViewDetails) FROM CustomViewDetails customViewDetails";
	private static final String _ORDER_BY_ENTITY_ALIAS = "customViewDetails.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CustomViewDetails exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(CustomViewDetailsPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"hierarchyId", "hierarchyIndicator", "customViewMasterSid",
				"customViewDetailsSid", "levelNo"
			});
}