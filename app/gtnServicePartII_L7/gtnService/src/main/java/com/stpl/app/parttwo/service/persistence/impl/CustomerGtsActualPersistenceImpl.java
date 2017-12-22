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

import com.stpl.app.parttwo.exception.NoSuchCustomerGtsActualException;
import com.stpl.app.parttwo.model.CustomerGtsActual;
import com.stpl.app.parttwo.model.impl.CustomerGtsActualImpl;
import com.stpl.app.parttwo.model.impl.CustomerGtsActualModelImpl;
import com.stpl.app.parttwo.service.persistence.CustomerGtsActualPersistence;

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
 * The persistence implementation for the customer gts actual service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CustomerGtsActualPersistence
 * @see com.stpl.app.parttwo.service.persistence.CustomerGtsActualUtil
 * @generated
 */
@ProviderType
public class CustomerGtsActualPersistenceImpl extends BasePersistenceImpl<CustomerGtsActual>
	implements CustomerGtsActualPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CustomerGtsActualUtil} to access the customer gts actual persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CustomerGtsActualImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CustomerGtsActualModelImpl.ENTITY_CACHE_ENABLED,
			CustomerGtsActualModelImpl.FINDER_CACHE_ENABLED,
			CustomerGtsActualImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CustomerGtsActualModelImpl.ENTITY_CACHE_ENABLED,
			CustomerGtsActualModelImpl.FINDER_CACHE_ENABLED,
			CustomerGtsActualImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CustomerGtsActualModelImpl.ENTITY_CACHE_ENABLED,
			CustomerGtsActualModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public CustomerGtsActualPersistenceImpl() {
		setModelClass(CustomerGtsActual.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("parentAccountId", "PARENT_ACCOUNT_ID");
			dbColumnNames.put("contractId", "CONTRACT_ID");
			dbColumnNames.put("accountId", "ACCOUNT_ID");
			dbColumnNames.put("customerGtsActualSid", "CUSTOMER_GTS_ACTUAL_SID");
			dbColumnNames.put("orderReceivedDate", "ORDER_RECEIVED_DATE");
			dbColumnNames.put("itemId", "ITEM_ID");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("amount", "AMOUNT");
			dbColumnNames.put("orderNumber", "ORDER_NUMBER");
			dbColumnNames.put("organizationKey", "ORGANIZATION_KEY");
			dbColumnNames.put("invoiceDate", "INVOICE_DATE");
			dbColumnNames.put("customerGtsActualIntfId",
				"CUSTOMER_GTS_ACTUAL_INTF_ID");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("salesId", "SALES_ID");
			dbColumnNames.put("itemUom", "ITEM_UOM");
			dbColumnNames.put("inboundStatus", "INBOUND_STATUS");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("invoiceNumber", "INVOICE_NUMBER");
			dbColumnNames.put("lotNo", "LOT_NO");
			dbColumnNames.put("invoiceLineNumber", "INVOICE_LINE_NUMBER");
			dbColumnNames.put("quantity", "QUANTITY");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the customer gts actual in the entity cache if it is enabled.
	 *
	 * @param customerGtsActual the customer gts actual
	 */
	@Override
	public void cacheResult(CustomerGtsActual customerGtsActual) {
		entityCache.putResult(CustomerGtsActualModelImpl.ENTITY_CACHE_ENABLED,
			CustomerGtsActualImpl.class, customerGtsActual.getPrimaryKey(),
			customerGtsActual);

		customerGtsActual.resetOriginalValues();
	}

	/**
	 * Caches the customer gts actuals in the entity cache if it is enabled.
	 *
	 * @param customerGtsActuals the customer gts actuals
	 */
	@Override
	public void cacheResult(List<CustomerGtsActual> customerGtsActuals) {
		for (CustomerGtsActual customerGtsActual : customerGtsActuals) {
			if (entityCache.getResult(
						CustomerGtsActualModelImpl.ENTITY_CACHE_ENABLED,
						CustomerGtsActualImpl.class,
						customerGtsActual.getPrimaryKey()) == null) {
				cacheResult(customerGtsActual);
			}
			else {
				customerGtsActual.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all customer gts actuals.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CustomerGtsActualImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the customer gts actual.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CustomerGtsActual customerGtsActual) {
		entityCache.removeResult(CustomerGtsActualModelImpl.ENTITY_CACHE_ENABLED,
			CustomerGtsActualImpl.class, customerGtsActual.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<CustomerGtsActual> customerGtsActuals) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CustomerGtsActual customerGtsActual : customerGtsActuals) {
			entityCache.removeResult(CustomerGtsActualModelImpl.ENTITY_CACHE_ENABLED,
				CustomerGtsActualImpl.class, customerGtsActual.getPrimaryKey());
		}
	}

	/**
	 * Creates a new customer gts actual with the primary key. Does not add the customer gts actual to the database.
	 *
	 * @param customerGtsActualSid the primary key for the new customer gts actual
	 * @return the new customer gts actual
	 */
	@Override
	public CustomerGtsActual create(int customerGtsActualSid) {
		CustomerGtsActual customerGtsActual = new CustomerGtsActualImpl();

		customerGtsActual.setNew(true);
		customerGtsActual.setPrimaryKey(customerGtsActualSid);

		return customerGtsActual;
	}

	/**
	 * Removes the customer gts actual with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param customerGtsActualSid the primary key of the customer gts actual
	 * @return the customer gts actual that was removed
	 * @throws NoSuchCustomerGtsActualException if a customer gts actual with the primary key could not be found
	 */
	@Override
	public CustomerGtsActual remove(int customerGtsActualSid)
		throws NoSuchCustomerGtsActualException {
		return remove((Serializable)customerGtsActualSid);
	}

	/**
	 * Removes the customer gts actual with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the customer gts actual
	 * @return the customer gts actual that was removed
	 * @throws NoSuchCustomerGtsActualException if a customer gts actual with the primary key could not be found
	 */
	@Override
	public CustomerGtsActual remove(Serializable primaryKey)
		throws NoSuchCustomerGtsActualException {
		Session session = null;

		try {
			session = openSession();

			CustomerGtsActual customerGtsActual = (CustomerGtsActual)session.get(CustomerGtsActualImpl.class,
					primaryKey);

			if (customerGtsActual == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCustomerGtsActualException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(customerGtsActual);
		}
		catch (NoSuchCustomerGtsActualException nsee) {
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
	protected CustomerGtsActual removeImpl(CustomerGtsActual customerGtsActual) {
		customerGtsActual = toUnwrappedModel(customerGtsActual);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(customerGtsActual)) {
				customerGtsActual = (CustomerGtsActual)session.get(CustomerGtsActualImpl.class,
						customerGtsActual.getPrimaryKeyObj());
			}

			if (customerGtsActual != null) {
				session.delete(customerGtsActual);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (customerGtsActual != null) {
			clearCache(customerGtsActual);
		}

		return customerGtsActual;
	}

	@Override
	public CustomerGtsActual updateImpl(CustomerGtsActual customerGtsActual) {
		customerGtsActual = toUnwrappedModel(customerGtsActual);

		boolean isNew = customerGtsActual.isNew();

		Session session = null;

		try {
			session = openSession();

			if (customerGtsActual.isNew()) {
				session.save(customerGtsActual);

				customerGtsActual.setNew(false);
			}
			else {
				customerGtsActual = (CustomerGtsActual)session.merge(customerGtsActual);
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

		entityCache.putResult(CustomerGtsActualModelImpl.ENTITY_CACHE_ENABLED,
			CustomerGtsActualImpl.class, customerGtsActual.getPrimaryKey(),
			customerGtsActual, false);

		customerGtsActual.resetOriginalValues();

		return customerGtsActual;
	}

	protected CustomerGtsActual toUnwrappedModel(
		CustomerGtsActual customerGtsActual) {
		if (customerGtsActual instanceof CustomerGtsActualImpl) {
			return customerGtsActual;
		}

		CustomerGtsActualImpl customerGtsActualImpl = new CustomerGtsActualImpl();

		customerGtsActualImpl.setNew(customerGtsActual.isNew());
		customerGtsActualImpl.setPrimaryKey(customerGtsActual.getPrimaryKey());

		customerGtsActualImpl.setParentAccountId(customerGtsActual.getParentAccountId());
		customerGtsActualImpl.setContractId(customerGtsActual.getContractId());
		customerGtsActualImpl.setAccountId(customerGtsActual.getAccountId());
		customerGtsActualImpl.setCustomerGtsActualSid(customerGtsActual.getCustomerGtsActualSid());
		customerGtsActualImpl.setOrderReceivedDate(customerGtsActual.getOrderReceivedDate());
		customerGtsActualImpl.setItemId(customerGtsActual.getItemId());
		customerGtsActualImpl.setModifiedDate(customerGtsActual.getModifiedDate());
		customerGtsActualImpl.setAmount(customerGtsActual.getAmount());
		customerGtsActualImpl.setOrderNumber(customerGtsActual.getOrderNumber());
		customerGtsActualImpl.setOrganizationKey(customerGtsActual.getOrganizationKey());
		customerGtsActualImpl.setInvoiceDate(customerGtsActual.getInvoiceDate());
		customerGtsActualImpl.setCustomerGtsActualIntfId(customerGtsActual.getCustomerGtsActualIntfId());
		customerGtsActualImpl.setCreatedDate(customerGtsActual.getCreatedDate());
		customerGtsActualImpl.setCreatedBy(customerGtsActual.getCreatedBy());
		customerGtsActualImpl.setSource(customerGtsActual.getSource());
		customerGtsActualImpl.setBatchId(customerGtsActual.getBatchId());
		customerGtsActualImpl.setSalesId(customerGtsActual.getSalesId());
		customerGtsActualImpl.setItemUom(customerGtsActual.getItemUom());
		customerGtsActualImpl.setInboundStatus(customerGtsActual.getInboundStatus());
		customerGtsActualImpl.setModifiedBy(customerGtsActual.getModifiedBy());
		customerGtsActualImpl.setInvoiceNumber(customerGtsActual.getInvoiceNumber());
		customerGtsActualImpl.setLotNo(customerGtsActual.getLotNo());
		customerGtsActualImpl.setInvoiceLineNumber(customerGtsActual.getInvoiceLineNumber());
		customerGtsActualImpl.setQuantity(customerGtsActual.getQuantity());

		return customerGtsActualImpl;
	}

	/**
	 * Returns the customer gts actual with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the customer gts actual
	 * @return the customer gts actual
	 * @throws NoSuchCustomerGtsActualException if a customer gts actual with the primary key could not be found
	 */
	@Override
	public CustomerGtsActual findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCustomerGtsActualException {
		CustomerGtsActual customerGtsActual = fetchByPrimaryKey(primaryKey);

		if (customerGtsActual == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCustomerGtsActualException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return customerGtsActual;
	}

	/**
	 * Returns the customer gts actual with the primary key or throws a {@link NoSuchCustomerGtsActualException} if it could not be found.
	 *
	 * @param customerGtsActualSid the primary key of the customer gts actual
	 * @return the customer gts actual
	 * @throws NoSuchCustomerGtsActualException if a customer gts actual with the primary key could not be found
	 */
	@Override
	public CustomerGtsActual findByPrimaryKey(int customerGtsActualSid)
		throws NoSuchCustomerGtsActualException {
		return findByPrimaryKey((Serializable)customerGtsActualSid);
	}

	/**
	 * Returns the customer gts actual with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the customer gts actual
	 * @return the customer gts actual, or <code>null</code> if a customer gts actual with the primary key could not be found
	 */
	@Override
	public CustomerGtsActual fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(CustomerGtsActualModelImpl.ENTITY_CACHE_ENABLED,
				CustomerGtsActualImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CustomerGtsActual customerGtsActual = (CustomerGtsActual)serializable;

		if (customerGtsActual == null) {
			Session session = null;

			try {
				session = openSession();

				customerGtsActual = (CustomerGtsActual)session.get(CustomerGtsActualImpl.class,
						primaryKey);

				if (customerGtsActual != null) {
					cacheResult(customerGtsActual);
				}
				else {
					entityCache.putResult(CustomerGtsActualModelImpl.ENTITY_CACHE_ENABLED,
						CustomerGtsActualImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(CustomerGtsActualModelImpl.ENTITY_CACHE_ENABLED,
					CustomerGtsActualImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return customerGtsActual;
	}

	/**
	 * Returns the customer gts actual with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param customerGtsActualSid the primary key of the customer gts actual
	 * @return the customer gts actual, or <code>null</code> if a customer gts actual with the primary key could not be found
	 */
	@Override
	public CustomerGtsActual fetchByPrimaryKey(int customerGtsActualSid) {
		return fetchByPrimaryKey((Serializable)customerGtsActualSid);
	}

	@Override
	public Map<Serializable, CustomerGtsActual> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CustomerGtsActual> map = new HashMap<Serializable, CustomerGtsActual>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CustomerGtsActual customerGtsActual = fetchByPrimaryKey(primaryKey);

			if (customerGtsActual != null) {
				map.put(primaryKey, customerGtsActual);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(CustomerGtsActualModelImpl.ENTITY_CACHE_ENABLED,
					CustomerGtsActualImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CustomerGtsActual)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_CUSTOMERGTSACTUAL_WHERE_PKS_IN);

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

			for (CustomerGtsActual customerGtsActual : (List<CustomerGtsActual>)q.list()) {
				map.put(customerGtsActual.getPrimaryKeyObj(), customerGtsActual);

				cacheResult(customerGtsActual);

				uncachedPrimaryKeys.remove(customerGtsActual.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(CustomerGtsActualModelImpl.ENTITY_CACHE_ENABLED,
					CustomerGtsActualImpl.class, primaryKey, nullModel);
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
	 * Returns all the customer gts actuals.
	 *
	 * @return the customer gts actuals
	 */
	@Override
	public List<CustomerGtsActual> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the customer gts actuals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CustomerGtsActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of customer gts actuals
	 * @param end the upper bound of the range of customer gts actuals (not inclusive)
	 * @return the range of customer gts actuals
	 */
	@Override
	public List<CustomerGtsActual> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the customer gts actuals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CustomerGtsActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of customer gts actuals
	 * @param end the upper bound of the range of customer gts actuals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of customer gts actuals
	 */
	@Override
	public List<CustomerGtsActual> findAll(int start, int end,
		OrderByComparator<CustomerGtsActual> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the customer gts actuals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CustomerGtsActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of customer gts actuals
	 * @param end the upper bound of the range of customer gts actuals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of customer gts actuals
	 */
	@Override
	public List<CustomerGtsActual> findAll(int start, int end,
		OrderByComparator<CustomerGtsActual> orderByComparator,
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

		List<CustomerGtsActual> list = null;

		if (retrieveFromCache) {
			list = (List<CustomerGtsActual>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CUSTOMERGTSACTUAL);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CUSTOMERGTSACTUAL;

				if (pagination) {
					sql = sql.concat(CustomerGtsActualModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CustomerGtsActual>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CustomerGtsActual>)QueryUtil.list(q,
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
	 * Removes all the customer gts actuals from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CustomerGtsActual customerGtsActual : findAll()) {
			remove(customerGtsActual);
		}
	}

	/**
	 * Returns the number of customer gts actuals.
	 *
	 * @return the number of customer gts actuals
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CUSTOMERGTSACTUAL);

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
		return CustomerGtsActualModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the customer gts actual persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(CustomerGtsActualImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_CUSTOMERGTSACTUAL = "SELECT customerGtsActual FROM CustomerGtsActual customerGtsActual";
	private static final String _SQL_SELECT_CUSTOMERGTSACTUAL_WHERE_PKS_IN = "SELECT customerGtsActual FROM CustomerGtsActual customerGtsActual WHERE CUSTOMER_GTS_ACTUAL_SID IN (";
	private static final String _SQL_COUNT_CUSTOMERGTSACTUAL = "SELECT COUNT(customerGtsActual) FROM CustomerGtsActual customerGtsActual";
	private static final String _ORDER_BY_ENTITY_ALIAS = "customerGtsActual.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CustomerGtsActual exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(CustomerGtsActualPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"parentAccountId", "contractId", "accountId",
				"customerGtsActualSid", "orderReceivedDate", "itemId",
				"modifiedDate", "amount", "orderNumber", "organizationKey",
				"invoiceDate", "customerGtsActualIntfId", "createdDate",
				"createdBy", "source", "batchId", "salesId", "itemUom",
				"inboundStatus", "modifiedBy", "invoiceNumber", "lotNo",
				"invoiceLineNumber", "quantity"
			});
}