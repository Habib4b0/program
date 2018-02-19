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

import com.stpl.app.parttwo.exception.NoSuchStAccClosureDetailsException;
import com.stpl.app.parttwo.model.StAccClosureDetails;
import com.stpl.app.parttwo.model.impl.StAccClosureDetailsImpl;
import com.stpl.app.parttwo.model.impl.StAccClosureDetailsModelImpl;
import com.stpl.app.parttwo.service.persistence.StAccClosureDetailsPersistence;

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
 * The persistence implementation for the st acc closure details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StAccClosureDetailsPersistence
 * @see com.stpl.app.parttwo.service.persistence.StAccClosureDetailsUtil
 * @generated
 */
@ProviderType
public class StAccClosureDetailsPersistenceImpl extends BasePersistenceImpl<StAccClosureDetails>
	implements StAccClosureDetailsPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link StAccClosureDetailsUtil} to access the st acc closure details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = StAccClosureDetailsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(StAccClosureDetailsModelImpl.ENTITY_CACHE_ENABLED,
			StAccClosureDetailsModelImpl.FINDER_CACHE_ENABLED,
			StAccClosureDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(StAccClosureDetailsModelImpl.ENTITY_CACHE_ENABLED,
			StAccClosureDetailsModelImpl.FINDER_CACHE_ENABLED,
			StAccClosureDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(StAccClosureDetailsModelImpl.ENTITY_CACHE_ENABLED,
			StAccClosureDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public StAccClosureDetailsPersistenceImpl() {
		setModelClass(StAccClosureDetails.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("lastModifiedDate", "LAST_MODIFIED_DATE");
			dbColumnNames.put("checkRecord", "CHECK_RECORD");
			dbColumnNames.put("contractName", "CONTRACT_NAME");
			dbColumnNames.put("userId", "USER_ID");
			dbColumnNames.put("itemMasterSid", "ITEM_MASTER_SID");
			dbColumnNames.put("moduleName", "MODULE_NAME");
			dbColumnNames.put("companyName", "COMPANY_NAME");
			dbColumnNames.put("brandName", "BRAND_NAME");
			dbColumnNames.put("companyCostCenter", "COMPANY_COST_CENTER");
			dbColumnNames.put("companyNo", "COMPANY_NO");
			dbColumnNames.put("contractMasterSid", "CONTRACT_MASTER_SID");
			dbColumnNames.put("sessionId", "SESSION_ID");
			dbColumnNames.put("ccpDetailsSid", "CCP_DETAILS_SID");
			dbColumnNames.put("itemName", "ITEM_NAME");
			dbColumnNames.put("accClosureMasterSid", "ACC_CLOSURE_MASTER_SID");
			dbColumnNames.put("rsModelSid", "RS_MODEL_SID");
			dbColumnNames.put("contractNo", "CONTRACT_NO");
			dbColumnNames.put("companyMasterSid", "COMPANY_MASTER_SID");
			dbColumnNames.put("ndc8", "NDC8");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the st acc closure details in the entity cache if it is enabled.
	 *
	 * @param stAccClosureDetails the st acc closure details
	 */
	@Override
	public void cacheResult(StAccClosureDetails stAccClosureDetails) {
		entityCache.putResult(StAccClosureDetailsModelImpl.ENTITY_CACHE_ENABLED,
			StAccClosureDetailsImpl.class, stAccClosureDetails.getPrimaryKey(),
			stAccClosureDetails);

		stAccClosureDetails.resetOriginalValues();
	}

	/**
	 * Caches the st acc closure detailses in the entity cache if it is enabled.
	 *
	 * @param stAccClosureDetailses the st acc closure detailses
	 */
	@Override
	public void cacheResult(List<StAccClosureDetails> stAccClosureDetailses) {
		for (StAccClosureDetails stAccClosureDetails : stAccClosureDetailses) {
			if (entityCache.getResult(
						StAccClosureDetailsModelImpl.ENTITY_CACHE_ENABLED,
						StAccClosureDetailsImpl.class,
						stAccClosureDetails.getPrimaryKey()) == null) {
				cacheResult(stAccClosureDetails);
			}
			else {
				stAccClosureDetails.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all st acc closure detailses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(StAccClosureDetailsImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the st acc closure details.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(StAccClosureDetails stAccClosureDetails) {
		entityCache.removeResult(StAccClosureDetailsModelImpl.ENTITY_CACHE_ENABLED,
			StAccClosureDetailsImpl.class, stAccClosureDetails.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<StAccClosureDetails> stAccClosureDetailses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (StAccClosureDetails stAccClosureDetails : stAccClosureDetailses) {
			entityCache.removeResult(StAccClosureDetailsModelImpl.ENTITY_CACHE_ENABLED,
				StAccClosureDetailsImpl.class,
				stAccClosureDetails.getPrimaryKey());
		}
	}

	/**
	 * Creates a new st acc closure details with the primary key. Does not add the st acc closure details to the database.
	 *
	 * @param accClosureMasterSid the primary key for the new st acc closure details
	 * @return the new st acc closure details
	 */
	@Override
	public StAccClosureDetails create(int accClosureMasterSid) {
		StAccClosureDetails stAccClosureDetails = new StAccClosureDetailsImpl();

		stAccClosureDetails.setNew(true);
		stAccClosureDetails.setPrimaryKey(accClosureMasterSid);

		return stAccClosureDetails;
	}

	/**
	 * Removes the st acc closure details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param accClosureMasterSid the primary key of the st acc closure details
	 * @return the st acc closure details that was removed
	 * @throws NoSuchStAccClosureDetailsException if a st acc closure details with the primary key could not be found
	 */
	@Override
	public StAccClosureDetails remove(int accClosureMasterSid)
		throws NoSuchStAccClosureDetailsException {
		return remove((Serializable)accClosureMasterSid);
	}

	/**
	 * Removes the st acc closure details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the st acc closure details
	 * @return the st acc closure details that was removed
	 * @throws NoSuchStAccClosureDetailsException if a st acc closure details with the primary key could not be found
	 */
	@Override
	public StAccClosureDetails remove(Serializable primaryKey)
		throws NoSuchStAccClosureDetailsException {
		Session session = null;

		try {
			session = openSession();

			StAccClosureDetails stAccClosureDetails = (StAccClosureDetails)session.get(StAccClosureDetailsImpl.class,
					primaryKey);

			if (stAccClosureDetails == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchStAccClosureDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(stAccClosureDetails);
		}
		catch (NoSuchStAccClosureDetailsException nsee) {
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
	protected StAccClosureDetails removeImpl(
		StAccClosureDetails stAccClosureDetails) {
		stAccClosureDetails = toUnwrappedModel(stAccClosureDetails);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(stAccClosureDetails)) {
				stAccClosureDetails = (StAccClosureDetails)session.get(StAccClosureDetailsImpl.class,
						stAccClosureDetails.getPrimaryKeyObj());
			}

			if (stAccClosureDetails != null) {
				session.delete(stAccClosureDetails);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (stAccClosureDetails != null) {
			clearCache(stAccClosureDetails);
		}

		return stAccClosureDetails;
	}

	@Override
	public StAccClosureDetails updateImpl(
		StAccClosureDetails stAccClosureDetails) {
		stAccClosureDetails = toUnwrappedModel(stAccClosureDetails);

		boolean isNew = stAccClosureDetails.isNew();

		Session session = null;

		try {
			session = openSession();

			if (stAccClosureDetails.isNew()) {
				session.save(stAccClosureDetails);

				stAccClosureDetails.setNew(false);
			}
			else {
				stAccClosureDetails = (StAccClosureDetails)session.merge(stAccClosureDetails);
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

		entityCache.putResult(StAccClosureDetailsModelImpl.ENTITY_CACHE_ENABLED,
			StAccClosureDetailsImpl.class, stAccClosureDetails.getPrimaryKey(),
			stAccClosureDetails, false);

		stAccClosureDetails.resetOriginalValues();

		return stAccClosureDetails;
	}

	protected StAccClosureDetails toUnwrappedModel(
		StAccClosureDetails stAccClosureDetails) {
		if (stAccClosureDetails instanceof StAccClosureDetailsImpl) {
			return stAccClosureDetails;
		}

		StAccClosureDetailsImpl stAccClosureDetailsImpl = new StAccClosureDetailsImpl();

		stAccClosureDetailsImpl.setNew(stAccClosureDetails.isNew());
		stAccClosureDetailsImpl.setPrimaryKey(stAccClosureDetails.getPrimaryKey());

		stAccClosureDetailsImpl.setLastModifiedDate(stAccClosureDetails.getLastModifiedDate());
		stAccClosureDetailsImpl.setCheckRecord(stAccClosureDetails.isCheckRecord());
		stAccClosureDetailsImpl.setContractName(stAccClosureDetails.getContractName());
		stAccClosureDetailsImpl.setUserId(stAccClosureDetails.getUserId());
		stAccClosureDetailsImpl.setItemMasterSid(stAccClosureDetails.getItemMasterSid());
		stAccClosureDetailsImpl.setModuleName(stAccClosureDetails.getModuleName());
		stAccClosureDetailsImpl.setCompanyName(stAccClosureDetails.getCompanyName());
		stAccClosureDetailsImpl.setBrandName(stAccClosureDetails.getBrandName());
		stAccClosureDetailsImpl.setCompanyCostCenter(stAccClosureDetails.getCompanyCostCenter());
		stAccClosureDetailsImpl.setCompanyNo(stAccClosureDetails.getCompanyNo());
		stAccClosureDetailsImpl.setContractMasterSid(stAccClosureDetails.getContractMasterSid());
		stAccClosureDetailsImpl.setSessionId(stAccClosureDetails.getSessionId());
		stAccClosureDetailsImpl.setCcpDetailsSid(stAccClosureDetails.getCcpDetailsSid());
		stAccClosureDetailsImpl.setItemName(stAccClosureDetails.getItemName());
		stAccClosureDetailsImpl.setAccClosureMasterSid(stAccClosureDetails.getAccClosureMasterSid());
		stAccClosureDetailsImpl.setRsModelSid(stAccClosureDetails.getRsModelSid());
		stAccClosureDetailsImpl.setContractNo(stAccClosureDetails.getContractNo());
		stAccClosureDetailsImpl.setCompanyMasterSid(stAccClosureDetails.getCompanyMasterSid());
		stAccClosureDetailsImpl.setNdc8(stAccClosureDetails.getNdc8());

		return stAccClosureDetailsImpl;
	}

	/**
	 * Returns the st acc closure details with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the st acc closure details
	 * @return the st acc closure details
	 * @throws NoSuchStAccClosureDetailsException if a st acc closure details with the primary key could not be found
	 */
	@Override
	public StAccClosureDetails findByPrimaryKey(Serializable primaryKey)
		throws NoSuchStAccClosureDetailsException {
		StAccClosureDetails stAccClosureDetails = fetchByPrimaryKey(primaryKey);

		if (stAccClosureDetails == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchStAccClosureDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return stAccClosureDetails;
	}

	/**
	 * Returns the st acc closure details with the primary key or throws a {@link NoSuchStAccClosureDetailsException} if it could not be found.
	 *
	 * @param accClosureMasterSid the primary key of the st acc closure details
	 * @return the st acc closure details
	 * @throws NoSuchStAccClosureDetailsException if a st acc closure details with the primary key could not be found
	 */
	@Override
	public StAccClosureDetails findByPrimaryKey(int accClosureMasterSid)
		throws NoSuchStAccClosureDetailsException {
		return findByPrimaryKey((Serializable)accClosureMasterSid);
	}

	/**
	 * Returns the st acc closure details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the st acc closure details
	 * @return the st acc closure details, or <code>null</code> if a st acc closure details with the primary key could not be found
	 */
	@Override
	public StAccClosureDetails fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(StAccClosureDetailsModelImpl.ENTITY_CACHE_ENABLED,
				StAccClosureDetailsImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		StAccClosureDetails stAccClosureDetails = (StAccClosureDetails)serializable;

		if (stAccClosureDetails == null) {
			Session session = null;

			try {
				session = openSession();

				stAccClosureDetails = (StAccClosureDetails)session.get(StAccClosureDetailsImpl.class,
						primaryKey);

				if (stAccClosureDetails != null) {
					cacheResult(stAccClosureDetails);
				}
				else {
					entityCache.putResult(StAccClosureDetailsModelImpl.ENTITY_CACHE_ENABLED,
						StAccClosureDetailsImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(StAccClosureDetailsModelImpl.ENTITY_CACHE_ENABLED,
					StAccClosureDetailsImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return stAccClosureDetails;
	}

	/**
	 * Returns the st acc closure details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param accClosureMasterSid the primary key of the st acc closure details
	 * @return the st acc closure details, or <code>null</code> if a st acc closure details with the primary key could not be found
	 */
	@Override
	public StAccClosureDetails fetchByPrimaryKey(int accClosureMasterSid) {
		return fetchByPrimaryKey((Serializable)accClosureMasterSid);
	}

	@Override
	public Map<Serializable, StAccClosureDetails> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, StAccClosureDetails> map = new HashMap<Serializable, StAccClosureDetails>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			StAccClosureDetails stAccClosureDetails = fetchByPrimaryKey(primaryKey);

			if (stAccClosureDetails != null) {
				map.put(primaryKey, stAccClosureDetails);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(StAccClosureDetailsModelImpl.ENTITY_CACHE_ENABLED,
					StAccClosureDetailsImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (StAccClosureDetails)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_STACCCLOSUREDETAILS_WHERE_PKS_IN);

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

			for (StAccClosureDetails stAccClosureDetails : (List<StAccClosureDetails>)q.list()) {
				map.put(stAccClosureDetails.getPrimaryKeyObj(),
					stAccClosureDetails);

				cacheResult(stAccClosureDetails);

				uncachedPrimaryKeys.remove(stAccClosureDetails.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(StAccClosureDetailsModelImpl.ENTITY_CACHE_ENABLED,
					StAccClosureDetailsImpl.class, primaryKey, nullModel);
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
	 * Returns all the st acc closure detailses.
	 *
	 * @return the st acc closure detailses
	 */
	@Override
	public List<StAccClosureDetails> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the st acc closure detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StAccClosureDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st acc closure detailses
	 * @param end the upper bound of the range of st acc closure detailses (not inclusive)
	 * @return the range of st acc closure detailses
	 */
	@Override
	public List<StAccClosureDetails> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the st acc closure detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StAccClosureDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st acc closure detailses
	 * @param end the upper bound of the range of st acc closure detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of st acc closure detailses
	 */
	@Override
	public List<StAccClosureDetails> findAll(int start, int end,
		OrderByComparator<StAccClosureDetails> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the st acc closure detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StAccClosureDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st acc closure detailses
	 * @param end the upper bound of the range of st acc closure detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of st acc closure detailses
	 */
	@Override
	public List<StAccClosureDetails> findAll(int start, int end,
		OrderByComparator<StAccClosureDetails> orderByComparator,
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

		List<StAccClosureDetails> list = null;

		if (retrieveFromCache) {
			list = (List<StAccClosureDetails>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_STACCCLOSUREDETAILS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_STACCCLOSUREDETAILS;

				if (pagination) {
					sql = sql.concat(StAccClosureDetailsModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<StAccClosureDetails>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<StAccClosureDetails>)QueryUtil.list(q,
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
	 * Removes all the st acc closure detailses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (StAccClosureDetails stAccClosureDetails : findAll()) {
			remove(stAccClosureDetails);
		}
	}

	/**
	 * Returns the number of st acc closure detailses.
	 *
	 * @return the number of st acc closure detailses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_STACCCLOSUREDETAILS);

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
		return StAccClosureDetailsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the st acc closure details persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(StAccClosureDetailsImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_STACCCLOSUREDETAILS = "SELECT stAccClosureDetails FROM StAccClosureDetails stAccClosureDetails";
	private static final String _SQL_SELECT_STACCCLOSUREDETAILS_WHERE_PKS_IN = "SELECT stAccClosureDetails FROM StAccClosureDetails stAccClosureDetails WHERE ACC_CLOSURE_MASTER_SID IN (";
	private static final String _SQL_COUNT_STACCCLOSUREDETAILS = "SELECT COUNT(stAccClosureDetails) FROM StAccClosureDetails stAccClosureDetails";
	private static final String _ORDER_BY_ENTITY_ALIAS = "stAccClosureDetails.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No StAccClosureDetails exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(StAccClosureDetailsPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"lastModifiedDate", "checkRecord", "contractName", "userId",
				"itemMasterSid", "moduleName", "companyName", "brandName",
				"companyCostCenter", "companyNo", "contractMasterSid",
				"sessionId", "ccpDetailsSid", "itemName", "accClosureMasterSid",
				"rsModelSid", "contractNo", "companyMasterSid", "ndc8"
			});
}