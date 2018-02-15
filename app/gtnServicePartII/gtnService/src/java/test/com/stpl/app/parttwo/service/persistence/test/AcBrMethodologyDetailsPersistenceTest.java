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

import com.stpl.app.parttwo.exception.NoSuchAcBrMethodologyDetailsException;
import com.stpl.app.parttwo.model.AcBrMethodologyDetails;
import com.stpl.app.parttwo.service.AcBrMethodologyDetailsLocalServiceUtil;
import com.stpl.app.parttwo.service.persistence.AcBrMethodologyDetailsPersistence;
import com.stpl.app.parttwo.service.persistence.AcBrMethodologyDetailsUtil;

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
public class AcBrMethodologyDetailsPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.parttwo.service"));

	@Before
	public void setUp() {
		_persistence = AcBrMethodologyDetailsUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<AcBrMethodologyDetails> iterator = _acBrMethodologyDetailses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		AcBrMethodologyDetails acBrMethodologyDetails = _persistence.create(pk);

		Assert.assertNotNull(acBrMethodologyDetails);

		Assert.assertEquals(acBrMethodologyDetails.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		AcBrMethodologyDetails newAcBrMethodologyDetails = addAcBrMethodologyDetails();

		_persistence.remove(newAcBrMethodologyDetails);

		AcBrMethodologyDetails existingAcBrMethodologyDetails = _persistence.fetchByPrimaryKey(newAcBrMethodologyDetails.getPrimaryKey());

		Assert.assertNull(existingAcBrMethodologyDetails);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addAcBrMethodologyDetails();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		AcBrMethodologyDetails newAcBrMethodologyDetails = _persistence.create(pk);

		newAcBrMethodologyDetails.setSalesGrowthRate(RandomTestUtil.nextDouble());

		newAcBrMethodologyDetails.setMethodologyStartDate(RandomTestUtil.nextDate());

		newAcBrMethodologyDetails.setFrequency(RandomTestUtil.randomString());

		newAcBrMethodologyDetails.setCalculationFlag(RandomTestUtil.randomBoolean());

		newAcBrMethodologyDetails.setProvisionGrowthRate(RandomTestUtil.nextDouble());

		newAcBrMethodologyDetails.setSalesBasis(RandomTestUtil.nextInt());

		newAcBrMethodologyDetails.setAccClosureMasterSid(RandomTestUtil.nextInt());

		newAcBrMethodologyDetails.setMethodologyEndDate(RandomTestUtil.nextDate());

		newAcBrMethodologyDetails.setMethodologyValue(RandomTestUtil.nextDouble());

		newAcBrMethodologyDetails.setDampingFactor(RandomTestUtil.nextDouble());

		newAcBrMethodologyDetails.setMethodologyName(RandomTestUtil.randomString());

		_acBrMethodologyDetailses.add(_persistence.update(
				newAcBrMethodologyDetails));

		AcBrMethodologyDetails existingAcBrMethodologyDetails = _persistence.findByPrimaryKey(newAcBrMethodologyDetails.getPrimaryKey());

		AssertUtils.assertEquals(existingAcBrMethodologyDetails.getSalesGrowthRate(),
			newAcBrMethodologyDetails.getSalesGrowthRate());
		Assert.assertEquals(Time.getShortTimestamp(
				existingAcBrMethodologyDetails.getMethodologyStartDate()),
			Time.getShortTimestamp(
				newAcBrMethodologyDetails.getMethodologyStartDate()));
		Assert.assertEquals(existingAcBrMethodologyDetails.getFrequency(),
			newAcBrMethodologyDetails.getFrequency());
		Assert.assertEquals(existingAcBrMethodologyDetails.getCalculationFlag(),
			newAcBrMethodologyDetails.getCalculationFlag());
		AssertUtils.assertEquals(existingAcBrMethodologyDetails.getProvisionGrowthRate(),
			newAcBrMethodologyDetails.getProvisionGrowthRate());
		Assert.assertEquals(existingAcBrMethodologyDetails.getSalesBasis(),
			newAcBrMethodologyDetails.getSalesBasis());
		Assert.assertEquals(existingAcBrMethodologyDetails.getAcBrMethodologyDetailsSid(),
			newAcBrMethodologyDetails.getAcBrMethodologyDetailsSid());
		Assert.assertEquals(existingAcBrMethodologyDetails.getAccClosureMasterSid(),
			newAcBrMethodologyDetails.getAccClosureMasterSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingAcBrMethodologyDetails.getMethodologyEndDate()),
			Time.getShortTimestamp(
				newAcBrMethodologyDetails.getMethodologyEndDate()));
		AssertUtils.assertEquals(existingAcBrMethodologyDetails.getMethodologyValue(),
			newAcBrMethodologyDetails.getMethodologyValue());
		AssertUtils.assertEquals(existingAcBrMethodologyDetails.getDampingFactor(),
			newAcBrMethodologyDetails.getDampingFactor());
		Assert.assertEquals(existingAcBrMethodologyDetails.getMethodologyName(),
			newAcBrMethodologyDetails.getMethodologyName());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		AcBrMethodologyDetails newAcBrMethodologyDetails = addAcBrMethodologyDetails();

		AcBrMethodologyDetails existingAcBrMethodologyDetails = _persistence.findByPrimaryKey(newAcBrMethodologyDetails.getPrimaryKey());

		Assert.assertEquals(existingAcBrMethodologyDetails,
			newAcBrMethodologyDetails);
	}

	@Test(expected = NoSuchAcBrMethodologyDetailsException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<AcBrMethodologyDetails> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("AC_BR_METHODOLOGY_DETAILS",
			"salesGrowthRate", true, "methodologyStartDate", true, "frequency",
			true, "calculationFlag", true, "provisionGrowthRate", true,
			"salesBasis", true, "acBrMethodologyDetailsSid", true,
			"accClosureMasterSid", true, "methodologyEndDate", true,
			"methodologyValue", true, "dampingFactor", true, "methodologyName",
			true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		AcBrMethodologyDetails newAcBrMethodologyDetails = addAcBrMethodologyDetails();

		AcBrMethodologyDetails existingAcBrMethodologyDetails = _persistence.fetchByPrimaryKey(newAcBrMethodologyDetails.getPrimaryKey());

		Assert.assertEquals(existingAcBrMethodologyDetails,
			newAcBrMethodologyDetails);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		AcBrMethodologyDetails missingAcBrMethodologyDetails = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingAcBrMethodologyDetails);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		AcBrMethodologyDetails newAcBrMethodologyDetails1 = addAcBrMethodologyDetails();
		AcBrMethodologyDetails newAcBrMethodologyDetails2 = addAcBrMethodologyDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newAcBrMethodologyDetails1.getPrimaryKey());
		primaryKeys.add(newAcBrMethodologyDetails2.getPrimaryKey());

		Map<Serializable, AcBrMethodologyDetails> acBrMethodologyDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, acBrMethodologyDetailses.size());
		Assert.assertEquals(newAcBrMethodologyDetails1,
			acBrMethodologyDetailses.get(
				newAcBrMethodologyDetails1.getPrimaryKey()));
		Assert.assertEquals(newAcBrMethodologyDetails2,
			acBrMethodologyDetailses.get(
				newAcBrMethodologyDetails2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, AcBrMethodologyDetails> acBrMethodologyDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(acBrMethodologyDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		AcBrMethodologyDetails newAcBrMethodologyDetails = addAcBrMethodologyDetails();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newAcBrMethodologyDetails.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, AcBrMethodologyDetails> acBrMethodologyDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, acBrMethodologyDetailses.size());
		Assert.assertEquals(newAcBrMethodologyDetails,
			acBrMethodologyDetailses.get(
				newAcBrMethodologyDetails.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, AcBrMethodologyDetails> acBrMethodologyDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(acBrMethodologyDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		AcBrMethodologyDetails newAcBrMethodologyDetails = addAcBrMethodologyDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newAcBrMethodologyDetails.getPrimaryKey());

		Map<Serializable, AcBrMethodologyDetails> acBrMethodologyDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, acBrMethodologyDetailses.size());
		Assert.assertEquals(newAcBrMethodologyDetails,
			acBrMethodologyDetailses.get(
				newAcBrMethodologyDetails.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = AcBrMethodologyDetailsLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<AcBrMethodologyDetails>() {
				@Override
				public void performAction(
					AcBrMethodologyDetails acBrMethodologyDetails) {
					Assert.assertNotNull(acBrMethodologyDetails);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		AcBrMethodologyDetails newAcBrMethodologyDetails = addAcBrMethodologyDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AcBrMethodologyDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"acBrMethodologyDetailsSid",
				newAcBrMethodologyDetails.getAcBrMethodologyDetailsSid()));

		List<AcBrMethodologyDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		AcBrMethodologyDetails existingAcBrMethodologyDetails = result.get(0);

		Assert.assertEquals(existingAcBrMethodologyDetails,
			newAcBrMethodologyDetails);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AcBrMethodologyDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"acBrMethodologyDetailsSid", RandomTestUtil.nextInt()));

		List<AcBrMethodologyDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		AcBrMethodologyDetails newAcBrMethodologyDetails = addAcBrMethodologyDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AcBrMethodologyDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"acBrMethodologyDetailsSid"));

		Object newAcBrMethodologyDetailsSid = newAcBrMethodologyDetails.getAcBrMethodologyDetailsSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"acBrMethodologyDetailsSid",
				new Object[] { newAcBrMethodologyDetailsSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingAcBrMethodologyDetailsSid = result.get(0);

		Assert.assertEquals(existingAcBrMethodologyDetailsSid,
			newAcBrMethodologyDetailsSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AcBrMethodologyDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"acBrMethodologyDetailsSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"acBrMethodologyDetailsSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected AcBrMethodologyDetails addAcBrMethodologyDetails()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		AcBrMethodologyDetails acBrMethodologyDetails = _persistence.create(pk);

		acBrMethodologyDetails.setSalesGrowthRate(RandomTestUtil.nextDouble());

		acBrMethodologyDetails.setMethodologyStartDate(RandomTestUtil.nextDate());

		acBrMethodologyDetails.setFrequency(RandomTestUtil.randomString());

		acBrMethodologyDetails.setCalculationFlag(RandomTestUtil.randomBoolean());

		acBrMethodologyDetails.setProvisionGrowthRate(RandomTestUtil.nextDouble());

		acBrMethodologyDetails.setSalesBasis(RandomTestUtil.nextInt());

		acBrMethodologyDetails.setAccClosureMasterSid(RandomTestUtil.nextInt());

		acBrMethodologyDetails.setMethodologyEndDate(RandomTestUtil.nextDate());

		acBrMethodologyDetails.setMethodologyValue(RandomTestUtil.nextDouble());

		acBrMethodologyDetails.setDampingFactor(RandomTestUtil.nextDouble());

		acBrMethodologyDetails.setMethodologyName(RandomTestUtil.randomString());

		_acBrMethodologyDetailses.add(_persistence.update(
				acBrMethodologyDetails));

		return acBrMethodologyDetails;
	}

	private List<AcBrMethodologyDetails> _acBrMethodologyDetailses = new ArrayList<AcBrMethodologyDetails>();
	private AcBrMethodologyDetailsPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}