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

import com.stpl.app.exception.NoSuchFileManagementException;
import com.stpl.app.model.FileManagement;
import com.stpl.app.model.impl.FileManagementImpl;
import com.stpl.app.model.impl.FileManagementModelImpl;
import com.stpl.app.service.persistence.FileManagementPersistence;

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
 * The persistence implementation for the file management service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see FileManagementPersistence
 * @see com.stpl.app.service.persistence.FileManagementUtil
 * @generated
 */
@ProviderType
public class FileManagementPersistenceImpl extends BasePersistenceImpl<FileManagement>
	implements FileManagementPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link FileManagementUtil} to access the file management persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = FileManagementImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(FileManagementModelImpl.ENTITY_CACHE_ENABLED,
			FileManagementModelImpl.FINDER_CACHE_ENABLED,
			FileManagementImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(FileManagementModelImpl.ENTITY_CACHE_ENABLED,
			FileManagementModelImpl.FINDER_CACHE_ENABLED,
			FileManagementImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(FileManagementModelImpl.ENTITY_CACHE_ENABLED,
			FileManagementModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public FileManagementPersistenceImpl() {
		setModelClass(FileManagement.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("country", "COUNTRY");
			dbColumnNames.put("fromPeriod", "FROM_PERIOD");
			dbColumnNames.put("versionNo", "VERSION_NO");
			dbColumnNames.put("forecastSource", "FORECAST_SOURCE");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("version", "VERSION");
			dbColumnNames.put("fileSource", "FILE_SOURCE");
			dbColumnNames.put("toPeriod", "TO_PERIOD");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("fileManagementSid", "FILE_MANAGEMENT_SID");
			dbColumnNames.put("forecastName", "FORECAST_NAME");
			dbColumnNames.put("fileType", "FILE_TYPE");
			dbColumnNames.put("businessUnit", "BUSINESS_UNIT");
			dbColumnNames.put("company", "COMPANY");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the file management in the entity cache if it is enabled.
	 *
	 * @param fileManagement the file management
	 */
	@Override
	public void cacheResult(FileManagement fileManagement) {
		entityCache.putResult(FileManagementModelImpl.ENTITY_CACHE_ENABLED,
			FileManagementImpl.class, fileManagement.getPrimaryKey(),
			fileManagement);

		fileManagement.resetOriginalValues();
	}

	/**
	 * Caches the file managements in the entity cache if it is enabled.
	 *
	 * @param fileManagements the file managements
	 */
	@Override
	public void cacheResult(List<FileManagement> fileManagements) {
		for (FileManagement fileManagement : fileManagements) {
			if (entityCache.getResult(
						FileManagementModelImpl.ENTITY_CACHE_ENABLED,
						FileManagementImpl.class, fileManagement.getPrimaryKey()) == null) {
				cacheResult(fileManagement);
			}
			else {
				fileManagement.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all file managements.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(FileManagementImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the file management.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(FileManagement fileManagement) {
		entityCache.removeResult(FileManagementModelImpl.ENTITY_CACHE_ENABLED,
			FileManagementImpl.class, fileManagement.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<FileManagement> fileManagements) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (FileManagement fileManagement : fileManagements) {
			entityCache.removeResult(FileManagementModelImpl.ENTITY_CACHE_ENABLED,
				FileManagementImpl.class, fileManagement.getPrimaryKey());
		}
	}

	/**
	 * Creates a new file management with the primary key. Does not add the file management to the database.
	 *
	 * @param fileManagementSid the primary key for the new file management
	 * @return the new file management
	 */
	@Override
	public FileManagement create(int fileManagementSid) {
		FileManagement fileManagement = new FileManagementImpl();

		fileManagement.setNew(true);
		fileManagement.setPrimaryKey(fileManagementSid);

		return fileManagement;
	}

	/**
	 * Removes the file management with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param fileManagementSid the primary key of the file management
	 * @return the file management that was removed
	 * @throws NoSuchFileManagementException if a file management with the primary key could not be found
	 */
	@Override
	public FileManagement remove(int fileManagementSid)
		throws NoSuchFileManagementException {
		return remove((Serializable)fileManagementSid);
	}

	/**
	 * Removes the file management with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the file management
	 * @return the file management that was removed
	 * @throws NoSuchFileManagementException if a file management with the primary key could not be found
	 */
	@Override
	public FileManagement remove(Serializable primaryKey)
		throws NoSuchFileManagementException {
		Session session = null;

		try {
			session = openSession();

			FileManagement fileManagement = (FileManagement)session.get(FileManagementImpl.class,
					primaryKey);

			if (fileManagement == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchFileManagementException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(fileManagement);
		}
		catch (NoSuchFileManagementException nsee) {
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
	protected FileManagement removeImpl(FileManagement fileManagement) {
		fileManagement = toUnwrappedModel(fileManagement);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(fileManagement)) {
				fileManagement = (FileManagement)session.get(FileManagementImpl.class,
						fileManagement.getPrimaryKeyObj());
			}

			if (fileManagement != null) {
				session.delete(fileManagement);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (fileManagement != null) {
			clearCache(fileManagement);
		}

		return fileManagement;
	}

	@Override
	public FileManagement updateImpl(FileManagement fileManagement) {
		fileManagement = toUnwrappedModel(fileManagement);

		boolean isNew = fileManagement.isNew();

		Session session = null;

		try {
			session = openSession();

			if (fileManagement.isNew()) {
				session.save(fileManagement);

				fileManagement.setNew(false);
			}
			else {
				fileManagement = (FileManagement)session.merge(fileManagement);
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

		entityCache.putResult(FileManagementModelImpl.ENTITY_CACHE_ENABLED,
			FileManagementImpl.class, fileManagement.getPrimaryKey(),
			fileManagement, false);

		fileManagement.resetOriginalValues();

		return fileManagement;
	}

	protected FileManagement toUnwrappedModel(FileManagement fileManagement) {
		if (fileManagement instanceof FileManagementImpl) {
			return fileManagement;
		}

		FileManagementImpl fileManagementImpl = new FileManagementImpl();

		fileManagementImpl.setNew(fileManagement.isNew());
		fileManagementImpl.setPrimaryKey(fileManagement.getPrimaryKey());

		fileManagementImpl.setCountry(fileManagement.getCountry());
		fileManagementImpl.setFromPeriod(fileManagement.getFromPeriod());
		fileManagementImpl.setVersionNo(fileManagement.getVersionNo());
		fileManagementImpl.setForecastSource(fileManagement.getForecastSource());
		fileManagementImpl.setModifiedDate(fileManagement.getModifiedDate());
		fileManagementImpl.setCreatedBy(fileManagement.getCreatedBy());
		fileManagementImpl.setCreatedDate(fileManagement.getCreatedDate());
		fileManagementImpl.setVersion(fileManagement.getVersion());
		fileManagementImpl.setFileSource(fileManagement.getFileSource());
		fileManagementImpl.setToPeriod(fileManagement.getToPeriod());
		fileManagementImpl.setModifiedBy(fileManagement.getModifiedBy());
		fileManagementImpl.setFileManagementSid(fileManagement.getFileManagementSid());
		fileManagementImpl.setForecastName(fileManagement.getForecastName());
		fileManagementImpl.setFileType(fileManagement.getFileType());
		fileManagementImpl.setBusinessUnit(fileManagement.getBusinessUnit());
		fileManagementImpl.setCompany(fileManagement.getCompany());

		return fileManagementImpl;
	}

	/**
	 * Returns the file management with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the file management
	 * @return the file management
	 * @throws NoSuchFileManagementException if a file management with the primary key could not be found
	 */
	@Override
	public FileManagement findByPrimaryKey(Serializable primaryKey)
		throws NoSuchFileManagementException {
		FileManagement fileManagement = fetchByPrimaryKey(primaryKey);

		if (fileManagement == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchFileManagementException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return fileManagement;
	}

	/**
	 * Returns the file management with the primary key or throws a {@link NoSuchFileManagementException} if it could not be found.
	 *
	 * @param fileManagementSid the primary key of the file management
	 * @return the file management
	 * @throws NoSuchFileManagementException if a file management with the primary key could not be found
	 */
	@Override
	public FileManagement findByPrimaryKey(int fileManagementSid)
		throws NoSuchFileManagementException {
		return findByPrimaryKey((Serializable)fileManagementSid);
	}

	/**
	 * Returns the file management with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the file management
	 * @return the file management, or <code>null</code> if a file management with the primary key could not be found
	 */
	@Override
	public FileManagement fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(FileManagementModelImpl.ENTITY_CACHE_ENABLED,
				FileManagementImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		FileManagement fileManagement = (FileManagement)serializable;

		if (fileManagement == null) {
			Session session = null;

			try {
				session = openSession();

				fileManagement = (FileManagement)session.get(FileManagementImpl.class,
						primaryKey);

				if (fileManagement != null) {
					cacheResult(fileManagement);
				}
				else {
					entityCache.putResult(FileManagementModelImpl.ENTITY_CACHE_ENABLED,
						FileManagementImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(FileManagementModelImpl.ENTITY_CACHE_ENABLED,
					FileManagementImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return fileManagement;
	}

	/**
	 * Returns the file management with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param fileManagementSid the primary key of the file management
	 * @return the file management, or <code>null</code> if a file management with the primary key could not be found
	 */
	@Override
	public FileManagement fetchByPrimaryKey(int fileManagementSid) {
		return fetchByPrimaryKey((Serializable)fileManagementSid);
	}

	@Override
	public Map<Serializable, FileManagement> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, FileManagement> map = new HashMap<Serializable, FileManagement>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			FileManagement fileManagement = fetchByPrimaryKey(primaryKey);

			if (fileManagement != null) {
				map.put(primaryKey, fileManagement);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(FileManagementModelImpl.ENTITY_CACHE_ENABLED,
					FileManagementImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (FileManagement)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_FILEMANAGEMENT_WHERE_PKS_IN);

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

			for (FileManagement fileManagement : (List<FileManagement>)q.list()) {
				map.put(fileManagement.getPrimaryKeyObj(), fileManagement);

				cacheResult(fileManagement);

				uncachedPrimaryKeys.remove(fileManagement.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(FileManagementModelImpl.ENTITY_CACHE_ENABLED,
					FileManagementImpl.class, primaryKey, nullModel);
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
	 * Returns all the file managements.
	 *
	 * @return the file managements
	 */
	@Override
	public List<FileManagement> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the file managements.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FileManagementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of file managements
	 * @param end the upper bound of the range of file managements (not inclusive)
	 * @return the range of file managements
	 */
	@Override
	public List<FileManagement> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the file managements.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FileManagementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of file managements
	 * @param end the upper bound of the range of file managements (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of file managements
	 */
	@Override
	public List<FileManagement> findAll(int start, int end,
		OrderByComparator<FileManagement> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the file managements.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FileManagementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of file managements
	 * @param end the upper bound of the range of file managements (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of file managements
	 */
	@Override
	public List<FileManagement> findAll(int start, int end,
		OrderByComparator<FileManagement> orderByComparator,
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

		List<FileManagement> list = null;

		if (retrieveFromCache) {
			list = (List<FileManagement>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_FILEMANAGEMENT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_FILEMANAGEMENT;

				if (pagination) {
					sql = sql.concat(FileManagementModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<FileManagement>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<FileManagement>)QueryUtil.list(q,
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
	 * Removes all the file managements from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (FileManagement fileManagement : findAll()) {
			remove(fileManagement);
		}
	}

	/**
	 * Returns the number of file managements.
	 *
	 * @return the number of file managements
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_FILEMANAGEMENT);

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
		return FileManagementModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the file management persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(FileManagementImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_FILEMANAGEMENT = "SELECT fileManagement FROM FileManagement fileManagement";
	private static final String _SQL_SELECT_FILEMANAGEMENT_WHERE_PKS_IN = "SELECT fileManagement FROM FileManagement fileManagement WHERE FILE_MANAGEMENT_SID IN (";
	private static final String _SQL_COUNT_FILEMANAGEMENT = "SELECT COUNT(fileManagement) FROM FileManagement fileManagement";
	private static final String _ORDER_BY_ENTITY_ALIAS = "fileManagement.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No FileManagement exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(FileManagementPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"country", "fromPeriod", "versionNo", "forecastSource",
				"modifiedDate", "createdBy", "createdDate", "version",
				"fileSource", "toPeriod", "modifiedBy", "fileManagementSid",
				"forecastName", "fileType", "businessUnit", "company"
			});
}