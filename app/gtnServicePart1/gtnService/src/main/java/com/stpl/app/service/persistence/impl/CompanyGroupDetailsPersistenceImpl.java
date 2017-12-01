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

import com.stpl.app.exception.NoSuchCompanyGroupDetailsException;
import com.stpl.app.model.CompanyGroupDetails;
import com.stpl.app.model.impl.CompanyGroupDetailsImpl;
import com.stpl.app.model.impl.CompanyGroupDetailsModelImpl;
import com.stpl.app.service.persistence.CompanyGroupDetailsPersistence;

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
 * The persistence implementation for the company group details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CompanyGroupDetailsPersistence
 * @see com.stpl.app.service.persistence.CompanyGroupDetailsUtil
 * @generated
 */
@ProviderType
public class CompanyGroupDetailsPersistenceImpl extends BasePersistenceImpl<CompanyGroupDetails>
	implements CompanyGroupDetailsPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CompanyGroupDetailsUtil} to access the company group details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CompanyGroupDetailsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CompanyGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
			CompanyGroupDetailsModelImpl.FINDER_CACHE_ENABLED,
			CompanyGroupDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CompanyGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
			CompanyGroupDetailsModelImpl.FINDER_CACHE_ENABLED,
			CompanyGroupDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CompanyGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
			CompanyGroupDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public CompanyGroupDetailsPersistenceImpl() {
		setModelClass(CompanyGroupDetails.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("companyParentDetailsSid",
				"COMPANY_PARENT_DETAILS_SID");
			dbColumnNames.put("companyTradeclassSid", "COMPANY_TRADECLASS_SID");
			dbColumnNames.put("companyGroupSid", "COMPANY_GROUP_SID");
			dbColumnNames.put("versionNo", "VERSION_NO");
			dbColumnNames.put("companyGroupDetailsSid",
				"COMPANY_GROUP_DETAILS_SID");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("companyMasterSid", "COMPANY_MASTER_SID");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the company group details in the entity cache if it is enabled.
	 *
	 * @param companyGroupDetails the company group details
	 */
	@Override
	public void cacheResult(CompanyGroupDetails companyGroupDetails) {
		entityCache.putResult(CompanyGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
			CompanyGroupDetailsImpl.class, companyGroupDetails.getPrimaryKey(),
			companyGroupDetails);

		companyGroupDetails.resetOriginalValues();
	}

	/**
	 * Caches the company group detailses in the entity cache if it is enabled.
	 *
	 * @param companyGroupDetailses the company group detailses
	 */
	@Override
	public void cacheResult(List<CompanyGroupDetails> companyGroupDetailses) {
		for (CompanyGroupDetails companyGroupDetails : companyGroupDetailses) {
			if (entityCache.getResult(
						CompanyGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
						CompanyGroupDetailsImpl.class,
						companyGroupDetails.getPrimaryKey()) == null) {
				cacheResult(companyGroupDetails);
			}
			else {
				companyGroupDetails.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all company group detailses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CompanyGroupDetailsImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the company group details.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CompanyGroupDetails companyGroupDetails) {
		entityCache.removeResult(CompanyGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
			CompanyGroupDetailsImpl.class, companyGroupDetails.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<CompanyGroupDetails> companyGroupDetailses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CompanyGroupDetails companyGroupDetails : companyGroupDetailses) {
			entityCache.removeResult(CompanyGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
				CompanyGroupDetailsImpl.class,
				companyGroupDetails.getPrimaryKey());
		}
	}

	/**
	 * Creates a new company group details with the primary key. Does not add the company group details to the database.
	 *
	 * @param companyGroupDetailsSid the primary key for the new company group details
	 * @return the new company group details
	 */
	@Override
	public CompanyGroupDetails create(int companyGroupDetailsSid) {
		CompanyGroupDetails companyGroupDetails = new CompanyGroupDetailsImpl();

		companyGroupDetails.setNew(true);
		companyGroupDetails.setPrimaryKey(companyGroupDetailsSid);

		return companyGroupDetails;
	}

	/**
	 * Removes the company group details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param companyGroupDetailsSid the primary key of the company group details
	 * @return the company group details that was removed
	 * @throws NoSuchCompanyGroupDetailsException if a company group details with the primary key could not be found
	 */
	@Override
	public CompanyGroupDetails remove(int companyGroupDetailsSid)
		throws NoSuchCompanyGroupDetailsException {
		return remove((Serializable)companyGroupDetailsSid);
	}

	/**
	 * Removes the company group details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the company group details
	 * @return the company group details that was removed
	 * @throws NoSuchCompanyGroupDetailsException if a company group details with the primary key could not be found
	 */
	@Override
	public CompanyGroupDetails remove(Serializable primaryKey)
		throws NoSuchCompanyGroupDetailsException {
		Session session = null;

		try {
			session = openSession();

			CompanyGroupDetails companyGroupDetails = (CompanyGroupDetails)session.get(CompanyGroupDetailsImpl.class,
					primaryKey);

			if (companyGroupDetails == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCompanyGroupDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(companyGroupDetails);
		}
		catch (NoSuchCompanyGroupDetailsException nsee) {
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
	protected CompanyGroupDetails removeImpl(
		CompanyGroupDetails companyGroupDetails) {
		companyGroupDetails = toUnwrappedModel(companyGroupDetails);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(companyGroupDetails)) {
				companyGroupDetails = (CompanyGroupDetails)session.get(CompanyGroupDetailsImpl.class,
						companyGroupDetails.getPrimaryKeyObj());
			}

			if (companyGroupDetails != null) {
				session.delete(companyGroupDetails);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (companyGroupDetails != null) {
			clearCache(companyGroupDetails);
		}

		return companyGroupDetails;
	}

	@Override
	public CompanyGroupDetails updateImpl(
		CompanyGroupDetails companyGroupDetails) {
		companyGroupDetails = toUnwrappedModel(companyGroupDetails);

		boolean isNew = companyGroupDetails.isNew();

		Session session = null;

		try {
			session = openSession();

			if (companyGroupDetails.isNew()) {
				session.save(companyGroupDetails);

				companyGroupDetails.setNew(false);
			}
			else {
				companyGroupDetails = (CompanyGroupDetails)session.merge(companyGroupDetails);
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

		entityCache.putResult(CompanyGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
			CompanyGroupDetailsImpl.class, companyGroupDetails.getPrimaryKey(),
			companyGroupDetails, false);

		companyGroupDetails.resetOriginalValues();

		return companyGroupDetails;
	}

	protected CompanyGroupDetails toUnwrappedModel(
		CompanyGroupDetails companyGroupDetails) {
		if (companyGroupDetails instanceof CompanyGroupDetailsImpl) {
			return companyGroupDetails;
		}

		CompanyGroupDetailsImpl companyGroupDetailsImpl = new CompanyGroupDetailsImpl();

		companyGroupDetailsImpl.setNew(companyGroupDetails.isNew());
		companyGroupDetailsImpl.setPrimaryKey(companyGroupDetails.getPrimaryKey());

		companyGroupDetailsImpl.setCreatedDate(companyGroupDetails.getCreatedDate());
		companyGroupDetailsImpl.setCreatedBy(companyGroupDetails.getCreatedBy());
		companyGroupDetailsImpl.setCompanyParentDetailsSid(companyGroupDetails.getCompanyParentDetailsSid());
		companyGroupDetailsImpl.setCompanyTradeclassSid(companyGroupDetails.getCompanyTradeclassSid());
		companyGroupDetailsImpl.setCompanyGroupSid(companyGroupDetails.getCompanyGroupSid());
		companyGroupDetailsImpl.setVersionNo(companyGroupDetails.getVersionNo());
		companyGroupDetailsImpl.setCompanyGroupDetailsSid(companyGroupDetails.getCompanyGroupDetailsSid());
		companyGroupDetailsImpl.setModifiedBy(companyGroupDetails.getModifiedBy());
		companyGroupDetailsImpl.setModifiedDate(companyGroupDetails.getModifiedDate());
		companyGroupDetailsImpl.setCompanyMasterSid(companyGroupDetails.getCompanyMasterSid());

		return companyGroupDetailsImpl;
	}

	/**
	 * Returns the company group details with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the company group details
	 * @return the company group details
	 * @throws NoSuchCompanyGroupDetailsException if a company group details with the primary key could not be found
	 */
	@Override
	public CompanyGroupDetails findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCompanyGroupDetailsException {
		CompanyGroupDetails companyGroupDetails = fetchByPrimaryKey(primaryKey);

		if (companyGroupDetails == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCompanyGroupDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return companyGroupDetails;
	}

	/**
	 * Returns the company group details with the primary key or throws a {@link NoSuchCompanyGroupDetailsException} if it could not be found.
	 *
	 * @param companyGroupDetailsSid the primary key of the company group details
	 * @return the company group details
	 * @throws NoSuchCompanyGroupDetailsException if a company group details with the primary key could not be found
	 */
	@Override
	public CompanyGroupDetails findByPrimaryKey(int companyGroupDetailsSid)
		throws NoSuchCompanyGroupDetailsException {
		return findByPrimaryKey((Serializable)companyGroupDetailsSid);
	}

	/**
	 * Returns the company group details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the company group details
	 * @return the company group details, or <code>null</code> if a company group details with the primary key could not be found
	 */
	@Override
	public CompanyGroupDetails fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(CompanyGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
				CompanyGroupDetailsImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CompanyGroupDetails companyGroupDetails = (CompanyGroupDetails)serializable;

		if (companyGroupDetails == null) {
			Session session = null;

			try {
				session = openSession();

				companyGroupDetails = (CompanyGroupDetails)session.get(CompanyGroupDetailsImpl.class,
						primaryKey);

				if (companyGroupDetails != null) {
					cacheResult(companyGroupDetails);
				}
				else {
					entityCache.putResult(CompanyGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
						CompanyGroupDetailsImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(CompanyGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
					CompanyGroupDetailsImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return companyGroupDetails;
	}

	/**
	 * Returns the company group details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param companyGroupDetailsSid the primary key of the company group details
	 * @return the company group details, or <code>null</code> if a company group details with the primary key could not be found
	 */
	@Override
	public CompanyGroupDetails fetchByPrimaryKey(int companyGroupDetailsSid) {
		return fetchByPrimaryKey((Serializable)companyGroupDetailsSid);
	}

	@Override
	public Map<Serializable, CompanyGroupDetails> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CompanyGroupDetails> map = new HashMap<Serializable, CompanyGroupDetails>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CompanyGroupDetails companyGroupDetails = fetchByPrimaryKey(primaryKey);

			if (companyGroupDetails != null) {
				map.put(primaryKey, companyGroupDetails);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(CompanyGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
					CompanyGroupDetailsImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CompanyGroupDetails)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_COMPANYGROUPDETAILS_WHERE_PKS_IN);

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

			for (CompanyGroupDetails companyGroupDetails : (List<CompanyGroupDetails>)q.list()) {
				map.put(companyGroupDetails.getPrimaryKeyObj(),
					companyGroupDetails);

				cacheResult(companyGroupDetails);

				uncachedPrimaryKeys.remove(companyGroupDetails.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(CompanyGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
					CompanyGroupDetailsImpl.class, primaryKey, nullModel);
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
	 * Returns all the company group detailses.
	 *
	 * @return the company group detailses
	 */
	@Override
	public List<CompanyGroupDetails> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the company group detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of company group detailses
	 * @param end the upper bound of the range of company group detailses (not inclusive)
	 * @return the range of company group detailses
	 */
	@Override
	public List<CompanyGroupDetails> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the company group detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of company group detailses
	 * @param end the upper bound of the range of company group detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of company group detailses
	 */
	@Override
	public List<CompanyGroupDetails> findAll(int start, int end,
		OrderByComparator<CompanyGroupDetails> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the company group detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of company group detailses
	 * @param end the upper bound of the range of company group detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of company group detailses
	 */
	@Override
	public List<CompanyGroupDetails> findAll(int start, int end,
		OrderByComparator<CompanyGroupDetails> orderByComparator,
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

		List<CompanyGroupDetails> list = null;

		if (retrieveFromCache) {
			list = (List<CompanyGroupDetails>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_COMPANYGROUPDETAILS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_COMPANYGROUPDETAILS;

				if (pagination) {
					sql = sql.concat(CompanyGroupDetailsModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CompanyGroupDetails>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CompanyGroupDetails>)QueryUtil.list(q,
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
	 * Removes all the company group detailses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CompanyGroupDetails companyGroupDetails : findAll()) {
			remove(companyGroupDetails);
		}
	}

	/**
	 * Returns the number of company group detailses.
	 *
	 * @return the number of company group detailses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_COMPANYGROUPDETAILS);

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
		return CompanyGroupDetailsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the company group details persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(CompanyGroupDetailsImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_COMPANYGROUPDETAILS = "SELECT companyGroupDetails FROM CompanyGroupDetails companyGroupDetails";
	private static final String _SQL_SELECT_COMPANYGROUPDETAILS_WHERE_PKS_IN = "SELECT companyGroupDetails FROM CompanyGroupDetails companyGroupDetails WHERE COMPANY_GROUP_DETAILS_SID IN (";
	private static final String _SQL_COUNT_COMPANYGROUPDETAILS = "SELECT COUNT(companyGroupDetails) FROM CompanyGroupDetails companyGroupDetails";
	private static final String _ORDER_BY_ENTITY_ALIAS = "companyGroupDetails.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CompanyGroupDetails exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(CompanyGroupDetailsPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"createdDate", "createdBy", "companyParentDetailsSid",
				"companyTradeclassSid", "companyGroupSid", "versionNo",
				"companyGroupDetailsSid", "modifiedBy", "modifiedDate",
				"companyMasterSid"
			});
}