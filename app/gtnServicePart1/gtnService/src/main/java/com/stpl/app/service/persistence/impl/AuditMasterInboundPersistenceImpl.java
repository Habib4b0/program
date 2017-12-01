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

import com.stpl.app.exception.NoSuchAuditMasterInboundException;
import com.stpl.app.model.AuditMasterInbound;
import com.stpl.app.model.impl.AuditMasterInboundImpl;
import com.stpl.app.model.impl.AuditMasterInboundModelImpl;
import com.stpl.app.service.persistence.AuditMasterInboundPersistence;

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
 * The persistence implementation for the audit master inbound service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see AuditMasterInboundPersistence
 * @see com.stpl.app.service.persistence.AuditMasterInboundUtil
 * @generated
 */
@ProviderType
public class AuditMasterInboundPersistenceImpl extends BasePersistenceImpl<AuditMasterInbound>
	implements AuditMasterInboundPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AuditMasterInboundUtil} to access the audit master inbound persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AuditMasterInboundImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AuditMasterInboundModelImpl.ENTITY_CACHE_ENABLED,
			AuditMasterInboundModelImpl.FINDER_CACHE_ENABLED,
			AuditMasterInboundImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AuditMasterInboundModelImpl.ENTITY_CACHE_ENABLED,
			AuditMasterInboundModelImpl.FINDER_CACHE_ENABLED,
			AuditMasterInboundImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AuditMasterInboundModelImpl.ENTITY_CACHE_ENABLED,
			AuditMasterInboundModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public AuditMasterInboundPersistenceImpl() {
		setModelClass(AuditMasterInbound.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("receivedRecordAmountAttr",
				"RECEIVED_RECORD_AMOUNT_ATTR");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("interfaceRunEndDate", "INTERFACE_RUN_END_DATE");
			dbColumnNames.put("applicationProcess", "APPLICATION_PROCESS");
			dbColumnNames.put("discrepancyAmount", "DISCREPANCY_AMOUNT");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("fileName", "FILE_NAME");
			dbColumnNames.put("sentRecordAmountAttribute",
				"SENT_RECORD_AMOUNT_ATTRIBUTE");
			dbColumnNames.put("status", "STATUS");
			dbColumnNames.put("receivedRecordAmount", "RECEIVED_RECORD_AMOUNT");
			dbColumnNames.put("validRecordAmount", "VALID_RECORD_AMOUNT");
			dbColumnNames.put("invalidRecordCount", "INVALID_RECORD_COUNT");
			dbColumnNames.put("receivedRecordCount", "RECEIVED_RECORD_COUNT");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("changeCount", "CHANGE_COUNT");
			dbColumnNames.put("unprocessedRecords", "UNPROCESSED_RECORDS");
			dbColumnNames.put("deleteCount", "DELETE_COUNT");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("auditInboundSid", "AUDIT_INBOUND_SID");
			dbColumnNames.put("sentRecordAmount", "SENT_RECORD_AMOUNT");
			dbColumnNames.put("sentRecordCount", "SENT_RECORD_COUNT");
			dbColumnNames.put("addCount", "ADD_COUNT");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("invalidRecordAmount", "INVALID_RECORD_AMOUNT");
			dbColumnNames.put("interfaceRunStartDate",
				"INTERFACE_RUN_START_DATE");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the audit master inbound in the entity cache if it is enabled.
	 *
	 * @param auditMasterInbound the audit master inbound
	 */
	@Override
	public void cacheResult(AuditMasterInbound auditMasterInbound) {
		entityCache.putResult(AuditMasterInboundModelImpl.ENTITY_CACHE_ENABLED,
			AuditMasterInboundImpl.class, auditMasterInbound.getPrimaryKey(),
			auditMasterInbound);

		auditMasterInbound.resetOriginalValues();
	}

	/**
	 * Caches the audit master inbounds in the entity cache if it is enabled.
	 *
	 * @param auditMasterInbounds the audit master inbounds
	 */
	@Override
	public void cacheResult(List<AuditMasterInbound> auditMasterInbounds) {
		for (AuditMasterInbound auditMasterInbound : auditMasterInbounds) {
			if (entityCache.getResult(
						AuditMasterInboundModelImpl.ENTITY_CACHE_ENABLED,
						AuditMasterInboundImpl.class,
						auditMasterInbound.getPrimaryKey()) == null) {
				cacheResult(auditMasterInbound);
			}
			else {
				auditMasterInbound.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all audit master inbounds.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(AuditMasterInboundImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the audit master inbound.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AuditMasterInbound auditMasterInbound) {
		entityCache.removeResult(AuditMasterInboundModelImpl.ENTITY_CACHE_ENABLED,
			AuditMasterInboundImpl.class, auditMasterInbound.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<AuditMasterInbound> auditMasterInbounds) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AuditMasterInbound auditMasterInbound : auditMasterInbounds) {
			entityCache.removeResult(AuditMasterInboundModelImpl.ENTITY_CACHE_ENABLED,
				AuditMasterInboundImpl.class, auditMasterInbound.getPrimaryKey());
		}
	}

	/**
	 * Creates a new audit master inbound with the primary key. Does not add the audit master inbound to the database.
	 *
	 * @param auditInboundSid the primary key for the new audit master inbound
	 * @return the new audit master inbound
	 */
	@Override
	public AuditMasterInbound create(int auditInboundSid) {
		AuditMasterInbound auditMasterInbound = new AuditMasterInboundImpl();

		auditMasterInbound.setNew(true);
		auditMasterInbound.setPrimaryKey(auditInboundSid);

		return auditMasterInbound;
	}

	/**
	 * Removes the audit master inbound with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param auditInboundSid the primary key of the audit master inbound
	 * @return the audit master inbound that was removed
	 * @throws NoSuchAuditMasterInboundException if a audit master inbound with the primary key could not be found
	 */
	@Override
	public AuditMasterInbound remove(int auditInboundSid)
		throws NoSuchAuditMasterInboundException {
		return remove((Serializable)auditInboundSid);
	}

	/**
	 * Removes the audit master inbound with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the audit master inbound
	 * @return the audit master inbound that was removed
	 * @throws NoSuchAuditMasterInboundException if a audit master inbound with the primary key could not be found
	 */
	@Override
	public AuditMasterInbound remove(Serializable primaryKey)
		throws NoSuchAuditMasterInboundException {
		Session session = null;

		try {
			session = openSession();

			AuditMasterInbound auditMasterInbound = (AuditMasterInbound)session.get(AuditMasterInboundImpl.class,
					primaryKey);

			if (auditMasterInbound == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAuditMasterInboundException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(auditMasterInbound);
		}
		catch (NoSuchAuditMasterInboundException nsee) {
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
	protected AuditMasterInbound removeImpl(
		AuditMasterInbound auditMasterInbound) {
		auditMasterInbound = toUnwrappedModel(auditMasterInbound);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(auditMasterInbound)) {
				auditMasterInbound = (AuditMasterInbound)session.get(AuditMasterInboundImpl.class,
						auditMasterInbound.getPrimaryKeyObj());
			}

			if (auditMasterInbound != null) {
				session.delete(auditMasterInbound);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (auditMasterInbound != null) {
			clearCache(auditMasterInbound);
		}

		return auditMasterInbound;
	}

	@Override
	public AuditMasterInbound updateImpl(AuditMasterInbound auditMasterInbound) {
		auditMasterInbound = toUnwrappedModel(auditMasterInbound);

		boolean isNew = auditMasterInbound.isNew();

		Session session = null;

		try {
			session = openSession();

			if (auditMasterInbound.isNew()) {
				session.save(auditMasterInbound);

				auditMasterInbound.setNew(false);
			}
			else {
				auditMasterInbound = (AuditMasterInbound)session.merge(auditMasterInbound);
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

		entityCache.putResult(AuditMasterInboundModelImpl.ENTITY_CACHE_ENABLED,
			AuditMasterInboundImpl.class, auditMasterInbound.getPrimaryKey(),
			auditMasterInbound, false);

		auditMasterInbound.resetOriginalValues();

		return auditMasterInbound;
	}

	protected AuditMasterInbound toUnwrappedModel(
		AuditMasterInbound auditMasterInbound) {
		if (auditMasterInbound instanceof AuditMasterInboundImpl) {
			return auditMasterInbound;
		}

		AuditMasterInboundImpl auditMasterInboundImpl = new AuditMasterInboundImpl();

		auditMasterInboundImpl.setNew(auditMasterInbound.isNew());
		auditMasterInboundImpl.setPrimaryKey(auditMasterInbound.getPrimaryKey());

		auditMasterInboundImpl.setReceivedRecordAmountAttr(auditMasterInbound.getReceivedRecordAmountAttr());
		auditMasterInboundImpl.setModifiedBy(auditMasterInbound.getModifiedBy());
		auditMasterInboundImpl.setCreatedDate(auditMasterInbound.getCreatedDate());
		auditMasterInboundImpl.setInterfaceRunEndDate(auditMasterInbound.getInterfaceRunEndDate());
		auditMasterInboundImpl.setApplicationProcess(auditMasterInbound.getApplicationProcess());
		auditMasterInboundImpl.setDiscrepancyAmount(auditMasterInbound.getDiscrepancyAmount());
		auditMasterInboundImpl.setBatchId(auditMasterInbound.getBatchId());
		auditMasterInboundImpl.setFileName(auditMasterInbound.getFileName());
		auditMasterInboundImpl.setSentRecordAmountAttribute(auditMasterInbound.getSentRecordAmountAttribute());
		auditMasterInboundImpl.setStatus(auditMasterInbound.getStatus());
		auditMasterInboundImpl.setReceivedRecordAmount(auditMasterInbound.getReceivedRecordAmount());
		auditMasterInboundImpl.setValidRecordAmount(auditMasterInbound.getValidRecordAmount());
		auditMasterInboundImpl.setInvalidRecordCount(auditMasterInbound.getInvalidRecordCount());
		auditMasterInboundImpl.setReceivedRecordCount(auditMasterInbound.getReceivedRecordCount());
		auditMasterInboundImpl.setCreatedBy(auditMasterInbound.getCreatedBy());
		auditMasterInboundImpl.setChangeCount(auditMasterInbound.getChangeCount());
		auditMasterInboundImpl.setUnprocessedRecords(auditMasterInbound.getUnprocessedRecords());
		auditMasterInboundImpl.setDeleteCount(auditMasterInbound.getDeleteCount());
		auditMasterInboundImpl.setModifiedDate(auditMasterInbound.getModifiedDate());
		auditMasterInboundImpl.setAuditInboundSid(auditMasterInbound.getAuditInboundSid());
		auditMasterInboundImpl.setSentRecordAmount(auditMasterInbound.getSentRecordAmount());
		auditMasterInboundImpl.setSentRecordCount(auditMasterInbound.getSentRecordCount());
		auditMasterInboundImpl.setAddCount(auditMasterInbound.getAddCount());
		auditMasterInboundImpl.setSource(auditMasterInbound.getSource());
		auditMasterInboundImpl.setInvalidRecordAmount(auditMasterInbound.getInvalidRecordAmount());
		auditMasterInboundImpl.setInterfaceRunStartDate(auditMasterInbound.getInterfaceRunStartDate());

		return auditMasterInboundImpl;
	}

	/**
	 * Returns the audit master inbound with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the audit master inbound
	 * @return the audit master inbound
	 * @throws NoSuchAuditMasterInboundException if a audit master inbound with the primary key could not be found
	 */
	@Override
	public AuditMasterInbound findByPrimaryKey(Serializable primaryKey)
		throws NoSuchAuditMasterInboundException {
		AuditMasterInbound auditMasterInbound = fetchByPrimaryKey(primaryKey);

		if (auditMasterInbound == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchAuditMasterInboundException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return auditMasterInbound;
	}

	/**
	 * Returns the audit master inbound with the primary key or throws a {@link NoSuchAuditMasterInboundException} if it could not be found.
	 *
	 * @param auditInboundSid the primary key of the audit master inbound
	 * @return the audit master inbound
	 * @throws NoSuchAuditMasterInboundException if a audit master inbound with the primary key could not be found
	 */
	@Override
	public AuditMasterInbound findByPrimaryKey(int auditInboundSid)
		throws NoSuchAuditMasterInboundException {
		return findByPrimaryKey((Serializable)auditInboundSid);
	}

	/**
	 * Returns the audit master inbound with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the audit master inbound
	 * @return the audit master inbound, or <code>null</code> if a audit master inbound with the primary key could not be found
	 */
	@Override
	public AuditMasterInbound fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(AuditMasterInboundModelImpl.ENTITY_CACHE_ENABLED,
				AuditMasterInboundImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		AuditMasterInbound auditMasterInbound = (AuditMasterInbound)serializable;

		if (auditMasterInbound == null) {
			Session session = null;

			try {
				session = openSession();

				auditMasterInbound = (AuditMasterInbound)session.get(AuditMasterInboundImpl.class,
						primaryKey);

				if (auditMasterInbound != null) {
					cacheResult(auditMasterInbound);
				}
				else {
					entityCache.putResult(AuditMasterInboundModelImpl.ENTITY_CACHE_ENABLED,
						AuditMasterInboundImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(AuditMasterInboundModelImpl.ENTITY_CACHE_ENABLED,
					AuditMasterInboundImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return auditMasterInbound;
	}

	/**
	 * Returns the audit master inbound with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param auditInboundSid the primary key of the audit master inbound
	 * @return the audit master inbound, or <code>null</code> if a audit master inbound with the primary key could not be found
	 */
	@Override
	public AuditMasterInbound fetchByPrimaryKey(int auditInboundSid) {
		return fetchByPrimaryKey((Serializable)auditInboundSid);
	}

	@Override
	public Map<Serializable, AuditMasterInbound> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, AuditMasterInbound> map = new HashMap<Serializable, AuditMasterInbound>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			AuditMasterInbound auditMasterInbound = fetchByPrimaryKey(primaryKey);

			if (auditMasterInbound != null) {
				map.put(primaryKey, auditMasterInbound);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(AuditMasterInboundModelImpl.ENTITY_CACHE_ENABLED,
					AuditMasterInboundImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (AuditMasterInbound)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_AUDITMASTERINBOUND_WHERE_PKS_IN);

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

			for (AuditMasterInbound auditMasterInbound : (List<AuditMasterInbound>)q.list()) {
				map.put(auditMasterInbound.getPrimaryKeyObj(),
					auditMasterInbound);

				cacheResult(auditMasterInbound);

				uncachedPrimaryKeys.remove(auditMasterInbound.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(AuditMasterInboundModelImpl.ENTITY_CACHE_ENABLED,
					AuditMasterInboundImpl.class, primaryKey, nullModel);
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
	 * Returns all the audit master inbounds.
	 *
	 * @return the audit master inbounds
	 */
	@Override
	public List<AuditMasterInbound> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the audit master inbounds.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditMasterInboundModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of audit master inbounds
	 * @param end the upper bound of the range of audit master inbounds (not inclusive)
	 * @return the range of audit master inbounds
	 */
	@Override
	public List<AuditMasterInbound> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the audit master inbounds.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditMasterInboundModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of audit master inbounds
	 * @param end the upper bound of the range of audit master inbounds (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of audit master inbounds
	 */
	@Override
	public List<AuditMasterInbound> findAll(int start, int end,
		OrderByComparator<AuditMasterInbound> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the audit master inbounds.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditMasterInboundModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of audit master inbounds
	 * @param end the upper bound of the range of audit master inbounds (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of audit master inbounds
	 */
	@Override
	public List<AuditMasterInbound> findAll(int start, int end,
		OrderByComparator<AuditMasterInbound> orderByComparator,
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

		List<AuditMasterInbound> list = null;

		if (retrieveFromCache) {
			list = (List<AuditMasterInbound>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_AUDITMASTERINBOUND);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_AUDITMASTERINBOUND;

				if (pagination) {
					sql = sql.concat(AuditMasterInboundModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<AuditMasterInbound>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AuditMasterInbound>)QueryUtil.list(q,
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
	 * Removes all the audit master inbounds from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (AuditMasterInbound auditMasterInbound : findAll()) {
			remove(auditMasterInbound);
		}
	}

	/**
	 * Returns the number of audit master inbounds.
	 *
	 * @return the number of audit master inbounds
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_AUDITMASTERINBOUND);

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
		return AuditMasterInboundModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the audit master inbound persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(AuditMasterInboundImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_AUDITMASTERINBOUND = "SELECT auditMasterInbound FROM AuditMasterInbound auditMasterInbound";
	private static final String _SQL_SELECT_AUDITMASTERINBOUND_WHERE_PKS_IN = "SELECT auditMasterInbound FROM AuditMasterInbound auditMasterInbound WHERE AUDIT_INBOUND_SID IN (";
	private static final String _SQL_COUNT_AUDITMASTERINBOUND = "SELECT COUNT(auditMasterInbound) FROM AuditMasterInbound auditMasterInbound";
	private static final String _ORDER_BY_ENTITY_ALIAS = "auditMasterInbound.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AuditMasterInbound exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(AuditMasterInboundPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"receivedRecordAmountAttr", "modifiedBy", "createdDate",
				"interfaceRunEndDate", "applicationProcess", "discrepancyAmount",
				"batchId", "fileName", "sentRecordAmountAttribute", "status",
				"receivedRecordAmount", "validRecordAmount",
				"invalidRecordCount", "receivedRecordCount", "createdBy",
				"changeCount", "unprocessedRecords", "deleteCount",
				"modifiedDate", "auditInboundSid", "sentRecordAmount",
				"sentRecordCount", "addCount", "source", "invalidRecordAmount",
				"interfaceRunStartDate"
			});
}