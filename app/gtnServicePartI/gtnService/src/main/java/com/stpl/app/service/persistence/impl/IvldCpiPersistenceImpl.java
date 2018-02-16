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

import com.stpl.app.exception.NoSuchIvldCpiException;
import com.stpl.app.model.IvldCpi;
import com.stpl.app.model.impl.IvldCpiImpl;
import com.stpl.app.model.impl.IvldCpiModelImpl;
import com.stpl.app.service.persistence.IvldCpiPersistence;

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
 * The persistence implementation for the ivld cpi service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldCpiPersistence
 * @see com.stpl.app.service.persistence.IvldCpiUtil
 * @generated
 */
@ProviderType
public class IvldCpiPersistenceImpl extends BasePersistenceImpl<IvldCpi>
	implements IvldCpiPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link IvldCpiUtil} to access the ivld cpi persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = IvldCpiImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(IvldCpiModelImpl.ENTITY_CACHE_ENABLED,
			IvldCpiModelImpl.FINDER_CACHE_ENABLED, IvldCpiImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(IvldCpiModelImpl.ENTITY_CACHE_ENABLED,
			IvldCpiModelImpl.FINDER_CACHE_ENABLED, IvldCpiImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(IvldCpiModelImpl.ENTITY_CACHE_ENABLED,
			IvldCpiModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public IvldCpiPersistenceImpl() {
		setModelClass(IvldCpi.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("effectiveDate", "EFFECTIVE_DATE");
			dbColumnNames.put("reasonForFailure", "REASON_FOR_FAILURE");
			dbColumnNames.put("indexType", "INDEX_TYPE");
			dbColumnNames.put("status", "STATUS");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("cpiIntfid", "CPI_INTFID");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("indexValue", "INDEX_VALUE");
			dbColumnNames.put("addChgDelIndicator", "ADD_CHG_DEL_INDICATOR");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("ivldCpiSid", "IVLD_CPI_SID");
			dbColumnNames.put("errorField", "ERROR_FIELD");
			dbColumnNames.put("errorCode", "ERROR_CODE");
			dbColumnNames.put("intfInsertedDate", "INTF_INSERTED_DATE");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("reprocessedFlag", "REPROCESSED_FLAG");
			dbColumnNames.put("indexId", "INDEX_ID");
			dbColumnNames.put("checkRecord", "CHECK_RECORD");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the ivld cpi in the entity cache if it is enabled.
	 *
	 * @param ivldCpi the ivld cpi
	 */
	@Override
	public void cacheResult(IvldCpi ivldCpi) {
		entityCache.putResult(IvldCpiModelImpl.ENTITY_CACHE_ENABLED,
			IvldCpiImpl.class, ivldCpi.getPrimaryKey(), ivldCpi);

		ivldCpi.resetOriginalValues();
	}

	/**
	 * Caches the ivld cpis in the entity cache if it is enabled.
	 *
	 * @param ivldCpis the ivld cpis
	 */
	@Override
	public void cacheResult(List<IvldCpi> ivldCpis) {
		for (IvldCpi ivldCpi : ivldCpis) {
			if (entityCache.getResult(IvldCpiModelImpl.ENTITY_CACHE_ENABLED,
						IvldCpiImpl.class, ivldCpi.getPrimaryKey()) == null) {
				cacheResult(ivldCpi);
			}
			else {
				ivldCpi.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all ivld cpis.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(IvldCpiImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the ivld cpi.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(IvldCpi ivldCpi) {
		entityCache.removeResult(IvldCpiModelImpl.ENTITY_CACHE_ENABLED,
			IvldCpiImpl.class, ivldCpi.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<IvldCpi> ivldCpis) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (IvldCpi ivldCpi : ivldCpis) {
			entityCache.removeResult(IvldCpiModelImpl.ENTITY_CACHE_ENABLED,
				IvldCpiImpl.class, ivldCpi.getPrimaryKey());
		}
	}

	/**
	 * Creates a new ivld cpi with the primary key. Does not add the ivld cpi to the database.
	 *
	 * @param ivldCpiSid the primary key for the new ivld cpi
	 * @return the new ivld cpi
	 */
	@Override
	public IvldCpi create(int ivldCpiSid) {
		IvldCpi ivldCpi = new IvldCpiImpl();

		ivldCpi.setNew(true);
		ivldCpi.setPrimaryKey(ivldCpiSid);

		return ivldCpi;
	}

	/**
	 * Removes the ivld cpi with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ivldCpiSid the primary key of the ivld cpi
	 * @return the ivld cpi that was removed
	 * @throws NoSuchIvldCpiException if a ivld cpi with the primary key could not be found
	 */
	@Override
	public IvldCpi remove(int ivldCpiSid) throws NoSuchIvldCpiException {
		return remove((Serializable)ivldCpiSid);
	}

	/**
	 * Removes the ivld cpi with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ivld cpi
	 * @return the ivld cpi that was removed
	 * @throws NoSuchIvldCpiException if a ivld cpi with the primary key could not be found
	 */
	@Override
	public IvldCpi remove(Serializable primaryKey)
		throws NoSuchIvldCpiException {
		Session session = null;

		try {
			session = openSession();

			IvldCpi ivldCpi = (IvldCpi)session.get(IvldCpiImpl.class, primaryKey);

			if (ivldCpi == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchIvldCpiException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(ivldCpi);
		}
		catch (NoSuchIvldCpiException nsee) {
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
	protected IvldCpi removeImpl(IvldCpi ivldCpi) {
		ivldCpi = toUnwrappedModel(ivldCpi);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(ivldCpi)) {
				ivldCpi = (IvldCpi)session.get(IvldCpiImpl.class,
						ivldCpi.getPrimaryKeyObj());
			}

			if (ivldCpi != null) {
				session.delete(ivldCpi);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (ivldCpi != null) {
			clearCache(ivldCpi);
		}

		return ivldCpi;
	}

	@Override
	public IvldCpi updateImpl(IvldCpi ivldCpi) {
		ivldCpi = toUnwrappedModel(ivldCpi);

		boolean isNew = ivldCpi.isNew();

		Session session = null;

		try {
			session = openSession();

			if (ivldCpi.isNew()) {
				session.save(ivldCpi);

				ivldCpi.setNew(false);
			}
			else {
				ivldCpi = (IvldCpi)session.merge(ivldCpi);
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

		entityCache.putResult(IvldCpiModelImpl.ENTITY_CACHE_ENABLED,
			IvldCpiImpl.class, ivldCpi.getPrimaryKey(), ivldCpi, false);

		ivldCpi.resetOriginalValues();

		return ivldCpi;
	}

	protected IvldCpi toUnwrappedModel(IvldCpi ivldCpi) {
		if (ivldCpi instanceof IvldCpiImpl) {
			return ivldCpi;
		}

		IvldCpiImpl ivldCpiImpl = new IvldCpiImpl();

		ivldCpiImpl.setNew(ivldCpi.isNew());
		ivldCpiImpl.setPrimaryKey(ivldCpi.getPrimaryKey());

		ivldCpiImpl.setEffectiveDate(ivldCpi.getEffectiveDate());
		ivldCpiImpl.setReasonForFailure(ivldCpi.getReasonForFailure());
		ivldCpiImpl.setIndexType(ivldCpi.getIndexType());
		ivldCpiImpl.setStatus(ivldCpi.getStatus());
		ivldCpiImpl.setModifiedDate(ivldCpi.getModifiedDate());
		ivldCpiImpl.setCpiIntfid(ivldCpi.getCpiIntfid());
		ivldCpiImpl.setCreatedBy(ivldCpi.getCreatedBy());
		ivldCpiImpl.setCreatedDate(ivldCpi.getCreatedDate());
		ivldCpiImpl.setSource(ivldCpi.getSource());
		ivldCpiImpl.setIndexValue(ivldCpi.getIndexValue());
		ivldCpiImpl.setAddChgDelIndicator(ivldCpi.getAddChgDelIndicator());
		ivldCpiImpl.setBatchId(ivldCpi.getBatchId());
		ivldCpiImpl.setIvldCpiSid(ivldCpi.getIvldCpiSid());
		ivldCpiImpl.setErrorField(ivldCpi.getErrorField());
		ivldCpiImpl.setErrorCode(ivldCpi.getErrorCode());
		ivldCpiImpl.setIntfInsertedDate(ivldCpi.getIntfInsertedDate());
		ivldCpiImpl.setModifiedBy(ivldCpi.getModifiedBy());
		ivldCpiImpl.setReprocessedFlag(ivldCpi.getReprocessedFlag());
		ivldCpiImpl.setIndexId(ivldCpi.getIndexId());
		ivldCpiImpl.setCheckRecord(ivldCpi.isCheckRecord());

		return ivldCpiImpl;
	}

	/**
	 * Returns the ivld cpi with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the ivld cpi
	 * @return the ivld cpi
	 * @throws NoSuchIvldCpiException if a ivld cpi with the primary key could not be found
	 */
	@Override
	public IvldCpi findByPrimaryKey(Serializable primaryKey)
		throws NoSuchIvldCpiException {
		IvldCpi ivldCpi = fetchByPrimaryKey(primaryKey);

		if (ivldCpi == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchIvldCpiException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return ivldCpi;
	}

	/**
	 * Returns the ivld cpi with the primary key or throws a {@link NoSuchIvldCpiException} if it could not be found.
	 *
	 * @param ivldCpiSid the primary key of the ivld cpi
	 * @return the ivld cpi
	 * @throws NoSuchIvldCpiException if a ivld cpi with the primary key could not be found
	 */
	@Override
	public IvldCpi findByPrimaryKey(int ivldCpiSid)
		throws NoSuchIvldCpiException {
		return findByPrimaryKey((Serializable)ivldCpiSid);
	}

	/**
	 * Returns the ivld cpi with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ivld cpi
	 * @return the ivld cpi, or <code>null</code> if a ivld cpi with the primary key could not be found
	 */
	@Override
	public IvldCpi fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(IvldCpiModelImpl.ENTITY_CACHE_ENABLED,
				IvldCpiImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		IvldCpi ivldCpi = (IvldCpi)serializable;

		if (ivldCpi == null) {
			Session session = null;

			try {
				session = openSession();

				ivldCpi = (IvldCpi)session.get(IvldCpiImpl.class, primaryKey);

				if (ivldCpi != null) {
					cacheResult(ivldCpi);
				}
				else {
					entityCache.putResult(IvldCpiModelImpl.ENTITY_CACHE_ENABLED,
						IvldCpiImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(IvldCpiModelImpl.ENTITY_CACHE_ENABLED,
					IvldCpiImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return ivldCpi;
	}

	/**
	 * Returns the ivld cpi with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ivldCpiSid the primary key of the ivld cpi
	 * @return the ivld cpi, or <code>null</code> if a ivld cpi with the primary key could not be found
	 */
	@Override
	public IvldCpi fetchByPrimaryKey(int ivldCpiSid) {
		return fetchByPrimaryKey((Serializable)ivldCpiSid);
	}

	@Override
	public Map<Serializable, IvldCpi> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, IvldCpi> map = new HashMap<Serializable, IvldCpi>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			IvldCpi ivldCpi = fetchByPrimaryKey(primaryKey);

			if (ivldCpi != null) {
				map.put(primaryKey, ivldCpi);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(IvldCpiModelImpl.ENTITY_CACHE_ENABLED,
					IvldCpiImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (IvldCpi)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_IVLDCPI_WHERE_PKS_IN);

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

			for (IvldCpi ivldCpi : (List<IvldCpi>)q.list()) {
				map.put(ivldCpi.getPrimaryKeyObj(), ivldCpi);

				cacheResult(ivldCpi);

				uncachedPrimaryKeys.remove(ivldCpi.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(IvldCpiModelImpl.ENTITY_CACHE_ENABLED,
					IvldCpiImpl.class, primaryKey, nullModel);
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
	 * Returns all the ivld cpis.
	 *
	 * @return the ivld cpis
	 */
	@Override
	public List<IvldCpi> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ivld cpis.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldCpiModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld cpis
	 * @param end the upper bound of the range of ivld cpis (not inclusive)
	 * @return the range of ivld cpis
	 */
	@Override
	public List<IvldCpi> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ivld cpis.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldCpiModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld cpis
	 * @param end the upper bound of the range of ivld cpis (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ivld cpis
	 */
	@Override
	public List<IvldCpi> findAll(int start, int end,
		OrderByComparator<IvldCpi> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ivld cpis.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldCpiModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld cpis
	 * @param end the upper bound of the range of ivld cpis (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of ivld cpis
	 */
	@Override
	public List<IvldCpi> findAll(int start, int end,
		OrderByComparator<IvldCpi> orderByComparator, boolean retrieveFromCache) {
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

		List<IvldCpi> list = null;

		if (retrieveFromCache) {
			list = (List<IvldCpi>)finderCache.getResult(finderPath, finderArgs,
					this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_IVLDCPI);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_IVLDCPI;

				if (pagination) {
					sql = sql.concat(IvldCpiModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<IvldCpi>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<IvldCpi>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the ivld cpis from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (IvldCpi ivldCpi : findAll()) {
			remove(ivldCpi);
		}
	}

	/**
	 * Returns the number of ivld cpis.
	 *
	 * @return the number of ivld cpis
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_IVLDCPI);

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
		return IvldCpiModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ivld cpi persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(IvldCpiImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_IVLDCPI = "SELECT ivldCpi FROM IvldCpi ivldCpi";
	private static final String _SQL_SELECT_IVLDCPI_WHERE_PKS_IN = "SELECT ivldCpi FROM IvldCpi ivldCpi WHERE IVLD_CPI_SID IN (";
	private static final String _SQL_COUNT_IVLDCPI = "SELECT COUNT(ivldCpi) FROM IvldCpi ivldCpi";
	private static final String _ORDER_BY_ENTITY_ALIAS = "ivldCpi.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IvldCpi exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(IvldCpiPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"effectiveDate", "reasonForFailure", "indexType", "status",
				"modifiedDate", "cpiIntfid", "createdBy", "createdDate",
				"source", "indexValue", "addChgDelIndicator", "batchId",
				"ivldCpiSid", "errorField", "errorCode", "intfInsertedDate",
				"modifiedBy", "reprocessedFlag", "indexId", "checkRecord"
			});
}