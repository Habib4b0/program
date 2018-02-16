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

import com.stpl.app.parttwo.exception.NoSuchCffMasterException;
import com.stpl.app.parttwo.model.CffMaster;
import com.stpl.app.parttwo.model.impl.CffMasterImpl;
import com.stpl.app.parttwo.model.impl.CffMasterModelImpl;
import com.stpl.app.parttwo.service.persistence.CffMasterPersistence;

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
 * The persistence implementation for the cff master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CffMasterPersistence
 * @see com.stpl.app.parttwo.service.persistence.CffMasterUtil
 * @generated
 */
@ProviderType
public class CffMasterPersistenceImpl extends BasePersistenceImpl<CffMaster>
	implements CffMasterPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CffMasterUtil} to access the cff master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CffMasterImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CffMasterModelImpl.ENTITY_CACHE_ENABLED,
			CffMasterModelImpl.FINDER_CACHE_ENABLED, CffMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CffMasterModelImpl.ENTITY_CACHE_ENABLED,
			CffMasterModelImpl.FINDER_CACHE_ENABLED, CffMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CffMasterModelImpl.ENTITY_CACHE_ENABLED,
			CffMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public CffMasterPersistenceImpl() {
		setModelClass(CffMaster.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("productHierarchyLevel", "PRODUCT_HIERARCHY_LEVEL");
			dbColumnNames.put("activeFromDate", "ACTIVE_FROM_DATE");
			dbColumnNames.put("cffType", "CFF_TYPE");
			dbColumnNames.put("cffOfficial", "CFF_OFFICIAL");
			dbColumnNames.put("cffMasterSid", "CFF_MASTER_SID");
			dbColumnNames.put("productHierVersionNo", "PRODUCT_HIER_VERSION_NO");
			dbColumnNames.put("activeToDate", "ACTIVE_TO_DATE");
			dbColumnNames.put("customerHierVersionNo",
				"CUSTOMER_HIER_VERSION_NO");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("customerHierarchyLevel",
				"CUSTOMER_HIERARCHY_LEVEL");
			dbColumnNames.put("productHierarchySid", "PRODUCT_HIERARCHY_SID");
			dbColumnNames.put("cffName", "CFF_NAME");
			dbColumnNames.put("customerHierarchyInnerLevel",
				"CUSTOMER_HIERARCHY_INNER_LEVEL");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("customerHierarchySid", "CUSTOMER_HIERARCHY_SID");
			dbColumnNames.put("companyGroupSid", "COMPANY_GROUP_SID");
			dbColumnNames.put("prodRelationshipBuilderSid",
				"PROD_RELATIONSHIP_BUILDER_SID");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("inboundStatus", "INBOUND_STATUS");
			dbColumnNames.put("productHierarchyInnerLevel",
				"PRODUCT_HIERARCHY_INNER_LEVEL");
			dbColumnNames.put("itemGroupSid", "ITEM_GROUP_SID");
			dbColumnNames.put("custRelationshipBuilderSid",
				"CUST_RELATIONSHIP_BUILDER_SID");
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
	 * Caches the cff master in the entity cache if it is enabled.
	 *
	 * @param cffMaster the cff master
	 */
	@Override
	public void cacheResult(CffMaster cffMaster) {
		entityCache.putResult(CffMasterModelImpl.ENTITY_CACHE_ENABLED,
			CffMasterImpl.class, cffMaster.getPrimaryKey(), cffMaster);

		cffMaster.resetOriginalValues();
	}

	/**
	 * Caches the cff masters in the entity cache if it is enabled.
	 *
	 * @param cffMasters the cff masters
	 */
	@Override
	public void cacheResult(List<CffMaster> cffMasters) {
		for (CffMaster cffMaster : cffMasters) {
			if (entityCache.getResult(CffMasterModelImpl.ENTITY_CACHE_ENABLED,
						CffMasterImpl.class, cffMaster.getPrimaryKey()) == null) {
				cacheResult(cffMaster);
			}
			else {
				cffMaster.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all cff masters.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CffMasterImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the cff master.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CffMaster cffMaster) {
		entityCache.removeResult(CffMasterModelImpl.ENTITY_CACHE_ENABLED,
			CffMasterImpl.class, cffMaster.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<CffMaster> cffMasters) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CffMaster cffMaster : cffMasters) {
			entityCache.removeResult(CffMasterModelImpl.ENTITY_CACHE_ENABLED,
				CffMasterImpl.class, cffMaster.getPrimaryKey());
		}
	}

	/**
	 * Creates a new cff master with the primary key. Does not add the cff master to the database.
	 *
	 * @param cffMasterSid the primary key for the new cff master
	 * @return the new cff master
	 */
	@Override
	public CffMaster create(int cffMasterSid) {
		CffMaster cffMaster = new CffMasterImpl();

		cffMaster.setNew(true);
		cffMaster.setPrimaryKey(cffMasterSid);

		return cffMaster;
	}

	/**
	 * Removes the cff master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param cffMasterSid the primary key of the cff master
	 * @return the cff master that was removed
	 * @throws NoSuchCffMasterException if a cff master with the primary key could not be found
	 */
	@Override
	public CffMaster remove(int cffMasterSid) throws NoSuchCffMasterException {
		return remove((Serializable)cffMasterSid);
	}

	/**
	 * Removes the cff master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the cff master
	 * @return the cff master that was removed
	 * @throws NoSuchCffMasterException if a cff master with the primary key could not be found
	 */
	@Override
	public CffMaster remove(Serializable primaryKey)
		throws NoSuchCffMasterException {
		Session session = null;

		try {
			session = openSession();

			CffMaster cffMaster = (CffMaster)session.get(CffMasterImpl.class,
					primaryKey);

			if (cffMaster == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCffMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(cffMaster);
		}
		catch (NoSuchCffMasterException nsee) {
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
	protected CffMaster removeImpl(CffMaster cffMaster) {
		cffMaster = toUnwrappedModel(cffMaster);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(cffMaster)) {
				cffMaster = (CffMaster)session.get(CffMasterImpl.class,
						cffMaster.getPrimaryKeyObj());
			}

			if (cffMaster != null) {
				session.delete(cffMaster);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (cffMaster != null) {
			clearCache(cffMaster);
		}

		return cffMaster;
	}

	@Override
	public CffMaster updateImpl(CffMaster cffMaster) {
		cffMaster = toUnwrappedModel(cffMaster);

		boolean isNew = cffMaster.isNew();

		Session session = null;

		try {
			session = openSession();

			if (cffMaster.isNew()) {
				session.save(cffMaster);

				cffMaster.setNew(false);
			}
			else {
				cffMaster = (CffMaster)session.merge(cffMaster);
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

		entityCache.putResult(CffMasterModelImpl.ENTITY_CACHE_ENABLED,
			CffMasterImpl.class, cffMaster.getPrimaryKey(), cffMaster, false);

		cffMaster.resetOriginalValues();

		return cffMaster;
	}

	protected CffMaster toUnwrappedModel(CffMaster cffMaster) {
		if (cffMaster instanceof CffMasterImpl) {
			return cffMaster;
		}

		CffMasterImpl cffMasterImpl = new CffMasterImpl();

		cffMasterImpl.setNew(cffMaster.isNew());
		cffMasterImpl.setPrimaryKey(cffMaster.getPrimaryKey());

		cffMasterImpl.setProductHierarchyLevel(cffMaster.getProductHierarchyLevel());
		cffMasterImpl.setActiveFromDate(cffMaster.getActiveFromDate());
		cffMasterImpl.setCffType(cffMaster.getCffType());
		cffMasterImpl.setCffOfficial(cffMaster.isCffOfficial());
		cffMasterImpl.setCffMasterSid(cffMaster.getCffMasterSid());
		cffMasterImpl.setProductHierVersionNo(cffMaster.getProductHierVersionNo());
		cffMasterImpl.setActiveToDate(cffMaster.getActiveToDate());
		cffMasterImpl.setCustomerHierVersionNo(cffMaster.getCustomerHierVersionNo());
		cffMasterImpl.setModifiedDate(cffMaster.getModifiedDate());
		cffMasterImpl.setCustomerHierarchyLevel(cffMaster.getCustomerHierarchyLevel());
		cffMasterImpl.setProductHierarchySid(cffMaster.getProductHierarchySid());
		cffMasterImpl.setCffName(cffMaster.getCffName());
		cffMasterImpl.setCustomerHierarchyInnerLevel(cffMaster.getCustomerHierarchyInnerLevel());
		cffMasterImpl.setCreatedDate(cffMaster.getCreatedDate());
		cffMasterImpl.setCreatedBy(cffMaster.getCreatedBy());
		cffMasterImpl.setCustomerHierarchySid(cffMaster.getCustomerHierarchySid());
		cffMasterImpl.setCompanyGroupSid(cffMaster.getCompanyGroupSid());
		cffMasterImpl.setProdRelationshipBuilderSid(cffMaster.getProdRelationshipBuilderSid());
		cffMasterImpl.setModifiedBy(cffMaster.getModifiedBy());
		cffMasterImpl.setInboundStatus(cffMaster.getInboundStatus());
		cffMasterImpl.setProductHierarchyInnerLevel(cffMaster.getProductHierarchyInnerLevel());
		cffMasterImpl.setItemGroupSid(cffMaster.getItemGroupSid());
		cffMasterImpl.setCustRelationshipBuilderSid(cffMaster.getCustRelationshipBuilderSid());
		cffMasterImpl.setCompanyMasterSid(cffMaster.getCompanyMasterSid());

		return cffMasterImpl;
	}

	/**
	 * Returns the cff master with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the cff master
	 * @return the cff master
	 * @throws NoSuchCffMasterException if a cff master with the primary key could not be found
	 */
	@Override
	public CffMaster findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCffMasterException {
		CffMaster cffMaster = fetchByPrimaryKey(primaryKey);

		if (cffMaster == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCffMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return cffMaster;
	}

	/**
	 * Returns the cff master with the primary key or throws a {@link NoSuchCffMasterException} if it could not be found.
	 *
	 * @param cffMasterSid the primary key of the cff master
	 * @return the cff master
	 * @throws NoSuchCffMasterException if a cff master with the primary key could not be found
	 */
	@Override
	public CffMaster findByPrimaryKey(int cffMasterSid)
		throws NoSuchCffMasterException {
		return findByPrimaryKey((Serializable)cffMasterSid);
	}

	/**
	 * Returns the cff master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the cff master
	 * @return the cff master, or <code>null</code> if a cff master with the primary key could not be found
	 */
	@Override
	public CffMaster fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(CffMasterModelImpl.ENTITY_CACHE_ENABLED,
				CffMasterImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CffMaster cffMaster = (CffMaster)serializable;

		if (cffMaster == null) {
			Session session = null;

			try {
				session = openSession();

				cffMaster = (CffMaster)session.get(CffMasterImpl.class,
						primaryKey);

				if (cffMaster != null) {
					cacheResult(cffMaster);
				}
				else {
					entityCache.putResult(CffMasterModelImpl.ENTITY_CACHE_ENABLED,
						CffMasterImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(CffMasterModelImpl.ENTITY_CACHE_ENABLED,
					CffMasterImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return cffMaster;
	}

	/**
	 * Returns the cff master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param cffMasterSid the primary key of the cff master
	 * @return the cff master, or <code>null</code> if a cff master with the primary key could not be found
	 */
	@Override
	public CffMaster fetchByPrimaryKey(int cffMasterSid) {
		return fetchByPrimaryKey((Serializable)cffMasterSid);
	}

	@Override
	public Map<Serializable, CffMaster> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CffMaster> map = new HashMap<Serializable, CffMaster>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CffMaster cffMaster = fetchByPrimaryKey(primaryKey);

			if (cffMaster != null) {
				map.put(primaryKey, cffMaster);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(CffMasterModelImpl.ENTITY_CACHE_ENABLED,
					CffMasterImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CffMaster)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_CFFMASTER_WHERE_PKS_IN);

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

			for (CffMaster cffMaster : (List<CffMaster>)q.list()) {
				map.put(cffMaster.getPrimaryKeyObj(), cffMaster);

				cacheResult(cffMaster);

				uncachedPrimaryKeys.remove(cffMaster.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(CffMasterModelImpl.ENTITY_CACHE_ENABLED,
					CffMasterImpl.class, primaryKey, nullModel);
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
	 * Returns all the cff masters.
	 *
	 * @return the cff masters
	 */
	@Override
	public List<CffMaster> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cff masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cff masters
	 * @param end the upper bound of the range of cff masters (not inclusive)
	 * @return the range of cff masters
	 */
	@Override
	public List<CffMaster> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the cff masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cff masters
	 * @param end the upper bound of the range of cff masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of cff masters
	 */
	@Override
	public List<CffMaster> findAll(int start, int end,
		OrderByComparator<CffMaster> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cff masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cff masters
	 * @param end the upper bound of the range of cff masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of cff masters
	 */
	@Override
	public List<CffMaster> findAll(int start, int end,
		OrderByComparator<CffMaster> orderByComparator,
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

		List<CffMaster> list = null;

		if (retrieveFromCache) {
			list = (List<CffMaster>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CFFMASTER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CFFMASTER;

				if (pagination) {
					sql = sql.concat(CffMasterModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CffMaster>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CffMaster>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the cff masters from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CffMaster cffMaster : findAll()) {
			remove(cffMaster);
		}
	}

	/**
	 * Returns the number of cff masters.
	 *
	 * @return the number of cff masters
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CFFMASTER);

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
		return CffMasterModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the cff master persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(CffMasterImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_CFFMASTER = "SELECT cffMaster FROM CffMaster cffMaster";
	private static final String _SQL_SELECT_CFFMASTER_WHERE_PKS_IN = "SELECT cffMaster FROM CffMaster cffMaster WHERE CFF_MASTER_SID IN (";
	private static final String _SQL_COUNT_CFFMASTER = "SELECT COUNT(cffMaster) FROM CffMaster cffMaster";
	private static final String _ORDER_BY_ENTITY_ALIAS = "cffMaster.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CffMaster exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(CffMasterPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"productHierarchyLevel", "activeFromDate", "cffType",
				"cffOfficial", "cffMasterSid", "productHierVersionNo",
				"activeToDate", "customerHierVersionNo", "modifiedDate",
				"customerHierarchyLevel", "productHierarchySid", "cffName",
				"customerHierarchyInnerLevel", "createdDate", "createdBy",
				"customerHierarchySid", "companyGroupSid",
				"prodRelationshipBuilderSid", "modifiedBy", "inboundStatus",
				"productHierarchyInnerLevel", "itemGroupSid",
				"custRelationshipBuilderSid", "companyMasterSid"
			});
}