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

import com.stpl.app.exception.NoSuchRebateTierFormulaException;
import com.stpl.app.model.RebateTierFormula;
import com.stpl.app.service.RebateTierFormulaLocalServiceUtil;
import com.stpl.app.service.persistence.RebateTierFormulaPersistence;
import com.stpl.app.service.persistence.RebateTierFormulaUtil;

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
public class RebateTierFormulaPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = RebateTierFormulaUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<RebateTierFormula> iterator = _rebateTierFormulas.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		RebateTierFormula rebateTierFormula = _persistence.create(pk);

		Assert.assertNotNull(rebateTierFormula);

		Assert.assertEquals(rebateTierFormula.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		RebateTierFormula newRebateTierFormula = addRebateTierFormula();

		_persistence.remove(newRebateTierFormula);

		RebateTierFormula existingRebateTierFormula = _persistence.fetchByPrimaryKey(newRebateTierFormula.getPrimaryKey());

		Assert.assertNull(existingRebateTierFormula);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addRebateTierFormula();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		RebateTierFormula newRebateTierFormula = _persistence.create(pk);

		newRebateTierFormula.setRebateTierFormulaNo(RandomTestUtil.randomString());

		newRebateTierFormula.setRebateTierFormulaName(RandomTestUtil.randomString());

		newRebateTierFormula.setRebatePlanMasterSid(RandomTestUtil.nextInt());

		newRebateTierFormula.setModifiedDate(RandomTestUtil.nextDate());

		newRebateTierFormula.setRecordLockStatus(RandomTestUtil.randomBoolean());

		newRebateTierFormula.setSource(RandomTestUtil.randomString());

		newRebateTierFormula.setCreatedBy(RandomTestUtil.nextInt());

		newRebateTierFormula.setCreatedDate(RandomTestUtil.nextDate());

		newRebateTierFormula.setBatchId(RandomTestUtil.randomString());

		newRebateTierFormula.setRebateTierFormulaId(RandomTestUtil.randomString());

		newRebateTierFormula.setInboundStatus(RandomTestUtil.randomString());

		newRebateTierFormula.setModifiedBy(RandomTestUtil.nextInt());

		_rebateTierFormulas.add(_persistence.update(newRebateTierFormula));

		RebateTierFormula existingRebateTierFormula = _persistence.findByPrimaryKey(newRebateTierFormula.getPrimaryKey());

		Assert.assertEquals(existingRebateTierFormula.getRebateTierFormulaNo(),
			newRebateTierFormula.getRebateTierFormulaNo());
		Assert.assertEquals(existingRebateTierFormula.getRebateTierFormulaName(),
			newRebateTierFormula.getRebateTierFormulaName());
		Assert.assertEquals(existingRebateTierFormula.getRebatePlanMasterSid(),
			newRebateTierFormula.getRebatePlanMasterSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingRebateTierFormula.getModifiedDate()),
			Time.getShortTimestamp(newRebateTierFormula.getModifiedDate()));
		Assert.assertEquals(existingRebateTierFormula.getRecordLockStatus(),
			newRebateTierFormula.getRecordLockStatus());
		Assert.assertEquals(existingRebateTierFormula.getSource(),
			newRebateTierFormula.getSource());
		Assert.assertEquals(existingRebateTierFormula.getCreatedBy(),
			newRebateTierFormula.getCreatedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingRebateTierFormula.getCreatedDate()),
			Time.getShortTimestamp(newRebateTierFormula.getCreatedDate()));
		Assert.assertEquals(existingRebateTierFormula.getBatchId(),
			newRebateTierFormula.getBatchId());
		Assert.assertEquals(existingRebateTierFormula.getRebateTierFormulaId(),
			newRebateTierFormula.getRebateTierFormulaId());
		Assert.assertEquals(existingRebateTierFormula.getInboundStatus(),
			newRebateTierFormula.getInboundStatus());
		Assert.assertEquals(existingRebateTierFormula.getModifiedBy(),
			newRebateTierFormula.getModifiedBy());
		Assert.assertEquals(existingRebateTierFormula.getRebateTierFormulaSid(),
			newRebateTierFormula.getRebateTierFormulaSid());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		RebateTierFormula newRebateTierFormula = addRebateTierFormula();

		RebateTierFormula existingRebateTierFormula = _persistence.findByPrimaryKey(newRebateTierFormula.getPrimaryKey());

		Assert.assertEquals(existingRebateTierFormula, newRebateTierFormula);
	}

	@Test(expected = NoSuchRebateTierFormulaException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<RebateTierFormula> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("REBATE_TIER_FORMULA",
			"rebateTierFormulaNo", true, "rebateTierFormulaName", true,
			"rebatePlanMasterSid", true, "modifiedDate", true,
			"recordLockStatus", true, "source", true, "createdBy", true,
			"createdDate", true, "batchId", true, "rebateTierFormulaId", true,
			"inboundStatus", true, "modifiedBy", true, "rebateTierFormulaSid",
			true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		RebateTierFormula newRebateTierFormula = addRebateTierFormula();

		RebateTierFormula existingRebateTierFormula = _persistence.fetchByPrimaryKey(newRebateTierFormula.getPrimaryKey());

		Assert.assertEquals(existingRebateTierFormula, newRebateTierFormula);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		RebateTierFormula missingRebateTierFormula = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingRebateTierFormula);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		RebateTierFormula newRebateTierFormula1 = addRebateTierFormula();
		RebateTierFormula newRebateTierFormula2 = addRebateTierFormula();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newRebateTierFormula1.getPrimaryKey());
		primaryKeys.add(newRebateTierFormula2.getPrimaryKey());

		Map<Serializable, RebateTierFormula> rebateTierFormulas = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, rebateTierFormulas.size());
		Assert.assertEquals(newRebateTierFormula1,
			rebateTierFormulas.get(newRebateTierFormula1.getPrimaryKey()));
		Assert.assertEquals(newRebateTierFormula2,
			rebateTierFormulas.get(newRebateTierFormula2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, RebateTierFormula> rebateTierFormulas = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(rebateTierFormulas.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		RebateTierFormula newRebateTierFormula = addRebateTierFormula();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newRebateTierFormula.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, RebateTierFormula> rebateTierFormulas = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, rebateTierFormulas.size());
		Assert.assertEquals(newRebateTierFormula,
			rebateTierFormulas.get(newRebateTierFormula.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, RebateTierFormula> rebateTierFormulas = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(rebateTierFormulas.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		RebateTierFormula newRebateTierFormula = addRebateTierFormula();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newRebateTierFormula.getPrimaryKey());

		Map<Serializable, RebateTierFormula> rebateTierFormulas = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, rebateTierFormulas.size());
		Assert.assertEquals(newRebateTierFormula,
			rebateTierFormulas.get(newRebateTierFormula.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = RebateTierFormulaLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<RebateTierFormula>() {
				@Override
				public void performAction(RebateTierFormula rebateTierFormula) {
					Assert.assertNotNull(rebateTierFormula);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		RebateTierFormula newRebateTierFormula = addRebateTierFormula();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RebateTierFormula.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("rebateTierFormulaSid",
				newRebateTierFormula.getRebateTierFormulaSid()));

		List<RebateTierFormula> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		RebateTierFormula existingRebateTierFormula = result.get(0);

		Assert.assertEquals(existingRebateTierFormula, newRebateTierFormula);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RebateTierFormula.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("rebateTierFormulaSid",
				RandomTestUtil.nextInt()));

		List<RebateTierFormula> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		RebateTierFormula newRebateTierFormula = addRebateTierFormula();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RebateTierFormula.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"rebateTierFormulaSid"));

		Object newRebateTierFormulaSid = newRebateTierFormula.getRebateTierFormulaSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("rebateTierFormulaSid",
				new Object[] { newRebateTierFormulaSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingRebateTierFormulaSid = result.get(0);

		Assert.assertEquals(existingRebateTierFormulaSid,
			newRebateTierFormulaSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RebateTierFormula.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"rebateTierFormulaSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("rebateTierFormulaSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected RebateTierFormula addRebateTierFormula()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		RebateTierFormula rebateTierFormula = _persistence.create(pk);

		rebateTierFormula.setRebateTierFormulaNo(RandomTestUtil.randomString());

		rebateTierFormula.setRebateTierFormulaName(RandomTestUtil.randomString());

		rebateTierFormula.setRebatePlanMasterSid(RandomTestUtil.nextInt());

		rebateTierFormula.setModifiedDate(RandomTestUtil.nextDate());

		rebateTierFormula.setRecordLockStatus(RandomTestUtil.randomBoolean());

		rebateTierFormula.setSource(RandomTestUtil.randomString());

		rebateTierFormula.setCreatedBy(RandomTestUtil.nextInt());

		rebateTierFormula.setCreatedDate(RandomTestUtil.nextDate());

		rebateTierFormula.setBatchId(RandomTestUtil.randomString());

		rebateTierFormula.setRebateTierFormulaId(RandomTestUtil.randomString());

		rebateTierFormula.setInboundStatus(RandomTestUtil.randomString());

		rebateTierFormula.setModifiedBy(RandomTestUtil.nextInt());

		_rebateTierFormulas.add(_persistence.update(rebateTierFormula));

		return rebateTierFormula;
	}

	private List<RebateTierFormula> _rebateTierFormulas = new ArrayList<RebateTierFormula>();
	private RebateTierFormulaPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}