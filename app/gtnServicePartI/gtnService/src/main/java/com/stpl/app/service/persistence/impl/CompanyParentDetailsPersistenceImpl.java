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

import com.stpl.app.exception.NoSuchCompanyParentDetailsException;
import com.stpl.app.model.CompanyParentDetails;
import com.stpl.app.model.impl.CompanyParentDetailsImpl;
import com.stpl.app.model.impl.CompanyParentDetailsModelImpl;
import com.stpl.app.service.persistence.CompanyParentDetailsPersistence;

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
 * The persistence implementation for the company parent details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CompanyParentDetailsPersistence
 * @see com.stpl.app.service.persistence.CompanyParentDetailsUtil
 * @generated
 */
@ProviderType
public class CompanyParentDetailsPersistenceImpl extends BasePersistenceImpl<CompanyParentDetails>
	implements CompanyParentDetailsPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CompanyParentDetailsUtil} to access the company parent details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CompanyParentDetailsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CompanyParentDetailsModelImpl.ENTITY_CACHE_ENABLED,
			CompanyParentDetailsModelImpl.FINDER_CACHE_ENABLED,
			CompanyParentDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CompanyParentDetailsModelImpl.ENTITY_CACHE_ENABLED,
			CompanyParentDetailsModelImpl.FINDER_CACHE_ENABLED,
			CompanyParentDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CompanyParentDetailsModelImpl.ENTITY_CACHE_ENABLED,
			CompanyParentDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public CompanyParentDetailsPersistenceImpl() {
		setModelClass(CompanyParentDetails.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("lastUpdatedDate", "LAST_UPDATED_DATE");
			dbColumnNames.put("parentEndDate", "PARENT_END_DATE");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("parentCompanyMasterSid",
				"PARENT_COMPANY_MASTER_SID");
			dbColumnNames.put("recordLockStatus", "RECORD_LOCK_STATUS");
			dbColumnNames.put("priorParentStartDate", "PRIOR_PARENT_START_DATE");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("companyParentDetailsSid",
				"COMPANY_PARENT_DETAILS_SID");
			dbColumnNames.put("priorParentCmpyMasterSid",
				"PRIOR_PARENT_CMPY_MASTER_SID");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("inboundStatus", "INBOUND_STATUS");
			dbColumnNames.put("companyMasterSid", "COMPANY_MASTER_SID");
			dbColumnNames.put("parentStartDate", "PARENT_START_DATE");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the company parent details in the entity cache if it is enabled.
	 *
	 * @param companyParentDetails the company parent details
	 */
	@Override
	public void cacheResult(CompanyParentDetails companyParentDetails) {
		entityCache.putResult(CompanyParentDetailsModelImpl.ENTITY_CACHE_ENABLED,
			CompanyParentDetailsImpl.class,
			companyParentDetails.getPrimaryKey(), companyParentDetails);

		companyParentDetails.resetOriginalValues();
	}

	/**
	 * Caches the company parent detailses in the entity cache if it is enabled.
	 *
	 * @param companyParentDetailses the company parent detailses
	 */
	@Override
	public void cacheResult(List<CompanyParentDetails> companyParentDetailses) {
		for (CompanyParentDetails companyParentDetails : companyParentDetailses) {
			if (entityCache.getResult(
						CompanyParentDetailsModelImpl.ENTITY_CACHE_ENABLED,
						CompanyParentDetailsImpl.class,
						companyParentDetails.getPrimaryKey()) == null) {
				cacheResult(companyParentDetails);
			}
			else {
				companyParentDetails.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all company parent detailses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CompanyParentDetailsImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the company parent details.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CompanyParentDetails companyParentDetails) {
		entityCache.removeResult(CompanyParentDetailsModelImpl.ENTITY_CACHE_ENABLED,
			CompanyParentDetailsImpl.class, companyParentDetails.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<CompanyParentDetails> companyParentDetailses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CompanyParentDetails companyParentDetails : companyParentDetailses) {
			entityCache.removeResult(CompanyParentDetailsModelImpl.ENTITY_CACHE_ENABLED,
				CompanyParentDetailsImpl.class,
				companyParentDetails.getPrimaryKey());
		}
	}

	/**
	 * Creates a new company parent details with the primary key. Does not add the company parent details to the database.
	 *
	 * @param companyParentDetailsSid the primary key for the new company parent details
	 * @return the new company parent details
	 */
	@Override
	public CompanyParentDetails create(int companyParentDetailsSid) {
		CompanyParentDetails companyParentDetails = new CompanyParentDetailsImpl();

		companyParentDetails.setNew(true);
		companyParentDetails.setPrimaryKey(companyParentDetailsSid);

		return companyParentDetails;
	}

	/**
	 * Removes the company parent details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param companyParentDetailsSid the primary key of the company parent details
	 * @return the company parent details that was removed
	 * @throws NoSuchCompanyParentDetailsException if a company parent details with the primary key could not be found
	 */
	@Override
	public CompanyParentDetails remove(int companyParentDetailsSid)
		throws NoSuchCompanyParentDetailsException {
		return remove((Serializable)companyParentDetailsSid);
	}

	/**
	 * Removes the company parent details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the company parent details
	 * @return the company parent details that was removed
	 * @throws NoSuchCompanyParentDetailsException if a company parent details with the primary key could not be found
	 */
	@Override
	public CompanyParentDetails remove(Serializable primaryKey)
		throws NoSuchCompanyParentDetailsException {
		Session session = null;

		try {
			session = openSession();

			CompanyParentDetails companyParentDetails = (CompanyParentDetails)session.get(CompanyParentDetailsImpl.class,
					primaryKey);

			if (companyParentDetails == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCompanyParentDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(companyParentDetails);
		}
		catch (NoSuchCompanyParentDetailsException nsee) {
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
	protected CompanyParentDetails removeImpl(
		CompanyParentDetails companyParentDetails) {
		companyParentDetails = toUnwrappedModel(companyParentDetails);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(companyParentDetails)) {
				companyParentDetails = (CompanyParentDetails)session.get(CompanyParentDetailsImpl.class,
						companyParentDetails.getPrimaryKeyObj());
			}

			if (companyParentDetails != null) {
				session.delete(companyParentDetails);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (companyParentDetails != null) {
			clearCache(companyParentDetails);
		}

		return companyParentDetails;
	}

	@Override
	public CompanyParentDetails updateImpl(
		CompanyParentDetails companyParentDetails) {
		companyParentDetails = toUnwrappedModel(companyParentDetails);

		boolean isNew = companyParentDetails.isNew();

		Session session = null;

		try {
			session = openSession();

			if (companyParentDetails.isNew()) {
				session.save(companyParentDetails);

				companyParentDetails.setNew(false);
			}
			else {
				companyParentDetails = (CompanyParentDetails)session.merge(companyParentDetails);
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

		entityCache.putResult(CompanyParentDetailsModelImpl.ENTITY_CACHE_ENABLED,
			CompanyParentDetailsImpl.class,
			companyParentDetails.getPrimaryKey(), companyParentDetails, false);

		companyParentDetails.resetOriginalValues();

		return companyParentDetails;
	}

	protected CompanyParentDetails toUnwrappedModel(
		CompanyParentDetails companyParentDetails) {
		if (companyParentDetails instanceof CompanyParentDetailsImpl) {
			return companyParentDetails;
		}

		CompanyParentDetailsImpl companyParentDetailsImpl = new CompanyParentDetailsImpl();

		companyParentDetailsImpl.setNew(companyParentDetails.isNew());
		companyParentDetailsImpl.setPrimaryKey(companyParentDetails.getPrimaryKey());

		companyParentDetailsImpl.setLastUpdatedDate(companyParentDetails.getLastUpdatedDate());
		companyParentDetailsImpl.setParentEndDate(companyParentDetails.getParentEndDate());
		companyParentDetailsImpl.setModifiedDate(companyParentDetails.getModifiedDate());
		companyParentDetailsImpl.setParentCompanyMasterSid(companyParentDetails.getParentCompanyMasterSid());
		companyParentDetailsImpl.setRecordLockStatus(companyParentDetails.isRecordLockStatus());
		companyParentDetailsImpl.setPriorParentStartDate(companyParentDetails.getPriorParentStartDate());
		companyParentDetailsImpl.setCreatedDate(companyParentDetails.getCreatedDate());
		companyParentDetailsImpl.setSource(companyParentDetails.getSource());
		companyParentDetailsImpl.setCreatedBy(companyParentDetails.getCreatedBy());
		companyParentDetailsImpl.setCompanyParentDetailsSid(companyParentDetails.getCompanyParentDetailsSid());
		companyParentDetailsImpl.setPriorParentCmpyMasterSid(companyParentDetails.getPriorParentCmpyMasterSid());
		companyParentDetailsImpl.setBatchId(companyParentDetails.getBatchId());
		companyParentDetailsImpl.setModifiedBy(companyParentDetails.getModifiedBy());
		companyParentDetailsImpl.setInboundStatus(companyParentDetails.getInboundStatus());
		companyParentDetailsImpl.setCompanyMasterSid(companyParentDetails.getCompanyMasterSid());
		companyParentDetailsImpl.setParentStartDate(companyParentDetails.getParentStartDate());

		return companyParentDetailsImpl;
	}

	/**
	 * Returns the company parent details with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the company parent details
	 * @return the company parent details
	 * @throws NoSuchCompanyParentDetailsException if a company parent details with the primary key could not be found
	 */
	@Override
	public CompanyParentDetails findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCompanyParentDetailsException {
		CompanyParentDetails companyParentDetails = fetchByPrimaryKey(primaryKey);

		if (companyParentDetails == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCompanyParentDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return companyParentDetails;
	}

	/**
	 * Returns the company parent details with the primary key or throws a {@link NoSuchCompanyParentDetailsException} if it could not be found.
	 *
	 * @param companyParentDetailsSid the primary key of the company parent details
	 * @return the company parent details
	 * @throws NoSuchCompanyParentDetailsException if a company parent details with the primary key could not be found
	 */
	@Override
	public CompanyParentDetails findByPrimaryKey(int companyParentDetailsSid)
		throws NoSuchCompanyParentDetailsException {
		return findByPrimaryKey((Serializable)companyParentDetailsSid);
	}

	/**
	 * Returns the company parent details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the company parent details
	 * @return the company parent details, or <code>null</code> if a company parent details with the primary key could not be found
	 */
	@Override
	public CompanyParentDetails fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(CompanyParentDetailsModelImpl.ENTITY_CACHE_ENABLED,
				CompanyParentDetailsImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CompanyParentDetails companyParentDetails = (CompanyParentDetails)serializable;

		if (companyParentDetails == null) {
			Session session = null;

			try {
				session = openSession();

				companyParentDetails = (CompanyParentDetails)session.get(CompanyParentDetailsImpl.class,
						primaryKey);

				if (companyParentDetails != null) {
					cacheResult(companyParentDetails);
				}
				else {
					entityCache.putResult(CompanyParentDetailsModelImpl.ENTITY_CACHE_ENABLED,
						CompanyParentDetailsImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(CompanyParentDetailsModelImpl.ENTITY_CACHE_ENABLED,
					CompanyParentDetailsImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return companyParentDetails;
	}

	/**
	 * Returns the company parent details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param companyParentDetailsSid the primary key of the company parent details
	 * @return the company parent details, or <code>null</code> if a company parent details with the primary key could not be found
	 */
	@Override
	public CompanyParentDetails fetchByPrimaryKey(int companyParentDetailsSid) {
		return fetchByPrimaryKey((Serializable)companyParentDetailsSid);
	}

	@Override
	public Map<Serializable, CompanyParentDetails> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CompanyParentDetails> map = new HashMap<Serializable, CompanyParentDetails>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CompanyParentDetails companyParentDetails = fetchByPrimaryKey(primaryKey);

			if (companyParentDetails != null) {
				map.put(primaryKey, companyParentDetails);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(CompanyParentDetailsModelImpl.ENTITY_CACHE_ENABLED,
					CompanyParentDetailsImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CompanyParentDetails)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_COMPANYPARENTDETAILS_WHERE_PKS_IN);

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

			for (CompanyParentDetails companyParentDetails : (List<CompanyParentDetails>)q.list()) {
				map.put(companyParentDetails.getPrimaryKeyObj(),
					companyParentDetails);

				cacheResult(companyParentDetails);

				uncachedPrimaryKeys.remove(companyParentDetails.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(CompanyParentDetailsModelImpl.ENTITY_CACHE_ENABLED,
					CompanyParentDetailsImpl.class, primaryKey, nullModel);
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
	 * Returns all the company parent detailses.
	 *
	 * @return the company parent detailses
	 */
	@Override
	public List<CompanyParentDetails> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the company parent detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyParentDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of company parent detailses
	 * @param end the upper bound of the range of company parent detailses (not inclusive)
	 * @return the range of company parent detailses
	 */
	@Override
	public List<CompanyParentDetails> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the company parent detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyParentDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of company parent detailses
	 * @param end the upper bound of the range of company parent detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of company parent detailses
	 */
	@Override
	public List<CompanyParentDetails> findAll(int start, int end,
		OrderByComparator<CompanyParentDetails> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the company parent detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyParentDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of company parent detailses
	 * @param end the upper bound of the range of company parent detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of company parent detailses
	 */
	@Override
	public List<CompanyParentDetails> findAll(int start, int end,
		OrderByComparator<CompanyParentDetails> orderByComparator,
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

		List<CompanyParentDetails> list = null;

		if (retrieveFromCache) {
			list = (List<CompanyParentDetails>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_COMPANYPARENTDETAILS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_COMPANYPARENTDETAILS;

				if (pagination) {
					sql = sql.concat(CompanyParentDetailsModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CompanyParentDetails>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CompanyParentDetails>)QueryUtil.list(q,
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
	 * Removes all the company parent detailses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CompanyParentDetails companyParentDetails : findAll()) {
			remove(companyParentDetails);
		}
	}

	/**
	 * Returns the number of company parent detailses.
	 *
	 * @return the number of company parent detailses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_COMPANYPARENTDETAILS);

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
		return CompanyParentDetailsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the company parent details persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(CompanyParentDetailsImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_COMPANYPARENTDETAILS = "SELECT companyParentDetails FROM CompanyParentDetails companyParentDetails";
	private static final String _SQL_SELECT_COMPANYPARENTDETAILS_WHERE_PKS_IN = "SELECT companyParentDetails FROM CompanyParentDetails companyParentDetails WHERE COMPANY_PARENT_DETAILS_SID IN (";
	private static final String _SQL_COUNT_COMPANYPARENTDETAILS = "SELECT COUNT(companyParentDetails) FROM CompanyParentDetails companyParentDetails";
	private static final String _ORDER_BY_ENTITY_ALIAS = "companyParentDetails.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CompanyParentDetails exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(CompanyParentDetailsPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"lastUpdatedDate", "parentEndDate", "modifiedDate",
				"parentCompanyMasterSid", "recordLockStatus",
				"priorParentStartDate", "createdDate", "source", "createdBy",
				"companyParentDetailsSid", "priorParentCmpyMasterSid", "batchId",
				"modifiedBy", "inboundStatus", "companyMasterSid",
				"parentStartDate"
			});
}