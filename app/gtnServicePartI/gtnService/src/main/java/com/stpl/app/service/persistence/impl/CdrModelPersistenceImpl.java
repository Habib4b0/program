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

import com.stpl.app.exception.NoSuchCdrModelException;
import com.stpl.app.model.CdrModel;
import com.stpl.app.model.impl.CdrModelImpl;
import com.stpl.app.model.impl.CdrModelModelImpl;
import com.stpl.app.service.persistence.CdrModelPersistence;

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
 * The persistence implementation for the cdr model service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CdrModelPersistence
 * @see com.stpl.app.service.persistence.CdrModelUtil
 * @generated
 */
@ProviderType
public class CdrModelPersistenceImpl extends BasePersistenceImpl<CdrModel>
	implements CdrModelPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CdrModelUtil} to access the cdr model persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CdrModelImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CdrModelModelImpl.ENTITY_CACHE_ENABLED,
			CdrModelModelImpl.FINDER_CACHE_ENABLED, CdrModelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CdrModelModelImpl.ENTITY_CACHE_ENABLED,
			CdrModelModelImpl.FINDER_CACHE_ENABLED, CdrModelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CdrModelModelImpl.ENTITY_CACHE_ENABLED,
			CdrModelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public CdrModelPersistenceImpl() {
		setModelClass(CdrModel.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("ruleCategory", "RULE_CATEGORY");
			dbColumnNames.put("ruleType", "RULE_TYPE");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("internalNotes", "INTERNAL_NOTES");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("ruleName", "RULE_NAME");
			dbColumnNames.put("cdrModelSid", "CDR_MODEL_SID");
			dbColumnNames.put("ruleNo", "RULE_NO");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the cdr model in the entity cache if it is enabled.
	 *
	 * @param cdrModel the cdr model
	 */
	@Override
	public void cacheResult(CdrModel cdrModel) {
		entityCache.putResult(CdrModelModelImpl.ENTITY_CACHE_ENABLED,
			CdrModelImpl.class, cdrModel.getPrimaryKey(), cdrModel);

		cdrModel.resetOriginalValues();
	}

	/**
	 * Caches the cdr models in the entity cache if it is enabled.
	 *
	 * @param cdrModels the cdr models
	 */
	@Override
	public void cacheResult(List<CdrModel> cdrModels) {
		for (CdrModel cdrModel : cdrModels) {
			if (entityCache.getResult(CdrModelModelImpl.ENTITY_CACHE_ENABLED,
						CdrModelImpl.class, cdrModel.getPrimaryKey()) == null) {
				cacheResult(cdrModel);
			}
			else {
				cdrModel.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all cdr models.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CdrModelImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the cdr model.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CdrModel cdrModel) {
		entityCache.removeResult(CdrModelModelImpl.ENTITY_CACHE_ENABLED,
			CdrModelImpl.class, cdrModel.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<CdrModel> cdrModels) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CdrModel cdrModel : cdrModels) {
			entityCache.removeResult(CdrModelModelImpl.ENTITY_CACHE_ENABLED,
				CdrModelImpl.class, cdrModel.getPrimaryKey());
		}
	}

	/**
	 * Creates a new cdr model with the primary key. Does not add the cdr model to the database.
	 *
	 * @param cdrModelSid the primary key for the new cdr model
	 * @return the new cdr model
	 */
	@Override
	public CdrModel create(int cdrModelSid) {
		CdrModel cdrModel = new CdrModelImpl();

		cdrModel.setNew(true);
		cdrModel.setPrimaryKey(cdrModelSid);

		return cdrModel;
	}

	/**
	 * Removes the cdr model with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param cdrModelSid the primary key of the cdr model
	 * @return the cdr model that was removed
	 * @throws NoSuchCdrModelException if a cdr model with the primary key could not be found
	 */
	@Override
	public CdrModel remove(int cdrModelSid) throws NoSuchCdrModelException {
		return remove((Serializable)cdrModelSid);
	}

	/**
	 * Removes the cdr model with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the cdr model
	 * @return the cdr model that was removed
	 * @throws NoSuchCdrModelException if a cdr model with the primary key could not be found
	 */
	@Override
	public CdrModel remove(Serializable primaryKey)
		throws NoSuchCdrModelException {
		Session session = null;

		try {
			session = openSession();

			CdrModel cdrModel = (CdrModel)session.get(CdrModelImpl.class,
					primaryKey);

			if (cdrModel == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCdrModelException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(cdrModel);
		}
		catch (NoSuchCdrModelException nsee) {
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
	protected CdrModel removeImpl(CdrModel cdrModel) {
		cdrModel = toUnwrappedModel(cdrModel);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(cdrModel)) {
				cdrModel = (CdrModel)session.get(CdrModelImpl.class,
						cdrModel.getPrimaryKeyObj());
			}

			if (cdrModel != null) {
				session.delete(cdrModel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (cdrModel != null) {
			clearCache(cdrModel);
		}

		return cdrModel;
	}

	@Override
	public CdrModel updateImpl(CdrModel cdrModel) {
		cdrModel = toUnwrappedModel(cdrModel);

		boolean isNew = cdrModel.isNew();

		Session session = null;

		try {
			session = openSession();

			if (cdrModel.isNew()) {
				session.save(cdrModel);

				cdrModel.setNew(false);
			}
			else {
				cdrModel = (CdrModel)session.merge(cdrModel);
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

		entityCache.putResult(CdrModelModelImpl.ENTITY_CACHE_ENABLED,
			CdrModelImpl.class, cdrModel.getPrimaryKey(), cdrModel, false);

		cdrModel.resetOriginalValues();

		return cdrModel;
	}

	protected CdrModel toUnwrappedModel(CdrModel cdrModel) {
		if (cdrModel instanceof CdrModelImpl) {
			return cdrModel;
		}

		CdrModelImpl cdrModelImpl = new CdrModelImpl();

		cdrModelImpl.setNew(cdrModel.isNew());
		cdrModelImpl.setPrimaryKey(cdrModel.getPrimaryKey());

		cdrModelImpl.setCreatedBy(cdrModel.getCreatedBy());
		cdrModelImpl.setRuleCategory(cdrModel.getRuleCategory());
		cdrModelImpl.setRuleType(cdrModel.getRuleType());
		cdrModelImpl.setModifiedBy(cdrModel.getModifiedBy());
		cdrModelImpl.setInternalNotes(cdrModel.getInternalNotes());
		cdrModelImpl.setCreatedDate(cdrModel.getCreatedDate());
		cdrModelImpl.setRuleName(cdrModel.getRuleName());
		cdrModelImpl.setCdrModelSid(cdrModel.getCdrModelSid());
		cdrModelImpl.setRuleNo(cdrModel.getRuleNo());
		cdrModelImpl.setModifiedDate(cdrModel.getModifiedDate());

		return cdrModelImpl;
	}

	/**
	 * Returns the cdr model with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the cdr model
	 * @return the cdr model
	 * @throws NoSuchCdrModelException if a cdr model with the primary key could not be found
	 */
	@Override
	public CdrModel findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCdrModelException {
		CdrModel cdrModel = fetchByPrimaryKey(primaryKey);

		if (cdrModel == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCdrModelException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return cdrModel;
	}

	/**
	 * Returns the cdr model with the primary key or throws a {@link NoSuchCdrModelException} if it could not be found.
	 *
	 * @param cdrModelSid the primary key of the cdr model
	 * @return the cdr model
	 * @throws NoSuchCdrModelException if a cdr model with the primary key could not be found
	 */
	@Override
	public CdrModel findByPrimaryKey(int cdrModelSid)
		throws NoSuchCdrModelException {
		return findByPrimaryKey((Serializable)cdrModelSid);
	}

	/**
	 * Returns the cdr model with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the cdr model
	 * @return the cdr model, or <code>null</code> if a cdr model with the primary key could not be found
	 */
	@Override
	public CdrModel fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(CdrModelModelImpl.ENTITY_CACHE_ENABLED,
				CdrModelImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CdrModel cdrModel = (CdrModel)serializable;

		if (cdrModel == null) {
			Session session = null;

			try {
				session = openSession();

				cdrModel = (CdrModel)session.get(CdrModelImpl.class, primaryKey);

				if (cdrModel != null) {
					cacheResult(cdrModel);
				}
				else {
					entityCache.putResult(CdrModelModelImpl.ENTITY_CACHE_ENABLED,
						CdrModelImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(CdrModelModelImpl.ENTITY_CACHE_ENABLED,
					CdrModelImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return cdrModel;
	}

	/**
	 * Returns the cdr model with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param cdrModelSid the primary key of the cdr model
	 * @return the cdr model, or <code>null</code> if a cdr model with the primary key could not be found
	 */
	@Override
	public CdrModel fetchByPrimaryKey(int cdrModelSid) {
		return fetchByPrimaryKey((Serializable)cdrModelSid);
	}

	@Override
	public Map<Serializable, CdrModel> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CdrModel> map = new HashMap<Serializable, CdrModel>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CdrModel cdrModel = fetchByPrimaryKey(primaryKey);

			if (cdrModel != null) {
				map.put(primaryKey, cdrModel);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(CdrModelModelImpl.ENTITY_CACHE_ENABLED,
					CdrModelImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CdrModel)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_CDRMODEL_WHERE_PKS_IN);

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

			for (CdrModel cdrModel : (List<CdrModel>)q.list()) {
				map.put(cdrModel.getPrimaryKeyObj(), cdrModel);

				cacheResult(cdrModel);

				uncachedPrimaryKeys.remove(cdrModel.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(CdrModelModelImpl.ENTITY_CACHE_ENABLED,
					CdrModelImpl.class, primaryKey, nullModel);
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
	 * Returns all the cdr models.
	 *
	 * @return the cdr models
	 */
	@Override
	public List<CdrModel> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cdr models.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CdrModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cdr models
	 * @param end the upper bound of the range of cdr models (not inclusive)
	 * @return the range of cdr models
	 */
	@Override
	public List<CdrModel> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the cdr models.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CdrModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cdr models
	 * @param end the upper bound of the range of cdr models (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of cdr models
	 */
	@Override
	public List<CdrModel> findAll(int start, int end,
		OrderByComparator<CdrModel> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cdr models.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CdrModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cdr models
	 * @param end the upper bound of the range of cdr models (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of cdr models
	 */
	@Override
	public List<CdrModel> findAll(int start, int end,
		OrderByComparator<CdrModel> orderByComparator, boolean retrieveFromCache) {
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

		List<CdrModel> list = null;

		if (retrieveFromCache) {
			list = (List<CdrModel>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CDRMODEL);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CDRMODEL;

				if (pagination) {
					sql = sql.concat(CdrModelModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CdrModel>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CdrModel>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the cdr models from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CdrModel cdrModel : findAll()) {
			remove(cdrModel);
		}
	}

	/**
	 * Returns the number of cdr models.
	 *
	 * @return the number of cdr models
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CDRMODEL);

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
		return CdrModelModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the cdr model persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(CdrModelImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_CDRMODEL = "SELECT cdrModel FROM CdrModel cdrModel";
	private static final String _SQL_SELECT_CDRMODEL_WHERE_PKS_IN = "SELECT cdrModel FROM CdrModel cdrModel WHERE CDR_MODEL_SID IN (";
	private static final String _SQL_COUNT_CDRMODEL = "SELECT COUNT(cdrModel) FROM CdrModel cdrModel";
	private static final String _ORDER_BY_ENTITY_ALIAS = "cdrModel.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CdrModel exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(CdrModelPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"createdBy", "ruleCategory", "ruleType", "modifiedBy",
				"internalNotes", "createdDate", "ruleName", "cdrModelSid",
				"ruleNo", "modifiedDate"
			});
}