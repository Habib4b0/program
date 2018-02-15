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

package com.stpl.app.service.persistence.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import com.stpl.app.exception.NoSuchImtdRsContractDetailsFrException;
import com.stpl.app.model.ImtdRsContractDetailsFr;
import com.stpl.app.service.ImtdRsContractDetailsFrLocalServiceUtil;
import com.stpl.app.service.persistence.ImtdRsContractDetailsFrPersistence;
import com.stpl.app.service.persistence.ImtdRsContractDetailsFrUtil;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.junit.runner.RunWith;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class ImtdRsContractDetailsFrPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = ImtdRsContractDetailsFrUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<ImtdRsContractDetailsFr> iterator = _imtdRsContractDetailsFrs.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ImtdRsContractDetailsFr imtdRsContractDetailsFr = _persistence.create(pk);

		Assert.assertNotNull(imtdRsContractDetailsFr);

		Assert.assertEquals(imtdRsContractDetailsFr.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		ImtdRsContractDetailsFr newImtdRsContractDetailsFr = addImtdRsContractDetailsFr();

		_persistence.remove(newImtdRsContractDetailsFr);

		ImtdRsContractDetailsFr existingImtdRsContractDetailsFr = _persistence.fetchByPrimaryKey(newImtdRsContractDetailsFr.getPrimaryKey());

		Assert.assertNull(existingImtdRsContractDetailsFr);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addImtdRsContractDetailsFr();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ImtdRsContractDetailsFr newImtdRsContractDetailsFr = _persistence.create(pk);

		newImtdRsContractDetailsFr.setFormulaMethodId(RandomTestUtil.randomString());

		newImtdRsContractDetailsFr.setItemMasterSid(RandomTestUtil.nextInt());

		newImtdRsContractDetailsFr.setRsContractDetailsFrSid(RandomTestUtil.nextInt());

		newImtdRsContractDetailsFr.setModifiedDate(RandomTestUtil.nextDate());

		newImtdRsContractDetailsFr.setRsContractDetailsSid(RandomTestUtil.nextInt());

		newImtdRsContractDetailsFr.setImtdItemPriceRebateDetailsSid(RandomTestUtil.nextInt());

		newImtdRsContractDetailsFr.setRecordLockStatus(RandomTestUtil.randomBoolean());

		newImtdRsContractDetailsFr.setCreatedDate(RandomTestUtil.nextDate());

		newImtdRsContractDetailsFr.setCreatedBy(RandomTestUtil.nextInt());

		newImtdRsContractDetailsFr.setSource(RandomTestUtil.randomString());

		newImtdRsContractDetailsFr.setBatchId(RandomTestUtil.randomString());

		newImtdRsContractDetailsFr.setImtdCreatedDate(RandomTestUtil.nextDate());

		newImtdRsContractDetailsFr.setSessionId(RandomTestUtil.randomString());

		newImtdRsContractDetailsFr.setUsersId(RandomTestUtil.randomString());

		newImtdRsContractDetailsFr.setOperation(RandomTestUtil.randomString());

		newImtdRsContractDetailsFr.setModifiedBy(RandomTestUtil.nextInt());

		newImtdRsContractDetailsFr.setFormulaId(RandomTestUtil.nextInt());

		newImtdRsContractDetailsFr.setInboundStatus(RandomTestUtil.randomString());

		_imtdRsContractDetailsFrs.add(_persistence.update(
				newImtdRsContractDetailsFr));

		ImtdRsContractDetailsFr existingImtdRsContractDetailsFr = _persistence.findByPrimaryKey(newImtdRsContractDetailsFr.getPrimaryKey());

		Assert.assertEquals(existingImtdRsContractDetailsFr.getImtdRsContractDetailsFrSid(),
			newImtdRsContractDetailsFr.getImtdRsContractDetailsFrSid());
		Assert.assertEquals(existingImtdRsContractDetailsFr.getFormulaMethodId(),
			newImtdRsContractDetailsFr.getFormulaMethodId());
		Assert.assertEquals(existingImtdRsContractDetailsFr.getItemMasterSid(),
			newImtdRsContractDetailsFr.getItemMasterSid());
		Assert.assertEquals(existingImtdRsContractDetailsFr.getRsContractDetailsFrSid(),
			newImtdRsContractDetailsFr.getRsContractDetailsFrSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingImtdRsContractDetailsFr.getModifiedDate()),
			Time.getShortTimestamp(newImtdRsContractDetailsFr.getModifiedDate()));
		Assert.assertEquals(existingImtdRsContractDetailsFr.getRsContractDetailsSid(),
			newImtdRsContractDetailsFr.getRsContractDetailsSid());
		Assert.assertEquals(existingImtdRsContractDetailsFr.getImtdItemPriceRebateDetailsSid(),
			newImtdRsContractDetailsFr.getImtdItemPriceRebateDetailsSid());
		Assert.assertEquals(existingImtdRsContractDetailsFr.getRecordLockStatus(),
			newImtdRsContractDetailsFr.getRecordLockStatus());
		Assert.assertEquals(Time.getShortTimestamp(
				existingImtdRsContractDetailsFr.getCreatedDate()),
			Time.getShortTimestamp(newImtdRsContractDetailsFr.getCreatedDate()));
		Assert.assertEquals(existingImtdRsContractDetailsFr.getCreatedBy(),
			newImtdRsContractDetailsFr.getCreatedBy());
		Assert.assertEquals(existingImtdRsContractDetailsFr.getSource(),
			newImtdRsContractDetailsFr.getSource());
		Assert.assertEquals(existingImtdRsContractDetailsFr.getBatchId(),
			newImtdRsContractDetailsFr.getBatchId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingImtdRsContractDetailsFr.getImtdCreatedDate()),
			Time.getShortTimestamp(
				newImtdRsContractDetailsFr.getImtdCreatedDate()));
		Assert.assertEquals(existingImtdRsContractDetailsFr.getSessionId(),
			newImtdRsContractDetailsFr.getSessionId());
		Assert.assertEquals(existingImtdRsContractDetailsFr.getUsersId(),
			newImtdRsContractDetailsFr.getUsersId());
		Assert.assertEquals(existingImtdRsContractDetailsFr.getOperation(),
			newImtdRsContractDetailsFr.getOperation());
		Assert.assertEquals(existingImtdRsContractDetailsFr.getModifiedBy(),
			newImtdRsContractDetailsFr.getModifiedBy());
		Assert.assertEquals(existingImtdRsContractDetailsFr.getFormulaId(),
			newImtdRsContractDetailsFr.getFormulaId());
		Assert.assertEquals(existingImtdRsContractDetailsFr.getInboundStatus(),
			newImtdRsContractDetailsFr.getInboundStatus());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		ImtdRsContractDetailsFr newImtdRsContractDetailsFr = addImtdRsContractDetailsFr();

		ImtdRsContractDetailsFr existingImtdRsContractDetailsFr = _persistence.findByPrimaryKey(newImtdRsContractDetailsFr.getPrimaryKey());

		Assert.assertEquals(existingImtdRsContractDetailsFr,
			newImtdRsContractDetailsFr);
	}

	@Test(expected = NoSuchImtdRsContractDetailsFrException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<ImtdRsContractDetailsFr> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("IMTD_RS_CONTRACT_DETAILS_FR",
			"imtdRsContractDetailsFrSid", true, "formulaMethodId", true,
			"itemMasterSid", true, "rsContractDetailsFrSid", true,
			"modifiedDate", true, "rsContractDetailsSid", true,
			"imtdItemPriceRebateDetailsSid", true, "recordLockStatus", true,
			"createdDate", true, "createdBy", true, "source", true, "batchId",
			true, "imtdCreatedDate", true, "sessionId", true, "usersId", true,
			"operation", true, "modifiedBy", true, "formulaId", true,
			"inboundStatus", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		ImtdRsContractDetailsFr newImtdRsContractDetailsFr = addImtdRsContractDetailsFr();

		ImtdRsContractDetailsFr existingImtdRsContractDetailsFr = _persistence.fetchByPrimaryKey(newImtdRsContractDetailsFr.getPrimaryKey());

		Assert.assertEquals(existingImtdRsContractDetailsFr,
			newImtdRsContractDetailsFr);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ImtdRsContractDetailsFr missingImtdRsContractDetailsFr = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingImtdRsContractDetailsFr);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		ImtdRsContractDetailsFr newImtdRsContractDetailsFr1 = addImtdRsContractDetailsFr();
		ImtdRsContractDetailsFr newImtdRsContractDetailsFr2 = addImtdRsContractDetailsFr();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newImtdRsContractDetailsFr1.getPrimaryKey());
		primaryKeys.add(newImtdRsContractDetailsFr2.getPrimaryKey());

		Map<Serializable, ImtdRsContractDetailsFr> imtdRsContractDetailsFrs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, imtdRsContractDetailsFrs.size());
		Assert.assertEquals(newImtdRsContractDetailsFr1,
			imtdRsContractDetailsFrs.get(
				newImtdRsContractDetailsFr1.getPrimaryKey()));
		Assert.assertEquals(newImtdRsContractDetailsFr2,
			imtdRsContractDetailsFrs.get(
				newImtdRsContractDetailsFr2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, ImtdRsContractDetailsFr> imtdRsContractDetailsFrs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(imtdRsContractDetailsFrs.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		ImtdRsContractDetailsFr newImtdRsContractDetailsFr = addImtdRsContractDetailsFr();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newImtdRsContractDetailsFr.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, ImtdRsContractDetailsFr> imtdRsContractDetailsFrs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, imtdRsContractDetailsFrs.size());
		Assert.assertEquals(newImtdRsContractDetailsFr,
			imtdRsContractDetailsFrs.get(
				newImtdRsContractDetailsFr.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, ImtdRsContractDetailsFr> imtdRsContractDetailsFrs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(imtdRsContractDetailsFrs.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		ImtdRsContractDetailsFr newImtdRsContractDetailsFr = addImtdRsContractDetailsFr();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newImtdRsContractDetailsFr.getPrimaryKey());

		Map<Serializable, ImtdRsContractDetailsFr> imtdRsContractDetailsFrs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, imtdRsContractDetailsFrs.size());
		Assert.assertEquals(newImtdRsContractDetailsFr,
			imtdRsContractDetailsFrs.get(
				newImtdRsContractDetailsFr.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = ImtdRsContractDetailsFrLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<ImtdRsContractDetailsFr>() {
				@Override
				public void performAction(
					ImtdRsContractDetailsFr imtdRsContractDetailsFr) {
					Assert.assertNotNull(imtdRsContractDetailsFr);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		ImtdRsContractDetailsFr newImtdRsContractDetailsFr = addImtdRsContractDetailsFr();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ImtdRsContractDetailsFr.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"imtdRsContractDetailsFrSid",
				newImtdRsContractDetailsFr.getImtdRsContractDetailsFrSid()));

		List<ImtdRsContractDetailsFr> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		ImtdRsContractDetailsFr existingImtdRsContractDetailsFr = result.get(0);

		Assert.assertEquals(existingImtdRsContractDetailsFr,
			newImtdRsContractDetailsFr);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ImtdRsContractDetailsFr.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"imtdRsContractDetailsFrSid", RandomTestUtil.nextInt()));

		List<ImtdRsContractDetailsFr> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		ImtdRsContractDetailsFr newImtdRsContractDetailsFr = addImtdRsContractDetailsFr();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ImtdRsContractDetailsFr.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"imtdRsContractDetailsFrSid"));

		Object newImtdRsContractDetailsFrSid = newImtdRsContractDetailsFr.getImtdRsContractDetailsFrSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"imtdRsContractDetailsFrSid",
				new Object[] { newImtdRsContractDetailsFrSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingImtdRsContractDetailsFrSid = result.get(0);

		Assert.assertEquals(existingImtdRsContractDetailsFrSid,
			newImtdRsContractDetailsFrSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ImtdRsContractDetailsFr.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"imtdRsContractDetailsFrSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"imtdRsContractDetailsFrSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected ImtdRsContractDetailsFr addImtdRsContractDetailsFr()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		ImtdRsContractDetailsFr imtdRsContractDetailsFr = _persistence.create(pk);

		imtdRsContractDetailsFr.setFormulaMethodId(RandomTestUtil.randomString());

		imtdRsContractDetailsFr.setItemMasterSid(RandomTestUtil.nextInt());

		imtdRsContractDetailsFr.setRsContractDetailsFrSid(RandomTestUtil.nextInt());

		imtdRsContractDetailsFr.setModifiedDate(RandomTestUtil.nextDate());

		imtdRsContractDetailsFr.setRsContractDetailsSid(RandomTestUtil.nextInt());

		imtdRsContractDetailsFr.setImtdItemPriceRebateDetailsSid(RandomTestUtil.nextInt());

		imtdRsContractDetailsFr.setRecordLockStatus(RandomTestUtil.randomBoolean());

		imtdRsContractDetailsFr.setCreatedDate(RandomTestUtil.nextDate());

		imtdRsContractDetailsFr.setCreatedBy(RandomTestUtil.nextInt());

		imtdRsContractDetailsFr.setSource(RandomTestUtil.randomString());

		imtdRsContractDetailsFr.setBatchId(RandomTestUtil.randomString());

		imtdRsContractDetailsFr.setImtdCreatedDate(RandomTestUtil.nextDate());

		imtdRsContractDetailsFr.setSessionId(RandomTestUtil.randomString());

		imtdRsContractDetailsFr.setUsersId(RandomTestUtil.randomString());

		imtdRsContractDetailsFr.setOperation(RandomTestUtil.randomString());

		imtdRsContractDetailsFr.setModifiedBy(RandomTestUtil.nextInt());

		imtdRsContractDetailsFr.setFormulaId(RandomTestUtil.nextInt());

		imtdRsContractDetailsFr.setInboundStatus(RandomTestUtil.randomString());

		_imtdRsContractDetailsFrs.add(_persistence.update(
				imtdRsContractDetailsFr));

		return imtdRsContractDetailsFr;
	}

	private List<ImtdRsContractDetailsFr> _imtdRsContractDetailsFrs = new ArrayList<ImtdRsContractDetailsFr>();
	private ImtdRsContractDetailsFrPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}