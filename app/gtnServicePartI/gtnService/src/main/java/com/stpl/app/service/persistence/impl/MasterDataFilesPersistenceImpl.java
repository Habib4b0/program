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

import com.stpl.app.exception.NoSuchMasterDataFilesException;
import com.stpl.app.model.MasterDataFiles;
import com.stpl.app.model.impl.MasterDataFilesImpl;
import com.stpl.app.model.impl.MasterDataFilesModelImpl;
import com.stpl.app.service.persistence.MasterDataFilesPersistence;

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
 * The persistence implementation for the master data files service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see MasterDataFilesPersistence
 * @see com.stpl.app.service.persistence.MasterDataFilesUtil
 * @generated
 */
@ProviderType
public class MasterDataFilesPersistenceImpl extends BasePersistenceImpl<MasterDataFiles>
	implements MasterDataFilesPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link MasterDataFilesUtil} to access the master data files persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = MasterDataFilesImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(MasterDataFilesModelImpl.ENTITY_CACHE_ENABLED,
			MasterDataFilesModelImpl.FINDER_CACHE_ENABLED,
			MasterDataFilesImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(MasterDataFilesModelImpl.ENTITY_CACHE_ENABLED,
			MasterDataFilesModelImpl.FINDER_CACHE_ENABLED,
			MasterDataFilesImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MasterDataFilesModelImpl.ENTITY_CACHE_ENABLED,
			MasterDataFilesModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public MasterDataFilesPersistenceImpl() {
		setModelClass(MasterDataFiles.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("masterTableSid", "MASTER_TABLE_SID");
			dbColumnNames.put("masterDataFilesSid", "MASTER_DATA_FILES_SID");
			dbColumnNames.put("masterTableName", "MASTER_TABLE_NAME");
			dbColumnNames.put("filePath", "FILE_PATH");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the master data files in the entity cache if it is enabled.
	 *
	 * @param masterDataFiles the master data files
	 */
	@Override
	public void cacheResult(MasterDataFiles masterDataFiles) {
		entityCache.putResult(MasterDataFilesModelImpl.ENTITY_CACHE_ENABLED,
			MasterDataFilesImpl.class, masterDataFiles.getPrimaryKey(),
			masterDataFiles);

		masterDataFiles.resetOriginalValues();
	}

	/**
	 * Caches the master data fileses in the entity cache if it is enabled.
	 *
	 * @param masterDataFileses the master data fileses
	 */
	@Override
	public void cacheResult(List<MasterDataFiles> masterDataFileses) {
		for (MasterDataFiles masterDataFiles : masterDataFileses) {
			if (entityCache.getResult(
						MasterDataFilesModelImpl.ENTITY_CACHE_ENABLED,
						MasterDataFilesImpl.class,
						masterDataFiles.getPrimaryKey()) == null) {
				cacheResult(masterDataFiles);
			}
			else {
				masterDataFiles.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all master data fileses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(MasterDataFilesImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the master data files.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(MasterDataFiles masterDataFiles) {
		entityCache.removeResult(MasterDataFilesModelImpl.ENTITY_CACHE_ENABLED,
			MasterDataFilesImpl.class, masterDataFiles.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<MasterDataFiles> masterDataFileses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (MasterDataFiles masterDataFiles : masterDataFileses) {
			entityCache.removeResult(MasterDataFilesModelImpl.ENTITY_CACHE_ENABLED,
				MasterDataFilesImpl.class, masterDataFiles.getPrimaryKey());
		}
	}

	/**
	 * Creates a new master data files with the primary key. Does not add the master data files to the database.
	 *
	 * @param masterDataFilesSid the primary key for the new master data files
	 * @return the new master data files
	 */
	@Override
	public MasterDataFiles create(int masterDataFilesSid) {
		MasterDataFiles masterDataFiles = new MasterDataFilesImpl();

		masterDataFiles.setNew(true);
		masterDataFiles.setPrimaryKey(masterDataFilesSid);

		return masterDataFiles;
	}

	/**
	 * Removes the master data files with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param masterDataFilesSid the primary key of the master data files
	 * @return the master data files that was removed
	 * @throws NoSuchMasterDataFilesException if a master data files with the primary key could not be found
	 */
	@Override
	public MasterDataFiles remove(int masterDataFilesSid)
		throws NoSuchMasterDataFilesException {
		return remove((Serializable)masterDataFilesSid);
	}

	/**
	 * Removes the master data files with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the master data files
	 * @return the master data files that was removed
	 * @throws NoSuchMasterDataFilesException if a master data files with the primary key could not be found
	 */
	@Override
	public MasterDataFiles remove(Serializable primaryKey)
		throws NoSuchMasterDataFilesException {
		Session session = null;

		try {
			session = openSession();

			MasterDataFiles masterDataFiles = (MasterDataFiles)session.get(MasterDataFilesImpl.class,
					primaryKey);

			if (masterDataFiles == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchMasterDataFilesException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(masterDataFiles);
		}
		catch (NoSuchMasterDataFilesException nsee) {
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
	protected MasterDataFiles removeImpl(MasterDataFiles masterDataFiles) {
		masterDataFiles = toUnwrappedModel(masterDataFiles);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(masterDataFiles)) {
				masterDataFiles = (MasterDataFiles)session.get(MasterDataFilesImpl.class,
						masterDataFiles.getPrimaryKeyObj());
			}

			if (masterDataFiles != null) {
				session.delete(masterDataFiles);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (masterDataFiles != null) {
			clearCache(masterDataFiles);
		}

		return masterDataFiles;
	}

	@Override
	public MasterDataFiles updateImpl(MasterDataFiles masterDataFiles) {
		masterDataFiles = toUnwrappedModel(masterDataFiles);

		boolean isNew = masterDataFiles.isNew();

		Session session = null;

		try {
			session = openSession();

			if (masterDataFiles.isNew()) {
				session.save(masterDataFiles);

				masterDataFiles.setNew(false);
			}
			else {
				masterDataFiles = (MasterDataFiles)session.merge(masterDataFiles);
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

		entityCache.putResult(MasterDataFilesModelImpl.ENTITY_CACHE_ENABLED,
			MasterDataFilesImpl.class, masterDataFiles.getPrimaryKey(),
			masterDataFiles, false);

		masterDataFiles.resetOriginalValues();

		return masterDataFiles;
	}

	protected MasterDataFiles toUnwrappedModel(MasterDataFiles masterDataFiles) {
		if (masterDataFiles instanceof MasterDataFilesImpl) {
			return masterDataFiles;
		}

		MasterDataFilesImpl masterDataFilesImpl = new MasterDataFilesImpl();

		masterDataFilesImpl.setNew(masterDataFiles.isNew());
		masterDataFilesImpl.setPrimaryKey(masterDataFiles.getPrimaryKey());

		masterDataFilesImpl.setMasterTableSid(masterDataFiles.getMasterTableSid());
		masterDataFilesImpl.setMasterDataFilesSid(masterDataFiles.getMasterDataFilesSid());
		masterDataFilesImpl.setMasterTableName(masterDataFiles.getMasterTableName());
		masterDataFilesImpl.setFilePath(masterDataFiles.getFilePath());
		masterDataFilesImpl.setCreatedDate(masterDataFiles.getCreatedDate());
		masterDataFilesImpl.setCreatedBy(masterDataFiles.getCreatedBy());

		return masterDataFilesImpl;
	}

	/**
	 * Returns the master data files with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the master data files
	 * @return the master data files
	 * @throws NoSuchMasterDataFilesException if a master data files with the primary key could not be found
	 */
	@Override
	public MasterDataFiles findByPrimaryKey(Serializable primaryKey)
		throws NoSuchMasterDataFilesException {
		MasterDataFiles masterDataFiles = fetchByPrimaryKey(primaryKey);

		if (masterDataFiles == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchMasterDataFilesException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return masterDataFiles;
	}

	/**
	 * Returns the master data files with the primary key or throws a {@link NoSuchMasterDataFilesException} if it could not be found.
	 *
	 * @param masterDataFilesSid the primary key of the master data files
	 * @return the master data files
	 * @throws NoSuchMasterDataFilesException if a master data files with the primary key could not be found
	 */
	@Override
	public MasterDataFiles findByPrimaryKey(int masterDataFilesSid)
		throws NoSuchMasterDataFilesException {
		return findByPrimaryKey((Serializable)masterDataFilesSid);
	}

	/**
	 * Returns the master data files with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the master data files
	 * @return the master data files, or <code>null</code> if a master data files with the primary key could not be found
	 */
	@Override
	public MasterDataFiles fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(MasterDataFilesModelImpl.ENTITY_CACHE_ENABLED,
				MasterDataFilesImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		MasterDataFiles masterDataFiles = (MasterDataFiles)serializable;

		if (masterDataFiles == null) {
			Session session = null;

			try {
				session = openSession();

				masterDataFiles = (MasterDataFiles)session.get(MasterDataFilesImpl.class,
						primaryKey);

				if (masterDataFiles != null) {
					cacheResult(masterDataFiles);
				}
				else {
					entityCache.putResult(MasterDataFilesModelImpl.ENTITY_CACHE_ENABLED,
						MasterDataFilesImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(MasterDataFilesModelImpl.ENTITY_CACHE_ENABLED,
					MasterDataFilesImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return masterDataFiles;
	}

	/**
	 * Returns the master data files with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param masterDataFilesSid the primary key of the master data files
	 * @return the master data files, or <code>null</code> if a master data files with the primary key could not be found
	 */
	@Override
	public MasterDataFiles fetchByPrimaryKey(int masterDataFilesSid) {
		return fetchByPrimaryKey((Serializable)masterDataFilesSid);
	}

	@Override
	public Map<Serializable, MasterDataFiles> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, MasterDataFiles> map = new HashMap<Serializable, MasterDataFiles>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			MasterDataFiles masterDataFiles = fetchByPrimaryKey(primaryKey);

			if (masterDataFiles != null) {
				map.put(primaryKey, masterDataFiles);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(MasterDataFilesModelImpl.ENTITY_CACHE_ENABLED,
					MasterDataFilesImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (MasterDataFiles)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_MASTERDATAFILES_WHERE_PKS_IN);

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

			for (MasterDataFiles masterDataFiles : (List<MasterDataFiles>)q.list()) {
				map.put(masterDataFiles.getPrimaryKeyObj(), masterDataFiles);

				cacheResult(masterDataFiles);

				uncachedPrimaryKeys.remove(masterDataFiles.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(MasterDataFilesModelImpl.ENTITY_CACHE_ENABLED,
					MasterDataFilesImpl.class, primaryKey, nullModel);
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
	 * Returns all the master data fileses.
	 *
	 * @return the master data fileses
	 */
	@Override
	public List<MasterDataFiles> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the master data fileses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MasterDataFilesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of master data fileses
	 * @param end the upper bound of the range of master data fileses (not inclusive)
	 * @return the range of master data fileses
	 */
	@Override
	public List<MasterDataFiles> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the master data fileses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MasterDataFilesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of master data fileses
	 * @param end the upper bound of the range of master data fileses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of master data fileses
	 */
	@Override
	public List<MasterDataFiles> findAll(int start, int end,
		OrderByComparator<MasterDataFiles> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the master data fileses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MasterDataFilesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of master data fileses
	 * @param end the upper bound of the range of master data fileses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of master data fileses
	 */
	@Override
	public List<MasterDataFiles> findAll(int start, int end,
		OrderByComparator<MasterDataFiles> orderByComparator,
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

		List<MasterDataFiles> list = null;

		if (retrieveFromCache) {
			list = (List<MasterDataFiles>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_MASTERDATAFILES);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_MASTERDATAFILES;

				if (pagination) {
					sql = sql.concat(MasterDataFilesModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<MasterDataFiles>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<MasterDataFiles>)QueryUtil.list(q,
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
	 * Removes all the master data fileses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (MasterDataFiles masterDataFiles : findAll()) {
			remove(masterDataFiles);
		}
	}

	/**
	 * Returns the number of master data fileses.
	 *
	 * @return the number of master data fileses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_MASTERDATAFILES);

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
		return MasterDataFilesModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the master data files persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(MasterDataFilesImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_MASTERDATAFILES = "SELECT masterDataFiles FROM MasterDataFiles masterDataFiles";
	private static final String _SQL_SELECT_MASTERDATAFILES_WHERE_PKS_IN = "SELECT masterDataFiles FROM MasterDataFiles masterDataFiles WHERE MASTER_DATA_FILES_SID IN (";
	private static final String _SQL_COUNT_MASTERDATAFILES = "SELECT COUNT(masterDataFiles) FROM MasterDataFiles masterDataFiles";
	private static final String _ORDER_BY_ENTITY_ALIAS = "masterDataFiles.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No MasterDataFiles exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(MasterDataFilesPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"masterTableSid", "masterDataFilesSid", "masterTableName",
				"filePath", "createdDate", "createdBy"
			});
}