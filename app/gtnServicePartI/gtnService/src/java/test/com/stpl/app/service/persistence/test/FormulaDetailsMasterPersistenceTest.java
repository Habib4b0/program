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
import com.liferay.portal.kernel.test.AssertUtils;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import com.stpl.app.exception.NoSuchFormulaDetailsMasterException;
import com.stpl.app.model.FormulaDetailsMaster;
import com.stpl.app.service.FormulaDetailsMasterLocalServiceUtil;
import com.stpl.app.service.persistence.FormulaDetailsMasterPersistence;
import com.stpl.app.service.persistence.FormulaDetailsMasterUtil;

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
public class FormulaDetailsMasterPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = FormulaDetailsMasterUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<FormulaDetailsMaster> iterator = _formulaDetailsMasters.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		FormulaDetailsMaster formulaDetailsMaster = _persistence.create(pk);

		Assert.assertNotNull(formulaDetailsMaster);

		Assert.assertEquals(formulaDetailsMaster.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		FormulaDetailsMaster newFormulaDetailsMaster = addFormulaDetailsMaster();

		_persistence.remove(newFormulaDetailsMaster);

		FormulaDetailsMaster existingFormulaDetailsMaster = _persistence.fetchByPrimaryKey(newFormulaDetailsMaster.getPrimaryKey());

		Assert.assertNull(existingFormulaDetailsMaster);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addFormulaDetailsMaster();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		FormulaDetailsMaster newFormulaDetailsMaster = _persistence.create(pk);

		newFormulaDetailsMaster.setCompanyStringId(RandomTestUtil.randomString());

		newFormulaDetailsMaster.setContractPrice1(RandomTestUtil.nextDouble());

		newFormulaDetailsMaster.setContractPrice2(RandomTestUtil.nextDouble());

		newFormulaDetailsMaster.setEndDate(RandomTestUtil.nextDate());

		newFormulaDetailsMaster.setFormulaNo(RandomTestUtil.randomString());

		newFormulaDetailsMaster.setItemId(RandomTestUtil.randomString());

		newFormulaDetailsMaster.setRebatePercent1(RandomTestUtil.nextDouble());

		newFormulaDetailsMaster.setModifiedDate(RandomTestUtil.nextDate());

		newFormulaDetailsMaster.setFormulaDesc(RandomTestUtil.randomString());

		newFormulaDetailsMaster.setRebatePercent2(RandomTestUtil.nextDouble());

		newFormulaDetailsMaster.setRebatePercent3(RandomTestUtil.nextDouble());

		newFormulaDetailsMaster.setRecordLockStatus(RandomTestUtil.randomBoolean());

		newFormulaDetailsMaster.setStartDate(RandomTestUtil.nextDate());

		newFormulaDetailsMaster.setCreatedDate(RandomTestUtil.nextDate());

		newFormulaDetailsMaster.setCreatedBy(RandomTestUtil.nextInt());

		newFormulaDetailsMaster.setSource(RandomTestUtil.randomString());

		newFormulaDetailsMaster.setBatchId(RandomTestUtil.randomString());

		newFormulaDetailsMaster.setContractPrice3(RandomTestUtil.nextDouble());

		newFormulaDetailsMaster.setModifiedBy(RandomTestUtil.nextInt());

		newFormulaDetailsMaster.setInboundStatus(RandomTestUtil.randomString());

		newFormulaDetailsMaster.setFormulaId(RandomTestUtil.randomString());

		_formulaDetailsMasters.add(_persistence.update(newFormulaDetailsMaster));

		FormulaDetailsMaster existingFormulaDetailsMaster = _persistence.findByPrimaryKey(newFormulaDetailsMaster.getPrimaryKey());

		Assert.assertEquals(existingFormulaDetailsMaster.getCompanyStringId(),
			newFormulaDetailsMaster.getCompanyStringId());
		AssertUtils.assertEquals(existingFormulaDetailsMaster.getContractPrice1(),
			newFormulaDetailsMaster.getContractPrice1());
		AssertUtils.assertEquals(existingFormulaDetailsMaster.getContractPrice2(),
			newFormulaDetailsMaster.getContractPrice2());
		Assert.assertEquals(Time.getShortTimestamp(
				existingFormulaDetailsMaster.getEndDate()),
			Time.getShortTimestamp(newFormulaDetailsMaster.getEndDate()));
		Assert.assertEquals(existingFormulaDetailsMaster.getFormulaNo(),
			newFormulaDetailsMaster.getFormulaNo());
		Assert.assertEquals(existingFormulaDetailsMaster.getFormulaDetailsMasterSid(),
			newFormulaDetailsMaster.getFormulaDetailsMasterSid());
		Assert.assertEquals(existingFormulaDetailsMaster.getItemId(),
			newFormulaDetailsMaster.getItemId());
		AssertUtils.assertEquals(existingFormulaDetailsMaster.getRebatePercent1(),
			newFormulaDetailsMaster.getRebatePercent1());
		Assert.assertEquals(Time.getShortTimestamp(
				existingFormulaDetailsMaster.getModifiedDate()),
			Time.getShortTimestamp(newFormulaDetailsMaster.getModifiedDate()));
		Assert.assertEquals(existingFormulaDetailsMaster.getFormulaDesc(),
			newFormulaDetailsMaster.getFormulaDesc());
		AssertUtils.assertEquals(existingFormulaDetailsMaster.getRebatePercent2(),
			newFormulaDetailsMaster.getRebatePercent2());
		AssertUtils.assertEquals(existingFormulaDetailsMaster.getRebatePercent3(),
			newFormulaDetailsMaster.getRebatePercent3());
		Assert.assertEquals(existingFormulaDetailsMaster.getRecordLockStatus(),
			newFormulaDetailsMaster.getRecordLockStatus());
		Assert.assertEquals(Time.getShortTimestamp(
				existingFormulaDetailsMaster.getStartDate()),
			Time.getShortTimestamp(newFormulaDetailsMaster.getStartDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingFormulaDetailsMaster.getCreatedDate()),
			Time.getShortTimestamp(newFormulaDetailsMaster.getCreatedDate()));
		Assert.assertEquals(existingFormulaDetailsMaster.getCreatedBy(),
			newFormulaDetailsMaster.getCreatedBy());
		Assert.assertEquals(existingFormulaDetailsMaster.getSource(),
			newFormulaDetailsMaster.getSource());
		Assert.assertEquals(existingFormulaDetailsMaster.getBatchId(),
			newFormulaDetailsMaster.getBatchId());
		AssertUtils.assertEquals(existingFormulaDetailsMaster.getContractPrice3(),
			newFormulaDetailsMaster.getContractPrice3());
		Assert.assertEquals(existingFormulaDetailsMaster.getModifiedBy(),
			newFormulaDetailsMaster.getModifiedBy());
		Assert.assertEquals(existingFormulaDetailsMaster.getInboundStatus(),
			newFormulaDetailsMaster.getInboundStatus());
		Assert.assertEquals(existingFormulaDetailsMaster.getFormulaId(),
			newFormulaDetailsMaster.getFormulaId());
	}

	@Test
	public void testCountByFormulaId() throws Exception {
		_persistence.countByFormulaId(StringPool.BLANK);

		_persistence.countByFormulaId(StringPool.NULL);

		_persistence.countByFormulaId((String)null);
	}

	@Test
	public void testCountByFormulaNo() throws Exception {
		_persistence.countByFormulaNo(StringPool.BLANK);

		_persistence.countByFormulaNo(StringPool.NULL);

		_persistence.countByFormulaNo((String)null);
	}

	@Test
	public void testCountByItemId() throws Exception {
		_persistence.countByItemId(StringPool.BLANK);

		_persistence.countByItemId(StringPool.NULL);

		_persistence.countByItemId((String)null);
	}

	@Test
	public void testCountByCompanyId() throws Exception {
		_persistence.countByCompanyId(StringPool.BLANK);

		_persistence.countByCompanyId(StringPool.NULL);

		_persistence.countByCompanyId((String)null);
	}

	@Test
	public void testCountByStartDate() throws Exception {
		_persistence.countByStartDate(RandomTestUtil.nextDate());

		_persistence.countByStartDate(RandomTestUtil.nextDate());
	}

	@Test
	public void testCountByEndDate() throws Exception {
		_persistence.countByEndDate(RandomTestUtil.nextDate());

		_persistence.countByEndDate(RandomTestUtil.nextDate());
	}

	@Test
	public void testCountByFormulaDetailsUnique() throws Exception {
		_persistence.countByFormulaDetailsUnique(StringPool.BLANK);

		_persistence.countByFormulaDetailsUnique(StringPool.NULL);

		_persistence.countByFormulaDetailsUnique((String)null);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		FormulaDetailsMaster newFormulaDetailsMaster = addFormulaDetailsMaster();

		FormulaDetailsMaster existingFormulaDetailsMaster = _persistence.findByPrimaryKey(newFormulaDetailsMaster.getPrimaryKey());

		Assert.assertEquals(existingFormulaDetailsMaster,
			newFormulaDetailsMaster);
	}

	@Test(expected = NoSuchFormulaDetailsMasterException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<FormulaDetailsMaster> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("FORMULA_DETAILS_MASTER",
			"companyStringId", true, "contractPrice1", true, "contractPrice2",
			true, "endDate", true, "formulaNo", true,
			"formulaDetailsMasterSid", true, "itemId", true, "rebatePercent1",
			true, "modifiedDate", true, "formulaDesc", true, "rebatePercent2",
			true, "rebatePercent3", true, "recordLockStatus", true,
			"startDate", true, "createdDate", true, "createdBy", true,
			"source", true, "batchId", true, "contractPrice3", true,
			"modifiedBy", true, "inboundStatus", true, "formulaId", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		FormulaDetailsMaster newFormulaDetailsMaster = addFormulaDetailsMaster();

		FormulaDetailsMaster existingFormulaDetailsMaster = _persistence.fetchByPrimaryKey(newFormulaDetailsMaster.getPrimaryKey());

		Assert.assertEquals(existingFormulaDetailsMaster,
			newFormulaDetailsMaster);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		FormulaDetailsMaster missingFormulaDetailsMaster = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingFormulaDetailsMaster);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		FormulaDetailsMaster newFormulaDetailsMaster1 = addFormulaDetailsMaster();
		FormulaDetailsMaster newFormulaDetailsMaster2 = addFormulaDetailsMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newFormulaDetailsMaster1.getPrimaryKey());
		primaryKeys.add(newFormulaDetailsMaster2.getPrimaryKey());

		Map<Serializable, FormulaDetailsMaster> formulaDetailsMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, formulaDetailsMasters.size());
		Assert.assertEquals(newFormulaDetailsMaster1,
			formulaDetailsMasters.get(newFormulaDetailsMaster1.getPrimaryKey()));
		Assert.assertEquals(newFormulaDetailsMaster2,
			formulaDetailsMasters.get(newFormulaDetailsMaster2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, FormulaDetailsMaster> formulaDetailsMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(formulaDetailsMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		FormulaDetailsMaster newFormulaDetailsMaster = addFormulaDetailsMaster();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newFormulaDetailsMaster.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, FormulaDetailsMaster> formulaDetailsMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, formulaDetailsMasters.size());
		Assert.assertEquals(newFormulaDetailsMaster,
			formulaDetailsMasters.get(newFormulaDetailsMaster.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, FormulaDetailsMaster> formulaDetailsMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(formulaDetailsMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		FormulaDetailsMaster newFormulaDetailsMaster = addFormulaDetailsMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newFormulaDetailsMaster.getPrimaryKey());

		Map<Serializable, FormulaDetailsMaster> formulaDetailsMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, formulaDetailsMasters.size());
		Assert.assertEquals(newFormulaDetailsMaster,
			formulaDetailsMasters.get(newFormulaDetailsMaster.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = FormulaDetailsMasterLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<FormulaDetailsMaster>() {
				@Override
				public void performAction(
					FormulaDetailsMaster formulaDetailsMaster) {
					Assert.assertNotNull(formulaDetailsMaster);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		FormulaDetailsMaster newFormulaDetailsMaster = addFormulaDetailsMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(FormulaDetailsMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("formulaDetailsMasterSid",
				newFormulaDetailsMaster.getFormulaDetailsMasterSid()));

		List<FormulaDetailsMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		FormulaDetailsMaster existingFormulaDetailsMaster = result.get(0);

		Assert.assertEquals(existingFormulaDetailsMaster,
			newFormulaDetailsMaster);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(FormulaDetailsMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("formulaDetailsMasterSid",
				RandomTestUtil.nextInt()));

		List<FormulaDetailsMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		FormulaDetailsMaster newFormulaDetailsMaster = addFormulaDetailsMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(FormulaDetailsMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"formulaDetailsMasterSid"));

		Object newFormulaDetailsMasterSid = newFormulaDetailsMaster.getFormulaDetailsMasterSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("formulaDetailsMasterSid",
				new Object[] { newFormulaDetailsMasterSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingFormulaDetailsMasterSid = result.get(0);

		Assert.assertEquals(existingFormulaDetailsMasterSid,
			newFormulaDetailsMasterSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(FormulaDetailsMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"formulaDetailsMasterSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("formulaDetailsMasterSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected FormulaDetailsMaster addFormulaDetailsMaster()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		FormulaDetailsMaster formulaDetailsMaster = _persistence.create(pk);

		formulaDetailsMaster.setCompanyStringId(RandomTestUtil.randomString());

		formulaDetailsMaster.setContractPrice1(RandomTestUtil.nextDouble());

		formulaDetailsMaster.setContractPrice2(RandomTestUtil.nextDouble());

		formulaDetailsMaster.setEndDate(RandomTestUtil.nextDate());

		formulaDetailsMaster.setFormulaNo(RandomTestUtil.randomString());

		formulaDetailsMaster.setItemId(RandomTestUtil.randomString());

		formulaDetailsMaster.setRebatePercent1(RandomTestUtil.nextDouble());

		formulaDetailsMaster.setModifiedDate(RandomTestUtil.nextDate());

		formulaDetailsMaster.setFormulaDesc(RandomTestUtil.randomString());

		formulaDetailsMaster.setRebatePercent2(RandomTestUtil.nextDouble());

		formulaDetailsMaster.setRebatePercent3(RandomTestUtil.nextDouble());

		formulaDetailsMaster.setRecordLockStatus(RandomTestUtil.randomBoolean());

		formulaDetailsMaster.setStartDate(RandomTestUtil.nextDate());

		formulaDetailsMaster.setCreatedDate(RandomTestUtil.nextDate());

		formulaDetailsMaster.setCreatedBy(RandomTestUtil.nextInt());

		formulaDetailsMaster.setSource(RandomTestUtil.randomString());

		formulaDetailsMaster.setBatchId(RandomTestUtil.randomString());

		formulaDetailsMaster.setContractPrice3(RandomTestUtil.nextDouble());

		formulaDetailsMaster.setModifiedBy(RandomTestUtil.nextInt());

		formulaDetailsMaster.setInboundStatus(RandomTestUtil.randomString());

		formulaDetailsMaster.setFormulaId(RandomTestUtil.randomString());

		_formulaDetailsMasters.add(_persistence.update(formulaDetailsMaster));

		return formulaDetailsMaster;
	}

	private List<FormulaDetailsMaster> _formulaDetailsMasters = new ArrayList<FormulaDetailsMaster>();
	private FormulaDetailsMasterPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}