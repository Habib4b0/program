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

import com.stpl.app.exception.NoSuchDocDetailsException;
import com.stpl.app.model.DocDetails;
import com.stpl.app.model.impl.DocDetailsImpl;
import com.stpl.app.model.impl.DocDetailsModelImpl;
import com.stpl.app.service.persistence.DocDetailsPersistence;

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
 * The persistence implementation for the doc details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see DocDetailsPersistence
 * @see com.stpl.app.service.persistence.DocDetailsUtil
 * @generated
 */
@ProviderType
public class DocDetailsPersistenceImpl extends BasePersistenceImpl<DocDetails>
	implements DocDetailsPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DocDetailsUtil} to access the doc details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = DocDetailsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(DocDetailsModelImpl.ENTITY_CACHE_ENABLED,
			DocDetailsModelImpl.FINDER_CACHE_ENABLED, DocDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(DocDetailsModelImpl.ENTITY_CACHE_ENABLED,
			DocDetailsModelImpl.FINDER_CACHE_ENABLED, DocDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DocDetailsModelImpl.ENTITY_CACHE_ENABLED,
			DocDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public DocDetailsPersistenceImpl() {
		setModelClass(DocDetails.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("fileName", "FILE_NAME");
			dbColumnNames.put("fileType", "FILE_TYPE");
			dbColumnNames.put("uploadedBy", "UPLOADED_BY");
			dbColumnNames.put("forecastType", "FORECAST_TYPE");
			dbColumnNames.put("projectionId", "PROJECTION_ID");
			dbColumnNames.put("docDetailsId", "DOC_DETAILS_ID");
			dbColumnNames.put("uploadedDate", "UPLOADED_DATE");
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
	 * Caches the doc details in the entity cache if it is enabled.
	 *
	 * @param docDetails the doc details
	 */
	@Override
	public void cacheResult(DocDetails docDetails) {
		entityCache.putResult(DocDetailsModelImpl.ENTITY_CACHE_ENABLED,
			DocDetailsImpl.class, docDetails.getPrimaryKey(), docDetails);

		docDetails.resetOriginalValues();
	}

	/**
	 * Caches the doc detailses in the entity cache if it is enabled.
	 *
	 * @param docDetailses the doc detailses
	 */
	@Override
	public void cacheResult(List<DocDetails> docDetailses) {
		for (DocDetails docDetails : docDetailses) {
			if (entityCache.getResult(
						DocDetailsModelImpl.ENTITY_CACHE_ENABLED,
						DocDetailsImpl.class, docDetails.getPrimaryKey()) == null) {
				cacheResult(docDetails);
			}
			else {
				docDetails.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all doc detailses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(DocDetailsImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the doc details.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(DocDetails docDetails) {
		entityCache.removeResult(DocDetailsModelImpl.ENTITY_CACHE_ENABLED,
			DocDetailsImpl.class, docDetails.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<DocDetails> docDetailses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (DocDetails docDetails : docDetailses) {
			entityCache.removeResult(DocDetailsModelImpl.ENTITY_CACHE_ENABLED,
				DocDetailsImpl.class, docDetails.getPrimaryKey());
		}
	}

	/**
	 * Creates a new doc details with the primary key. Does not add the doc details to the database.
	 *
	 * @param docDetailsId the primary key for the new doc details
	 * @return the new doc details
	 */
	@Override
	public DocDetails create(int docDetailsId) {
		DocDetails docDetails = new DocDetailsImpl();

		docDetails.setNew(true);
		docDetails.setPrimaryKey(docDetailsId);

		return docDetails;
	}

	/**
	 * Removes the doc details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param docDetailsId the primary key of the doc details
	 * @return the doc details that was removed
	 * @throws NoSuchDocDetailsException if a doc details with the primary key could not be found
	 */
	@Override
	public DocDetails remove(int docDetailsId) throws NoSuchDocDetailsException {
		return remove((Serializable)docDetailsId);
	}

	/**
	 * Removes the doc details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the doc details
	 * @return the doc details that was removed
	 * @throws NoSuchDocDetailsException if a doc details with the primary key could not be found
	 */
	@Override
	public DocDetails remove(Serializable primaryKey)
		throws NoSuchDocDetailsException {
		Session session = null;

		try {
			session = openSession();

			DocDetails docDetails = (DocDetails)session.get(DocDetailsImpl.class,
					primaryKey);

			if (docDetails == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDocDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(docDetails);
		}
		catch (NoSuchDocDetailsException nsee) {
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
	protected DocDetails removeImpl(DocDetails docDetails) {
		docDetails = toUnwrappedModel(docDetails);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(docDetails)) {
				docDetails = (DocDetails)session.get(DocDetailsImpl.class,
						docDetails.getPrimaryKeyObj());
			}

			if (docDetails != null) {
				session.delete(docDetails);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (docDetails != null) {
			clearCache(docDetails);
		}

		return docDetails;
	}

	@Override
	public DocDetails updateImpl(DocDetails docDetails) {
		docDetails = toUnwrappedModel(docDetails);

		boolean isNew = docDetails.isNew();

		Session session = null;

		try {
			session = openSession();

			if (docDetails.isNew()) {
				session.save(docDetails);

				docDetails.setNew(false);
			}
			else {
				docDetails = (DocDetails)session.merge(docDetails);
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

		entityCache.putResult(DocDetailsModelImpl.ENTITY_CACHE_ENABLED,
			DocDetailsImpl.class, docDetails.getPrimaryKey(), docDetails, false);

		docDetails.resetOriginalValues();

		return docDetails;
	}

	protected DocDetails toUnwrappedModel(DocDetails docDetails) {
		if (docDetails instanceof DocDetailsImpl) {
			return docDetails;
		}

		DocDetailsImpl docDetailsImpl = new DocDetailsImpl();

		docDetailsImpl.setNew(docDetails.isNew());
		docDetailsImpl.setPrimaryKey(docDetails.getPrimaryKey());

		docDetailsImpl.setFileName(docDetails.getFileName());
		docDetailsImpl.setFileType(docDetails.getFileType());
		docDetailsImpl.setUploadedBy(docDetails.getUploadedBy());
		docDetailsImpl.setForecastType(docDetails.getForecastType());
		docDetailsImpl.setProjectionId(docDetails.getProjectionId());
		docDetailsImpl.setDocDetailsId(docDetails.getDocDetailsId());
		docDetailsImpl.setUploadedDate(docDetails.getUploadedDate());
		docDetailsImpl.setFileSize(docDetails.getFileSize());

		return docDetailsImpl;
	}

	/**
	 * Returns the doc details with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the doc details
	 * @return the doc details
	 * @throws NoSuchDocDetailsException if a doc details with the primary key could not be found
	 */
	@Override
	public DocDetails findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDocDetailsException {
		DocDetails docDetails = fetchByPrimaryKey(primaryKey);

		if (docDetails == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDocDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return docDetails;
	}

	/**
	 * Returns the doc details with the primary key or throws a {@link NoSuchDocDetailsException} if it could not be found.
	 *
	 * @param docDetailsId the primary key of the doc details
	 * @return the doc details
	 * @throws NoSuchDocDetailsException if a doc details with the primary key could not be found
	 */
	@Override
	public DocDetails findByPrimaryKey(int docDetailsId)
		throws NoSuchDocDetailsException {
		return findByPrimaryKey((Serializable)docDetailsId);
	}

	/**
	 * Returns the doc details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the doc details
	 * @return the doc details, or <code>null</code> if a doc details with the primary key could not be found
	 */
	@Override
	public DocDetails fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(DocDetailsModelImpl.ENTITY_CACHE_ENABLED,
				DocDetailsImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		DocDetails docDetails = (DocDetails)serializable;

		if (docDetails == null) {
			Session session = null;

			try {
				session = openSession();

				docDetails = (DocDetails)session.get(DocDetailsImpl.class,
						primaryKey);

				if (docDetails != null) {
					cacheResult(docDetails);
				}
				else {
					entityCache.putResult(DocDetailsModelImpl.ENTITY_CACHE_ENABLED,
						DocDetailsImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(DocDetailsModelImpl.ENTITY_CACHE_ENABLED,
					DocDetailsImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return docDetails;
	}

	/**
	 * Returns the doc details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param docDetailsId the primary key of the doc details
	 * @return the doc details, or <code>null</code> if a doc details with the primary key could not be found
	 */
	@Override
	public DocDetails fetchByPrimaryKey(int docDetailsId) {
		return fetchByPrimaryKey((Serializable)docDetailsId);
	}

	@Override
	public Map<Serializable, DocDetails> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, DocDetails> map = new HashMap<Serializable, DocDetails>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			DocDetails docDetails = fetchByPrimaryKey(primaryKey);

			if (docDetails != null) {
				map.put(primaryKey, docDetails);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(DocDetailsModelImpl.ENTITY_CACHE_ENABLED,
					DocDetailsImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (DocDetails)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_DOCDETAILS_WHERE_PKS_IN);

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

			for (DocDetails docDetails : (List<DocDetails>)q.list()) {
				map.put(docDetails.getPrimaryKeyObj(), docDetails);

				cacheResult(docDetails);

				uncachedPrimaryKeys.remove(docDetails.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(DocDetailsModelImpl.ENTITY_CACHE_ENABLED,
					DocDetailsImpl.class, primaryKey, nullModel);
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
	 * Returns all the doc detailses.
	 *
	 * @return the doc detailses
	 */
	@Override
	public List<DocDetails> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the doc detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DocDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of doc detailses
	 * @param end the upper bound of the range of doc detailses (not inclusive)
	 * @return the range of doc detailses
	 */
	@Override
	public List<DocDetails> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the doc detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DocDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of doc detailses
	 * @param end the upper bound of the range of doc detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of doc detailses
	 */
	@Override
	public List<DocDetails> findAll(int start, int end,
		OrderByComparator<DocDetails> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the doc detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DocDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of doc detailses
	 * @param end the upper bound of the range of doc detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of doc detailses
	 */
	@Override
	public List<DocDetails> findAll(int start, int end,
		OrderByComparator<DocDetails> orderByComparator,
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

		List<DocDetails> list = null;

		if (retrieveFromCache) {
			list = (List<DocDetails>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_DOCDETAILS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DOCDETAILS;

				if (pagination) {
					sql = sql.concat(DocDetailsModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<DocDetails>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DocDetails>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the doc detailses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (DocDetails docDetails : findAll()) {
			remove(docDetails);
		}
	}

	/**
	 * Returns the number of doc detailses.
	 *
	 * @return the number of doc detailses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_DOCDETAILS);

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
		return DocDetailsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the doc details persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(DocDetailsImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_DOCDETAILS = "SELECT docDetails FROM DocDetails docDetails";
	private static final String _SQL_SELECT_DOCDETAILS_WHERE_PKS_IN = "SELECT docDetails FROM DocDetails docDetails WHERE DOC_DETAILS_ID IN (";
	private static final String _SQL_COUNT_DOCDETAILS = "SELECT COUNT(docDetails) FROM DocDetails docDetails";
	private static final String _ORDER_BY_ENTITY_ALIAS = "docDetails.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DocDetails exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(DocDetailsPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"fileName", "fileType", "uploadedBy", "forecastType",
				"projectionId", "docDetailsId", "uploadedDate", "fileSize"
			});
}