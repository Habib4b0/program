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

import com.stpl.app.exception.NoSuchIvldCpiException;
import com.stpl.app.model.IvldCpi;
import com.stpl.app.service.IvldCpiLocalServiceUtil;
import com.stpl.app.service.persistence.IvldCpiPersistence;
import com.stpl.app.service.persistence.IvldCpiUtil;

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
public class IvldCpiPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = IvldCpiUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<IvldCpi> iterator = _ivldCpis.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldCpi ivldCpi = _persistence.create(pk);

		Assert.assertNotNull(ivldCpi);

		Assert.assertEquals(ivldCpi.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		IvldCpi newIvldCpi = addIvldCpi();

		_persistence.remove(newIvldCpi);

		IvldCpi existingIvldCpi = _persistence.fetchByPrimaryKey(newIvldCpi.getPrimaryKey());

		Assert.assertNull(existingIvldCpi);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addIvldCpi();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldCpi newIvldCpi = _persistence.create(pk);

		newIvldCpi.setEffectiveDate(RandomTestUtil.randomString());

		newIvldCpi.setReasonForFailure(RandomTestUtil.randomString());

		newIvldCpi.setIndexType(RandomTestUtil.randomString());

		newIvldCpi.setStatus(RandomTestUtil.randomString());

		newIvldCpi.setModifiedDate(RandomTestUtil.nextDate());

		newIvldCpi.setCpiIntfid(RandomTestUtil.randomString());

		newIvldCpi.setCreatedBy(RandomTestUtil.randomString());

		newIvldCpi.setCreatedDate(RandomTestUtil.nextDate());

		newIvldCpi.setSource(RandomTestUtil.randomString());

		newIvldCpi.setIndexValue(RandomTestUtil.randomString());

		newIvldCpi.setAddChgDelIndicator(RandomTestUtil.randomString());

		newIvldCpi.setBatchId(RandomTestUtil.randomString());

		newIvldCpi.setErrorField(RandomTestUtil.randomString());

		newIvldCpi.setErrorCode(RandomTestUtil.randomString());

		newIvldCpi.setIntfInsertedDate(RandomTestUtil.nextDate());

		newIvldCpi.setModifiedBy(RandomTestUtil.randomString());

		newIvldCpi.setReprocessedFlag(RandomTestUtil.randomString());

		newIvldCpi.setIndexId(RandomTestUtil.randomString());

		newIvldCpi.setCheckRecord(RandomTestUtil.randomBoolean());

		_ivldCpis.add(_persistence.update(newIvldCpi));

		IvldCpi existingIvldCpi = _persistence.findByPrimaryKey(newIvldCpi.getPrimaryKey());

		Assert.assertEquals(existingIvldCpi.getEffectiveDate(),
			newIvldCpi.getEffectiveDate());
		Assert.assertEquals(existingIvldCpi.getReasonForFailure(),
			newIvldCpi.getReasonForFailure());
		Assert.assertEquals(existingIvldCpi.getIndexType(),
			newIvldCpi.getIndexType());
		Assert.assertEquals(existingIvldCpi.getStatus(), newIvldCpi.getStatus());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldCpi.getModifiedDate()),
			Time.getShortTimestamp(newIvldCpi.getModifiedDate()));
		Assert.assertEquals(existingIvldCpi.getCpiIntfid(),
			newIvldCpi.getCpiIntfid());
		Assert.assertEquals(existingIvldCpi.getCreatedBy(),
			newIvldCpi.getCreatedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldCpi.getCreatedDate()),
			Time.getShortTimestamp(newIvldCpi.getCreatedDate()));
		Assert.assertEquals(existingIvldCpi.getSource(), newIvldCpi.getSource());
		Assert.assertEquals(existingIvldCpi.getIndexValue(),
			newIvldCpi.getIndexValue());
		Assert.assertEquals(existingIvldCpi.getAddChgDelIndicator(),
			newIvldCpi.getAddChgDelIndicator());
		Assert.assertEquals(existingIvldCpi.getBatchId(),
			newIvldCpi.getBatchId());
		Assert.assertEquals(existingIvldCpi.getIvldCpiSid(),
			newIvldCpi.getIvldCpiSid());
		Assert.assertEquals(existingIvldCpi.getErrorField(),
			newIvldCpi.getErrorField());
		Assert.assertEquals(existingIvldCpi.getErrorCode(),
			newIvldCpi.getErrorCode());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldCpi.getIntfInsertedDate()),
			Time.getShortTimestamp(newIvldCpi.getIntfInsertedDate()));
		Assert.assertEquals(existingIvldCpi.getModifiedBy(),
			newIvldCpi.getModifiedBy());
		Assert.assertEquals(existingIvldCpi.getReprocessedFlag(),
			newIvldCpi.getReprocessedFlag());
		Assert.assertEquals(existingIvldCpi.getIndexId(),
			newIvldCpi.getIndexId());
		Assert.assertEquals(existingIvldCpi.getCheckRecord(),
			newIvldCpi.getCheckRecord());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		IvldCpi newIvldCpi = addIvldCpi();

		IvldCpi existingIvldCpi = _persistence.findByPrimaryKey(newIvldCpi.getPrimaryKey());

		Assert.assertEquals(existingIvldCpi, newIvldCpi);
	}

	@Test(expected = NoSuchIvldCpiException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<IvldCpi> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("IVLD_CPI", "effectiveDate",
			true, "reasonForFailure", true, "indexType", true, "status", true,
			"modifiedDate", true, "cpiIntfid", true, "createdBy", true,
			"createdDate", true, "source", true, "indexValue", true,
			"addChgDelIndicator", true, "batchId", true, "ivldCpiSid", true,
			"errorField", true, "errorCode", true, "intfInsertedDate", true,
			"modifiedBy", true, "reprocessedFlag", true, "indexId", true,
			"checkRecord", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		IvldCpi newIvldCpi = addIvldCpi();

		IvldCpi existingIvldCpi = _persistence.fetchByPrimaryKey(newIvldCpi.getPrimaryKey());

		Assert.assertEquals(existingIvldCpi, newIvldCpi);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldCpi missingIvldCpi = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingIvldCpi);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		IvldCpi newIvldCpi1 = addIvldCpi();
		IvldCpi newIvldCpi2 = addIvldCpi();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldCpi1.getPrimaryKey());
		primaryKeys.add(newIvldCpi2.getPrimaryKey());

		Map<Serializable, IvldCpi> ivldCpis = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, ivldCpis.size());
		Assert.assertEquals(newIvldCpi1,
			ivldCpis.get(newIvldCpi1.getPrimaryKey()));
		Assert.assertEquals(newIvldCpi2,
			ivldCpis.get(newIvldCpi2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, IvldCpi> ivldCpis = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ivldCpis.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		IvldCpi newIvldCpi = addIvldCpi();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldCpi.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, IvldCpi> ivldCpis = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ivldCpis.size());
		Assert.assertEquals(newIvldCpi, ivldCpis.get(newIvldCpi.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, IvldCpi> ivldCpis = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ivldCpis.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		IvldCpi newIvldCpi = addIvldCpi();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldCpi.getPrimaryKey());

		Map<Serializable, IvldCpi> ivldCpis = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ivldCpis.size());
		Assert.assertEquals(newIvldCpi, ivldCpis.get(newIvldCpi.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = IvldCpiLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<IvldCpi>() {
				@Override
				public void performAction(IvldCpi ivldCpi) {
					Assert.assertNotNull(ivldCpi);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		IvldCpi newIvldCpi = addIvldCpi();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldCpi.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("ivldCpiSid",
				newIvldCpi.getIvldCpiSid()));

		List<IvldCpi> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		IvldCpi existingIvldCpi = result.get(0);

		Assert.assertEquals(existingIvldCpi, newIvldCpi);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldCpi.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("ivldCpiSid",
				RandomTestUtil.nextInt()));

		List<IvldCpi> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		IvldCpi newIvldCpi = addIvldCpi();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldCpi.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("ivldCpiSid"));

		Object newIvldCpiSid = newIvldCpi.getIvldCpiSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("ivldCpiSid",
				new Object[] { newIvldCpiSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingIvldCpiSid = result.get(0);

		Assert.assertEquals(existingIvldCpiSid, newIvldCpiSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldCpi.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("ivldCpiSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("ivldCpiSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected IvldCpi addIvldCpi() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldCpi ivldCpi = _persistence.create(pk);

		ivldCpi.setEffectiveDate(RandomTestUtil.randomString());

		ivldCpi.setReasonForFailure(RandomTestUtil.randomString());

		ivldCpi.setIndexType(RandomTestUtil.randomString());

		ivldCpi.setStatus(RandomTestUtil.randomString());

		ivldCpi.setModifiedDate(RandomTestUtil.nextDate());

		ivldCpi.setCpiIntfid(RandomTestUtil.randomString());

		ivldCpi.setCreatedBy(RandomTestUtil.randomString());

		ivldCpi.setCreatedDate(RandomTestUtil.nextDate());

		ivldCpi.setSource(RandomTestUtil.randomString());

		ivldCpi.setIndexValue(RandomTestUtil.randomString());

		ivldCpi.setAddChgDelIndicator(RandomTestUtil.randomString());

		ivldCpi.setBatchId(RandomTestUtil.randomString());

		ivldCpi.setErrorField(RandomTestUtil.randomString());

		ivldCpi.setErrorCode(RandomTestUtil.randomString());

		ivldCpi.setIntfInsertedDate(RandomTestUtil.nextDate());

		ivldCpi.setModifiedBy(RandomTestUtil.randomString());

		ivldCpi.setReprocessedFlag(RandomTestUtil.randomString());

		ivldCpi.setIndexId(RandomTestUtil.randomString());

		ivldCpi.setCheckRecord(RandomTestUtil.randomBoolean());

		_ivldCpis.add(_persistence.update(ivldCpi));

		return ivldCpi;
	}

	private List<IvldCpi> _ivldCpis = new ArrayList<IvldCpi>();
	private IvldCpiPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}