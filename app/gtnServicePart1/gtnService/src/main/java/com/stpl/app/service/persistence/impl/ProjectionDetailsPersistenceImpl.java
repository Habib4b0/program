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

import com.stpl.app.exception.NoSuchProjectionDetailsException;
import com.stpl.app.model.ProjectionDetails;
import com.stpl.app.model.impl.ProjectionDetailsImpl;
import com.stpl.app.model.impl.ProjectionDetailsModelImpl;
import com.stpl.app.service.persistence.ProjectionDetailsPersistence;

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
 * The persistence implementation for the projection details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ProjectionDetailsPersistence
 * @see com.stpl.app.service.persistence.ProjectionDetailsUtil
 * @generated
 */
@ProviderType
public class ProjectionDetailsPersistenceImpl extends BasePersistenceImpl<ProjectionDetails>
	implements ProjectionDetailsPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ProjectionDetailsUtil} to access the projection details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ProjectionDetailsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ProjectionDetailsModelImpl.ENTITY_CACHE_ENABLED,
			ProjectionDetailsModelImpl.FINDER_CACHE_ENABLED,
			ProjectionDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ProjectionDetailsModelImpl.ENTITY_CACHE_ENABLED,
			ProjectionDetailsModelImpl.FINDER_CACHE_ENABLED,
			ProjectionDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ProjectionDetailsModelImpl.ENTITY_CACHE_ENABLED,
			ProjectionDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public ProjectionDetailsPersistenceImpl() {
		setModelClass(ProjectionDetails.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("projectionDetailsSid", "PROJECTION_DETAILS_SID");
			dbColumnNames.put("ccpDetailsSid", "CCP_DETAILS_SID");
			dbColumnNames.put("projectionMasterSid", "PROJECTION_MASTER_SID");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the projection details in the entity cache if it is enabled.
	 *
	 * @param projectionDetails the projection details
	 */
	@Override
	public void cacheResult(ProjectionDetails projectionDetails) {
		entityCache.putResult(ProjectionDetailsModelImpl.ENTITY_CACHE_ENABLED,
			ProjectionDetailsImpl.class, projectionDetails.getPrimaryKey(),
			projectionDetails);

		projectionDetails.resetOriginalValues();
	}

	/**
	 * Caches the projection detailses in the entity cache if it is enabled.
	 *
	 * @param projectionDetailses the projection detailses
	 */
	@Override
	public void cacheResult(List<ProjectionDetails> projectionDetailses) {
		for (ProjectionDetails projectionDetails : projectionDetailses) {
			if (entityCache.getResult(
						ProjectionDetailsModelImpl.ENTITY_CACHE_ENABLED,
						ProjectionDetailsImpl.class,
						projectionDetails.getPrimaryKey()) == null) {
				cacheResult(projectionDetails);
			}
			else {
				projectionDetails.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all projection detailses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ProjectionDetailsImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the projection details.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ProjectionDetails projectionDetails) {
		entityCache.removeResult(ProjectionDetailsModelImpl.ENTITY_CACHE_ENABLED,
			ProjectionDetailsImpl.class, projectionDetails.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ProjectionDetails> projectionDetailses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ProjectionDetails projectionDetails : projectionDetailses) {
			entityCache.removeResult(ProjectionDetailsModelImpl.ENTITY_CACHE_ENABLED,
				ProjectionDetailsImpl.class, projectionDetails.getPrimaryKey());
		}
	}

	/**
	 * Creates a new projection details with the primary key. Does not add the projection details to the database.
	 *
	 * @param projectionDetailsSid the primary key for the new projection details
	 * @return the new projection details
	 */
	@Override
	public ProjectionDetails create(int projectionDetailsSid) {
		ProjectionDetails projectionDetails = new ProjectionDetailsImpl();

		projectionDetails.setNew(true);
		projectionDetails.setPrimaryKey(projectionDetailsSid);

		return projectionDetails;
	}

	/**
	 * Removes the projection details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param projectionDetailsSid the primary key of the projection details
	 * @return the projection details that was removed
	 * @throws NoSuchProjectionDetailsException if a projection details with the primary key could not be found
	 */
	@Override
	public ProjectionDetails remove(int projectionDetailsSid)
		throws NoSuchProjectionDetailsException {
		return remove((Serializable)projectionDetailsSid);
	}

	/**
	 * Removes the projection details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the projection details
	 * @return the projection details that was removed
	 * @throws NoSuchProjectionDetailsException if a projection details with the primary key could not be found
	 */
	@Override
	public ProjectionDetails remove(Serializable primaryKey)
		throws NoSuchProjectionDetailsException {
		Session session = null;

		try {
			session = openSession();

			ProjectionDetails projectionDetails = (ProjectionDetails)session.get(ProjectionDetailsImpl.class,
					primaryKey);

			if (projectionDetails == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchProjectionDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(projectionDetails);
		}
		catch (NoSuchProjectionDetailsException nsee) {
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
	protected ProjectionDetails removeImpl(ProjectionDetails projectionDetails) {
		projectionDetails = toUnwrappedModel(projectionDetails);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(projectionDetails)) {
				projectionDetails = (ProjectionDetails)session.get(ProjectionDetailsImpl.class,
						projectionDetails.getPrimaryKeyObj());
			}

			if (projectionDetails != null) {
				session.delete(projectionDetails);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (projectionDetails != null) {
			clearCache(projectionDetails);
		}

		return projectionDetails;
	}

	@Override
	public ProjectionDetails updateImpl(ProjectionDetails projectionDetails) {
		projectionDetails = toUnwrappedModel(projectionDetails);

		boolean isNew = projectionDetails.isNew();

		Session session = null;

		try {
			session = openSession();

			if (projectionDetails.isNew()) {
				session.save(projectionDetails);

				projectionDetails.setNew(false);
			}
			else {
				projectionDetails = (ProjectionDetails)session.merge(projectionDetails);
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

		entityCache.putResult(ProjectionDetailsModelImpl.ENTITY_CACHE_ENABLED,
			ProjectionDetailsImpl.class, projectionDetails.getPrimaryKey(),
			projectionDetails, false);

		projectionDetails.resetOriginalValues();

		return projectionDetails;
	}

	protected ProjectionDetails toUnwrappedModel(
		ProjectionDetails projectionDetails) {
		if (projectionDetails instanceof ProjectionDetailsImpl) {
			return projectionDetails;
		}

		ProjectionDetailsImpl projectionDetailsImpl = new ProjectionDetailsImpl();

		projectionDetailsImpl.setNew(projectionDetails.isNew());
		projectionDetailsImpl.setPrimaryKey(projectionDetails.getPrimaryKey());

		projectionDetailsImpl.setProjectionDetailsSid(projectionDetails.getProjectionDetailsSid());
		projectionDetailsImpl.setCcpDetailsSid(projectionDetails.getCcpDetailsSid());
		projectionDetailsImpl.setProjectionMasterSid(projectionDetails.getProjectionMasterSid());

		return projectionDetailsImpl;
	}

	/**
	 * Returns the projection details with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the projection details
	 * @return the projection details
	 * @throws NoSuchProjectionDetailsException if a projection details with the primary key could not be found
	 */
	@Override
	public ProjectionDetails findByPrimaryKey(Serializable primaryKey)
		throws NoSuchProjectionDetailsException {
		ProjectionDetails projectionDetails = fetchByPrimaryKey(primaryKey);

		if (projectionDetails == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchProjectionDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return projectionDetails;
	}

	/**
	 * Returns the projection details with the primary key or throws a {@link NoSuchProjectionDetailsException} if it could not be found.
	 *
	 * @param projectionDetailsSid the primary key of the projection details
	 * @return the projection details
	 * @throws NoSuchProjectionDetailsException if a projection details with the primary key could not be found
	 */
	@Override
	public ProjectionDetails findByPrimaryKey(int projectionDetailsSid)
		throws NoSuchProjectionDetailsException {
		return findByPrimaryKey((Serializable)projectionDetailsSid);
	}

	/**
	 * Returns the projection details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the projection details
	 * @return the projection details, or <code>null</code> if a projection details with the primary key could not be found
	 */
	@Override
	public ProjectionDetails fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ProjectionDetailsModelImpl.ENTITY_CACHE_ENABLED,
				ProjectionDetailsImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ProjectionDetails projectionDetails = (ProjectionDetails)serializable;

		if (projectionDetails == null) {
			Session session = null;

			try {
				session = openSession();

				projectionDetails = (ProjectionDetails)session.get(ProjectionDetailsImpl.class,
						primaryKey);

				if (projectionDetails != null) {
					cacheResult(projectionDetails);
				}
				else {
					entityCache.putResult(ProjectionDetailsModelImpl.ENTITY_CACHE_ENABLED,
						ProjectionDetailsImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ProjectionDetailsModelImpl.ENTITY_CACHE_ENABLED,
					ProjectionDetailsImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return projectionDetails;
	}

	/**
	 * Returns the projection details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param projectionDetailsSid the primary key of the projection details
	 * @return the projection details, or <code>null</code> if a projection details with the primary key could not be found
	 */
	@Override
	public ProjectionDetails fetchByPrimaryKey(int projectionDetailsSid) {
		return fetchByPrimaryKey((Serializable)projectionDetailsSid);
	}

	@Override
	public Map<Serializable, ProjectionDetails> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ProjectionDetails> map = new HashMap<Serializable, ProjectionDetails>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ProjectionDetails projectionDetails = fetchByPrimaryKey(primaryKey);

			if (projectionDetails != null) {
				map.put(primaryKey, projectionDetails);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ProjectionDetailsModelImpl.ENTITY_CACHE_ENABLED,
					ProjectionDetailsImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ProjectionDetails)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_PROJECTIONDETAILS_WHERE_PKS_IN);

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

			for (ProjectionDetails projectionDetails : (List<ProjectionDetails>)q.list()) {
				map.put(projectionDetails.getPrimaryKeyObj(), projectionDetails);

				cacheResult(projectionDetails);

				uncachedPrimaryKeys.remove(projectionDetails.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ProjectionDetailsModelImpl.ENTITY_CACHE_ENABLED,
					ProjectionDetailsImpl.class, primaryKey, nullModel);
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
	 * Returns all the projection detailses.
	 *
	 * @return the projection detailses
	 */
	@Override
	public List<ProjectionDetails> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the projection detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectionDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of projection detailses
	 * @param end the upper bound of the range of projection detailses (not inclusive)
	 * @return the range of projection detailses
	 */
	@Override
	public List<ProjectionDetails> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the projection detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectionDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of projection detailses
	 * @param end the upper bound of the range of projection detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of projection detailses
	 */
	@Override
	public List<ProjectionDetails> findAll(int start, int end,
		OrderByComparator<ProjectionDetails> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the projection detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectionDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of projection detailses
	 * @param end the upper bound of the range of projection detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of projection detailses
	 */
	@Override
	public List<ProjectionDetails> findAll(int start, int end,
		OrderByComparator<ProjectionDetails> orderByComparator,
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

		List<ProjectionDetails> list = null;

		if (retrieveFromCache) {
			list = (List<ProjectionDetails>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_PROJECTIONDETAILS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_PROJECTIONDETAILS;

				if (pagination) {
					sql = sql.concat(ProjectionDetailsModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ProjectionDetails>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ProjectionDetails>)QueryUtil.list(q,
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
	 * Removes all the projection detailses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ProjectionDetails projectionDetails : findAll()) {
			remove(projectionDetails);
		}
	}

	/**
	 * Returns the number of projection detailses.
	 *
	 * @return the number of projection detailses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_PROJECTIONDETAILS);

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
		return ProjectionDetailsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the projection details persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ProjectionDetailsImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_PROJECTIONDETAILS = "SELECT projectionDetails FROM ProjectionDetails projectionDetails";
	private static final String _SQL_SELECT_PROJECTIONDETAILS_WHERE_PKS_IN = "SELECT projectionDetails FROM ProjectionDetails projectionDetails WHERE PROJECTION_DETAILS_SID IN (";
	private static final String _SQL_COUNT_PROJECTIONDETAILS = "SELECT COUNT(projectionDetails) FROM ProjectionDetails projectionDetails";
	private static final String _ORDER_BY_ENTITY_ALIAS = "projectionDetails.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ProjectionDetails exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(ProjectionDetailsPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"projectionDetailsSid", "ccpDetailsSid", "projectionMasterSid"
			});
}