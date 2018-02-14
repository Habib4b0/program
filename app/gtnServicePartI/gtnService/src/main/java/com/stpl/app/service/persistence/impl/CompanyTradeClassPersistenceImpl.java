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

import com.stpl.app.exception.NoSuchCompanyTradeClassException;
import com.stpl.app.model.CompanyTradeClass;
import com.stpl.app.model.impl.CompanyTradeClassImpl;
import com.stpl.app.model.impl.CompanyTradeClassModelImpl;
import com.stpl.app.service.persistence.CompanyTradeClassPersistence;

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
 * The persistence implementation for the company trade class service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CompanyTradeClassPersistence
 * @see com.stpl.app.service.persistence.CompanyTradeClassUtil
 * @generated
 */
@ProviderType
public class CompanyTradeClassPersistenceImpl extends BasePersistenceImpl<CompanyTradeClass>
	implements CompanyTradeClassPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CompanyTradeClassUtil} to access the company trade class persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CompanyTradeClassImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CompanyTradeClassModelImpl.ENTITY_CACHE_ENABLED,
			CompanyTradeClassModelImpl.FINDER_CACHE_ENABLED,
			CompanyTradeClassImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CompanyTradeClassModelImpl.ENTITY_CACHE_ENABLED,
			CompanyTradeClassModelImpl.FINDER_CACHE_ENABLED,
			CompanyTradeClassImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CompanyTradeClassModelImpl.ENTITY_CACHE_ENABLED,
			CompanyTradeClassModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public CompanyTradeClassPersistenceImpl() {
		setModelClass(CompanyTradeClass.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("priorTradeClass", "PRIOR_TRADE_CLASS");
			dbColumnNames.put("companyTradeClassSid", "COMPANY_TRADE_CLASS_SID");
			dbColumnNames.put("lastUpdatedDate", "LAST_UPDATED_DATE");
			dbColumnNames.put("priorTradeClassStartDate",
				"PRIOR_TRADE_CLASS_START_DATE");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("tradeClassEndDate", "TRADE_CLASS_END_DATE");
			dbColumnNames.put("tradeClassStartDate", "TRADE_CLASS_START_DATE");
			dbColumnNames.put("recordLockStatus", "RECORD_LOCK_STATUS");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("companyTradeClass", "COMPANY_TRADE_CLASS");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("inboundStatus", "INBOUND_STATUS");
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
	 * Caches the company trade class in the entity cache if it is enabled.
	 *
	 * @param companyTradeClass the company trade class
	 */
	@Override
	public void cacheResult(CompanyTradeClass companyTradeClass) {
		entityCache.putResult(CompanyTradeClassModelImpl.ENTITY_CACHE_ENABLED,
			CompanyTradeClassImpl.class, companyTradeClass.getPrimaryKey(),
			companyTradeClass);

		companyTradeClass.resetOriginalValues();
	}

	/**
	 * Caches the company trade classes in the entity cache if it is enabled.
	 *
	 * @param companyTradeClasses the company trade classes
	 */
	@Override
	public void cacheResult(List<CompanyTradeClass> companyTradeClasses) {
		for (CompanyTradeClass companyTradeClass : companyTradeClasses) {
			if (entityCache.getResult(
						CompanyTradeClassModelImpl.ENTITY_CACHE_ENABLED,
						CompanyTradeClassImpl.class,
						companyTradeClass.getPrimaryKey()) == null) {
				cacheResult(companyTradeClass);
			}
			else {
				companyTradeClass.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all company trade classes.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CompanyTradeClassImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the company trade class.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CompanyTradeClass companyTradeClass) {
		entityCache.removeResult(CompanyTradeClassModelImpl.ENTITY_CACHE_ENABLED,
			CompanyTradeClassImpl.class, companyTradeClass.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<CompanyTradeClass> companyTradeClasses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CompanyTradeClass companyTradeClass : companyTradeClasses) {
			entityCache.removeResult(CompanyTradeClassModelImpl.ENTITY_CACHE_ENABLED,
				CompanyTradeClassImpl.class, companyTradeClass.getPrimaryKey());
		}
	}

	/**
	 * Creates a new company trade class with the primary key. Does not add the company trade class to the database.
	 *
	 * @param companyTradeClassSid the primary key for the new company trade class
	 * @return the new company trade class
	 */
	@Override
	public CompanyTradeClass create(int companyTradeClassSid) {
		CompanyTradeClass companyTradeClass = new CompanyTradeClassImpl();

		companyTradeClass.setNew(true);
		companyTradeClass.setPrimaryKey(companyTradeClassSid);

		return companyTradeClass;
	}

	/**
	 * Removes the company trade class with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param companyTradeClassSid the primary key of the company trade class
	 * @return the company trade class that was removed
	 * @throws NoSuchCompanyTradeClassException if a company trade class with the primary key could not be found
	 */
	@Override
	public CompanyTradeClass remove(int companyTradeClassSid)
		throws NoSuchCompanyTradeClassException {
		return remove((Serializable)companyTradeClassSid);
	}

	/**
	 * Removes the company trade class with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the company trade class
	 * @return the company trade class that was removed
	 * @throws NoSuchCompanyTradeClassException if a company trade class with the primary key could not be found
	 */
	@Override
	public CompanyTradeClass remove(Serializable primaryKey)
		throws NoSuchCompanyTradeClassException {
		Session session = null;

		try {
			session = openSession();

			CompanyTradeClass companyTradeClass = (CompanyTradeClass)session.get(CompanyTradeClassImpl.class,
					primaryKey);

			if (companyTradeClass == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCompanyTradeClassException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(companyTradeClass);
		}
		catch (NoSuchCompanyTradeClassException nsee) {
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
	protected CompanyTradeClass removeImpl(CompanyTradeClass companyTradeClass) {
		companyTradeClass = toUnwrappedModel(companyTradeClass);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(companyTradeClass)) {
				companyTradeClass = (CompanyTradeClass)session.get(CompanyTradeClassImpl.class,
						companyTradeClass.getPrimaryKeyObj());
			}

			if (companyTradeClass != null) {
				session.delete(companyTradeClass);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (companyTradeClass != null) {
			clearCache(companyTradeClass);
		}

		return companyTradeClass;
	}

	@Override
	public CompanyTradeClass updateImpl(CompanyTradeClass companyTradeClass) {
		companyTradeClass = toUnwrappedModel(companyTradeClass);

		boolean isNew = companyTradeClass.isNew();

		Session session = null;

		try {
			session = openSession();

			if (companyTradeClass.isNew()) {
				session.save(companyTradeClass);

				companyTradeClass.setNew(false);
			}
			else {
				companyTradeClass = (CompanyTradeClass)session.merge(companyTradeClass);
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

		entityCache.putResult(CompanyTradeClassModelImpl.ENTITY_CACHE_ENABLED,
			CompanyTradeClassImpl.class, companyTradeClass.getPrimaryKey(),
			companyTradeClass, false);

		companyTradeClass.resetOriginalValues();

		return companyTradeClass;
	}

	protected CompanyTradeClass toUnwrappedModel(
		CompanyTradeClass companyTradeClass) {
		if (companyTradeClass instanceof CompanyTradeClassImpl) {
			return companyTradeClass;
		}

		CompanyTradeClassImpl companyTradeClassImpl = new CompanyTradeClassImpl();

		companyTradeClassImpl.setNew(companyTradeClass.isNew());
		companyTradeClassImpl.setPrimaryKey(companyTradeClass.getPrimaryKey());

		companyTradeClassImpl.setPriorTradeClass(companyTradeClass.getPriorTradeClass());
		companyTradeClassImpl.setCompanyTradeClassSid(companyTradeClass.getCompanyTradeClassSid());
		companyTradeClassImpl.setLastUpdatedDate(companyTradeClass.getLastUpdatedDate());
		companyTradeClassImpl.setPriorTradeClassStartDate(companyTradeClass.getPriorTradeClassStartDate());
		companyTradeClassImpl.setModifiedDate(companyTradeClass.getModifiedDate());
		companyTradeClassImpl.setTradeClassEndDate(companyTradeClass.getTradeClassEndDate());
		companyTradeClassImpl.setTradeClassStartDate(companyTradeClass.getTradeClassStartDate());
		companyTradeClassImpl.setRecordLockStatus(companyTradeClass.isRecordLockStatus());
		companyTradeClassImpl.setCreatedDate(companyTradeClass.getCreatedDate());
		companyTradeClassImpl.setSource(companyTradeClass.getSource());
		companyTradeClassImpl.setCreatedBy(companyTradeClass.getCreatedBy());
		companyTradeClassImpl.setBatchId(companyTradeClass.getBatchId());
		companyTradeClassImpl.setCompanyTradeClass(companyTradeClass.getCompanyTradeClass());
		companyTradeClassImpl.setModifiedBy(companyTradeClass.getModifiedBy());
		companyTradeClassImpl.setInboundStatus(companyTradeClass.getInboundStatus());
		companyTradeClassImpl.setCompanyMasterSid(companyTradeClass.getCompanyMasterSid());

		return companyTradeClassImpl;
	}

	/**
	 * Returns the company trade class with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the company trade class
	 * @return the company trade class
	 * @throws NoSuchCompanyTradeClassException if a company trade class with the primary key could not be found
	 */
	@Override
	public CompanyTradeClass findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCompanyTradeClassException {
		CompanyTradeClass companyTradeClass = fetchByPrimaryKey(primaryKey);

		if (companyTradeClass == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCompanyTradeClassException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return companyTradeClass;
	}

	/**
	 * Returns the company trade class with the primary key or throws a {@link NoSuchCompanyTradeClassException} if it could not be found.
	 *
	 * @param companyTradeClassSid the primary key of the company trade class
	 * @return the company trade class
	 * @throws NoSuchCompanyTradeClassException if a company trade class with the primary key could not be found
	 */
	@Override
	public CompanyTradeClass findByPrimaryKey(int companyTradeClassSid)
		throws NoSuchCompanyTradeClassException {
		return findByPrimaryKey((Serializable)companyTradeClassSid);
	}

	/**
	 * Returns the company trade class with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the company trade class
	 * @return the company trade class, or <code>null</code> if a company trade class with the primary key could not be found
	 */
	@Override
	public CompanyTradeClass fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(CompanyTradeClassModelImpl.ENTITY_CACHE_ENABLED,
				CompanyTradeClassImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CompanyTradeClass companyTradeClass = (CompanyTradeClass)serializable;

		if (companyTradeClass == null) {
			Session session = null;

			try {
				session = openSession();

				companyTradeClass = (CompanyTradeClass)session.get(CompanyTradeClassImpl.class,
						primaryKey);

				if (companyTradeClass != null) {
					cacheResult(companyTradeClass);
				}
				else {
					entityCache.putResult(CompanyTradeClassModelImpl.ENTITY_CACHE_ENABLED,
						CompanyTradeClassImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(CompanyTradeClassModelImpl.ENTITY_CACHE_ENABLED,
					CompanyTradeClassImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return companyTradeClass;
	}

	/**
	 * Returns the company trade class with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param companyTradeClassSid the primary key of the company trade class
	 * @return the company trade class, or <code>null</code> if a company trade class with the primary key could not be found
	 */
	@Override
	public CompanyTradeClass fetchByPrimaryKey(int companyTradeClassSid) {
		return fetchByPrimaryKey((Serializable)companyTradeClassSid);
	}

	@Override
	public Map<Serializable, CompanyTradeClass> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CompanyTradeClass> map = new HashMap<Serializable, CompanyTradeClass>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CompanyTradeClass companyTradeClass = fetchByPrimaryKey(primaryKey);

			if (companyTradeClass != null) {
				map.put(primaryKey, companyTradeClass);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(CompanyTradeClassModelImpl.ENTITY_CACHE_ENABLED,
					CompanyTradeClassImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CompanyTradeClass)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_COMPANYTRADECLASS_WHERE_PKS_IN);

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

			for (CompanyTradeClass companyTradeClass : (List<CompanyTradeClass>)q.list()) {
				map.put(companyTradeClass.getPrimaryKeyObj(), companyTradeClass);

				cacheResult(companyTradeClass);

				uncachedPrimaryKeys.remove(companyTradeClass.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(CompanyTradeClassModelImpl.ENTITY_CACHE_ENABLED,
					CompanyTradeClassImpl.class, primaryKey, nullModel);
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
	 * Returns all the company trade classes.
	 *
	 * @return the company trade classes
	 */
	@Override
	public List<CompanyTradeClass> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the company trade classes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyTradeClassModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of company trade classes
	 * @param end the upper bound of the range of company trade classes (not inclusive)
	 * @return the range of company trade classes
	 */
	@Override
	public List<CompanyTradeClass> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the company trade classes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyTradeClassModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of company trade classes
	 * @param end the upper bound of the range of company trade classes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of company trade classes
	 */
	@Override
	public List<CompanyTradeClass> findAll(int start, int end,
		OrderByComparator<CompanyTradeClass> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the company trade classes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyTradeClassModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of company trade classes
	 * @param end the upper bound of the range of company trade classes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of company trade classes
	 */
	@Override
	public List<CompanyTradeClass> findAll(int start, int end,
		OrderByComparator<CompanyTradeClass> orderByComparator,
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

		List<CompanyTradeClass> list = null;

		if (retrieveFromCache) {
			list = (List<CompanyTradeClass>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_COMPANYTRADECLASS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_COMPANYTRADECLASS;

				if (pagination) {
					sql = sql.concat(CompanyTradeClassModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CompanyTradeClass>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CompanyTradeClass>)QueryUtil.list(q,
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
	 * Removes all the company trade classes from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CompanyTradeClass companyTradeClass : findAll()) {
			remove(companyTradeClass);
		}
	}

	/**
	 * Returns the number of company trade classes.
	 *
	 * @return the number of company trade classes
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_COMPANYTRADECLASS);

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
		return CompanyTradeClassModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the company trade class persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(CompanyTradeClassImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_COMPANYTRADECLASS = "SELECT companyTradeClass FROM CompanyTradeClass companyTradeClass";
	private static final String _SQL_SELECT_COMPANYTRADECLASS_WHERE_PKS_IN = "SELECT companyTradeClass FROM CompanyTradeClass companyTradeClass WHERE COMPANY_TRADE_CLASS_SID IN (";
	private static final String _SQL_COUNT_COMPANYTRADECLASS = "SELECT COUNT(companyTradeClass) FROM CompanyTradeClass companyTradeClass";
	private static final String _ORDER_BY_ENTITY_ALIAS = "companyTradeClass.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CompanyTradeClass exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(CompanyTradeClassPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"priorTradeClass", "companyTradeClassSid", "lastUpdatedDate",
				"priorTradeClassStartDate", "modifiedDate", "tradeClassEndDate",
				"tradeClassStartDate", "recordLockStatus", "createdDate",
				"source", "createdBy", "batchId", "companyTradeClass",
				"modifiedBy", "inboundStatus", "companyMasterSid"
			});
}