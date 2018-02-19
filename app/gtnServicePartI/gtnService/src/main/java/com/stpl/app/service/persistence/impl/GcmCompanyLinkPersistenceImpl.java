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

import com.stpl.app.exception.NoSuchGcmCompanyLinkException;
import com.stpl.app.model.GcmCompanyLink;
import com.stpl.app.model.impl.GcmCompanyLinkImpl;
import com.stpl.app.model.impl.GcmCompanyLinkModelImpl;
import com.stpl.app.service.persistence.GcmCompanyLinkPersistence;

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
 * The persistence implementation for the gcm company link service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see GcmCompanyLinkPersistence
 * @see com.stpl.app.service.persistence.GcmCompanyLinkUtil
 * @generated
 */
@ProviderType
public class GcmCompanyLinkPersistenceImpl extends BasePersistenceImpl<GcmCompanyLink>
	implements GcmCompanyLinkPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link GcmCompanyLinkUtil} to access the gcm company link persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = GcmCompanyLinkImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(GcmCompanyLinkModelImpl.ENTITY_CACHE_ENABLED,
			GcmCompanyLinkModelImpl.FINDER_CACHE_ENABLED,
			GcmCompanyLinkImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(GcmCompanyLinkModelImpl.ENTITY_CACHE_ENABLED,
			GcmCompanyLinkModelImpl.FINDER_CACHE_ENABLED,
			GcmCompanyLinkImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(GcmCompanyLinkModelImpl.ENTITY_CACHE_ENABLED,
			GcmCompanyLinkModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public GcmCompanyLinkPersistenceImpl() {
		setModelClass(GcmCompanyLink.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("checkRecord", "CHECK_RECORD");
			dbColumnNames.put("userId", "USER_ID");
			dbColumnNames.put("companyNo", "COMPANY_NO");
			dbColumnNames.put("companyStringId", "COMPANY_ID");
			dbColumnNames.put("gcmCompanyLinkSid", "GCM_COMPANY_LINK_SID");
			dbColumnNames.put("sessionId", "SESSION_ID");
			dbColumnNames.put("companyName", "COMPANY_NAME");
			dbColumnNames.put("linkId", "LINK_ID");
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
	 * Caches the gcm company link in the entity cache if it is enabled.
	 *
	 * @param gcmCompanyLink the gcm company link
	 */
	@Override
	public void cacheResult(GcmCompanyLink gcmCompanyLink) {
		entityCache.putResult(GcmCompanyLinkModelImpl.ENTITY_CACHE_ENABLED,
			GcmCompanyLinkImpl.class, gcmCompanyLink.getPrimaryKey(),
			gcmCompanyLink);

		gcmCompanyLink.resetOriginalValues();
	}

	/**
	 * Caches the gcm company links in the entity cache if it is enabled.
	 *
	 * @param gcmCompanyLinks the gcm company links
	 */
	@Override
	public void cacheResult(List<GcmCompanyLink> gcmCompanyLinks) {
		for (GcmCompanyLink gcmCompanyLink : gcmCompanyLinks) {
			if (entityCache.getResult(
						GcmCompanyLinkModelImpl.ENTITY_CACHE_ENABLED,
						GcmCompanyLinkImpl.class, gcmCompanyLink.getPrimaryKey()) == null) {
				cacheResult(gcmCompanyLink);
			}
			else {
				gcmCompanyLink.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all gcm company links.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(GcmCompanyLinkImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the gcm company link.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(GcmCompanyLink gcmCompanyLink) {
		entityCache.removeResult(GcmCompanyLinkModelImpl.ENTITY_CACHE_ENABLED,
			GcmCompanyLinkImpl.class, gcmCompanyLink.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<GcmCompanyLink> gcmCompanyLinks) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (GcmCompanyLink gcmCompanyLink : gcmCompanyLinks) {
			entityCache.removeResult(GcmCompanyLinkModelImpl.ENTITY_CACHE_ENABLED,
				GcmCompanyLinkImpl.class, gcmCompanyLink.getPrimaryKey());
		}
	}

	/**
	 * Creates a new gcm company link with the primary key. Does not add the gcm company link to the database.
	 *
	 * @param gcmCompanyLinkSid the primary key for the new gcm company link
	 * @return the new gcm company link
	 */
	@Override
	public GcmCompanyLink create(int gcmCompanyLinkSid) {
		GcmCompanyLink gcmCompanyLink = new GcmCompanyLinkImpl();

		gcmCompanyLink.setNew(true);
		gcmCompanyLink.setPrimaryKey(gcmCompanyLinkSid);

		return gcmCompanyLink;
	}

	/**
	 * Removes the gcm company link with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param gcmCompanyLinkSid the primary key of the gcm company link
	 * @return the gcm company link that was removed
	 * @throws NoSuchGcmCompanyLinkException if a gcm company link with the primary key could not be found
	 */
	@Override
	public GcmCompanyLink remove(int gcmCompanyLinkSid)
		throws NoSuchGcmCompanyLinkException {
		return remove((Serializable)gcmCompanyLinkSid);
	}

	/**
	 * Removes the gcm company link with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the gcm company link
	 * @return the gcm company link that was removed
	 * @throws NoSuchGcmCompanyLinkException if a gcm company link with the primary key could not be found
	 */
	@Override
	public GcmCompanyLink remove(Serializable primaryKey)
		throws NoSuchGcmCompanyLinkException {
		Session session = null;

		try {
			session = openSession();

			GcmCompanyLink gcmCompanyLink = (GcmCompanyLink)session.get(GcmCompanyLinkImpl.class,
					primaryKey);

			if (gcmCompanyLink == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchGcmCompanyLinkException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(gcmCompanyLink);
		}
		catch (NoSuchGcmCompanyLinkException nsee) {
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
	protected GcmCompanyLink removeImpl(GcmCompanyLink gcmCompanyLink) {
		gcmCompanyLink = toUnwrappedModel(gcmCompanyLink);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(gcmCompanyLink)) {
				gcmCompanyLink = (GcmCompanyLink)session.get(GcmCompanyLinkImpl.class,
						gcmCompanyLink.getPrimaryKeyObj());
			}

			if (gcmCompanyLink != null) {
				session.delete(gcmCompanyLink);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (gcmCompanyLink != null) {
			clearCache(gcmCompanyLink);
		}

		return gcmCompanyLink;
	}

	@Override
	public GcmCompanyLink updateImpl(GcmCompanyLink gcmCompanyLink) {
		gcmCompanyLink = toUnwrappedModel(gcmCompanyLink);

		boolean isNew = gcmCompanyLink.isNew();

		Session session = null;

		try {
			session = openSession();

			if (gcmCompanyLink.isNew()) {
				session.save(gcmCompanyLink);

				gcmCompanyLink.setNew(false);
			}
			else {
				gcmCompanyLink = (GcmCompanyLink)session.merge(gcmCompanyLink);
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

		entityCache.putResult(GcmCompanyLinkModelImpl.ENTITY_CACHE_ENABLED,
			GcmCompanyLinkImpl.class, gcmCompanyLink.getPrimaryKey(),
			gcmCompanyLink, false);

		gcmCompanyLink.resetOriginalValues();

		return gcmCompanyLink;
	}

	protected GcmCompanyLink toUnwrappedModel(GcmCompanyLink gcmCompanyLink) {
		if (gcmCompanyLink instanceof GcmCompanyLinkImpl) {
			return gcmCompanyLink;
		}

		GcmCompanyLinkImpl gcmCompanyLinkImpl = new GcmCompanyLinkImpl();

		gcmCompanyLinkImpl.setNew(gcmCompanyLink.isNew());
		gcmCompanyLinkImpl.setPrimaryKey(gcmCompanyLink.getPrimaryKey());

		gcmCompanyLinkImpl.setCheckRecord(gcmCompanyLink.isCheckRecord());
		gcmCompanyLinkImpl.setUserId(gcmCompanyLink.getUserId());
		gcmCompanyLinkImpl.setCompanyNo(gcmCompanyLink.getCompanyNo());
		gcmCompanyLinkImpl.setCompanyStringId(gcmCompanyLink.getCompanyStringId());
		gcmCompanyLinkImpl.setGcmCompanyLinkSid(gcmCompanyLink.getGcmCompanyLinkSid());
		gcmCompanyLinkImpl.setSessionId(gcmCompanyLink.getSessionId());
		gcmCompanyLinkImpl.setCompanyName(gcmCompanyLink.getCompanyName());
		gcmCompanyLinkImpl.setLinkId(gcmCompanyLink.getLinkId());
		gcmCompanyLinkImpl.setCompanyMasterSid(gcmCompanyLink.getCompanyMasterSid());

		return gcmCompanyLinkImpl;
	}

	/**
	 * Returns the gcm company link with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the gcm company link
	 * @return the gcm company link
	 * @throws NoSuchGcmCompanyLinkException if a gcm company link with the primary key could not be found
	 */
	@Override
	public GcmCompanyLink findByPrimaryKey(Serializable primaryKey)
		throws NoSuchGcmCompanyLinkException {
		GcmCompanyLink gcmCompanyLink = fetchByPrimaryKey(primaryKey);

		if (gcmCompanyLink == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchGcmCompanyLinkException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return gcmCompanyLink;
	}

	/**
	 * Returns the gcm company link with the primary key or throws a {@link NoSuchGcmCompanyLinkException} if it could not be found.
	 *
	 * @param gcmCompanyLinkSid the primary key of the gcm company link
	 * @return the gcm company link
	 * @throws NoSuchGcmCompanyLinkException if a gcm company link with the primary key could not be found
	 */
	@Override
	public GcmCompanyLink findByPrimaryKey(int gcmCompanyLinkSid)
		throws NoSuchGcmCompanyLinkException {
		return findByPrimaryKey((Serializable)gcmCompanyLinkSid);
	}

	/**
	 * Returns the gcm company link with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the gcm company link
	 * @return the gcm company link, or <code>null</code> if a gcm company link with the primary key could not be found
	 */
	@Override
	public GcmCompanyLink fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(GcmCompanyLinkModelImpl.ENTITY_CACHE_ENABLED,
				GcmCompanyLinkImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		GcmCompanyLink gcmCompanyLink = (GcmCompanyLink)serializable;

		if (gcmCompanyLink == null) {
			Session session = null;

			try {
				session = openSession();

				gcmCompanyLink = (GcmCompanyLink)session.get(GcmCompanyLinkImpl.class,
						primaryKey);

				if (gcmCompanyLink != null) {
					cacheResult(gcmCompanyLink);
				}
				else {
					entityCache.putResult(GcmCompanyLinkModelImpl.ENTITY_CACHE_ENABLED,
						GcmCompanyLinkImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(GcmCompanyLinkModelImpl.ENTITY_CACHE_ENABLED,
					GcmCompanyLinkImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return gcmCompanyLink;
	}

	/**
	 * Returns the gcm company link with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param gcmCompanyLinkSid the primary key of the gcm company link
	 * @return the gcm company link, or <code>null</code> if a gcm company link with the primary key could not be found
	 */
	@Override
	public GcmCompanyLink fetchByPrimaryKey(int gcmCompanyLinkSid) {
		return fetchByPrimaryKey((Serializable)gcmCompanyLinkSid);
	}

	@Override
	public Map<Serializable, GcmCompanyLink> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, GcmCompanyLink> map = new HashMap<Serializable, GcmCompanyLink>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			GcmCompanyLink gcmCompanyLink = fetchByPrimaryKey(primaryKey);

			if (gcmCompanyLink != null) {
				map.put(primaryKey, gcmCompanyLink);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(GcmCompanyLinkModelImpl.ENTITY_CACHE_ENABLED,
					GcmCompanyLinkImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (GcmCompanyLink)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_GCMCOMPANYLINK_WHERE_PKS_IN);

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

			for (GcmCompanyLink gcmCompanyLink : (List<GcmCompanyLink>)q.list()) {
				map.put(gcmCompanyLink.getPrimaryKeyObj(), gcmCompanyLink);

				cacheResult(gcmCompanyLink);

				uncachedPrimaryKeys.remove(gcmCompanyLink.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(GcmCompanyLinkModelImpl.ENTITY_CACHE_ENABLED,
					GcmCompanyLinkImpl.class, primaryKey, nullModel);
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
	 * Returns all the gcm company links.
	 *
	 * @return the gcm company links
	 */
	@Override
	public List<GcmCompanyLink> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the gcm company links.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GcmCompanyLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of gcm company links
	 * @param end the upper bound of the range of gcm company links (not inclusive)
	 * @return the range of gcm company links
	 */
	@Override
	public List<GcmCompanyLink> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the gcm company links.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GcmCompanyLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of gcm company links
	 * @param end the upper bound of the range of gcm company links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of gcm company links
	 */
	@Override
	public List<GcmCompanyLink> findAll(int start, int end,
		OrderByComparator<GcmCompanyLink> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the gcm company links.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GcmCompanyLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of gcm company links
	 * @param end the upper bound of the range of gcm company links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of gcm company links
	 */
	@Override
	public List<GcmCompanyLink> findAll(int start, int end,
		OrderByComparator<GcmCompanyLink> orderByComparator,
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

		List<GcmCompanyLink> list = null;

		if (retrieveFromCache) {
			list = (List<GcmCompanyLink>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_GCMCOMPANYLINK);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_GCMCOMPANYLINK;

				if (pagination) {
					sql = sql.concat(GcmCompanyLinkModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<GcmCompanyLink>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<GcmCompanyLink>)QueryUtil.list(q,
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
	 * Removes all the gcm company links from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (GcmCompanyLink gcmCompanyLink : findAll()) {
			remove(gcmCompanyLink);
		}
	}

	/**
	 * Returns the number of gcm company links.
	 *
	 * @return the number of gcm company links
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_GCMCOMPANYLINK);

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
		return GcmCompanyLinkModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the gcm company link persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(GcmCompanyLinkImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_GCMCOMPANYLINK = "SELECT gcmCompanyLink FROM GcmCompanyLink gcmCompanyLink";
	private static final String _SQL_SELECT_GCMCOMPANYLINK_WHERE_PKS_IN = "SELECT gcmCompanyLink FROM GcmCompanyLink gcmCompanyLink WHERE GCM_COMPANY_LINK_SID IN (";
	private static final String _SQL_COUNT_GCMCOMPANYLINK = "SELECT COUNT(gcmCompanyLink) FROM GcmCompanyLink gcmCompanyLink";
	private static final String _ORDER_BY_ENTITY_ALIAS = "gcmCompanyLink.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No GcmCompanyLink exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(GcmCompanyLinkPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"checkRecord", "userId", "companyNo", "companyStringId",
				"gcmCompanyLinkSid", "sessionId", "companyName", "linkId",
				"companyMasterSid"
			});
}