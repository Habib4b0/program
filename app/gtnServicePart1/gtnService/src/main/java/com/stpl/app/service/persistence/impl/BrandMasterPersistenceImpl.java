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

import com.stpl.app.exception.NoSuchBrandMasterException;
import com.stpl.app.model.BrandMaster;
import com.stpl.app.model.impl.BrandMasterImpl;
import com.stpl.app.model.impl.BrandMasterModelImpl;
import com.stpl.app.service.persistence.BrandMasterPersistence;

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
 * The persistence implementation for the brand master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see BrandMasterPersistence
 * @see com.stpl.app.service.persistence.BrandMasterUtil
 * @generated
 */
@ProviderType
public class BrandMasterPersistenceImpl extends BasePersistenceImpl<BrandMaster>
	implements BrandMasterPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link BrandMasterUtil} to access the brand master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = BrandMasterImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(BrandMasterModelImpl.ENTITY_CACHE_ENABLED,
			BrandMasterModelImpl.FINDER_CACHE_ENABLED, BrandMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(BrandMasterModelImpl.ENTITY_CACHE_ENABLED,
			BrandMasterModelImpl.FINDER_CACHE_ENABLED, BrandMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(BrandMasterModelImpl.ENTITY_CACHE_ENABLED,
			BrandMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public BrandMasterPersistenceImpl() {
		setModelClass(BrandMaster.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("brandMasterSid", "BRAND_MASTER_SID");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("brandId", "BRAND_ID");
			dbColumnNames.put("displayBrand", "DISPLAY_BRAND");
			dbColumnNames.put("recordLockStatus", "RECORD_LOCK_STATUS");
			dbColumnNames.put("brandName", "BRAND_NAME");
			dbColumnNames.put("brandDesc", "BRAND_DESC");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("inboundStatus", "INBOUND_STATUS");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the brand master in the entity cache if it is enabled.
	 *
	 * @param brandMaster the brand master
	 */
	@Override
	public void cacheResult(BrandMaster brandMaster) {
		entityCache.putResult(BrandMasterModelImpl.ENTITY_CACHE_ENABLED,
			BrandMasterImpl.class, brandMaster.getPrimaryKey(), brandMaster);

		brandMaster.resetOriginalValues();
	}

	/**
	 * Caches the brand masters in the entity cache if it is enabled.
	 *
	 * @param brandMasters the brand masters
	 */
	@Override
	public void cacheResult(List<BrandMaster> brandMasters) {
		for (BrandMaster brandMaster : brandMasters) {
			if (entityCache.getResult(
						BrandMasterModelImpl.ENTITY_CACHE_ENABLED,
						BrandMasterImpl.class, brandMaster.getPrimaryKey()) == null) {
				cacheResult(brandMaster);
			}
			else {
				brandMaster.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all brand masters.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(BrandMasterImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the brand master.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(BrandMaster brandMaster) {
		entityCache.removeResult(BrandMasterModelImpl.ENTITY_CACHE_ENABLED,
			BrandMasterImpl.class, brandMaster.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<BrandMaster> brandMasters) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (BrandMaster brandMaster : brandMasters) {
			entityCache.removeResult(BrandMasterModelImpl.ENTITY_CACHE_ENABLED,
				BrandMasterImpl.class, brandMaster.getPrimaryKey());
		}
	}

	/**
	 * Creates a new brand master with the primary key. Does not add the brand master to the database.
	 *
	 * @param brandMasterSid the primary key for the new brand master
	 * @return the new brand master
	 */
	@Override
	public BrandMaster create(int brandMasterSid) {
		BrandMaster brandMaster = new BrandMasterImpl();

		brandMaster.setNew(true);
		brandMaster.setPrimaryKey(brandMasterSid);

		return brandMaster;
	}

	/**
	 * Removes the brand master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param brandMasterSid the primary key of the brand master
	 * @return the brand master that was removed
	 * @throws NoSuchBrandMasterException if a brand master with the primary key could not be found
	 */
	@Override
	public BrandMaster remove(int brandMasterSid)
		throws NoSuchBrandMasterException {
		return remove((Serializable)brandMasterSid);
	}

	/**
	 * Removes the brand master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the brand master
	 * @return the brand master that was removed
	 * @throws NoSuchBrandMasterException if a brand master with the primary key could not be found
	 */
	@Override
	public BrandMaster remove(Serializable primaryKey)
		throws NoSuchBrandMasterException {
		Session session = null;

		try {
			session = openSession();

			BrandMaster brandMaster = (BrandMaster)session.get(BrandMasterImpl.class,
					primaryKey);

			if (brandMaster == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchBrandMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(brandMaster);
		}
		catch (NoSuchBrandMasterException nsee) {
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
	protected BrandMaster removeImpl(BrandMaster brandMaster) {
		brandMaster = toUnwrappedModel(brandMaster);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(brandMaster)) {
				brandMaster = (BrandMaster)session.get(BrandMasterImpl.class,
						brandMaster.getPrimaryKeyObj());
			}

			if (brandMaster != null) {
				session.delete(brandMaster);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (brandMaster != null) {
			clearCache(brandMaster);
		}

		return brandMaster;
	}

	@Override
	public BrandMaster updateImpl(BrandMaster brandMaster) {
		brandMaster = toUnwrappedModel(brandMaster);

		boolean isNew = brandMaster.isNew();

		Session session = null;

		try {
			session = openSession();

			if (brandMaster.isNew()) {
				session.save(brandMaster);

				brandMaster.setNew(false);
			}
			else {
				brandMaster = (BrandMaster)session.merge(brandMaster);
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

		entityCache.putResult(BrandMasterModelImpl.ENTITY_CACHE_ENABLED,
			BrandMasterImpl.class, brandMaster.getPrimaryKey(), brandMaster,
			false);

		brandMaster.resetOriginalValues();

		return brandMaster;
	}

	protected BrandMaster toUnwrappedModel(BrandMaster brandMaster) {
		if (brandMaster instanceof BrandMasterImpl) {
			return brandMaster;
		}

		BrandMasterImpl brandMasterImpl = new BrandMasterImpl();

		brandMasterImpl.setNew(brandMaster.isNew());
		brandMasterImpl.setPrimaryKey(brandMaster.getPrimaryKey());

		brandMasterImpl.setCreatedBy(brandMaster.getCreatedBy());
		brandMasterImpl.setModifiedBy(brandMaster.getModifiedBy());
		brandMasterImpl.setCreatedDate(brandMaster.getCreatedDate());
		brandMasterImpl.setBrandMasterSid(brandMaster.getBrandMasterSid());
		brandMasterImpl.setBatchId(brandMaster.getBatchId());
		brandMasterImpl.setModifiedDate(brandMaster.getModifiedDate());
		brandMasterImpl.setBrandId(brandMaster.getBrandId());
		brandMasterImpl.setDisplayBrand(brandMaster.getDisplayBrand());
		brandMasterImpl.setRecordLockStatus(brandMaster.isRecordLockStatus());
		brandMasterImpl.setBrandName(brandMaster.getBrandName());
		brandMasterImpl.setBrandDesc(brandMaster.getBrandDesc());
		brandMasterImpl.setSource(brandMaster.getSource());
		brandMasterImpl.setInboundStatus(brandMaster.getInboundStatus());

		return brandMasterImpl;
	}

	/**
	 * Returns the brand master with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the brand master
	 * @return the brand master
	 * @throws NoSuchBrandMasterException if a brand master with the primary key could not be found
	 */
	@Override
	public BrandMaster findByPrimaryKey(Serializable primaryKey)
		throws NoSuchBrandMasterException {
		BrandMaster brandMaster = fetchByPrimaryKey(primaryKey);

		if (brandMaster == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchBrandMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return brandMaster;
	}

	/**
	 * Returns the brand master with the primary key or throws a {@link NoSuchBrandMasterException} if it could not be found.
	 *
	 * @param brandMasterSid the primary key of the brand master
	 * @return the brand master
	 * @throws NoSuchBrandMasterException if a brand master with the primary key could not be found
	 */
	@Override
	public BrandMaster findByPrimaryKey(int brandMasterSid)
		throws NoSuchBrandMasterException {
		return findByPrimaryKey((Serializable)brandMasterSid);
	}

	/**
	 * Returns the brand master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the brand master
	 * @return the brand master, or <code>null</code> if a brand master with the primary key could not be found
	 */
	@Override
	public BrandMaster fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(BrandMasterModelImpl.ENTITY_CACHE_ENABLED,
				BrandMasterImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		BrandMaster brandMaster = (BrandMaster)serializable;

		if (brandMaster == null) {
			Session session = null;

			try {
				session = openSession();

				brandMaster = (BrandMaster)session.get(BrandMasterImpl.class,
						primaryKey);

				if (brandMaster != null) {
					cacheResult(brandMaster);
				}
				else {
					entityCache.putResult(BrandMasterModelImpl.ENTITY_CACHE_ENABLED,
						BrandMasterImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(BrandMasterModelImpl.ENTITY_CACHE_ENABLED,
					BrandMasterImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return brandMaster;
	}

	/**
	 * Returns the brand master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param brandMasterSid the primary key of the brand master
	 * @return the brand master, or <code>null</code> if a brand master with the primary key could not be found
	 */
	@Override
	public BrandMaster fetchByPrimaryKey(int brandMasterSid) {
		return fetchByPrimaryKey((Serializable)brandMasterSid);
	}

	@Override
	public Map<Serializable, BrandMaster> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, BrandMaster> map = new HashMap<Serializable, BrandMaster>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			BrandMaster brandMaster = fetchByPrimaryKey(primaryKey);

			if (brandMaster != null) {
				map.put(primaryKey, brandMaster);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(BrandMasterModelImpl.ENTITY_CACHE_ENABLED,
					BrandMasterImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (BrandMaster)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_BRANDMASTER_WHERE_PKS_IN);

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

			for (BrandMaster brandMaster : (List<BrandMaster>)q.list()) {
				map.put(brandMaster.getPrimaryKeyObj(), brandMaster);

				cacheResult(brandMaster);

				uncachedPrimaryKeys.remove(brandMaster.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(BrandMasterModelImpl.ENTITY_CACHE_ENABLED,
					BrandMasterImpl.class, primaryKey, nullModel);
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
	 * Returns all the brand masters.
	 *
	 * @return the brand masters
	 */
	@Override
	public List<BrandMaster> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the brand masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BrandMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of brand masters
	 * @param end the upper bound of the range of brand masters (not inclusive)
	 * @return the range of brand masters
	 */
	@Override
	public List<BrandMaster> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the brand masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BrandMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of brand masters
	 * @param end the upper bound of the range of brand masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of brand masters
	 */
	@Override
	public List<BrandMaster> findAll(int start, int end,
		OrderByComparator<BrandMaster> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the brand masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BrandMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of brand masters
	 * @param end the upper bound of the range of brand masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of brand masters
	 */
	@Override
	public List<BrandMaster> findAll(int start, int end,
		OrderByComparator<BrandMaster> orderByComparator,
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

		List<BrandMaster> list = null;

		if (retrieveFromCache) {
			list = (List<BrandMaster>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_BRANDMASTER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_BRANDMASTER;

				if (pagination) {
					sql = sql.concat(BrandMasterModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<BrandMaster>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<BrandMaster>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the brand masters from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (BrandMaster brandMaster : findAll()) {
			remove(brandMaster);
		}
	}

	/**
	 * Returns the number of brand masters.
	 *
	 * @return the number of brand masters
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_BRANDMASTER);

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
		return BrandMasterModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the brand master persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(BrandMasterImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_BRANDMASTER = "SELECT brandMaster FROM BrandMaster brandMaster";
	private static final String _SQL_SELECT_BRANDMASTER_WHERE_PKS_IN = "SELECT brandMaster FROM BrandMaster brandMaster WHERE BRAND_MASTER_SID IN (";
	private static final String _SQL_COUNT_BRANDMASTER = "SELECT COUNT(brandMaster) FROM BrandMaster brandMaster";
	private static final String _ORDER_BY_ENTITY_ALIAS = "brandMaster.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No BrandMaster exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(BrandMasterPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"createdBy", "modifiedBy", "createdDate", "brandMasterSid",
				"batchId", "modifiedDate", "brandId", "displayBrand",
				"recordLockStatus", "brandName", "brandDesc", "source",
				"inboundStatus"
			});
}