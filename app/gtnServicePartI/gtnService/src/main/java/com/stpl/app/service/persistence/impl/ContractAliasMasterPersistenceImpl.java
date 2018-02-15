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
import com.liferay.portal.kernel.dao.orm.QueryPos;
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

import com.stpl.app.exception.NoSuchContractAliasMasterException;
import com.stpl.app.model.ContractAliasMaster;
import com.stpl.app.model.impl.ContractAliasMasterImpl;
import com.stpl.app.model.impl.ContractAliasMasterModelImpl;
import com.stpl.app.service.persistence.ContractAliasMasterPersistence;

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
 * The persistence implementation for the contract alias master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ContractAliasMasterPersistence
 * @see com.stpl.app.service.persistence.ContractAliasMasterUtil
 * @generated
 */
@ProviderType
public class ContractAliasMasterPersistenceImpl extends BasePersistenceImpl<ContractAliasMaster>
	implements ContractAliasMasterPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ContractAliasMasterUtil} to access the contract alias master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ContractAliasMasterImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ContractAliasMasterModelImpl.ENTITY_CACHE_ENABLED,
			ContractAliasMasterModelImpl.FINDER_CACHE_ENABLED,
			ContractAliasMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ContractAliasMasterModelImpl.ENTITY_CACHE_ENABLED,
			ContractAliasMasterModelImpl.FINDER_CACHE_ENABLED,
			ContractAliasMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ContractAliasMasterModelImpl.ENTITY_CACHE_ENABLED,
			ContractAliasMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CONTRACTSYSTEMID =
		new FinderPath(ContractAliasMasterModelImpl.ENTITY_CACHE_ENABLED,
			ContractAliasMasterModelImpl.FINDER_CACHE_ENABLED,
			ContractAliasMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByContractSystemId",
			new String[] {
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTRACTSYSTEMID =
		new FinderPath(ContractAliasMasterModelImpl.ENTITY_CACHE_ENABLED,
			ContractAliasMasterModelImpl.FINDER_CACHE_ENABLED,
			ContractAliasMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByContractSystemId", new String[] { Integer.class.getName() },
			ContractAliasMasterModelImpl.CONTRACTMASTERSID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CONTRACTSYSTEMID = new FinderPath(ContractAliasMasterModelImpl.ENTITY_CACHE_ENABLED,
			ContractAliasMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByContractSystemId", new String[] { Integer.class.getName() });

	/**
	 * Returns all the contract alias masters where contractMasterSid = &#63;.
	 *
	 * @param contractMasterSid the contract master sid
	 * @return the matching contract alias masters
	 */
	@Override
	public List<ContractAliasMaster> findByContractSystemId(
		int contractMasterSid) {
		return findByContractSystemId(contractMasterSid, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the contract alias masters where contractMasterSid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContractAliasMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param contractMasterSid the contract master sid
	 * @param start the lower bound of the range of contract alias masters
	 * @param end the upper bound of the range of contract alias masters (not inclusive)
	 * @return the range of matching contract alias masters
	 */
	@Override
	public List<ContractAliasMaster> findByContractSystemId(
		int contractMasterSid, int start, int end) {
		return findByContractSystemId(contractMasterSid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the contract alias masters where contractMasterSid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContractAliasMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param contractMasterSid the contract master sid
	 * @param start the lower bound of the range of contract alias masters
	 * @param end the upper bound of the range of contract alias masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching contract alias masters
	 */
	@Override
	public List<ContractAliasMaster> findByContractSystemId(
		int contractMasterSid, int start, int end,
		OrderByComparator<ContractAliasMaster> orderByComparator) {
		return findByContractSystemId(contractMasterSid, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the contract alias masters where contractMasterSid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContractAliasMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param contractMasterSid the contract master sid
	 * @param start the lower bound of the range of contract alias masters
	 * @param end the upper bound of the range of contract alias masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching contract alias masters
	 */
	@Override
	public List<ContractAliasMaster> findByContractSystemId(
		int contractMasterSid, int start, int end,
		OrderByComparator<ContractAliasMaster> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTRACTSYSTEMID;
			finderArgs = new Object[] { contractMasterSid };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CONTRACTSYSTEMID;
			finderArgs = new Object[] {
					contractMasterSid,
					
					start, end, orderByComparator
				};
		}

		List<ContractAliasMaster> list = null;

		if (retrieveFromCache) {
			list = (List<ContractAliasMaster>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ContractAliasMaster contractAliasMaster : list) {
					if ((contractMasterSid != contractAliasMaster.getContractMasterSid())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_CONTRACTALIASMASTER_WHERE);

			query.append(_FINDER_COLUMN_CONTRACTSYSTEMID_CONTRACTMASTERSID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ContractAliasMasterModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(contractMasterSid);

				if (!pagination) {
					list = (List<ContractAliasMaster>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ContractAliasMaster>)QueryUtil.list(q,
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
	 * Returns the first contract alias master in the ordered set where contractMasterSid = &#63;.
	 *
	 * @param contractMasterSid the contract master sid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contract alias master
	 * @throws NoSuchContractAliasMasterException if a matching contract alias master could not be found
	 */
	@Override
	public ContractAliasMaster findByContractSystemId_First(
		int contractMasterSid,
		OrderByComparator<ContractAliasMaster> orderByComparator)
		throws NoSuchContractAliasMasterException {
		ContractAliasMaster contractAliasMaster = fetchByContractSystemId_First(contractMasterSid,
				orderByComparator);

		if (contractAliasMaster != null) {
			return contractAliasMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("contractMasterSid=");
		msg.append(contractMasterSid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchContractAliasMasterException(msg.toString());
	}

	/**
	 * Returns the first contract alias master in the ordered set where contractMasterSid = &#63;.
	 *
	 * @param contractMasterSid the contract master sid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contract alias master, or <code>null</code> if a matching contract alias master could not be found
	 */
	@Override
	public ContractAliasMaster fetchByContractSystemId_First(
		int contractMasterSid,
		OrderByComparator<ContractAliasMaster> orderByComparator) {
		List<ContractAliasMaster> list = findByContractSystemId(contractMasterSid,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last contract alias master in the ordered set where contractMasterSid = &#63;.
	 *
	 * @param contractMasterSid the contract master sid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contract alias master
	 * @throws NoSuchContractAliasMasterException if a matching contract alias master could not be found
	 */
	@Override
	public ContractAliasMaster findByContractSystemId_Last(
		int contractMasterSid,
		OrderByComparator<ContractAliasMaster> orderByComparator)
		throws NoSuchContractAliasMasterException {
		ContractAliasMaster contractAliasMaster = fetchByContractSystemId_Last(contractMasterSid,
				orderByComparator);

		if (contractAliasMaster != null) {
			return contractAliasMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("contractMasterSid=");
		msg.append(contractMasterSid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchContractAliasMasterException(msg.toString());
	}

	/**
	 * Returns the last contract alias master in the ordered set where contractMasterSid = &#63;.
	 *
	 * @param contractMasterSid the contract master sid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contract alias master, or <code>null</code> if a matching contract alias master could not be found
	 */
	@Override
	public ContractAliasMaster fetchByContractSystemId_Last(
		int contractMasterSid,
		OrderByComparator<ContractAliasMaster> orderByComparator) {
		int count = countByContractSystemId(contractMasterSid);

		if (count == 0) {
			return null;
		}

		List<ContractAliasMaster> list = findByContractSystemId(contractMasterSid,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the contract alias masters before and after the current contract alias master in the ordered set where contractMasterSid = &#63;.
	 *
	 * @param contractAliasMasterSid the primary key of the current contract alias master
	 * @param contractMasterSid the contract master sid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next contract alias master
	 * @throws NoSuchContractAliasMasterException if a contract alias master with the primary key could not be found
	 */
	@Override
	public ContractAliasMaster[] findByContractSystemId_PrevAndNext(
		int contractAliasMasterSid, int contractMasterSid,
		OrderByComparator<ContractAliasMaster> orderByComparator)
		throws NoSuchContractAliasMasterException {
		ContractAliasMaster contractAliasMaster = findByPrimaryKey(contractAliasMasterSid);

		Session session = null;

		try {
			session = openSession();

			ContractAliasMaster[] array = new ContractAliasMasterImpl[3];

			array[0] = getByContractSystemId_PrevAndNext(session,
					contractAliasMaster, contractMasterSid, orderByComparator,
					true);

			array[1] = contractAliasMaster;

			array[2] = getByContractSystemId_PrevAndNext(session,
					contractAliasMaster, contractMasterSid, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ContractAliasMaster getByContractSystemId_PrevAndNext(
		Session session, ContractAliasMaster contractAliasMaster,
		int contractMasterSid,
		OrderByComparator<ContractAliasMaster> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CONTRACTALIASMASTER_WHERE);

		query.append(_FINDER_COLUMN_CONTRACTSYSTEMID_CONTRACTMASTERSID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(ContractAliasMasterModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(contractMasterSid);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(contractAliasMaster);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ContractAliasMaster> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the contract alias masters where contractMasterSid = &#63; from the database.
	 *
	 * @param contractMasterSid the contract master sid
	 */
	@Override
	public void removeByContractSystemId(int contractMasterSid) {
		for (ContractAliasMaster contractAliasMaster : findByContractSystemId(
				contractMasterSid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(contractAliasMaster);
		}
	}

	/**
	 * Returns the number of contract alias masters where contractMasterSid = &#63;.
	 *
	 * @param contractMasterSid the contract master sid
	 * @return the number of matching contract alias masters
	 */
	@Override
	public int countByContractSystemId(int contractMasterSid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CONTRACTSYSTEMID;

		Object[] finderArgs = new Object[] { contractMasterSid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CONTRACTALIASMASTER_WHERE);

			query.append(_FINDER_COLUMN_CONTRACTSYSTEMID_CONTRACTMASTERSID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(contractMasterSid);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_CONTRACTSYSTEMID_CONTRACTMASTERSID_2 =
		"contractAliasMaster.contractMasterSid = ?";

	public ContractAliasMasterPersistenceImpl() {
		setModelClass(ContractAliasMaster.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("contractAliasType", "CONTRACT_ALIAS_TYPE");
			dbColumnNames.put("tpCompanyMasterSid", "TP_COMPANY_MASTER_SID");
			dbColumnNames.put("endDate", "END_DATE");
			dbColumnNames.put("contractAliasMasterSid",
				"CONTRACT_ALIAS_MASTER_SID");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("contractAliasNo", "CONTRACT_ALIAS_NO");
			dbColumnNames.put("recordLockStatus", "RECORD_LOCK_STATUS");
			dbColumnNames.put("startDate", "START_DATE");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("contractMasterSid", "CONTRACT_MASTER_SID");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("contractAliasName", "CONTRACT_ALIAS_NAME");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("inboundStatus", "INBOUND_STATUS");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the contract alias master in the entity cache if it is enabled.
	 *
	 * @param contractAliasMaster the contract alias master
	 */
	@Override
	public void cacheResult(ContractAliasMaster contractAliasMaster) {
		entityCache.putResult(ContractAliasMasterModelImpl.ENTITY_CACHE_ENABLED,
			ContractAliasMasterImpl.class, contractAliasMaster.getPrimaryKey(),
			contractAliasMaster);

		contractAliasMaster.resetOriginalValues();
	}

	/**
	 * Caches the contract alias masters in the entity cache if it is enabled.
	 *
	 * @param contractAliasMasters the contract alias masters
	 */
	@Override
	public void cacheResult(List<ContractAliasMaster> contractAliasMasters) {
		for (ContractAliasMaster contractAliasMaster : contractAliasMasters) {
			if (entityCache.getResult(
						ContractAliasMasterModelImpl.ENTITY_CACHE_ENABLED,
						ContractAliasMasterImpl.class,
						contractAliasMaster.getPrimaryKey()) == null) {
				cacheResult(contractAliasMaster);
			}
			else {
				contractAliasMaster.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all contract alias masters.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ContractAliasMasterImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the contract alias master.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ContractAliasMaster contractAliasMaster) {
		entityCache.removeResult(ContractAliasMasterModelImpl.ENTITY_CACHE_ENABLED,
			ContractAliasMasterImpl.class, contractAliasMaster.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ContractAliasMaster> contractAliasMasters) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ContractAliasMaster contractAliasMaster : contractAliasMasters) {
			entityCache.removeResult(ContractAliasMasterModelImpl.ENTITY_CACHE_ENABLED,
				ContractAliasMasterImpl.class,
				contractAliasMaster.getPrimaryKey());
		}
	}

	/**
	 * Creates a new contract alias master with the primary key. Does not add the contract alias master to the database.
	 *
	 * @param contractAliasMasterSid the primary key for the new contract alias master
	 * @return the new contract alias master
	 */
	@Override
	public ContractAliasMaster create(int contractAliasMasterSid) {
		ContractAliasMaster contractAliasMaster = new ContractAliasMasterImpl();

		contractAliasMaster.setNew(true);
		contractAliasMaster.setPrimaryKey(contractAliasMasterSid);

		return contractAliasMaster;
	}

	/**
	 * Removes the contract alias master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param contractAliasMasterSid the primary key of the contract alias master
	 * @return the contract alias master that was removed
	 * @throws NoSuchContractAliasMasterException if a contract alias master with the primary key could not be found
	 */
	@Override
	public ContractAliasMaster remove(int contractAliasMasterSid)
		throws NoSuchContractAliasMasterException {
		return remove((Serializable)contractAliasMasterSid);
	}

	/**
	 * Removes the contract alias master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the contract alias master
	 * @return the contract alias master that was removed
	 * @throws NoSuchContractAliasMasterException if a contract alias master with the primary key could not be found
	 */
	@Override
	public ContractAliasMaster remove(Serializable primaryKey)
		throws NoSuchContractAliasMasterException {
		Session session = null;

		try {
			session = openSession();

			ContractAliasMaster contractAliasMaster = (ContractAliasMaster)session.get(ContractAliasMasterImpl.class,
					primaryKey);

			if (contractAliasMaster == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchContractAliasMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(contractAliasMaster);
		}
		catch (NoSuchContractAliasMasterException nsee) {
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
	protected ContractAliasMaster removeImpl(
		ContractAliasMaster contractAliasMaster) {
		contractAliasMaster = toUnwrappedModel(contractAliasMaster);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(contractAliasMaster)) {
				contractAliasMaster = (ContractAliasMaster)session.get(ContractAliasMasterImpl.class,
						contractAliasMaster.getPrimaryKeyObj());
			}

			if (contractAliasMaster != null) {
				session.delete(contractAliasMaster);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (contractAliasMaster != null) {
			clearCache(contractAliasMaster);
		}

		return contractAliasMaster;
	}

	@Override
	public ContractAliasMaster updateImpl(
		ContractAliasMaster contractAliasMaster) {
		contractAliasMaster = toUnwrappedModel(contractAliasMaster);

		boolean isNew = contractAliasMaster.isNew();

		ContractAliasMasterModelImpl contractAliasMasterModelImpl = (ContractAliasMasterModelImpl)contractAliasMaster;

		Session session = null;

		try {
			session = openSession();

			if (contractAliasMaster.isNew()) {
				session.save(contractAliasMaster);

				contractAliasMaster.setNew(false);
			}
			else {
				contractAliasMaster = (ContractAliasMaster)session.merge(contractAliasMaster);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!ContractAliasMasterModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					contractAliasMasterModelImpl.getContractMasterSid()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_CONTRACTSYSTEMID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTRACTSYSTEMID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((contractAliasMasterModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTRACTSYSTEMID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						contractAliasMasterModelImpl.getOriginalContractMasterSid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CONTRACTSYSTEMID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTRACTSYSTEMID,
					args);

				args = new Object[] {
						contractAliasMasterModelImpl.getContractMasterSid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CONTRACTSYSTEMID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTRACTSYSTEMID,
					args);
			}
		}

		entityCache.putResult(ContractAliasMasterModelImpl.ENTITY_CACHE_ENABLED,
			ContractAliasMasterImpl.class, contractAliasMaster.getPrimaryKey(),
			contractAliasMaster, false);

		contractAliasMaster.resetOriginalValues();

		return contractAliasMaster;
	}

	protected ContractAliasMaster toUnwrappedModel(
		ContractAliasMaster contractAliasMaster) {
		if (contractAliasMaster instanceof ContractAliasMasterImpl) {
			return contractAliasMaster;
		}

		ContractAliasMasterImpl contractAliasMasterImpl = new ContractAliasMasterImpl();

		contractAliasMasterImpl.setNew(contractAliasMaster.isNew());
		contractAliasMasterImpl.setPrimaryKey(contractAliasMaster.getPrimaryKey());

		contractAliasMasterImpl.setContractAliasType(contractAliasMaster.getContractAliasType());
		contractAliasMasterImpl.setTpCompanyMasterSid(contractAliasMaster.getTpCompanyMasterSid());
		contractAliasMasterImpl.setEndDate(contractAliasMaster.getEndDate());
		contractAliasMasterImpl.setContractAliasMasterSid(contractAliasMaster.getContractAliasMasterSid());
		contractAliasMasterImpl.setModifiedDate(contractAliasMaster.getModifiedDate());
		contractAliasMasterImpl.setContractAliasNo(contractAliasMaster.getContractAliasNo());
		contractAliasMasterImpl.setRecordLockStatus(contractAliasMaster.isRecordLockStatus());
		contractAliasMasterImpl.setStartDate(contractAliasMaster.getStartDate());
		contractAliasMasterImpl.setCreatedDate(contractAliasMaster.getCreatedDate());
		contractAliasMasterImpl.setSource(contractAliasMaster.getSource());
		contractAliasMasterImpl.setCreatedBy(contractAliasMaster.getCreatedBy());
		contractAliasMasterImpl.setContractMasterSid(contractAliasMaster.getContractMasterSid());
		contractAliasMasterImpl.setBatchId(contractAliasMaster.getBatchId());
		contractAliasMasterImpl.setContractAliasName(contractAliasMaster.getContractAliasName());
		contractAliasMasterImpl.setModifiedBy(contractAliasMaster.getModifiedBy());
		contractAliasMasterImpl.setInboundStatus(contractAliasMaster.getInboundStatus());

		return contractAliasMasterImpl;
	}

	/**
	 * Returns the contract alias master with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the contract alias master
	 * @return the contract alias master
	 * @throws NoSuchContractAliasMasterException if a contract alias master with the primary key could not be found
	 */
	@Override
	public ContractAliasMaster findByPrimaryKey(Serializable primaryKey)
		throws NoSuchContractAliasMasterException {
		ContractAliasMaster contractAliasMaster = fetchByPrimaryKey(primaryKey);

		if (contractAliasMaster == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchContractAliasMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return contractAliasMaster;
	}

	/**
	 * Returns the contract alias master with the primary key or throws a {@link NoSuchContractAliasMasterException} if it could not be found.
	 *
	 * @param contractAliasMasterSid the primary key of the contract alias master
	 * @return the contract alias master
	 * @throws NoSuchContractAliasMasterException if a contract alias master with the primary key could not be found
	 */
	@Override
	public ContractAliasMaster findByPrimaryKey(int contractAliasMasterSid)
		throws NoSuchContractAliasMasterException {
		return findByPrimaryKey((Serializable)contractAliasMasterSid);
	}

	/**
	 * Returns the contract alias master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the contract alias master
	 * @return the contract alias master, or <code>null</code> if a contract alias master with the primary key could not be found
	 */
	@Override
	public ContractAliasMaster fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ContractAliasMasterModelImpl.ENTITY_CACHE_ENABLED,
				ContractAliasMasterImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ContractAliasMaster contractAliasMaster = (ContractAliasMaster)serializable;

		if (contractAliasMaster == null) {
			Session session = null;

			try {
				session = openSession();

				contractAliasMaster = (ContractAliasMaster)session.get(ContractAliasMasterImpl.class,
						primaryKey);

				if (contractAliasMaster != null) {
					cacheResult(contractAliasMaster);
				}
				else {
					entityCache.putResult(ContractAliasMasterModelImpl.ENTITY_CACHE_ENABLED,
						ContractAliasMasterImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ContractAliasMasterModelImpl.ENTITY_CACHE_ENABLED,
					ContractAliasMasterImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return contractAliasMaster;
	}

	/**
	 * Returns the contract alias master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param contractAliasMasterSid the primary key of the contract alias master
	 * @return the contract alias master, or <code>null</code> if a contract alias master with the primary key could not be found
	 */
	@Override
	public ContractAliasMaster fetchByPrimaryKey(int contractAliasMasterSid) {
		return fetchByPrimaryKey((Serializable)contractAliasMasterSid);
	}

	@Override
	public Map<Serializable, ContractAliasMaster> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ContractAliasMaster> map = new HashMap<Serializable, ContractAliasMaster>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ContractAliasMaster contractAliasMaster = fetchByPrimaryKey(primaryKey);

			if (contractAliasMaster != null) {
				map.put(primaryKey, contractAliasMaster);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ContractAliasMasterModelImpl.ENTITY_CACHE_ENABLED,
					ContractAliasMasterImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ContractAliasMaster)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_CONTRACTALIASMASTER_WHERE_PKS_IN);

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

			for (ContractAliasMaster contractAliasMaster : (List<ContractAliasMaster>)q.list()) {
				map.put(contractAliasMaster.getPrimaryKeyObj(),
					contractAliasMaster);

				cacheResult(contractAliasMaster);

				uncachedPrimaryKeys.remove(contractAliasMaster.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ContractAliasMasterModelImpl.ENTITY_CACHE_ENABLED,
					ContractAliasMasterImpl.class, primaryKey, nullModel);
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
	 * Returns all the contract alias masters.
	 *
	 * @return the contract alias masters
	 */
	@Override
	public List<ContractAliasMaster> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the contract alias masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContractAliasMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of contract alias masters
	 * @param end the upper bound of the range of contract alias masters (not inclusive)
	 * @return the range of contract alias masters
	 */
	@Override
	public List<ContractAliasMaster> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the contract alias masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContractAliasMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of contract alias masters
	 * @param end the upper bound of the range of contract alias masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of contract alias masters
	 */
	@Override
	public List<ContractAliasMaster> findAll(int start, int end,
		OrderByComparator<ContractAliasMaster> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the contract alias masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContractAliasMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of contract alias masters
	 * @param end the upper bound of the range of contract alias masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of contract alias masters
	 */
	@Override
	public List<ContractAliasMaster> findAll(int start, int end,
		OrderByComparator<ContractAliasMaster> orderByComparator,
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

		List<ContractAliasMaster> list = null;

		if (retrieveFromCache) {
			list = (List<ContractAliasMaster>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CONTRACTALIASMASTER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CONTRACTALIASMASTER;

				if (pagination) {
					sql = sql.concat(ContractAliasMasterModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ContractAliasMaster>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ContractAliasMaster>)QueryUtil.list(q,
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
	 * Removes all the contract alias masters from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ContractAliasMaster contractAliasMaster : findAll()) {
			remove(contractAliasMaster);
		}
	}

	/**
	 * Returns the number of contract alias masters.
	 *
	 * @return the number of contract alias masters
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CONTRACTALIASMASTER);

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
		return ContractAliasMasterModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the contract alias master persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ContractAliasMasterImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_CONTRACTALIASMASTER = "SELECT contractAliasMaster FROM ContractAliasMaster contractAliasMaster";
	private static final String _SQL_SELECT_CONTRACTALIASMASTER_WHERE_PKS_IN = "SELECT contractAliasMaster FROM ContractAliasMaster contractAliasMaster WHERE CONTRACT_ALIAS_MASTER_SID IN (";
	private static final String _SQL_SELECT_CONTRACTALIASMASTER_WHERE = "SELECT contractAliasMaster FROM ContractAliasMaster contractAliasMaster WHERE ";
	private static final String _SQL_COUNT_CONTRACTALIASMASTER = "SELECT COUNT(contractAliasMaster) FROM ContractAliasMaster contractAliasMaster";
	private static final String _SQL_COUNT_CONTRACTALIASMASTER_WHERE = "SELECT COUNT(contractAliasMaster) FROM ContractAliasMaster contractAliasMaster WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "contractAliasMaster.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ContractAliasMaster exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ContractAliasMaster exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ContractAliasMasterPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"contractAliasType", "tpCompanyMasterSid", "endDate",
				"contractAliasMasterSid", "modifiedDate", "contractAliasNo",
				"recordLockStatus", "startDate", "createdDate", "source",
				"createdBy", "contractMasterSid", "batchId", "contractAliasName",
				"modifiedBy", "inboundStatus"
			});
}