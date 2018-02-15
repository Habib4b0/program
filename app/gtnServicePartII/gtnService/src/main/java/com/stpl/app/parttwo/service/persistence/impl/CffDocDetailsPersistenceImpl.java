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

package com.stpl.app.parttwo.service.persistence.impl;

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

import com.stpl.app.parttwo.exception.NoSuchCffDocDetailsException;
import com.stpl.app.parttwo.model.CffDocDetails;
import com.stpl.app.parttwo.model.impl.CffDocDetailsImpl;
import com.stpl.app.parttwo.model.impl.CffDocDetailsModelImpl;
import com.stpl.app.parttwo.service.persistence.CffDocDetailsPersistence;

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
 * The persistence implementation for the cff doc details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CffDocDetailsPersistence
 * @see com.stpl.app.parttwo.service.persistence.CffDocDetailsUtil
 * @generated
 */
@ProviderType
public class CffDocDetailsPersistenceImpl extends BasePersistenceImpl<CffDocDetails>
	implements CffDocDetailsPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CffDocDetailsUtil} to access the cff doc details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CffDocDetailsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CffDocDetailsModelImpl.ENTITY_CACHE_ENABLED,
			CffDocDetailsModelImpl.FINDER_CACHE_ENABLED,
			CffDocDetailsImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CffDocDetailsModelImpl.ENTITY_CACHE_ENABLED,
			CffDocDetailsModelImpl.FINDER_CACHE_ENABLED,
			CffDocDetailsImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CffDocDetailsModelImpl.ENTITY_CACHE_ENABLED,
			CffDocDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public CffDocDetailsPersistenceImpl() {
		setModelClass(CffDocDetails.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("fileName", "FILE_NAME");
			dbColumnNames.put("uploadDate", "UPLOAD_DATE");
			dbColumnNames.put("fileType", "FILE_TYPE");
			dbColumnNames.put("uploadBy", "UPLOAD_BY");
			dbColumnNames.put("cffMasterSid", "CFF_MASTER_SID");
			dbColumnNames.put("cffDocDetailsSid", "CFF_DOC_DETAILS_SID");
			dbColumnNames.put("fileSize", "FILE_SIZE");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the cff doc details in the entity cache if it is enabled.
	 *
	 * @param cffDocDetails the cff doc details
	 */
	@Override
	public void cacheResult(CffDocDetails cffDocDetails) {
		entityCache.putResult(CffDocDetailsModelImpl.ENTITY_CACHE_ENABLED,
			CffDocDetailsImpl.class, cffDocDetails.getPrimaryKey(),
			cffDocDetails);

		cffDocDetails.resetOriginalValues();
	}

	/**
	 * Caches the cff doc detailses in the entity cache if it is enabled.
	 *
	 * @param cffDocDetailses the cff doc detailses
	 */
	@Override
	public void cacheResult(List<CffDocDetails> cffDocDetailses) {
		for (CffDocDetails cffDocDetails : cffDocDetailses) {
			if (entityCache.getResult(
						CffDocDetailsModelImpl.ENTITY_CACHE_ENABLED,
						CffDocDetailsImpl.class, cffDocDetails.getPrimaryKey()) == null) {
				cacheResult(cffDocDetails);
			}
			else {
				cffDocDetails.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all cff doc detailses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CffDocDetailsImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the cff doc details.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CffDocDetails cffDocDetails) {
		entityCache.removeResult(CffDocDetailsModelImpl.ENTITY_CACHE_ENABLED,
			CffDocDetailsImpl.class, cffDocDetails.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<CffDocDetails> cffDocDetailses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CffDocDetails cffDocDetails : cffDocDetailses) {
			entityCache.removeResult(CffDocDetailsModelImpl.ENTITY_CACHE_ENABLED,
				CffDocDetailsImpl.class, cffDocDetails.getPrimaryKey());
		}
	}

	/**
	 * Creates a new cff doc details with the primary key. Does not add the cff doc details to the database.
	 *
	 * @param cffDocDetailsSid the primary key for the new cff doc details
	 * @return the new cff doc details
	 */
	@Override
	public CffDocDetails create(int cffDocDetailsSid) {
		CffDocDetails cffDocDetails = new CffDocDetailsImpl();

		cffDocDetails.setNew(true);
		cffDocDetails.setPrimaryKey(cffDocDetailsSid);

		return cffDocDetails;
	}

	/**
	 * Removes the cff doc details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param cffDocDetailsSid the primary key of the cff doc details
	 * @return the cff doc details that was removed
	 * @throws NoSuchCffDocDetailsException if a cff doc details with the primary key could not be found
	 */
	@Override
	public CffDocDetails remove(int cffDocDetailsSid)
		throws NoSuchCffDocDetailsException {
		return remove((Serializable)cffDocDetailsSid);
	}

	/**
	 * Removes the cff doc details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the cff doc details
	 * @return the cff doc details that was removed
	 * @throws NoSuchCffDocDetailsException if a cff doc details with the primary key could not be found
	 */
	@Override
	public CffDocDetails remove(Serializable primaryKey)
		throws NoSuchCffDocDetailsException {
		Session session = null;

		try {
			session = openSession();

			CffDocDetails cffDocDetails = (CffDocDetails)session.get(CffDocDetailsImpl.class,
					primaryKey);

			if (cffDocDetails == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCffDocDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(cffDocDetails);
		}
		catch (NoSuchCffDocDetailsException nsee) {
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
	protected CffDocDetails removeImpl(CffDocDetails cffDocDetails) {
		cffDocDetails = toUnwrappedModel(cffDocDetails);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(cffDocDetails)) {
				cffDocDetails = (CffDocDetails)session.get(CffDocDetailsImpl.class,
						cffDocDetails.getPrimaryKeyObj());
			}

			if (cffDocDetails != null) {
				session.delete(cffDocDetails);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (cffDocDetails != null) {
			clearCache(cffDocDetails);
		}

		return cffDocDetails;
	}

	@Override
	public CffDocDetails updateImpl(CffDocDetails cffDocDetails) {
		cffDocDetails = toUnwrappedModel(cffDocDetails);

		boolean isNew = cffDocDetails.isNew();

		Session session = null;

		try {
			session = openSession();

			if (cffDocDetails.isNew()) {
				session.save(cffDocDetails);

				cffDocDetails.setNew(false);
			}
			else {
				cffDocDetails = (CffDocDetails)session.merge(cffDocDetails);
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

		entityCache.putResult(CffDocDetailsModelImpl.ENTITY_CACHE_ENABLED,
			CffDocDetailsImpl.class, cffDocDetails.getPrimaryKey(),
			cffDocDetails, false);

		cffDocDetails.resetOriginalValues();

		return cffDocDetails;
	}

	protected CffDocDetails toUnwrappedModel(CffDocDetails cffDocDetails) {
		if (cffDocDetails instanceof CffDocDetailsImpl) {
			return cffDocDetails;
		}

		CffDocDetailsImpl cffDocDetailsImpl = new CffDocDetailsImpl();

		cffDocDetailsImpl.setNew(cffDocDetails.isNew());
		cffDocDetailsImpl.setPrimaryKey(cffDocDetails.getPrimaryKey());

		cffDocDetailsImpl.setFileName(cffDocDetails.getFileName());
		cffDocDetailsImpl.setUploadDate(cffDocDetails.getUploadDate());
		cffDocDetailsImpl.setFileType(cffDocDetails.getFileType());
		cffDocDetailsImpl.setUploadBy(cffDocDetails.getUploadBy());
		cffDocDetailsImpl.setCffMasterSid(cffDocDetails.getCffMasterSid());
		cffDocDetailsImpl.setCffDocDetailsSid(cffDocDetails.getCffDocDetailsSid());
		cffDocDetailsImpl.setFileSize(cffDocDetails.getFileSize());

		return cffDocDetailsImpl;
	}

	/**
	 * Returns the cff doc details with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the cff doc details
	 * @return the cff doc details
	 * @throws NoSuchCffDocDetailsException if a cff doc details with the primary key could not be found
	 */
	@Override
	public CffDocDetails findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCffDocDetailsException {
		CffDocDetails cffDocDetails = fetchByPrimaryKey(primaryKey);

		if (cffDocDetails == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCffDocDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return cffDocDetails;
	}

	/**
	 * Returns the cff doc details with the primary key or throws a {@link NoSuchCffDocDetailsException} if it could not be found.
	 *
	 * @param cffDocDetailsSid the primary key of the cff doc details
	 * @return the cff doc details
	 * @throws NoSuchCffDocDetailsException if a cff doc details with the primary key could not be found
	 */
	@Override
	public CffDocDetails findByPrimaryKey(int cffDocDetailsSid)
		throws NoSuchCffDocDetailsException {
		return findByPrimaryKey((Serializable)cffDocDetailsSid);
	}

	/**
	 * Returns the cff doc details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the cff doc details
	 * @return the cff doc details, or <code>null</code> if a cff doc details with the primary key could not be found
	 */
	@Override
	public CffDocDetails fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(CffDocDetailsModelImpl.ENTITY_CACHE_ENABLED,
				CffDocDetailsImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CffDocDetails cffDocDetails = (CffDocDetails)serializable;

		if (cffDocDetails == null) {
			Session session = null;

			try {
				session = openSession();

				cffDocDetails = (CffDocDetails)session.get(CffDocDetailsImpl.class,
						primaryKey);

				if (cffDocDetails != null) {
					cacheResult(cffDocDetails);
				}
				else {
					entityCache.putResult(CffDocDetailsModelImpl.ENTITY_CACHE_ENABLED,
						CffDocDetailsImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(CffDocDetailsModelImpl.ENTITY_CACHE_ENABLED,
					CffDocDetailsImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return cffDocDetails;
	}

	/**
	 * Returns the cff doc details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param cffDocDetailsSid the primary key of the cff doc details
	 * @return the cff doc details, or <code>null</code> if a cff doc details with the primary key could not be found
	 */
	@Override
	public CffDocDetails fetchByPrimaryKey(int cffDocDetailsSid) {
		return fetchByPrimaryKey((Serializable)cffDocDetailsSid);
	}

	@Override
	public Map<Serializable, CffDocDetails> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CffDocDetails> map = new HashMap<Serializable, CffDocDetails>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CffDocDetails cffDocDetails = fetchByPrimaryKey(primaryKey);

			if (cffDocDetails != null) {
				map.put(primaryKey, cffDocDetails);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(CffDocDetailsModelImpl.ENTITY_CACHE_ENABLED,
					CffDocDetailsImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CffDocDetails)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_CFFDOCDETAILS_WHERE_PKS_IN);

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

			for (CffDocDetails cffDocDetails : (List<CffDocDetails>)q.list()) {
				map.put(cffDocDetails.getPrimaryKeyObj(), cffDocDetails);

				cacheResult(cffDocDetails);

				uncachedPrimaryKeys.remove(cffDocDetails.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(CffDocDetailsModelImpl.ENTITY_CACHE_ENABLED,
					CffDocDetailsImpl.class, primaryKey, nullModel);
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
	 * Returns all the cff doc detailses.
	 *
	 * @return the cff doc detailses
	 */
	@Override
	public List<CffDocDetails> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cff doc detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffDocDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cff doc detailses
	 * @param end the upper bound of the range of cff doc detailses (not inclusive)
	 * @return the range of cff doc detailses
	 */
	@Override
	public List<CffDocDetails> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the cff doc detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffDocDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cff doc detailses
	 * @param end the upper bound of the range of cff doc detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of cff doc detailses
	 */
	@Override
	public List<CffDocDetails> findAll(int start, int end,
		OrderByComparator<CffDocDetails> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cff doc detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffDocDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cff doc detailses
	 * @param end the upper bound of the range of cff doc detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of cff doc detailses
	 */
	@Override
	public List<CffDocDetails> findAll(int start, int end,
		OrderByComparator<CffDocDetails> orderByComparator,
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

		List<CffDocDetails> list = null;

		if (retrieveFromCache) {
			list = (List<CffDocDetails>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CFFDOCDETAILS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CFFDOCDETAILS;

				if (pagination) {
					sql = sql.concat(CffDocDetailsModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CffDocDetails>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CffDocDetails>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the cff doc detailses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CffDocDetails cffDocDetails : findAll()) {
			remove(cffDocDetails);
		}
	}

	/**
	 * Returns the number of cff doc detailses.
	 *
	 * @return the number of cff doc detailses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CFFDOCDETAILS);

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
		return CffDocDetailsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the cff doc details persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(CffDocDetailsImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_CFFDOCDETAILS = "SELECT cffDocDetails FROM CffDocDetails cffDocDetails";
	private static final String _SQL_SELECT_CFFDOCDETAILS_WHERE_PKS_IN = "SELECT cffDocDetails FROM CffDocDetails cffDocDetails WHERE CFF_DOC_DETAILS_SID IN (";
	private static final String _SQL_COUNT_CFFDOCDETAILS = "SELECT COUNT(cffDocDetails) FROM CffDocDetails cffDocDetails";
	private static final String _ORDER_BY_ENTITY_ALIAS = "cffDocDetails.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CffDocDetails exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(CffDocDetailsPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"fileName", "uploadDate", "fileType", "uploadBy", "cffMasterSid",
				"cffDocDetailsSid", "fileSize"
			});
}