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

package com.stpl.app.parttwo.service.persistence.test;

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
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import com.stpl.app.parttwo.exception.NoSuchAcFdAdjustmentSelectionException;
import com.stpl.app.parttwo.model.AcFdAdjustmentSelection;
import com.stpl.app.parttwo.service.AcFdAdjustmentSelectionLocalServiceUtil;
import com.stpl.app.parttwo.service.persistence.AcFdAdjustmentSelectionPersistence;
import com.stpl.app.parttwo.service.persistence.AcFdAdjustmentSelectionUtil;

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
public class AcFdAdjustmentSelectionPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.parttwo.service"));

	@Before
	public void setUp() {
		_persistence = AcFdAdjustmentSelectionUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<AcFdAdjustmentSelection> iterator = _acFdAdjustmentSelections.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		AcFdAdjustmentSelection acFdAdjustmentSelection = _persistence.create(pk);

		Assert.assertNotNull(acFdAdjustmentSelection);

		Assert.assertEquals(acFdAdjustmentSelection.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		AcFdAdjustmentSelection newAcFdAdjustmentSelection = addAcFdAdjustmentSelection();

		_persistence.remove(newAcFdAdjustmentSelection);

		AcFdAdjustmentSelection existingAcFdAdjustmentSelection = _persistence.fetchByPrimaryKey(newAcFdAdjustmentSelection.getPrimaryKey());

		Assert.assertNull(existingAcFdAdjustmentSelection);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addAcFdAdjustmentSelection();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		AcFdAdjustmentSelection newAcFdAdjustmentSelection = _persistence.create(pk);

		newAcFdAdjustmentSelection.setMethodologyStartDate(RandomTestUtil.randomString());

		newAcFdAdjustmentSelection.setAllocationMethod(RandomTestUtil.nextInt());

		newAcFdAdjustmentSelection.setStartDate(RandomTestUtil.nextDate());

		newAcFdAdjustmentSelection.setTotalFixedDollarAdj(RandomTestUtil.nextDouble());

		newAcFdAdjustmentSelection.setCalculationFlag(RandomTestUtil.randomBoolean());

		newAcFdAdjustmentSelection.setRateCorrection(RandomTestUtil.nextDouble());

		newAcFdAdjustmentSelection.setBusinessDays(RandomTestUtil.nextInt());

		newAcFdAdjustmentSelection.setGlImpactDate(RandomTestUtil.nextDate());

		newAcFdAdjustmentSelection.setSalesBasis(RandomTestUtil.nextInt());

		newAcFdAdjustmentSelection.setReleaseType(RandomTestUtil.randomBoolean());

		newAcFdAdjustmentSelection.setReleaseAmount(RandomTestUtil.nextDouble());

		newAcFdAdjustmentSelection.setSuggestedAdj(RandomTestUtil.nextDouble());

		newAcFdAdjustmentSelection.setMethodologyEndDate(RandomTestUtil.randomString());

		_acFdAdjustmentSelections.add(_persistence.update(
				newAcFdAdjustmentSelection));

		AcFdAdjustmentSelection existingAcFdAdjustmentSelection = _persistence.findByPrimaryKey(newAcFdAdjustmentSelection.getPrimaryKey());

		Assert.assertEquals(existingAcFdAdjustmentSelection.getMethodologyStartDate(),
			newAcFdAdjustmentSelection.getMethodologyStartDate());
		Assert.assertEquals(existingAcFdAdjustmentSelection.getAllocationMethod(),
			newAcFdAdjustmentSelection.getAllocationMethod());
		Assert.assertEquals(Time.getShortTimestamp(
				existingAcFdAdjustmentSelection.getStartDate()),
			Time.getShortTimestamp(newAcFdAdjustmentSelection.getStartDate()));
		AssertUtils.assertEquals(existingAcFdAdjustmentSelection.getTotalFixedDollarAdj(),
			newAcFdAdjustmentSelection.getTotalFixedDollarAdj());
		Assert.assertEquals(existingAcFdAdjustmentSelection.getCalculationFlag(),
			newAcFdAdjustmentSelection.getCalculationFlag());
		AssertUtils.assertEquals(existingAcFdAdjustmentSelection.getRateCorrection(),
			newAcFdAdjustmentSelection.getRateCorrection());
		Assert.assertEquals(existingAcFdAdjustmentSelection.getBusinessDays(),
			newAcFdAdjustmentSelection.getBusinessDays());
		Assert.assertEquals(Time.getShortTimestamp(
				existingAcFdAdjustmentSelection.getGlImpactDate()),
			Time.getShortTimestamp(newAcFdAdjustmentSelection.getGlImpactDate()));
		Assert.assertEquals(existingAcFdAdjustmentSelection.getSalesBasis(),
			newAcFdAdjustmentSelection.getSalesBasis());
		Assert.assertEquals(existingAcFdAdjustmentSelection.getReleaseType(),
			newAcFdAdjustmentSelection.getReleaseType());
		Assert.assertEquals(existingAcFdAdjustmentSelection.getAccClosureMasterSid(),
			newAcFdAdjustmentSelection.getAccClosureMasterSid());
		AssertUtils.assertEquals(existingAcFdAdjustmentSelection.getReleaseAmount(),
			newAcFdAdjustmentSelection.getReleaseAmount());
		AssertUtils.assertEquals(existingAcFdAdjustmentSelection.getSuggestedAdj(),
			newAcFdAdjustmentSelection.getSuggestedAdj());
		Assert.assertEquals(existingAcFdAdjustmentSelection.getMethodologyEndDate(),
			newAcFdAdjustmentSelection.getMethodologyEndDate());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		AcFdAdjustmentSelection newAcFdAdjustmentSelection = addAcFdAdjustmentSelection();

		AcFdAdjustmentSelection existingAcFdAdjustmentSelection = _persistence.findByPrimaryKey(newAcFdAdjustmentSelection.getPrimaryKey());

		Assert.assertEquals(existingAcFdAdjustmentSelection,
			newAcFdAdjustmentSelection);
	}

	@Test(expected = NoSuchAcFdAdjustmentSelectionException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<AcFdAdjustmentSelection> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("AC_FD_ADJUSTMENT_SELECTION",
			"methodologyStartDate", true, "allocationMethod", true,
			"startDate", true, "totalFixedDollarAdj", true, "calculationFlag",
			true, "rateCorrection", true, "businessDays", true, "glImpactDate",
			true, "salesBasis", true, "releaseType", true,
			"accClosureMasterSid", true, "releaseAmount", true, "suggestedAdj",
			true, "methodologyEndDate", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		AcFdAdjustmentSelection newAcFdAdjustmentSelection = addAcFdAdjustmentSelection();

		AcFdAdjustmentSelection existingAcFdAdjustmentSelection = _persistence.fetchByPrimaryKey(newAcFdAdjustmentSelection.getPrimaryKey());

		Assert.assertEquals(existingAcFdAdjustmentSelection,
			newAcFdAdjustmentSelection);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		AcFdAdjustmentSelection missingAcFdAdjustmentSelection = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingAcFdAdjustmentSelection);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		AcFdAdjustmentSelection newAcFdAdjustmentSelection1 = addAcFdAdjustmentSelection();
		AcFdAdjustmentSelection newAcFdAdjustmentSelection2 = addAcFdAdjustmentSelection();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newAcFdAdjustmentSelection1.getPrimaryKey());
		primaryKeys.add(newAcFdAdjustmentSelection2.getPrimaryKey());

		Map<Serializable, AcFdAdjustmentSelection> acFdAdjustmentSelections = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, acFdAdjustmentSelections.size());
		Assert.assertEquals(newAcFdAdjustmentSelection1,
			acFdAdjustmentSelections.get(
				newAcFdAdjustmentSelection1.getPrimaryKey()));
		Assert.assertEquals(newAcFdAdjustmentSelection2,
			acFdAdjustmentSelections.get(
				newAcFdAdjustmentSelection2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, AcFdAdjustmentSelection> acFdAdjustmentSelections = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(acFdAdjustmentSelections.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		AcFdAdjustmentSelection newAcFdAdjustmentSelection = addAcFdAdjustmentSelection();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newAcFdAdjustmentSelection.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, AcFdAdjustmentSelection> acFdAdjustmentSelections = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, acFdAdjustmentSelections.size());
		Assert.assertEquals(newAcFdAdjustmentSelection,
			acFdAdjustmentSelections.get(
				newAcFdAdjustmentSelection.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, AcFdAdjustmentSelection> acFdAdjustmentSelections = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(acFdAdjustmentSelections.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		AcFdAdjustmentSelection newAcFdAdjustmentSelection = addAcFdAdjustmentSelection();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newAcFdAdjustmentSelection.getPrimaryKey());

		Map<Serializable, AcFdAdjustmentSelection> acFdAdjustmentSelections = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, acFdAdjustmentSelections.size());
		Assert.assertEquals(newAcFdAdjustmentSelection,
			acFdAdjustmentSelections.get(
				newAcFdAdjustmentSelection.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = AcFdAdjustmentSelectionLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<AcFdAdjustmentSelection>() {
				@Override
				public void performAction(
					AcFdAdjustmentSelection acFdAdjustmentSelection) {
					Assert.assertNotNull(acFdAdjustmentSelection);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		AcFdAdjustmentSelection newAcFdAdjustmentSelection = addAcFdAdjustmentSelection();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AcFdAdjustmentSelection.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("accClosureMasterSid",
				newAcFdAdjustmentSelection.getAccClosureMasterSid()));

		List<AcFdAdjustmentSelection> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		AcFdAdjustmentSelection existingAcFdAdjustmentSelection = result.get(0);

		Assert.assertEquals(existingAcFdAdjustmentSelection,
			newAcFdAdjustmentSelection);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AcFdAdjustmentSelection.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("accClosureMasterSid",
				RandomTestUtil.nextInt()));

		List<AcFdAdjustmentSelection> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		AcFdAdjustmentSelection newAcFdAdjustmentSelection = addAcFdAdjustmentSelection();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AcFdAdjustmentSelection.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"accClosureMasterSid"));

		Object newAccClosureMasterSid = newAcFdAdjustmentSelection.getAccClosureMasterSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("accClosureMasterSid",
				new Object[] { newAccClosureMasterSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingAccClosureMasterSid = result.get(0);

		Assert.assertEquals(existingAccClosureMasterSid, newAccClosureMasterSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AcFdAdjustmentSelection.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"accClosureMasterSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("accClosureMasterSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected AcFdAdjustmentSelection addAcFdAdjustmentSelection()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		AcFdAdjustmentSelection acFdAdjustmentSelection = _persistence.create(pk);

		acFdAdjustmentSelection.setMethodologyStartDate(RandomTestUtil.randomString());

		acFdAdjustmentSelection.setAllocationMethod(RandomTestUtil.nextInt());

		acFdAdjustmentSelection.setStartDate(RandomTestUtil.nextDate());

		acFdAdjustmentSelection.setTotalFixedDollarAdj(RandomTestUtil.nextDouble());

		acFdAdjustmentSelection.setCalculationFlag(RandomTestUtil.randomBoolean());

		acFdAdjustmentSelection.setRateCorrection(RandomTestUtil.nextDouble());

		acFdAdjustmentSelection.setBusinessDays(RandomTestUtil.nextInt());

		acFdAdjustmentSelection.setGlImpactDate(RandomTestUtil.nextDate());

		acFdAdjustmentSelection.setSalesBasis(RandomTestUtil.nextInt());

		acFdAdjustmentSelection.setReleaseType(RandomTestUtil.randomBoolean());

		acFdAdjustmentSelection.setReleaseAmount(RandomTestUtil.nextDouble());

		acFdAdjustmentSelection.setSuggestedAdj(RandomTestUtil.nextDouble());

		acFdAdjustmentSelection.setMethodologyEndDate(RandomTestUtil.randomString());

		_acFdAdjustmentSelections.add(_persistence.update(
				acFdAdjustmentSelection));

		return acFdAdjustmentSelection;
	}

	private List<AcFdAdjustmentSelection> _acFdAdjustmentSelections = new ArrayList<AcFdAdjustmentSelection>();
	private AcFdAdjustmentSelectionPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}