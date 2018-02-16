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

import com.stpl.app.exception.NoSuchProjectionCustDetailsException;
import com.stpl.app.model.ProjectionCustDetails;
import com.stpl.app.model.impl.ProjectionCustDetailsImpl;
import com.stpl.app.model.impl.ProjectionCustDetailsModelImpl;
import com.stpl.app.service.persistence.ProjectionCustDetailsPersistence;

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
 * The persistence implementation for the projection cust details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ProjectionCustDetailsPersistence
 * @see com.stpl.app.service.persistence.ProjectionCustDetailsUtil
 * @generated
 */
@ProviderType
public class ProjectionCustDetailsPersistenceImpl extends BasePersistenceImpl<ProjectionCustDetails>
	implements ProjectionCustDetailsPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ProjectionCustDetailsUtil} to access the projection cust details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ProjectionCustDetailsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ProjectionCustDetailsModelImpl.ENTITY_CACHE_ENABLED,
			ProjectionCustDetailsModelImpl.FINDER_CACHE_ENABLED,
			ProjectionCustDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ProjectionCustDetailsModelImpl.ENTITY_CACHE_ENABLED,
			ProjectionCustDetailsModelImpl.FINDER_CACHE_ENABLED,
			ProjectionCustDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ProjectionCustDetailsModelImpl.ENTITY_CACHE_ENABLED,
			ProjectionCustDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public ProjectionCustDetailsPersistenceImpl() {
		setModelClass(ProjectionCustDetails.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("contractName", "CONTRACT_NAME");
			dbColumnNames.put("customerName", "CUSTOMER_NAME");
			dbColumnNames.put("customerDetailsId", "CUSTOMER_DETAILS_ID");
			dbColumnNames.put("costCenter", "COST_CENTER");
			dbColumnNames.put("customerAlias", "CUSTOMER_ALIAS");
			dbColumnNames.put("subLedgerCode", "SUB_LEDGER_CODE");
			dbColumnNames.put("projectionId", "PROJECTION_ID");
			dbColumnNames.put("marketType", "MARKET_TYPE");
			dbColumnNames.put("contractNo", "CONTRACT_NO");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the projection cust details in the entity cache if it is enabled.
	 *
	 * @param projectionCustDetails the projection cust details
	 */
	@Override
	public void cacheResult(ProjectionCustDetails projectionCustDetails) {
		entityCache.putResult(ProjectionCustDetailsModelImpl.ENTITY_CACHE_ENABLED,
			ProjectionCustDetailsImpl.class,
			projectionCustDetails.getPrimaryKey(), projectionCustDetails);

		projectionCustDetails.resetOriginalValues();
	}

	/**
	 * Caches the projection cust detailses in the entity cache if it is enabled.
	 *
	 * @param projectionCustDetailses the projection cust detailses
	 */
	@Override
	public void cacheResult(List<ProjectionCustDetails> projectionCustDetailses) {
		for (ProjectionCustDetails projectionCustDetails : projectionCustDetailses) {
			if (entityCache.getResult(
						ProjectionCustDetailsModelImpl.ENTITY_CACHE_ENABLED,
						ProjectionCustDetailsImpl.class,
						projectionCustDetails.getPrimaryKey()) == null) {
				cacheResult(projectionCustDetails);
			}
			else {
				projectionCustDetails.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all projection cust detailses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ProjectionCustDetailsImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the projection cust details.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ProjectionCustDetails projectionCustDetails) {
		entityCache.removeResult(ProjectionCustDetailsModelImpl.ENTITY_CACHE_ENABLED,
			ProjectionCustDetailsImpl.class,
			projectionCustDetails.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ProjectionCustDetails> projectionCustDetailses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ProjectionCustDetails projectionCustDetails : projectionCustDetailses) {
			entityCache.removeResult(ProjectionCustDetailsModelImpl.ENTITY_CACHE_ENABLED,
				ProjectionCustDetailsImpl.class,
				projectionCustDetails.getPrimaryKey());
		}
	}

	/**
	 * Creates a new projection cust details with the primary key. Does not add the projection cust details to the database.
	 *
	 * @param customerDetailsId the primary key for the new projection cust details
	 * @return the new projection cust details
	 */
	@Override
	public ProjectionCustDetails create(int customerDetailsId) {
		ProjectionCustDetails projectionCustDetails = new ProjectionCustDetailsImpl();

		projectionCustDetails.setNew(true);
		projectionCustDetails.setPrimaryKey(customerDetailsId);

		return projectionCustDetails;
	}

	/**
	 * Removes the projection cust details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param customerDetailsId the primary key of the projection cust details
	 * @return the projection cust details that was removed
	 * @throws NoSuchProjectionCustDetailsException if a projection cust details with the primary key could not be found
	 */
	@Override
	public ProjectionCustDetails remove(int customerDetailsId)
		throws NoSuchProjectionCustDetailsException {
		return remove((Serializable)customerDetailsId);
	}

	/**
	 * Removes the projection cust details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the projection cust details
	 * @return the projection cust details that was removed
	 * @throws NoSuchProjectionCustDetailsException if a projection cust details with the primary key could not be found
	 */
	@Override
	public ProjectionCustDetails remove(Serializable primaryKey)
		throws NoSuchProjectionCustDetailsException {
		Session session = null;

		try {
			session = openSession();

			ProjectionCustDetails projectionCustDetails = (ProjectionCustDetails)session.get(ProjectionCustDetailsImpl.class,
					primaryKey);

			if (projectionCustDetails == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchProjectionCustDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(projectionCustDetails);
		}
		catch (NoSuchProjectionCustDetailsException nsee) {
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
	protected ProjectionCustDetails removeImpl(
		ProjectionCustDetails projectionCustDetails) {
		projectionCustDetails = toUnwrappedModel(projectionCustDetails);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(projectionCustDetails)) {
				projectionCustDetails = (ProjectionCustDetails)session.get(ProjectionCustDetailsImpl.class,
						projectionCustDetails.getPrimaryKeyObj());
			}

			if (projectionCustDetails != null) {
				session.delete(projectionCustDetails);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (projectionCustDetails != null) {
			clearCache(projectionCustDetails);
		}

		return projectionCustDetails;
	}

	@Override
	public ProjectionCustDetails updateImpl(
		ProjectionCustDetails projectionCustDetails) {
		projectionCustDetails = toUnwrappedModel(projectionCustDetails);

		boolean isNew = projectionCustDetails.isNew();

		Session session = null;

		try {
			session = openSession();

			if (projectionCustDetails.isNew()) {
				session.save(projectionCustDetails);

				projectionCustDetails.setNew(false);
			}
			else {
				projectionCustDetails = (ProjectionCustDetails)session.merge(projectionCustDetails);
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

		entityCache.putResult(ProjectionCustDetailsModelImpl.ENTITY_CACHE_ENABLED,
			ProjectionCustDetailsImpl.class,
			projectionCustDetails.getPrimaryKey(), projectionCustDetails, false);

		projectionCustDetails.resetOriginalValues();

		return projectionCustDetails;
	}

	protected ProjectionCustDetails toUnwrappedModel(
		ProjectionCustDetails projectionCustDetails) {
		if (projectionCustDetails instanceof ProjectionCustDetailsImpl) {
			return projectionCustDetails;
		}

		ProjectionCustDetailsImpl projectionCustDetailsImpl = new ProjectionCustDetailsImpl();

		projectionCustDetailsImpl.setNew(projectionCustDetails.isNew());
		projectionCustDetailsImpl.setPrimaryKey(projectionCustDetails.getPrimaryKey());

		projectionCustDetailsImpl.setContractName(projectionCustDetails.getContractName());
		projectionCustDetailsImpl.setCustomerName(projectionCustDetails.getCustomerName());
		projectionCustDetailsImpl.setCustomerDetailsId(projectionCustDetails.getCustomerDetailsId());
		projectionCustDetailsImpl.setCostCenter(projectionCustDetails.getCostCenter());
		projectionCustDetailsImpl.setCustomerAlias(projectionCustDetails.getCustomerAlias());
		projectionCustDetailsImpl.setSubLedgerCode(projectionCustDetails.getSubLedgerCode());
		projectionCustDetailsImpl.setProjectionId(projectionCustDetails.getProjectionId());
		projectionCustDetailsImpl.setMarketType(projectionCustDetails.getMarketType());
		projectionCustDetailsImpl.setContractNo(projectionCustDetails.getContractNo());

		return projectionCustDetailsImpl;
	}

	/**
	 * Returns the projection cust details with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the projection cust details
	 * @return the projection cust details
	 * @throws NoSuchProjectionCustDetailsException if a projection cust details with the primary key could not be found
	 */
	@Override
	public ProjectionCustDetails findByPrimaryKey(Serializable primaryKey)
		throws NoSuchProjectionCustDetailsException {
		ProjectionCustDetails projectionCustDetails = fetchByPrimaryKey(primaryKey);

		if (projectionCustDetails == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchProjectionCustDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return projectionCustDetails;
	}

	/**
	 * Returns the projection cust details with the primary key or throws a {@link NoSuchProjectionCustDetailsException} if it could not be found.
	 *
	 * @param customerDetailsId the primary key of the projection cust details
	 * @return the projection cust details
	 * @throws NoSuchProjectionCustDetailsException if a projection cust details with the primary key could not be found
	 */
	@Override
	public ProjectionCustDetails findByPrimaryKey(int customerDetailsId)
		throws NoSuchProjectionCustDetailsException {
		return findByPrimaryKey((Serializable)customerDetailsId);
	}

	/**
	 * Returns the projection cust details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the projection cust details
	 * @return the projection cust details, or <code>null</code> if a projection cust details with the primary key could not be found
	 */
	@Override
	public ProjectionCustDetails fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ProjectionCustDetailsModelImpl.ENTITY_CACHE_ENABLED,
				ProjectionCustDetailsImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ProjectionCustDetails projectionCustDetails = (ProjectionCustDetails)serializable;

		if (projectionCustDetails == null) {
			Session session = null;

			try {
				session = openSession();

				projectionCustDetails = (ProjectionCustDetails)session.get(ProjectionCustDetailsImpl.class,
						primaryKey);

				if (projectionCustDetails != null) {
					cacheResult(projectionCustDetails);
				}
				else {
					entityCache.putResult(ProjectionCustDetailsModelImpl.ENTITY_CACHE_ENABLED,
						ProjectionCustDetailsImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ProjectionCustDetailsModelImpl.ENTITY_CACHE_ENABLED,
					ProjectionCustDetailsImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return projectionCustDetails;
	}

	/**
	 * Returns the projection cust details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param customerDetailsId the primary key of the projection cust details
	 * @return the projection cust details, or <code>null</code> if a projection cust details with the primary key could not be found
	 */
	@Override
	public ProjectionCustDetails fetchByPrimaryKey(int customerDetailsId) {
		return fetchByPrimaryKey((Serializable)customerDetailsId);
	}

	@Override
	public Map<Serializable, ProjectionCustDetails> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ProjectionCustDetails> map = new HashMap<Serializable, ProjectionCustDetails>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ProjectionCustDetails projectionCustDetails = fetchByPrimaryKey(primaryKey);

			if (projectionCustDetails != null) {
				map.put(primaryKey, projectionCustDetails);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ProjectionCustDetailsModelImpl.ENTITY_CACHE_ENABLED,
					ProjectionCustDetailsImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ProjectionCustDetails)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_PROJECTIONCUSTDETAILS_WHERE_PKS_IN);

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

			for (ProjectionCustDetails projectionCustDetails : (List<ProjectionCustDetails>)q.list()) {
				map.put(projectionCustDetails.getPrimaryKeyObj(),
					projectionCustDetails);

				cacheResult(projectionCustDetails);

				uncachedPrimaryKeys.remove(projectionCustDetails.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ProjectionCustDetailsModelImpl.ENTITY_CACHE_ENABLED,
					ProjectionCustDetailsImpl.class, primaryKey, nullModel);
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
	 * Returns all the projection cust detailses.
	 *
	 * @return the projection cust detailses
	 */
	@Override
	public List<ProjectionCustDetails> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the projection cust detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectionCustDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of projection cust detailses
	 * @param end the upper bound of the range of projection cust detailses (not inclusive)
	 * @return the range of projection cust detailses
	 */
	@Override
	public List<ProjectionCustDetails> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the projection cust detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectionCustDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of projection cust detailses
	 * @param end the upper bound of the range of projection cust detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of projection cust detailses
	 */
	@Override
	public List<ProjectionCustDetails> findAll(int start, int end,
		OrderByComparator<ProjectionCustDetails> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the projection cust detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectionCustDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of projection cust detailses
	 * @param end the upper bound of the range of projection cust detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of projection cust detailses
	 */
	@Override
	public List<ProjectionCustDetails> findAll(int start, int end,
		OrderByComparator<ProjectionCustDetails> orderByComparator,
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

		List<ProjectionCustDetails> list = null;

		if (retrieveFromCache) {
			list = (List<ProjectionCustDetails>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_PROJECTIONCUSTDETAILS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_PROJECTIONCUSTDETAILS;

				if (pagination) {
					sql = sql.concat(ProjectionCustDetailsModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ProjectionCustDetails>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ProjectionCustDetails>)QueryUtil.list(q,
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
	 * Removes all the projection cust detailses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ProjectionCustDetails projectionCustDetails : findAll()) {
			remove(projectionCustDetails);
		}
	}

	/**
	 * Returns the number of projection cust detailses.
	 *
	 * @return the number of projection cust detailses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_PROJECTIONCUSTDETAILS);

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
		return ProjectionCustDetailsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the projection cust details persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ProjectionCustDetailsImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_PROJECTIONCUSTDETAILS = "SELECT projectionCustDetails FROM ProjectionCustDetails projectionCustDetails";
	private static final String _SQL_SELECT_PROJECTIONCUSTDETAILS_WHERE_PKS_IN = "SELECT projectionCustDetails FROM ProjectionCustDetails projectionCustDetails WHERE CUSTOMER_DETAILS_ID IN (";
	private static final String _SQL_COUNT_PROJECTIONCUSTDETAILS = "SELECT COUNT(projectionCustDetails) FROM ProjectionCustDetails projectionCustDetails";
	private static final String _ORDER_BY_ENTITY_ALIAS = "projectionCustDetails.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ProjectionCustDetails exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(ProjectionCustDetailsPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"contractName", "customerName", "customerDetailsId",
				"costCenter", "customerAlias", "subLedgerCode", "projectionId",
				"marketType", "contractNo"
			});
}