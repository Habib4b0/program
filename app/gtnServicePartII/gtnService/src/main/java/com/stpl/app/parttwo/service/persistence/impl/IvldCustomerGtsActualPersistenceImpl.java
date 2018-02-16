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

import com.stpl.app.parttwo.exception.NoSuchIvldCustomerGtsActualException;
import com.stpl.app.parttwo.model.IvldCustomerGtsActual;
import com.stpl.app.parttwo.model.impl.IvldCustomerGtsActualImpl;
import com.stpl.app.parttwo.model.impl.IvldCustomerGtsActualModelImpl;
import com.stpl.app.parttwo.service.persistence.IvldCustomerGtsActualPersistence;

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
 * The persistence implementation for the ivld customer gts actual service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldCustomerGtsActualPersistence
 * @see com.stpl.app.parttwo.service.persistence.IvldCustomerGtsActualUtil
 * @generated
 */
@ProviderType
public class IvldCustomerGtsActualPersistenceImpl extends BasePersistenceImpl<IvldCustomerGtsActual>
	implements IvldCustomerGtsActualPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link IvldCustomerGtsActualUtil} to access the ivld customer gts actual persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = IvldCustomerGtsActualImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(IvldCustomerGtsActualModelImpl.ENTITY_CACHE_ENABLED,
			IvldCustomerGtsActualModelImpl.FINDER_CACHE_ENABLED,
			IvldCustomerGtsActualImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(IvldCustomerGtsActualModelImpl.ENTITY_CACHE_ENABLED,
			IvldCustomerGtsActualModelImpl.FINDER_CACHE_ENABLED,
			IvldCustomerGtsActualImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(IvldCustomerGtsActualModelImpl.ENTITY_CACHE_ENABLED,
			IvldCustomerGtsActualModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public IvldCustomerGtsActualPersistenceImpl() {
		setModelClass(IvldCustomerGtsActual.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("parentAccountId", "PARENT_ACCOUNT_ID");
			dbColumnNames.put("ivldCustomerGtsActualSid",
				"IVLD_CUSTOMER_GTS_ACTUAL_SID");
			dbColumnNames.put("accountId", "ACCOUNT_ID");
			dbColumnNames.put("itemId", "ITEM_ID");
			dbColumnNames.put("orderReceivedDate", "ORDER_RECEIVED_DATE");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("orderNumber", "ORDER_NUMBER");
			dbColumnNames.put("organizationKey", "ORGANIZATION_KEY");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("addChgDelIndicator", "ADD_CHG_DEL_INDICATOR");
			dbColumnNames.put("errorCode", "ERROR_CODE");
			dbColumnNames.put("itemUom", "ITEM_UOM");
			dbColumnNames.put("invoiceNumber", "INVOICE_NUMBER");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("intfInsertedDate", "INTF_INSERTED_DATE");
			dbColumnNames.put("lotNo", "LOT_NO");
			dbColumnNames.put("reprocessedFlag", "REPROCESSED_FLAG");
			dbColumnNames.put("quantity", "QUANTITY");
			dbColumnNames.put("invoiceLineNumber", "INVOICE_LINE_NUMBER");
			dbColumnNames.put("contractId", "CONTRACT_ID");
			dbColumnNames.put("reasonForFailure", "REASON_FOR_FAILURE");
			dbColumnNames.put("amount", "AMOUNT");
			dbColumnNames.put("invoiceDate", "INVOICE_DATE");
			dbColumnNames.put("customerGtsActualIntfId",
				"CUSTOMER_GTS_ACTUAL_INTF_ID");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("salesId", "SALES_ID");
			dbColumnNames.put("errorField", "ERROR_FIELD");
			dbColumnNames.put("checkRecord", "CHECK_RECORD");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the ivld customer gts actual in the entity cache if it is enabled.
	 *
	 * @param ivldCustomerGtsActual the ivld customer gts actual
	 */
	@Override
	public void cacheResult(IvldCustomerGtsActual ivldCustomerGtsActual) {
		entityCache.putResult(IvldCustomerGtsActualModelImpl.ENTITY_CACHE_ENABLED,
			IvldCustomerGtsActualImpl.class,
			ivldCustomerGtsActual.getPrimaryKey(), ivldCustomerGtsActual);

		ivldCustomerGtsActual.resetOriginalValues();
	}

	/**
	 * Caches the ivld customer gts actuals in the entity cache if it is enabled.
	 *
	 * @param ivldCustomerGtsActuals the ivld customer gts actuals
	 */
	@Override
	public void cacheResult(List<IvldCustomerGtsActual> ivldCustomerGtsActuals) {
		for (IvldCustomerGtsActual ivldCustomerGtsActual : ivldCustomerGtsActuals) {
			if (entityCache.getResult(
						IvldCustomerGtsActualModelImpl.ENTITY_CACHE_ENABLED,
						IvldCustomerGtsActualImpl.class,
						ivldCustomerGtsActual.getPrimaryKey()) == null) {
				cacheResult(ivldCustomerGtsActual);
			}
			else {
				ivldCustomerGtsActual.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all ivld customer gts actuals.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(IvldCustomerGtsActualImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the ivld customer gts actual.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(IvldCustomerGtsActual ivldCustomerGtsActual) {
		entityCache.removeResult(IvldCustomerGtsActualModelImpl.ENTITY_CACHE_ENABLED,
			IvldCustomerGtsActualImpl.class,
			ivldCustomerGtsActual.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<IvldCustomerGtsActual> ivldCustomerGtsActuals) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (IvldCustomerGtsActual ivldCustomerGtsActual : ivldCustomerGtsActuals) {
			entityCache.removeResult(IvldCustomerGtsActualModelImpl.ENTITY_CACHE_ENABLED,
				IvldCustomerGtsActualImpl.class,
				ivldCustomerGtsActual.getPrimaryKey());
		}
	}

	/**
	 * Creates a new ivld customer gts actual with the primary key. Does not add the ivld customer gts actual to the database.
	 *
	 * @param ivldCustomerGtsActualSid the primary key for the new ivld customer gts actual
	 * @return the new ivld customer gts actual
	 */
	@Override
	public IvldCustomerGtsActual create(int ivldCustomerGtsActualSid) {
		IvldCustomerGtsActual ivldCustomerGtsActual = new IvldCustomerGtsActualImpl();

		ivldCustomerGtsActual.setNew(true);
		ivldCustomerGtsActual.setPrimaryKey(ivldCustomerGtsActualSid);

		return ivldCustomerGtsActual;
	}

	/**
	 * Removes the ivld customer gts actual with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ivldCustomerGtsActualSid the primary key of the ivld customer gts actual
	 * @return the ivld customer gts actual that was removed
	 * @throws NoSuchIvldCustomerGtsActualException if a ivld customer gts actual with the primary key could not be found
	 */
	@Override
	public IvldCustomerGtsActual remove(int ivldCustomerGtsActualSid)
		throws NoSuchIvldCustomerGtsActualException {
		return remove((Serializable)ivldCustomerGtsActualSid);
	}

	/**
	 * Removes the ivld customer gts actual with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ivld customer gts actual
	 * @return the ivld customer gts actual that was removed
	 * @throws NoSuchIvldCustomerGtsActualException if a ivld customer gts actual with the primary key could not be found
	 */
	@Override
	public IvldCustomerGtsActual remove(Serializable primaryKey)
		throws NoSuchIvldCustomerGtsActualException {
		Session session = null;

		try {
			session = openSession();

			IvldCustomerGtsActual ivldCustomerGtsActual = (IvldCustomerGtsActual)session.get(IvldCustomerGtsActualImpl.class,
					primaryKey);

			if (ivldCustomerGtsActual == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchIvldCustomerGtsActualException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(ivldCustomerGtsActual);
		}
		catch (NoSuchIvldCustomerGtsActualException nsee) {
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
	protected IvldCustomerGtsActual removeImpl(
		IvldCustomerGtsActual ivldCustomerGtsActual) {
		ivldCustomerGtsActual = toUnwrappedModel(ivldCustomerGtsActual);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(ivldCustomerGtsActual)) {
				ivldCustomerGtsActual = (IvldCustomerGtsActual)session.get(IvldCustomerGtsActualImpl.class,
						ivldCustomerGtsActual.getPrimaryKeyObj());
			}

			if (ivldCustomerGtsActual != null) {
				session.delete(ivldCustomerGtsActual);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (ivldCustomerGtsActual != null) {
			clearCache(ivldCustomerGtsActual);
		}

		return ivldCustomerGtsActual;
	}

	@Override
	public IvldCustomerGtsActual updateImpl(
		IvldCustomerGtsActual ivldCustomerGtsActual) {
		ivldCustomerGtsActual = toUnwrappedModel(ivldCustomerGtsActual);

		boolean isNew = ivldCustomerGtsActual.isNew();

		Session session = null;

		try {
			session = openSession();

			if (ivldCustomerGtsActual.isNew()) {
				session.save(ivldCustomerGtsActual);

				ivldCustomerGtsActual.setNew(false);
			}
			else {
				ivldCustomerGtsActual = (IvldCustomerGtsActual)session.merge(ivldCustomerGtsActual);
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

		entityCache.putResult(IvldCustomerGtsActualModelImpl.ENTITY_CACHE_ENABLED,
			IvldCustomerGtsActualImpl.class,
			ivldCustomerGtsActual.getPrimaryKey(), ivldCustomerGtsActual, false);

		ivldCustomerGtsActual.resetOriginalValues();

		return ivldCustomerGtsActual;
	}

	protected IvldCustomerGtsActual toUnwrappedModel(
		IvldCustomerGtsActual ivldCustomerGtsActual) {
		if (ivldCustomerGtsActual instanceof IvldCustomerGtsActualImpl) {
			return ivldCustomerGtsActual;
		}

		IvldCustomerGtsActualImpl ivldCustomerGtsActualImpl = new IvldCustomerGtsActualImpl();

		ivldCustomerGtsActualImpl.setNew(ivldCustomerGtsActual.isNew());
		ivldCustomerGtsActualImpl.setPrimaryKey(ivldCustomerGtsActual.getPrimaryKey());

		ivldCustomerGtsActualImpl.setParentAccountId(ivldCustomerGtsActual.getParentAccountId());
		ivldCustomerGtsActualImpl.setIvldCustomerGtsActualSid(ivldCustomerGtsActual.getIvldCustomerGtsActualSid());
		ivldCustomerGtsActualImpl.setAccountId(ivldCustomerGtsActual.getAccountId());
		ivldCustomerGtsActualImpl.setItemId(ivldCustomerGtsActual.getItemId());
		ivldCustomerGtsActualImpl.setOrderReceivedDate(ivldCustomerGtsActual.getOrderReceivedDate());
		ivldCustomerGtsActualImpl.setModifiedDate(ivldCustomerGtsActual.getModifiedDate());
		ivldCustomerGtsActualImpl.setOrderNumber(ivldCustomerGtsActual.getOrderNumber());
		ivldCustomerGtsActualImpl.setOrganizationKey(ivldCustomerGtsActual.getOrganizationKey());
		ivldCustomerGtsActualImpl.setSource(ivldCustomerGtsActual.getSource());
		ivldCustomerGtsActualImpl.setCreatedBy(ivldCustomerGtsActual.getCreatedBy());
		ivldCustomerGtsActualImpl.setCreatedDate(ivldCustomerGtsActual.getCreatedDate());
		ivldCustomerGtsActualImpl.setAddChgDelIndicator(ivldCustomerGtsActual.getAddChgDelIndicator());
		ivldCustomerGtsActualImpl.setErrorCode(ivldCustomerGtsActual.getErrorCode());
		ivldCustomerGtsActualImpl.setItemUom(ivldCustomerGtsActual.getItemUom());
		ivldCustomerGtsActualImpl.setInvoiceNumber(ivldCustomerGtsActual.getInvoiceNumber());
		ivldCustomerGtsActualImpl.setModifiedBy(ivldCustomerGtsActual.getModifiedBy());
		ivldCustomerGtsActualImpl.setIntfInsertedDate(ivldCustomerGtsActual.getIntfInsertedDate());
		ivldCustomerGtsActualImpl.setLotNo(ivldCustomerGtsActual.getLotNo());
		ivldCustomerGtsActualImpl.setReprocessedFlag(ivldCustomerGtsActual.getReprocessedFlag());
		ivldCustomerGtsActualImpl.setQuantity(ivldCustomerGtsActual.getQuantity());
		ivldCustomerGtsActualImpl.setInvoiceLineNumber(ivldCustomerGtsActual.getInvoiceLineNumber());
		ivldCustomerGtsActualImpl.setContractId(ivldCustomerGtsActual.getContractId());
		ivldCustomerGtsActualImpl.setReasonForFailure(ivldCustomerGtsActual.getReasonForFailure());
		ivldCustomerGtsActualImpl.setAmount(ivldCustomerGtsActual.getAmount());
		ivldCustomerGtsActualImpl.setInvoiceDate(ivldCustomerGtsActual.getInvoiceDate());
		ivldCustomerGtsActualImpl.setCustomerGtsActualIntfId(ivldCustomerGtsActual.getCustomerGtsActualIntfId());
		ivldCustomerGtsActualImpl.setBatchId(ivldCustomerGtsActual.getBatchId());
		ivldCustomerGtsActualImpl.setSalesId(ivldCustomerGtsActual.getSalesId());
		ivldCustomerGtsActualImpl.setErrorField(ivldCustomerGtsActual.getErrorField());
		ivldCustomerGtsActualImpl.setCheckRecord(ivldCustomerGtsActual.isCheckRecord());

		return ivldCustomerGtsActualImpl;
	}

	/**
	 * Returns the ivld customer gts actual with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the ivld customer gts actual
	 * @return the ivld customer gts actual
	 * @throws NoSuchIvldCustomerGtsActualException if a ivld customer gts actual with the primary key could not be found
	 */
	@Override
	public IvldCustomerGtsActual findByPrimaryKey(Serializable primaryKey)
		throws NoSuchIvldCustomerGtsActualException {
		IvldCustomerGtsActual ivldCustomerGtsActual = fetchByPrimaryKey(primaryKey);

		if (ivldCustomerGtsActual == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchIvldCustomerGtsActualException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return ivldCustomerGtsActual;
	}

	/**
	 * Returns the ivld customer gts actual with the primary key or throws a {@link NoSuchIvldCustomerGtsActualException} if it could not be found.
	 *
	 * @param ivldCustomerGtsActualSid the primary key of the ivld customer gts actual
	 * @return the ivld customer gts actual
	 * @throws NoSuchIvldCustomerGtsActualException if a ivld customer gts actual with the primary key could not be found
	 */
	@Override
	public IvldCustomerGtsActual findByPrimaryKey(int ivldCustomerGtsActualSid)
		throws NoSuchIvldCustomerGtsActualException {
		return findByPrimaryKey((Serializable)ivldCustomerGtsActualSid);
	}

	/**
	 * Returns the ivld customer gts actual with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ivld customer gts actual
	 * @return the ivld customer gts actual, or <code>null</code> if a ivld customer gts actual with the primary key could not be found
	 */
	@Override
	public IvldCustomerGtsActual fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(IvldCustomerGtsActualModelImpl.ENTITY_CACHE_ENABLED,
				IvldCustomerGtsActualImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		IvldCustomerGtsActual ivldCustomerGtsActual = (IvldCustomerGtsActual)serializable;

		if (ivldCustomerGtsActual == null) {
			Session session = null;

			try {
				session = openSession();

				ivldCustomerGtsActual = (IvldCustomerGtsActual)session.get(IvldCustomerGtsActualImpl.class,
						primaryKey);

				if (ivldCustomerGtsActual != null) {
					cacheResult(ivldCustomerGtsActual);
				}
				else {
					entityCache.putResult(IvldCustomerGtsActualModelImpl.ENTITY_CACHE_ENABLED,
						IvldCustomerGtsActualImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(IvldCustomerGtsActualModelImpl.ENTITY_CACHE_ENABLED,
					IvldCustomerGtsActualImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return ivldCustomerGtsActual;
	}

	/**
	 * Returns the ivld customer gts actual with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ivldCustomerGtsActualSid the primary key of the ivld customer gts actual
	 * @return the ivld customer gts actual, or <code>null</code> if a ivld customer gts actual with the primary key could not be found
	 */
	@Override
	public IvldCustomerGtsActual fetchByPrimaryKey(int ivldCustomerGtsActualSid) {
		return fetchByPrimaryKey((Serializable)ivldCustomerGtsActualSid);
	}

	@Override
	public Map<Serializable, IvldCustomerGtsActual> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, IvldCustomerGtsActual> map = new HashMap<Serializable, IvldCustomerGtsActual>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			IvldCustomerGtsActual ivldCustomerGtsActual = fetchByPrimaryKey(primaryKey);

			if (ivldCustomerGtsActual != null) {
				map.put(primaryKey, ivldCustomerGtsActual);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(IvldCustomerGtsActualModelImpl.ENTITY_CACHE_ENABLED,
					IvldCustomerGtsActualImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (IvldCustomerGtsActual)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_IVLDCUSTOMERGTSACTUAL_WHERE_PKS_IN);

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

			for (IvldCustomerGtsActual ivldCustomerGtsActual : (List<IvldCustomerGtsActual>)q.list()) {
				map.put(ivldCustomerGtsActual.getPrimaryKeyObj(),
					ivldCustomerGtsActual);

				cacheResult(ivldCustomerGtsActual);

				uncachedPrimaryKeys.remove(ivldCustomerGtsActual.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(IvldCustomerGtsActualModelImpl.ENTITY_CACHE_ENABLED,
					IvldCustomerGtsActualImpl.class, primaryKey, nullModel);
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
	 * Returns all the ivld customer gts actuals.
	 *
	 * @return the ivld customer gts actuals
	 */
	@Override
	public List<IvldCustomerGtsActual> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ivld customer gts actuals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldCustomerGtsActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld customer gts actuals
	 * @param end the upper bound of the range of ivld customer gts actuals (not inclusive)
	 * @return the range of ivld customer gts actuals
	 */
	@Override
	public List<IvldCustomerGtsActual> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ivld customer gts actuals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldCustomerGtsActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld customer gts actuals
	 * @param end the upper bound of the range of ivld customer gts actuals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ivld customer gts actuals
	 */
	@Override
	public List<IvldCustomerGtsActual> findAll(int start, int end,
		OrderByComparator<IvldCustomerGtsActual> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ivld customer gts actuals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldCustomerGtsActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld customer gts actuals
	 * @param end the upper bound of the range of ivld customer gts actuals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of ivld customer gts actuals
	 */
	@Override
	public List<IvldCustomerGtsActual> findAll(int start, int end,
		OrderByComparator<IvldCustomerGtsActual> orderByComparator,
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

		List<IvldCustomerGtsActual> list = null;

		if (retrieveFromCache) {
			list = (List<IvldCustomerGtsActual>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_IVLDCUSTOMERGTSACTUAL);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_IVLDCUSTOMERGTSACTUAL;

				if (pagination) {
					sql = sql.concat(IvldCustomerGtsActualModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<IvldCustomerGtsActual>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<IvldCustomerGtsActual>)QueryUtil.list(q,
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
	 * Removes all the ivld customer gts actuals from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (IvldCustomerGtsActual ivldCustomerGtsActual : findAll()) {
			remove(ivldCustomerGtsActual);
		}
	}

	/**
	 * Returns the number of ivld customer gts actuals.
	 *
	 * @return the number of ivld customer gts actuals
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_IVLDCUSTOMERGTSACTUAL);

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
		return IvldCustomerGtsActualModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ivld customer gts actual persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(IvldCustomerGtsActualImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_IVLDCUSTOMERGTSACTUAL = "SELECT ivldCustomerGtsActual FROM IvldCustomerGtsActual ivldCustomerGtsActual";
	private static final String _SQL_SELECT_IVLDCUSTOMERGTSACTUAL_WHERE_PKS_IN = "SELECT ivldCustomerGtsActual FROM IvldCustomerGtsActual ivldCustomerGtsActual WHERE IVLD_CUSTOMER_GTS_ACTUAL_SID IN (";
	private static final String _SQL_COUNT_IVLDCUSTOMERGTSACTUAL = "SELECT COUNT(ivldCustomerGtsActual) FROM IvldCustomerGtsActual ivldCustomerGtsActual";
	private static final String _ORDER_BY_ENTITY_ALIAS = "ivldCustomerGtsActual.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IvldCustomerGtsActual exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(IvldCustomerGtsActualPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"parentAccountId", "ivldCustomerGtsActualSid", "accountId",
				"itemId", "orderReceivedDate", "modifiedDate", "orderNumber",
				"organizationKey", "source", "createdBy", "createdDate",
				"addChgDelIndicator", "errorCode", "itemUom", "invoiceNumber",
				"modifiedBy", "intfInsertedDate", "lotNo", "reprocessedFlag",
				"quantity", "invoiceLineNumber", "contractId",
				"reasonForFailure", "amount", "invoiceDate",
				"customerGtsActualIntfId", "batchId", "salesId", "errorField",
				"checkRecord"
			});
}