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

import com.stpl.app.exception.NoSuchProjectionProdDetailsException;
import com.stpl.app.model.ProjectionProdDetails;
import com.stpl.app.model.impl.ProjectionProdDetailsImpl;
import com.stpl.app.model.impl.ProjectionProdDetailsModelImpl;
import com.stpl.app.service.persistence.ProjectionProdDetailsPersistence;

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
 * The persistence implementation for the projection prod details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ProjectionProdDetailsPersistence
 * @see com.stpl.app.service.persistence.ProjectionProdDetailsUtil
 * @generated
 */
@ProviderType
public class ProjectionProdDetailsPersistenceImpl extends BasePersistenceImpl<ProjectionProdDetails>
	implements ProjectionProdDetailsPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ProjectionProdDetailsUtil} to access the projection prod details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ProjectionProdDetailsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ProjectionProdDetailsModelImpl.ENTITY_CACHE_ENABLED,
			ProjectionProdDetailsModelImpl.FINDER_CACHE_ENABLED,
			ProjectionProdDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ProjectionProdDetailsModelImpl.ENTITY_CACHE_ENABLED,
			ProjectionProdDetailsModelImpl.FINDER_CACHE_ENABLED,
			ProjectionProdDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ProjectionProdDetailsModelImpl.ENTITY_CACHE_ENABLED,
			ProjectionProdDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public ProjectionProdDetailsPersistenceImpl() {
		setModelClass(ProjectionProdDetails.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("productName", "PRODUCT_NAME");
			dbColumnNames.put("costCenter", "COST_CENTER");
			dbColumnNames.put("productNo", "PRODUCT_NO");
			dbColumnNames.put("subLedgerCode", "SUB_LEDGER_CODE");
			dbColumnNames.put("productDetailsId", "PRODUCT_DETAILS_ID");
			dbColumnNames.put("brandName", "BRAND_NAME");
			dbColumnNames.put("projectionId", "PROJECTION_ID");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the projection prod details in the entity cache if it is enabled.
	 *
	 * @param projectionProdDetails the projection prod details
	 */
	@Override
	public void cacheResult(ProjectionProdDetails projectionProdDetails) {
		entityCache.putResult(ProjectionProdDetailsModelImpl.ENTITY_CACHE_ENABLED,
			ProjectionProdDetailsImpl.class,
			projectionProdDetails.getPrimaryKey(), projectionProdDetails);

		projectionProdDetails.resetOriginalValues();
	}

	/**
	 * Caches the projection prod detailses in the entity cache if it is enabled.
	 *
	 * @param projectionProdDetailses the projection prod detailses
	 */
	@Override
	public void cacheResult(List<ProjectionProdDetails> projectionProdDetailses) {
		for (ProjectionProdDetails projectionProdDetails : projectionProdDetailses) {
			if (entityCache.getResult(
						ProjectionProdDetailsModelImpl.ENTITY_CACHE_ENABLED,
						ProjectionProdDetailsImpl.class,
						projectionProdDetails.getPrimaryKey()) == null) {
				cacheResult(projectionProdDetails);
			}
			else {
				projectionProdDetails.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all projection prod detailses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ProjectionProdDetailsImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the projection prod details.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ProjectionProdDetails projectionProdDetails) {
		entityCache.removeResult(ProjectionProdDetailsModelImpl.ENTITY_CACHE_ENABLED,
			ProjectionProdDetailsImpl.class,
			projectionProdDetails.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ProjectionProdDetails> projectionProdDetailses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ProjectionProdDetails projectionProdDetails : projectionProdDetailses) {
			entityCache.removeResult(ProjectionProdDetailsModelImpl.ENTITY_CACHE_ENABLED,
				ProjectionProdDetailsImpl.class,
				projectionProdDetails.getPrimaryKey());
		}
	}

	/**
	 * Creates a new projection prod details with the primary key. Does not add the projection prod details to the database.
	 *
	 * @param productDetailsId the primary key for the new projection prod details
	 * @return the new projection prod details
	 */
	@Override
	public ProjectionProdDetails create(int productDetailsId) {
		ProjectionProdDetails projectionProdDetails = new ProjectionProdDetailsImpl();

		projectionProdDetails.setNew(true);
		projectionProdDetails.setPrimaryKey(productDetailsId);

		return projectionProdDetails;
	}

	/**
	 * Removes the projection prod details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param productDetailsId the primary key of the projection prod details
	 * @return the projection prod details that was removed
	 * @throws NoSuchProjectionProdDetailsException if a projection prod details with the primary key could not be found
	 */
	@Override
	public ProjectionProdDetails remove(int productDetailsId)
		throws NoSuchProjectionProdDetailsException {
		return remove((Serializable)productDetailsId);
	}

	/**
	 * Removes the projection prod details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the projection prod details
	 * @return the projection prod details that was removed
	 * @throws NoSuchProjectionProdDetailsException if a projection prod details with the primary key could not be found
	 */
	@Override
	public ProjectionProdDetails remove(Serializable primaryKey)
		throws NoSuchProjectionProdDetailsException {
		Session session = null;

		try {
			session = openSession();

			ProjectionProdDetails projectionProdDetails = (ProjectionProdDetails)session.get(ProjectionProdDetailsImpl.class,
					primaryKey);

			if (projectionProdDetails == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchProjectionProdDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(projectionProdDetails);
		}
		catch (NoSuchProjectionProdDetailsException nsee) {
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
	protected ProjectionProdDetails removeImpl(
		ProjectionProdDetails projectionProdDetails) {
		projectionProdDetails = toUnwrappedModel(projectionProdDetails);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(projectionProdDetails)) {
				projectionProdDetails = (ProjectionProdDetails)session.get(ProjectionProdDetailsImpl.class,
						projectionProdDetails.getPrimaryKeyObj());
			}

			if (projectionProdDetails != null) {
				session.delete(projectionProdDetails);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (projectionProdDetails != null) {
			clearCache(projectionProdDetails);
		}

		return projectionProdDetails;
	}

	@Override
	public ProjectionProdDetails updateImpl(
		ProjectionProdDetails projectionProdDetails) {
		projectionProdDetails = toUnwrappedModel(projectionProdDetails);

		boolean isNew = projectionProdDetails.isNew();

		Session session = null;

		try {
			session = openSession();

			if (projectionProdDetails.isNew()) {
				session.save(projectionProdDetails);

				projectionProdDetails.setNew(false);
			}
			else {
				projectionProdDetails = (ProjectionProdDetails)session.merge(projectionProdDetails);
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

		entityCache.putResult(ProjectionProdDetailsModelImpl.ENTITY_CACHE_ENABLED,
			ProjectionProdDetailsImpl.class,
			projectionProdDetails.getPrimaryKey(), projectionProdDetails, false);

		projectionProdDetails.resetOriginalValues();

		return projectionProdDetails;
	}

	protected ProjectionProdDetails toUnwrappedModel(
		ProjectionProdDetails projectionProdDetails) {
		if (projectionProdDetails instanceof ProjectionProdDetailsImpl) {
			return projectionProdDetails;
		}

		ProjectionProdDetailsImpl projectionProdDetailsImpl = new ProjectionProdDetailsImpl();

		projectionProdDetailsImpl.setNew(projectionProdDetails.isNew());
		projectionProdDetailsImpl.setPrimaryKey(projectionProdDetails.getPrimaryKey());

		projectionProdDetailsImpl.setProductName(projectionProdDetails.getProductName());
		projectionProdDetailsImpl.setCostCenter(projectionProdDetails.getCostCenter());
		projectionProdDetailsImpl.setProductNo(projectionProdDetails.getProductNo());
		projectionProdDetailsImpl.setSubLedgerCode(projectionProdDetails.getSubLedgerCode());
		projectionProdDetailsImpl.setProductDetailsId(projectionProdDetails.getProductDetailsId());
		projectionProdDetailsImpl.setBrandName(projectionProdDetails.getBrandName());
		projectionProdDetailsImpl.setProjectionId(projectionProdDetails.getProjectionId());

		return projectionProdDetailsImpl;
	}

	/**
	 * Returns the projection prod details with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the projection prod details
	 * @return the projection prod details
	 * @throws NoSuchProjectionProdDetailsException if a projection prod details with the primary key could not be found
	 */
	@Override
	public ProjectionProdDetails findByPrimaryKey(Serializable primaryKey)
		throws NoSuchProjectionProdDetailsException {
		ProjectionProdDetails projectionProdDetails = fetchByPrimaryKey(primaryKey);

		if (projectionProdDetails == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchProjectionProdDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return projectionProdDetails;
	}

	/**
	 * Returns the projection prod details with the primary key or throws a {@link NoSuchProjectionProdDetailsException} if it could not be found.
	 *
	 * @param productDetailsId the primary key of the projection prod details
	 * @return the projection prod details
	 * @throws NoSuchProjectionProdDetailsException if a projection prod details with the primary key could not be found
	 */
	@Override
	public ProjectionProdDetails findByPrimaryKey(int productDetailsId)
		throws NoSuchProjectionProdDetailsException {
		return findByPrimaryKey((Serializable)productDetailsId);
	}

	/**
	 * Returns the projection prod details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the projection prod details
	 * @return the projection prod details, or <code>null</code> if a projection prod details with the primary key could not be found
	 */
	@Override
	public ProjectionProdDetails fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ProjectionProdDetailsModelImpl.ENTITY_CACHE_ENABLED,
				ProjectionProdDetailsImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ProjectionProdDetails projectionProdDetails = (ProjectionProdDetails)serializable;

		if (projectionProdDetails == null) {
			Session session = null;

			try {
				session = openSession();

				projectionProdDetails = (ProjectionProdDetails)session.get(ProjectionProdDetailsImpl.class,
						primaryKey);

				if (projectionProdDetails != null) {
					cacheResult(projectionProdDetails);
				}
				else {
					entityCache.putResult(ProjectionProdDetailsModelImpl.ENTITY_CACHE_ENABLED,
						ProjectionProdDetailsImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ProjectionProdDetailsModelImpl.ENTITY_CACHE_ENABLED,
					ProjectionProdDetailsImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return projectionProdDetails;
	}

	/**
	 * Returns the projection prod details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param productDetailsId the primary key of the projection prod details
	 * @return the projection prod details, or <code>null</code> if a projection prod details with the primary key could not be found
	 */
	@Override
	public ProjectionProdDetails fetchByPrimaryKey(int productDetailsId) {
		return fetchByPrimaryKey((Serializable)productDetailsId);
	}

	@Override
	public Map<Serializable, ProjectionProdDetails> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ProjectionProdDetails> map = new HashMap<Serializable, ProjectionProdDetails>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ProjectionProdDetails projectionProdDetails = fetchByPrimaryKey(primaryKey);

			if (projectionProdDetails != null) {
				map.put(primaryKey, projectionProdDetails);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ProjectionProdDetailsModelImpl.ENTITY_CACHE_ENABLED,
					ProjectionProdDetailsImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ProjectionProdDetails)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_PROJECTIONPRODDETAILS_WHERE_PKS_IN);

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

			for (ProjectionProdDetails projectionProdDetails : (List<ProjectionProdDetails>)q.list()) {
				map.put(projectionProdDetails.getPrimaryKeyObj(),
					projectionProdDetails);

				cacheResult(projectionProdDetails);

				uncachedPrimaryKeys.remove(projectionProdDetails.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ProjectionProdDetailsModelImpl.ENTITY_CACHE_ENABLED,
					ProjectionProdDetailsImpl.class, primaryKey, nullModel);
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
	 * Returns all the projection prod detailses.
	 *
	 * @return the projection prod detailses
	 */
	@Override
	public List<ProjectionProdDetails> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the projection prod detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectionProdDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of projection prod detailses
	 * @param end the upper bound of the range of projection prod detailses (not inclusive)
	 * @return the range of projection prod detailses
	 */
	@Override
	public List<ProjectionProdDetails> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the projection prod detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectionProdDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of projection prod detailses
	 * @param end the upper bound of the range of projection prod detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of projection prod detailses
	 */
	@Override
	public List<ProjectionProdDetails> findAll(int start, int end,
		OrderByComparator<ProjectionProdDetails> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the projection prod detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectionProdDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of projection prod detailses
	 * @param end the upper bound of the range of projection prod detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of projection prod detailses
	 */
	@Override
	public List<ProjectionProdDetails> findAll(int start, int end,
		OrderByComparator<ProjectionProdDetails> orderByComparator,
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

		List<ProjectionProdDetails> list = null;

		if (retrieveFromCache) {
			list = (List<ProjectionProdDetails>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_PROJECTIONPRODDETAILS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_PROJECTIONPRODDETAILS;

				if (pagination) {
					sql = sql.concat(ProjectionProdDetailsModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ProjectionProdDetails>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ProjectionProdDetails>)QueryUtil.list(q,
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
	 * Removes all the projection prod detailses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ProjectionProdDetails projectionProdDetails : findAll()) {
			remove(projectionProdDetails);
		}
	}

	/**
	 * Returns the number of projection prod detailses.
	 *
	 * @return the number of projection prod detailses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_PROJECTIONPRODDETAILS);

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
		return ProjectionProdDetailsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the projection prod details persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ProjectionProdDetailsImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_PROJECTIONPRODDETAILS = "SELECT projectionProdDetails FROM ProjectionProdDetails projectionProdDetails";
	private static final String _SQL_SELECT_PROJECTIONPRODDETAILS_WHERE_PKS_IN = "SELECT projectionProdDetails FROM ProjectionProdDetails projectionProdDetails WHERE PRODUCT_DETAILS_ID IN (";
	private static final String _SQL_COUNT_PROJECTIONPRODDETAILS = "SELECT COUNT(projectionProdDetails) FROM ProjectionProdDetails projectionProdDetails";
	private static final String _ORDER_BY_ENTITY_ALIAS = "projectionProdDetails.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ProjectionProdDetails exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(ProjectionProdDetailsPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"productName", "costCenter", "productNo", "subLedgerCode",
				"productDetailsId", "brandName", "projectionId"
			});
}