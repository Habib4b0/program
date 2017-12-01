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

import com.stpl.app.exception.NoSuchImtdRsDetailsFrException;
import com.stpl.app.model.ImtdRsDetailsFr;
import com.stpl.app.service.ImtdRsDetailsFrLocalServiceUtil;
import com.stpl.app.service.persistence.ImtdRsDetailsFrPersistence;
import com.stpl.app.service.persistence.ImtdRsDetailsFrUtil;

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
public class ImtdRsDetailsFrPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = ImtdRsDetailsFrUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<ImtdRsDetailsFr> iterator = _imtdRsDetailsFrs.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ImtdRsDetailsFr imtdRsDetailsFr = _persistence.create(pk);

		Assert.assertNotNull(imtdRsDetailsFr);

		Assert.assertEquals(imtdRsDetailsFr.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		ImtdRsDetailsFr newImtdRsDetailsFr = addImtdRsDetailsFr();

		_persistence.remove(newImtdRsDetailsFr);

		ImtdRsDetailsFr existingImtdRsDetailsFr = _persistence.fetchByPrimaryKey(newImtdRsDetailsFr.getPrimaryKey());

		Assert.assertNull(existingImtdRsDetailsFr);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addImtdRsDetailsFr();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ImtdRsDetailsFr newImtdRsDetailsFr = _persistence.create(pk);

		newImtdRsDetailsFr.setFormulaMethodId(RandomTestUtil.randomString());

		newImtdRsDetailsFr.setItemMasterSid(RandomTestUtil.nextInt());

		newImtdRsDetailsFr.setImtdRsDetailsSid(RandomTestUtil.nextInt());

		newImtdRsDetailsFr.setModifiedDate(RandomTestUtil.nextDate());

		newImtdRsDetailsFr.setRsDetailsFrSid(RandomTestUtil.nextInt());

		newImtdRsDetailsFr.setRecordLockStatus(RandomTestUtil.randomBoolean());

		newImtdRsDetailsFr.setCreatedDate(RandomTestUtil.nextDate());

		newImtdRsDetailsFr.setCreatedBy(RandomTestUtil.nextInt());

		newImtdRsDetailsFr.setSource(RandomTestUtil.randomString());

		newImtdRsDetailsFr.setBatchId(RandomTestUtil.randomString());

		newImtdRsDetailsFr.setImtdCreatedDate(RandomTestUtil.nextDate());

		newImtdRsDetailsFr.setSessionId(RandomTestUtil.randomString());

		newImtdRsDetailsFr.setUsersId(RandomTestUtil.randomString());

		newImtdRsDetailsFr.setOperation(RandomTestUtil.randomString());

		newImtdRsDetailsFr.setModifiedBy(RandomTestUtil.nextInt());

		newImtdRsDetailsFr.setRsDetailsSid(RandomTestUtil.nextInt());

		newImtdRsDetailsFr.setFormulaId(RandomTestUtil.nextInt());

		newImtdRsDetailsFr.setInboundStatus(RandomTestUtil.randomString());

		_imtdRsDetailsFrs.add(_persistence.update(newImtdRsDetailsFr));

		ImtdRsDetailsFr existingImtdRsDetailsFr = _persistence.findByPrimaryKey(newImtdRsDetailsFr.getPrimaryKey());

		Assert.assertEquals(existingImtdRsDetailsFr.getFormulaMethodId(),
			newImtdRsDetailsFr.getFormulaMethodId());
		Assert.assertEquals(existingImtdRsDetailsFr.getItemMasterSid(),
			newImtdRsDetailsFr.getItemMasterSid());
		Assert.assertEquals(existingImtdRsDetailsFr.getImtdRsDetailsSid(),
			newImtdRsDetailsFr.getImtdRsDetailsSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingImtdRsDetailsFr.getModifiedDate()),
			Time.getShortTimestamp(newImtdRsDetailsFr.getModifiedDate()));
		Assert.assertEquals(existingImtdRsDetailsFr.getRsDetailsFrSid(),
			newImtdRsDetailsFr.getRsDetailsFrSid());
		Assert.assertEquals(existingImtdRsDetailsFr.getRecordLockStatus(),
			newImtdRsDetailsFr.getRecordLockStatus());
		Assert.assertEquals(Time.getShortTimestamp(
				existingImtdRsDetailsFr.getCreatedDate()),
			Time.getShortTimestamp(newImtdRsDetailsFr.getCreatedDate()));
		Assert.assertEquals(existingImtdRsDetailsFr.getCreatedBy(),
			newImtdRsDetailsFr.getCreatedBy());
		Assert.assertEquals(existingImtdRsDetailsFr.getSource(),
			newImtdRsDetailsFr.getSource());
		Assert.assertEquals(existingImtdRsDetailsFr.getImtdRsDetailsFrSid(),
			newImtdRsDetailsFr.getImtdRsDetailsFrSid());
		Assert.assertEquals(existingImtdRsDetailsFr.getBatchId(),
			newImtdRsDetailsFr.getBatchId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingImtdRsDetailsFr.getImtdCreatedDate()),
			Time.getShortTimestamp(newImtdRsDetailsFr.getImtdCreatedDate()));
		Assert.assertEquals(existingImtdRsDetailsFr.getSessionId(),
			newImtdRsDetailsFr.getSessionId());
		Assert.assertEquals(existingImtdRsDetailsFr.getUsersId(),
			newImtdRsDetailsFr.getUsersId());
		Assert.assertEquals(existingImtdRsDetailsFr.getOperation(),
			newImtdRsDetailsFr.getOperation());
		Assert.assertEquals(existingImtdRsDetailsFr.getModifiedBy(),
			newImtdRsDetailsFr.getModifiedBy());
		Assert.assertEquals(existingImtdRsDetailsFr.getRsDetailsSid(),
			newImtdRsDetailsFr.getRsDetailsSid());
		Assert.assertEquals(existingImtdRsDetailsFr.getFormulaId(),
			newImtdRsDetailsFr.getFormulaId());
		Assert.assertEquals(existingImtdRsDetailsFr.getInboundStatus(),
			newImtdRsDetailsFr.getInboundStatus());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		ImtdRsDetailsFr newImtdRsDetailsFr = addImtdRsDetailsFr();

		ImtdRsDetailsFr existingImtdRsDetailsFr = _persistence.findByPrimaryKey(newImtdRsDetailsFr.getPrimaryKey());

		Assert.assertEquals(existingImtdRsDetailsFr, newImtdRsDetailsFr);
	}

	@Test(expected = NoSuchImtdRsDetailsFrException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<ImtdRsDetailsFr> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("IMTD_RS_DETAILS_FR",
			"formulaMethodId", true, "itemMasterSid", true, "imtdRsDetailsSid",
			true, "modifiedDate", true, "rsDetailsFrSid", true,
			"recordLockStatus", true, "createdDate", true, "createdBy", true,
			"source", true, "imtdRsDetailsFrSid", true, "batchId", true,
			"imtdCreatedDate", true, "sessionId", true, "usersId", true,
			"operation", true, "modifiedBy", true, "rsDetailsSid", true,
			"formulaId", true, "inboundStatus", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		ImtdRsDetailsFr newImtdRsDetailsFr = addImtdRsDetailsFr();

		ImtdRsDetailsFr existingImtdRsDetailsFr = _persistence.fetchByPrimaryKey(newImtdRsDetailsFr.getPrimaryKey());

		Assert.assertEquals(existingImtdRsDetailsFr, newImtdRsDetailsFr);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ImtdRsDetailsFr missingImtdRsDetailsFr = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingImtdRsDetailsFr);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		ImtdRsDetailsFr newImtdRsDetailsFr1 = addImtdRsDetailsFr();
		ImtdRsDetailsFr newImtdRsDetailsFr2 = addImtdRsDetailsFr();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newImtdRsDetailsFr1.getPrimaryKey());
		primaryKeys.add(newImtdRsDetailsFr2.getPrimaryKey());

		Map<Serializable, ImtdRsDetailsFr> imtdRsDetailsFrs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, imtdRsDetailsFrs.size());
		Assert.assertEquals(newImtdRsDetailsFr1,
			imtdRsDetailsFrs.get(newImtdRsDetailsFr1.getPrimaryKey()));
		Assert.assertEquals(newImtdRsDetailsFr2,
			imtdRsDetailsFrs.get(newImtdRsDetailsFr2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, ImtdRsDetailsFr> imtdRsDetailsFrs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(imtdRsDetailsFrs.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		ImtdRsDetailsFr newImtdRsDetailsFr = addImtdRsDetailsFr();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newImtdRsDetailsFr.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, ImtdRsDetailsFr> imtdRsDetailsFrs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, imtdRsDetailsFrs.size());
		Assert.assertEquals(newImtdRsDetailsFr,
			imtdRsDetailsFrs.get(newImtdRsDetailsFr.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, ImtdRsDetailsFr> imtdRsDetailsFrs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(imtdRsDetailsFrs.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		ImtdRsDetailsFr newImtdRsDetailsFr = addImtdRsDetailsFr();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newImtdRsDetailsFr.getPrimaryKey());

		Map<Serializable, ImtdRsDetailsFr> imtdRsDetailsFrs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, imtdRsDetailsFrs.size());
		Assert.assertEquals(newImtdRsDetailsFr,
			imtdRsDetailsFrs.get(newImtdRsDetailsFr.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = ImtdRsDetailsFrLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<ImtdRsDetailsFr>() {
				@Override
				public void performAction(ImtdRsDetailsFr imtdRsDetailsFr) {
					Assert.assertNotNull(imtdRsDetailsFr);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		ImtdRsDetailsFr newImtdRsDetailsFr = addImtdRsDetailsFr();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ImtdRsDetailsFr.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("imtdRsDetailsFrSid",
				newImtdRsDetailsFr.getImtdRsDetailsFrSid()));

		List<ImtdRsDetailsFr> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		ImtdRsDetailsFr existingImtdRsDetailsFr = result.get(0);

		Assert.assertEquals(existingImtdRsDetailsFr, newImtdRsDetailsFr);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ImtdRsDetailsFr.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("imtdRsDetailsFrSid",
				RandomTestUtil.nextInt()));

		List<ImtdRsDetailsFr> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		ImtdRsDetailsFr newImtdRsDetailsFr = addImtdRsDetailsFr();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ImtdRsDetailsFr.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"imtdRsDetailsFrSid"));

		Object newImtdRsDetailsFrSid = newImtdRsDetailsFr.getImtdRsDetailsFrSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("imtdRsDetailsFrSid",
				new Object[] { newImtdRsDetailsFrSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingImtdRsDetailsFrSid = result.get(0);

		Assert.assertEquals(existingImtdRsDetailsFrSid, newImtdRsDetailsFrSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ImtdRsDetailsFr.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"imtdRsDetailsFrSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("imtdRsDetailsFrSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected ImtdRsDetailsFr addImtdRsDetailsFr() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ImtdRsDetailsFr imtdRsDetailsFr = _persistence.create(pk);

		imtdRsDetailsFr.setFormulaMethodId(RandomTestUtil.randomString());

		imtdRsDetailsFr.setItemMasterSid(RandomTestUtil.nextInt());

		imtdRsDetailsFr.setImtdRsDetailsSid(RandomTestUtil.nextInt());

		imtdRsDetailsFr.setModifiedDate(RandomTestUtil.nextDate());

		imtdRsDetailsFr.setRsDetailsFrSid(RandomTestUtil.nextInt());

		imtdRsDetailsFr.setRecordLockStatus(RandomTestUtil.randomBoolean());

		imtdRsDetailsFr.setCreatedDate(RandomTestUtil.nextDate());

		imtdRsDetailsFr.setCreatedBy(RandomTestUtil.nextInt());

		imtdRsDetailsFr.setSource(RandomTestUtil.randomString());

		imtdRsDetailsFr.setBatchId(RandomTestUtil.randomString());

		imtdRsDetailsFr.setImtdCreatedDate(RandomTestUtil.nextDate());

		imtdRsDetailsFr.setSessionId(RandomTestUtil.randomString());

		imtdRsDetailsFr.setUsersId(RandomTestUtil.randomString());

		imtdRsDetailsFr.setOperation(RandomTestUtil.randomString());

		imtdRsDetailsFr.setModifiedBy(RandomTestUtil.nextInt());

		imtdRsDetailsFr.setRsDetailsSid(RandomTestUtil.nextInt());

		imtdRsDetailsFr.setFormulaId(RandomTestUtil.nextInt());

		imtdRsDetailsFr.setInboundStatus(RandomTestUtil.randomString());

		_imtdRsDetailsFrs.add(_persistence.update(imtdRsDetailsFr));

		return imtdRsDetailsFr;
	}

	private List<ImtdRsDetailsFr> _imtdRsDetailsFrs = new ArrayList<ImtdRsDetailsFr>();
	private ImtdRsDetailsFrPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}