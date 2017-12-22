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

import com.stpl.app.parttwo.exception.NoSuchAhTempDetailsException;
import com.stpl.app.parttwo.model.AhTempDetails;
import com.stpl.app.parttwo.model.impl.AhTempDetailsImpl;
import com.stpl.app.parttwo.model.impl.AhTempDetailsModelImpl;
import com.stpl.app.parttwo.service.persistence.AhTempDetailsPersistence;

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
 * The persistence implementation for the ah temp details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see AhTempDetailsPersistence
 * @see com.stpl.app.parttwo.service.persistence.AhTempDetailsUtil
 * @generated
 */
@ProviderType
public class AhTempDetailsPersistenceImpl extends BasePersistenceImpl<AhTempDetails>
	implements AhTempDetailsPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AhTempDetailsUtil} to access the ah temp details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AhTempDetailsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AhTempDetailsModelImpl.ENTITY_CACHE_ENABLED,
			AhTempDetailsModelImpl.FINDER_CACHE_ENABLED,
			AhTempDetailsImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AhTempDetailsModelImpl.ENTITY_CACHE_ENABLED,
			AhTempDetailsModelImpl.FINDER_CACHE_ENABLED,
			AhTempDetailsImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AhTempDetailsModelImpl.ENTITY_CACHE_ENABLED,
			AhTempDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public AhTempDetailsPersistenceImpl() {
		setModelClass(AhTempDetails.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("checkRecord", "CHECK_RECORD");
			dbColumnNames.put("contractHolder", "CONTRACT_HOLDER");
			dbColumnNames.put("userId", "USER_ID");
			dbColumnNames.put("itemMasterSid", "ITEM_MASTER_SID");
			dbColumnNames.put("businessUnitNo", "BUSINESS_UNIT_NO");
			dbColumnNames.put("companyName", "COMPANY_NAME");
			dbColumnNames.put("itemId", "ITEM_ID");
			dbColumnNames.put("brandName", "BRAND_NAME");
			dbColumnNames.put("componentName", "COMPONENT_NAME");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("screenName", "SCREEN_NAME");
			dbColumnNames.put("businessUnitName", "BUSINESS_UNIT_NAME");
			dbColumnNames.put("companyNo", "COMPANY_NO");
			dbColumnNames.put("itemIdentifierType", "ITEM_IDENTIFIER_TYPE");
			dbColumnNames.put("componentNo", "COMPONENT_NO");
			dbColumnNames.put("sessionId", "SESSION_ID");
			dbColumnNames.put("itemName", "ITEM_NAME");
			dbColumnNames.put("itemIdentifier", "ITEM_IDENTIFIER");
			dbColumnNames.put("companySid", "COMPANY_SID");
			dbColumnNames.put("itemNo", "ITEM_NO");
			dbColumnNames.put("componentType", "COMPONENT_TYPE");
			dbColumnNames.put("theraputicClass", "THERAPUTIC_CLASS");
			dbColumnNames.put("componentMasterSid", "COMPONENT_MASTER_SID");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the ah temp details in the entity cache if it is enabled.
	 *
	 * @param ahTempDetails the ah temp details
	 */
	@Override
	public void cacheResult(AhTempDetails ahTempDetails) {
		entityCache.putResult(AhTempDetailsModelImpl.ENTITY_CACHE_ENABLED,
			AhTempDetailsImpl.class, ahTempDetails.getPrimaryKey(),
			ahTempDetails);

		ahTempDetails.resetOriginalValues();
	}

	/**
	 * Caches the ah temp detailses in the entity cache if it is enabled.
	 *
	 * @param ahTempDetailses the ah temp detailses
	 */
	@Override
	public void cacheResult(List<AhTempDetails> ahTempDetailses) {
		for (AhTempDetails ahTempDetails : ahTempDetailses) {
			if (entityCache.getResult(
						AhTempDetailsModelImpl.ENTITY_CACHE_ENABLED,
						AhTempDetailsImpl.class, ahTempDetails.getPrimaryKey()) == null) {
				cacheResult(ahTempDetails);
			}
			else {
				ahTempDetails.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all ah temp detailses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(AhTempDetailsImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the ah temp details.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AhTempDetails ahTempDetails) {
		entityCache.removeResult(AhTempDetailsModelImpl.ENTITY_CACHE_ENABLED,
			AhTempDetailsImpl.class, ahTempDetails.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<AhTempDetails> ahTempDetailses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AhTempDetails ahTempDetails : ahTempDetailses) {
			entityCache.removeResult(AhTempDetailsModelImpl.ENTITY_CACHE_ENABLED,
				AhTempDetailsImpl.class, ahTempDetails.getPrimaryKey());
		}
	}

	/**
	 * Creates a new ah temp details with the primary key. Does not add the ah temp details to the database.
	 *
	 * @param componentMasterSid the primary key for the new ah temp details
	 * @return the new ah temp details
	 */
	@Override
	public AhTempDetails create(int componentMasterSid) {
		AhTempDetails ahTempDetails = new AhTempDetailsImpl();

		ahTempDetails.setNew(true);
		ahTempDetails.setPrimaryKey(componentMasterSid);

		return ahTempDetails;
	}

	/**
	 * Removes the ah temp details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param componentMasterSid the primary key of the ah temp details
	 * @return the ah temp details that was removed
	 * @throws NoSuchAhTempDetailsException if a ah temp details with the primary key could not be found
	 */
	@Override
	public AhTempDetails remove(int componentMasterSid)
		throws NoSuchAhTempDetailsException {
		return remove((Serializable)componentMasterSid);
	}

	/**
	 * Removes the ah temp details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ah temp details
	 * @return the ah temp details that was removed
	 * @throws NoSuchAhTempDetailsException if a ah temp details with the primary key could not be found
	 */
	@Override
	public AhTempDetails remove(Serializable primaryKey)
		throws NoSuchAhTempDetailsException {
		Session session = null;

		try {
			session = openSession();

			AhTempDetails ahTempDetails = (AhTempDetails)session.get(AhTempDetailsImpl.class,
					primaryKey);

			if (ahTempDetails == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAhTempDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(ahTempDetails);
		}
		catch (NoSuchAhTempDetailsException nsee) {
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
	protected AhTempDetails removeImpl(AhTempDetails ahTempDetails) {
		ahTempDetails = toUnwrappedModel(ahTempDetails);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(ahTempDetails)) {
				ahTempDetails = (AhTempDetails)session.get(AhTempDetailsImpl.class,
						ahTempDetails.getPrimaryKeyObj());
			}

			if (ahTempDetails != null) {
				session.delete(ahTempDetails);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (ahTempDetails != null) {
			clearCache(ahTempDetails);
		}

		return ahTempDetails;
	}

	@Override
	public AhTempDetails updateImpl(AhTempDetails ahTempDetails) {
		ahTempDetails = toUnwrappedModel(ahTempDetails);

		boolean isNew = ahTempDetails.isNew();

		Session session = null;

		try {
			session = openSession();

			if (ahTempDetails.isNew()) {
				session.save(ahTempDetails);

				ahTempDetails.setNew(false);
			}
			else {
				ahTempDetails = (AhTempDetails)session.merge(ahTempDetails);
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

		entityCache.putResult(AhTempDetailsModelImpl.ENTITY_CACHE_ENABLED,
			AhTempDetailsImpl.class, ahTempDetails.getPrimaryKey(),
			ahTempDetails, false);

		ahTempDetails.resetOriginalValues();

		return ahTempDetails;
	}

	protected AhTempDetails toUnwrappedModel(AhTempDetails ahTempDetails) {
		if (ahTempDetails instanceof AhTempDetailsImpl) {
			return ahTempDetails;
		}

		AhTempDetailsImpl ahTempDetailsImpl = new AhTempDetailsImpl();

		ahTempDetailsImpl.setNew(ahTempDetails.isNew());
		ahTempDetailsImpl.setPrimaryKey(ahTempDetails.getPrimaryKey());

		ahTempDetailsImpl.setCheckRecord(ahTempDetails.isCheckRecord());
		ahTempDetailsImpl.setContractHolder(ahTempDetails.getContractHolder());
		ahTempDetailsImpl.setUserId(ahTempDetails.getUserId());
		ahTempDetailsImpl.setItemMasterSid(ahTempDetails.getItemMasterSid());
		ahTempDetailsImpl.setBusinessUnitNo(ahTempDetails.getBusinessUnitNo());
		ahTempDetailsImpl.setCompanyName(ahTempDetails.getCompanyName());
		ahTempDetailsImpl.setItemId(ahTempDetails.getItemId());
		ahTempDetailsImpl.setBrandName(ahTempDetails.getBrandName());
		ahTempDetailsImpl.setComponentName(ahTempDetails.getComponentName());
		ahTempDetailsImpl.setCreatedDate(ahTempDetails.getCreatedDate());
		ahTempDetailsImpl.setCreatedBy(ahTempDetails.getCreatedBy());
		ahTempDetailsImpl.setScreenName(ahTempDetails.getScreenName());
		ahTempDetailsImpl.setBusinessUnitName(ahTempDetails.getBusinessUnitName());
		ahTempDetailsImpl.setCompanyNo(ahTempDetails.getCompanyNo());
		ahTempDetailsImpl.setItemIdentifierType(ahTempDetails.getItemIdentifierType());
		ahTempDetailsImpl.setComponentNo(ahTempDetails.getComponentNo());
		ahTempDetailsImpl.setSessionId(ahTempDetails.getSessionId());
		ahTempDetailsImpl.setItemName(ahTempDetails.getItemName());
		ahTempDetailsImpl.setItemIdentifier(ahTempDetails.getItemIdentifier());
		ahTempDetailsImpl.setCompanySid(ahTempDetails.getCompanySid());
		ahTempDetailsImpl.setItemNo(ahTempDetails.getItemNo());
		ahTempDetailsImpl.setComponentType(ahTempDetails.getComponentType());
		ahTempDetailsImpl.setTheraputicClass(ahTempDetails.getTheraputicClass());
		ahTempDetailsImpl.setComponentMasterSid(ahTempDetails.getComponentMasterSid());

		return ahTempDetailsImpl;
	}

	/**
	 * Returns the ah temp details with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the ah temp details
	 * @return the ah temp details
	 * @throws NoSuchAhTempDetailsException if a ah temp details with the primary key could not be found
	 */
	@Override
	public AhTempDetails findByPrimaryKey(Serializable primaryKey)
		throws NoSuchAhTempDetailsException {
		AhTempDetails ahTempDetails = fetchByPrimaryKey(primaryKey);

		if (ahTempDetails == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchAhTempDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return ahTempDetails;
	}

	/**
	 * Returns the ah temp details with the primary key or throws a {@link NoSuchAhTempDetailsException} if it could not be found.
	 *
	 * @param componentMasterSid the primary key of the ah temp details
	 * @return the ah temp details
	 * @throws NoSuchAhTempDetailsException if a ah temp details with the primary key could not be found
	 */
	@Override
	public AhTempDetails findByPrimaryKey(int componentMasterSid)
		throws NoSuchAhTempDetailsException {
		return findByPrimaryKey((Serializable)componentMasterSid);
	}

	/**
	 * Returns the ah temp details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ah temp details
	 * @return the ah temp details, or <code>null</code> if a ah temp details with the primary key could not be found
	 */
	@Override
	public AhTempDetails fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(AhTempDetailsModelImpl.ENTITY_CACHE_ENABLED,
				AhTempDetailsImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		AhTempDetails ahTempDetails = (AhTempDetails)serializable;

		if (ahTempDetails == null) {
			Session session = null;

			try {
				session = openSession();

				ahTempDetails = (AhTempDetails)session.get(AhTempDetailsImpl.class,
						primaryKey);

				if (ahTempDetails != null) {
					cacheResult(ahTempDetails);
				}
				else {
					entityCache.putResult(AhTempDetailsModelImpl.ENTITY_CACHE_ENABLED,
						AhTempDetailsImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(AhTempDetailsModelImpl.ENTITY_CACHE_ENABLED,
					AhTempDetailsImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return ahTempDetails;
	}

	/**
	 * Returns the ah temp details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param componentMasterSid the primary key of the ah temp details
	 * @return the ah temp details, or <code>null</code> if a ah temp details with the primary key could not be found
	 */
	@Override
	public AhTempDetails fetchByPrimaryKey(int componentMasterSid) {
		return fetchByPrimaryKey((Serializable)componentMasterSid);
	}

	@Override
	public Map<Serializable, AhTempDetails> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, AhTempDetails> map = new HashMap<Serializable, AhTempDetails>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			AhTempDetails ahTempDetails = fetchByPrimaryKey(primaryKey);

			if (ahTempDetails != null) {
				map.put(primaryKey, ahTempDetails);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(AhTempDetailsModelImpl.ENTITY_CACHE_ENABLED,
					AhTempDetailsImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (AhTempDetails)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_AHTEMPDETAILS_WHERE_PKS_IN);

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

			for (AhTempDetails ahTempDetails : (List<AhTempDetails>)q.list()) {
				map.put(ahTempDetails.getPrimaryKeyObj(), ahTempDetails);

				cacheResult(ahTempDetails);

				uncachedPrimaryKeys.remove(ahTempDetails.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(AhTempDetailsModelImpl.ENTITY_CACHE_ENABLED,
					AhTempDetailsImpl.class, primaryKey, nullModel);
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
	 * Returns all the ah temp detailses.
	 *
	 * @return the ah temp detailses
	 */
	@Override
	public List<AhTempDetails> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ah temp detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AhTempDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ah temp detailses
	 * @param end the upper bound of the range of ah temp detailses (not inclusive)
	 * @return the range of ah temp detailses
	 */
	@Override
	public List<AhTempDetails> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ah temp detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AhTempDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ah temp detailses
	 * @param end the upper bound of the range of ah temp detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ah temp detailses
	 */
	@Override
	public List<AhTempDetails> findAll(int start, int end,
		OrderByComparator<AhTempDetails> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ah temp detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AhTempDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ah temp detailses
	 * @param end the upper bound of the range of ah temp detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of ah temp detailses
	 */
	@Override
	public List<AhTempDetails> findAll(int start, int end,
		OrderByComparator<AhTempDetails> orderByComparator,
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

		List<AhTempDetails> list = null;

		if (retrieveFromCache) {
			list = (List<AhTempDetails>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_AHTEMPDETAILS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_AHTEMPDETAILS;

				if (pagination) {
					sql = sql.concat(AhTempDetailsModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<AhTempDetails>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AhTempDetails>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the ah temp detailses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (AhTempDetails ahTempDetails : findAll()) {
			remove(ahTempDetails);
		}
	}

	/**
	 * Returns the number of ah temp detailses.
	 *
	 * @return the number of ah temp detailses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_AHTEMPDETAILS);

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
		return AhTempDetailsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ah temp details persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(AhTempDetailsImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_AHTEMPDETAILS = "SELECT ahTempDetails FROM AhTempDetails ahTempDetails";
	private static final String _SQL_SELECT_AHTEMPDETAILS_WHERE_PKS_IN = "SELECT ahTempDetails FROM AhTempDetails ahTempDetails WHERE COMPONENT_MASTER_SID IN (";
	private static final String _SQL_COUNT_AHTEMPDETAILS = "SELECT COUNT(ahTempDetails) FROM AhTempDetails ahTempDetails";
	private static final String _ORDER_BY_ENTITY_ALIAS = "ahTempDetails.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AhTempDetails exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(AhTempDetailsPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"checkRecord", "contractHolder", "userId", "itemMasterSid",
				"businessUnitNo", "companyName", "itemId", "brandName",
				"componentName", "createdDate", "createdBy", "screenName",
				"businessUnitName", "companyNo", "itemIdentifierType",
				"componentNo", "sessionId", "itemName", "itemIdentifier",
				"companySid", "itemNo", "componentType", "theraputicClass",
				"componentMasterSid"
			});
}