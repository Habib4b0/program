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
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReflectionUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.spring.extender.service.ServiceReference;

import com.stpl.app.parttwo.exception.NoSuchVwcompanyIdentifierException;
import com.stpl.app.parttwo.model.VwcompanyIdentifier;
import com.stpl.app.parttwo.model.impl.VwcompanyIdentifierImpl;
import com.stpl.app.parttwo.model.impl.VwcompanyIdentifierModelImpl;
import com.stpl.app.parttwo.service.persistence.VwcompanyIdentifierPersistence;

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
 * The persistence implementation for the vwcompany identifier service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see VwcompanyIdentifierPersistence
 * @see com.stpl.app.parttwo.service.persistence.VwcompanyIdentifierUtil
 * @generated
 */
@ProviderType
public class VwcompanyIdentifierPersistenceImpl extends BasePersistenceImpl<VwcompanyIdentifier>
	implements VwcompanyIdentifierPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link VwcompanyIdentifierUtil} to access the vwcompany identifier persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = VwcompanyIdentifierImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(VwcompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
			VwcompanyIdentifierModelImpl.FINDER_CACHE_ENABLED,
			VwcompanyIdentifierImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(VwcompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
			VwcompanyIdentifierModelImpl.FINDER_CACHE_ENABLED,
			VwcompanyIdentifierImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(VwcompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
			VwcompanyIdentifierModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public VwcompanyIdentifierPersistenceImpl() {
		setModelClass(VwcompanyIdentifier.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("companyId", "COMPANY_ID");
			dbColumnNames.put("companyName", "COMPANY_NAME");
			dbColumnNames.put("endDate", "END_DATE");
			dbColumnNames.put("companyIdentifierSid", "COMPANY_IDENTIFIER_SID");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("identifierStatus", "IDENTIFIER_STATUS");
			dbColumnNames.put("companyIdentifier", "COMPANY_IDENTIFIER");
			dbColumnNames.put("entityCode", "ENTITY_CODE");
			dbColumnNames.put("startDate", "START_DATE");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("companyNo", "COMPANY_NO");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("addChgDelIndicator", "ADD_CHG_DEL_INDICATOR");
			dbColumnNames.put("identifierCodeQualifierName",
				"IDENTIFIER_CODE_QUALIFIER_NAME");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("identifierCodeQualifier",
				"IDENTIFIER_CODE_QUALIFIER");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the vwcompany identifier in the entity cache if it is enabled.
	 *
	 * @param vwcompanyIdentifier the vwcompany identifier
	 */
	@Override
	public void cacheResult(VwcompanyIdentifier vwcompanyIdentifier) {
		entityCache.putResult(VwcompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
			VwcompanyIdentifierImpl.class, vwcompanyIdentifier.getPrimaryKey(),
			vwcompanyIdentifier);

		vwcompanyIdentifier.resetOriginalValues();
	}

	/**
	 * Caches the vwcompany identifiers in the entity cache if it is enabled.
	 *
	 * @param vwcompanyIdentifiers the vwcompany identifiers
	 */
	@Override
	public void cacheResult(List<VwcompanyIdentifier> vwcompanyIdentifiers) {
		for (VwcompanyIdentifier vwcompanyIdentifier : vwcompanyIdentifiers) {
			if (entityCache.getResult(
						VwcompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
						VwcompanyIdentifierImpl.class,
						vwcompanyIdentifier.getPrimaryKey()) == null) {
				cacheResult(vwcompanyIdentifier);
			}
			else {
				vwcompanyIdentifier.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all vwcompany identifiers.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(VwcompanyIdentifierImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the vwcompany identifier.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(VwcompanyIdentifier vwcompanyIdentifier) {
		entityCache.removeResult(VwcompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
			VwcompanyIdentifierImpl.class, vwcompanyIdentifier.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<VwcompanyIdentifier> vwcompanyIdentifiers) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (VwcompanyIdentifier vwcompanyIdentifier : vwcompanyIdentifiers) {
			entityCache.removeResult(VwcompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
				VwcompanyIdentifierImpl.class,
				vwcompanyIdentifier.getPrimaryKey());
		}
	}

	/**
	 * Creates a new vwcompany identifier with the primary key. Does not add the vwcompany identifier to the database.
	 *
	 * @param companyIdentifierSid the primary key for the new vwcompany identifier
	 * @return the new vwcompany identifier
	 */
	@Override
	public VwcompanyIdentifier create(int companyIdentifierSid) {
		VwcompanyIdentifier vwcompanyIdentifier = new VwcompanyIdentifierImpl();

		vwcompanyIdentifier.setNew(true);
		vwcompanyIdentifier.setPrimaryKey(companyIdentifierSid);

		vwcompanyIdentifier.setCompanyId(companyProvider.getCompanyId());

		return vwcompanyIdentifier;
	}

	/**
	 * Removes the vwcompany identifier with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param companyIdentifierSid the primary key of the vwcompany identifier
	 * @return the vwcompany identifier that was removed
	 * @throws NoSuchVwcompanyIdentifierException if a vwcompany identifier with the primary key could not be found
	 */
	@Override
	public VwcompanyIdentifier remove(int companyIdentifierSid)
		throws NoSuchVwcompanyIdentifierException {
		return remove((Serializable)companyIdentifierSid);
	}

	/**
	 * Removes the vwcompany identifier with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the vwcompany identifier
	 * @return the vwcompany identifier that was removed
	 * @throws NoSuchVwcompanyIdentifierException if a vwcompany identifier with the primary key could not be found
	 */
	@Override
	public VwcompanyIdentifier remove(Serializable primaryKey)
		throws NoSuchVwcompanyIdentifierException {
		Session session = null;

		try {
			session = openSession();

			VwcompanyIdentifier vwcompanyIdentifier = (VwcompanyIdentifier)session.get(VwcompanyIdentifierImpl.class,
					primaryKey);

			if (vwcompanyIdentifier == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchVwcompanyIdentifierException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(vwcompanyIdentifier);
		}
		catch (NoSuchVwcompanyIdentifierException nsee) {
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
	protected VwcompanyIdentifier removeImpl(
		VwcompanyIdentifier vwcompanyIdentifier) {
		vwcompanyIdentifier = toUnwrappedModel(vwcompanyIdentifier);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(vwcompanyIdentifier)) {
				vwcompanyIdentifier = (VwcompanyIdentifier)session.get(VwcompanyIdentifierImpl.class,
						vwcompanyIdentifier.getPrimaryKeyObj());
			}

			if (vwcompanyIdentifier != null) {
				session.delete(vwcompanyIdentifier);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (vwcompanyIdentifier != null) {
			clearCache(vwcompanyIdentifier);
		}

		return vwcompanyIdentifier;
	}

	@Override
	public VwcompanyIdentifier updateImpl(
		VwcompanyIdentifier vwcompanyIdentifier) {
		vwcompanyIdentifier = toUnwrappedModel(vwcompanyIdentifier);

		boolean isNew = vwcompanyIdentifier.isNew();

		Session session = null;

		try {
			session = openSession();

			if (vwcompanyIdentifier.isNew()) {
				session.save(vwcompanyIdentifier);

				vwcompanyIdentifier.setNew(false);
			}
			else {
				vwcompanyIdentifier = (VwcompanyIdentifier)session.merge(vwcompanyIdentifier);
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

		entityCache.putResult(VwcompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
			VwcompanyIdentifierImpl.class, vwcompanyIdentifier.getPrimaryKey(),
			vwcompanyIdentifier, false);

		vwcompanyIdentifier.resetOriginalValues();

		return vwcompanyIdentifier;
	}

	protected VwcompanyIdentifier toUnwrappedModel(
		VwcompanyIdentifier vwcompanyIdentifier) {
		if (vwcompanyIdentifier instanceof VwcompanyIdentifierImpl) {
			return vwcompanyIdentifier;
		}

		VwcompanyIdentifierImpl vwcompanyIdentifierImpl = new VwcompanyIdentifierImpl();

		vwcompanyIdentifierImpl.setNew(vwcompanyIdentifier.isNew());
		vwcompanyIdentifierImpl.setPrimaryKey(vwcompanyIdentifier.getPrimaryKey());

		vwcompanyIdentifierImpl.setCompanyId(vwcompanyIdentifier.getCompanyId());
		vwcompanyIdentifierImpl.setCompanyName(vwcompanyIdentifier.getCompanyName());
		vwcompanyIdentifierImpl.setEndDate(vwcompanyIdentifier.getEndDate());
		vwcompanyIdentifierImpl.setCompanyIdentifierSid(vwcompanyIdentifier.getCompanyIdentifierSid());
		vwcompanyIdentifierImpl.setModifiedDate(vwcompanyIdentifier.getModifiedDate());
		vwcompanyIdentifierImpl.setIdentifierStatus(vwcompanyIdentifier.getIdentifierStatus());
		vwcompanyIdentifierImpl.setCompanyIdentifier(vwcompanyIdentifier.getCompanyIdentifier());
		vwcompanyIdentifierImpl.setEntityCode(vwcompanyIdentifier.getEntityCode());
		vwcompanyIdentifierImpl.setStartDate(vwcompanyIdentifier.getStartDate());
		vwcompanyIdentifierImpl.setCreatedDate(vwcompanyIdentifier.getCreatedDate());
		vwcompanyIdentifierImpl.setCreatedBy(vwcompanyIdentifier.getCreatedBy());
		vwcompanyIdentifierImpl.setSource(vwcompanyIdentifier.getSource());
		vwcompanyIdentifierImpl.setCompanyNo(vwcompanyIdentifier.getCompanyNo());
		vwcompanyIdentifierImpl.setBatchId(vwcompanyIdentifier.getBatchId());
		vwcompanyIdentifierImpl.setAddChgDelIndicator(vwcompanyIdentifier.getAddChgDelIndicator());
		vwcompanyIdentifierImpl.setIdentifierCodeQualifierName(vwcompanyIdentifier.getIdentifierCodeQualifierName());
		vwcompanyIdentifierImpl.setModifiedBy(vwcompanyIdentifier.getModifiedBy());
		vwcompanyIdentifierImpl.setIdentifierCodeQualifier(vwcompanyIdentifier.getIdentifierCodeQualifier());

		return vwcompanyIdentifierImpl;
	}

	/**
	 * Returns the vwcompany identifier with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the vwcompany identifier
	 * @return the vwcompany identifier
	 * @throws NoSuchVwcompanyIdentifierException if a vwcompany identifier with the primary key could not be found
	 */
	@Override
	public VwcompanyIdentifier findByPrimaryKey(Serializable primaryKey)
		throws NoSuchVwcompanyIdentifierException {
		VwcompanyIdentifier vwcompanyIdentifier = fetchByPrimaryKey(primaryKey);

		if (vwcompanyIdentifier == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchVwcompanyIdentifierException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return vwcompanyIdentifier;
	}

	/**
	 * Returns the vwcompany identifier with the primary key or throws a {@link NoSuchVwcompanyIdentifierException} if it could not be found.
	 *
	 * @param companyIdentifierSid the primary key of the vwcompany identifier
	 * @return the vwcompany identifier
	 * @throws NoSuchVwcompanyIdentifierException if a vwcompany identifier with the primary key could not be found
	 */
	@Override
	public VwcompanyIdentifier findByPrimaryKey(int companyIdentifierSid)
		throws NoSuchVwcompanyIdentifierException {
		return findByPrimaryKey((Serializable)companyIdentifierSid);
	}

	/**
	 * Returns the vwcompany identifier with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the vwcompany identifier
	 * @return the vwcompany identifier, or <code>null</code> if a vwcompany identifier with the primary key could not be found
	 */
	@Override
	public VwcompanyIdentifier fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(VwcompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
				VwcompanyIdentifierImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		VwcompanyIdentifier vwcompanyIdentifier = (VwcompanyIdentifier)serializable;

		if (vwcompanyIdentifier == null) {
			Session session = null;

			try {
				session = openSession();

				vwcompanyIdentifier = (VwcompanyIdentifier)session.get(VwcompanyIdentifierImpl.class,
						primaryKey);

				if (vwcompanyIdentifier != null) {
					cacheResult(vwcompanyIdentifier);
				}
				else {
					entityCache.putResult(VwcompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
						VwcompanyIdentifierImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(VwcompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
					VwcompanyIdentifierImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return vwcompanyIdentifier;
	}

	/**
	 * Returns the vwcompany identifier with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param companyIdentifierSid the primary key of the vwcompany identifier
	 * @return the vwcompany identifier, or <code>null</code> if a vwcompany identifier with the primary key could not be found
	 */
	@Override
	public VwcompanyIdentifier fetchByPrimaryKey(int companyIdentifierSid) {
		return fetchByPrimaryKey((Serializable)companyIdentifierSid);
	}

	@Override
	public Map<Serializable, VwcompanyIdentifier> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, VwcompanyIdentifier> map = new HashMap<Serializable, VwcompanyIdentifier>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			VwcompanyIdentifier vwcompanyIdentifier = fetchByPrimaryKey(primaryKey);

			if (vwcompanyIdentifier != null) {
				map.put(primaryKey, vwcompanyIdentifier);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(VwcompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
					VwcompanyIdentifierImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (VwcompanyIdentifier)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_VWCOMPANYIDENTIFIER_WHERE_PKS_IN);

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

			for (VwcompanyIdentifier vwcompanyIdentifier : (List<VwcompanyIdentifier>)q.list()) {
				map.put(vwcompanyIdentifier.getPrimaryKeyObj(),
					vwcompanyIdentifier);

				cacheResult(vwcompanyIdentifier);

				uncachedPrimaryKeys.remove(vwcompanyIdentifier.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(VwcompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
					VwcompanyIdentifierImpl.class, primaryKey, nullModel);
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
	 * Returns all the vwcompany identifiers.
	 *
	 * @return the vwcompany identifiers
	 */
	@Override
	public List<VwcompanyIdentifier> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vwcompany identifiers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwcompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of vwcompany identifiers
	 * @param end the upper bound of the range of vwcompany identifiers (not inclusive)
	 * @return the range of vwcompany identifiers
	 */
	@Override
	public List<VwcompanyIdentifier> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the vwcompany identifiers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwcompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of vwcompany identifiers
	 * @param end the upper bound of the range of vwcompany identifiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of vwcompany identifiers
	 */
	@Override
	public List<VwcompanyIdentifier> findAll(int start, int end,
		OrderByComparator<VwcompanyIdentifier> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the vwcompany identifiers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwcompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of vwcompany identifiers
	 * @param end the upper bound of the range of vwcompany identifiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of vwcompany identifiers
	 */
	@Override
	public List<VwcompanyIdentifier> findAll(int start, int end,
		OrderByComparator<VwcompanyIdentifier> orderByComparator,
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

		List<VwcompanyIdentifier> list = null;

		if (retrieveFromCache) {
			list = (List<VwcompanyIdentifier>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_VWCOMPANYIDENTIFIER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_VWCOMPANYIDENTIFIER;

				if (pagination) {
					sql = sql.concat(VwcompanyIdentifierModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<VwcompanyIdentifier>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<VwcompanyIdentifier>)QueryUtil.list(q,
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
	 * Removes all the vwcompany identifiers from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (VwcompanyIdentifier vwcompanyIdentifier : findAll()) {
			remove(vwcompanyIdentifier);
		}
	}

	/**
	 * Returns the number of vwcompany identifiers.
	 *
	 * @return the number of vwcompany identifiers
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_VWCOMPANYIDENTIFIER);

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
		return VwcompanyIdentifierModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the vwcompany identifier persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(VwcompanyIdentifierImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_VWCOMPANYIDENTIFIER = "SELECT vwcompanyIdentifier FROM VwcompanyIdentifier vwcompanyIdentifier";
	private static final String _SQL_SELECT_VWCOMPANYIDENTIFIER_WHERE_PKS_IN = "SELECT vwcompanyIdentifier FROM VwcompanyIdentifier vwcompanyIdentifier WHERE COMPANY_IDENTIFIER_SID IN (";
	private static final String _SQL_COUNT_VWCOMPANYIDENTIFIER = "SELECT COUNT(vwcompanyIdentifier) FROM VwcompanyIdentifier vwcompanyIdentifier";
	private static final String _ORDER_BY_ENTITY_ALIAS = "vwcompanyIdentifier.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VwcompanyIdentifier exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(VwcompanyIdentifierPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"companyId", "companyName", "endDate", "companyIdentifierSid",
				"modifiedDate", "identifierStatus", "companyIdentifier",
				"entityCode", "startDate", "createdDate", "createdBy", "source",
				"companyNo", "batchId", "addChgDelIndicator",
				"identifierCodeQualifierName", "modifiedBy",
				"identifierCodeQualifier"
			});
}