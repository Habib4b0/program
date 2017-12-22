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
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import com.stpl.app.parttwo.exception.NoSuchAcBaseRateBaselineDetailsException;
import com.stpl.app.parttwo.model.AcBaseRateBaselineDetails;
import com.stpl.app.parttwo.service.AcBaseRateBaselineDetailsLocalServiceUtil;
import com.stpl.app.parttwo.service.persistence.AcBaseRateBaselineDetailsPersistence;
import com.stpl.app.parttwo.service.persistence.AcBaseRateBaselineDetailsUtil;

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
public class AcBaseRateBaselineDetailsPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.parttwo.service"));

	@Before
	public void setUp() {
		_persistence = AcBaseRateBaselineDetailsUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<AcBaseRateBaselineDetails> iterator = _acBaseRateBaselineDetailses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		AcBaseRateBaselineDetails acBaseRateBaselineDetails = _persistence.create(pk);

		Assert.assertNotNull(acBaseRateBaselineDetails);

		Assert.assertEquals(acBaseRateBaselineDetails.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		AcBaseRateBaselineDetails newAcBaseRateBaselineDetails = addAcBaseRateBaselineDetails();

		_persistence.remove(newAcBaseRateBaselineDetails);

		AcBaseRateBaselineDetails existingAcBaseRateBaselineDetails = _persistence.fetchByPrimaryKey(newAcBaseRateBaselineDetails.getPrimaryKey());

		Assert.assertNull(existingAcBaseRateBaselineDetails);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addAcBaseRateBaselineDetails();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		AcBaseRateBaselineDetails newAcBaseRateBaselineDetails = _persistence.create(pk);

		newAcBaseRateBaselineDetails.setPeriodValue(RandomTestUtil.nextDouble());

		newAcBaseRateBaselineDetails.setPeriodSid(RandomTestUtil.nextInt());

		newAcBaseRateBaselineDetails.setPaymentsPeriod(RandomTestUtil.randomBoolean());

		newAcBaseRateBaselineDetails.setSalesPeriod(RandomTestUtil.randomBoolean());

		_acBaseRateBaselineDetailses.add(_persistence.update(
				newAcBaseRateBaselineDetails));

		AcBaseRateBaselineDetails existingAcBaseRateBaselineDetails = _persistence.findByPrimaryKey(newAcBaseRateBaselineDetails.getPrimaryKey());

		AssertUtils.assertEquals(existingAcBaseRateBaselineDetails.getPeriodValue(),
			newAcBaseRateBaselineDetails.getPeriodValue());
		Assert.assertEquals(existingAcBaseRateBaselineDetails.getPeriodSid(),
			newAcBaseRateBaselineDetails.getPeriodSid());
		Assert.assertEquals(existingAcBaseRateBaselineDetails.getPaymentsPeriod(),
			newAcBaseRateBaselineDetails.getPaymentsPeriod());
		Assert.assertEquals(existingAcBaseRateBaselineDetails.getAcBrMethodologyDetailsSid(),
			newAcBaseRateBaselineDetails.getAcBrMethodologyDetailsSid());
		Assert.assertEquals(existingAcBaseRateBaselineDetails.getSalesPeriod(),
			newAcBaseRateBaselineDetails.getSalesPeriod());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		AcBaseRateBaselineDetails newAcBaseRateBaselineDetails = addAcBaseRateBaselineDetails();

		AcBaseRateBaselineDetails existingAcBaseRateBaselineDetails = _persistence.findByPrimaryKey(newAcBaseRateBaselineDetails.getPrimaryKey());

		Assert.assertEquals(existingAcBaseRateBaselineDetails,
			newAcBaseRateBaselineDetails);
	}

	@Test(expected = NoSuchAcBaseRateBaselineDetailsException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<AcBaseRateBaselineDetails> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("Ac_Base_Rate_Baseline_Details",
			"periodValue", true, "periodSid", true, "paymentsPeriod", true,
			"acBrMethodologyDetailsSid", true, "salesPeriod", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		AcBaseRateBaselineDetails newAcBaseRateBaselineDetails = addAcBaseRateBaselineDetails();

		AcBaseRateBaselineDetails existingAcBaseRateBaselineDetails = _persistence.fetchByPrimaryKey(newAcBaseRateBaselineDetails.getPrimaryKey());

		Assert.assertEquals(existingAcBaseRateBaselineDetails,
			newAcBaseRateBaselineDetails);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		AcBaseRateBaselineDetails missingAcBaseRateBaselineDetails = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingAcBaseRateBaselineDetails);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		AcBaseRateBaselineDetails newAcBaseRateBaselineDetails1 = addAcBaseRateBaselineDetails();
		AcBaseRateBaselineDetails newAcBaseRateBaselineDetails2 = addAcBaseRateBaselineDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newAcBaseRateBaselineDetails1.getPrimaryKey());
		primaryKeys.add(newAcBaseRateBaselineDetails2.getPrimaryKey());

		Map<Serializable, AcBaseRateBaselineDetails> acBaseRateBaselineDetailses =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, acBaseRateBaselineDetailses.size());
		Assert.assertEquals(newAcBaseRateBaselineDetails1,
			acBaseRateBaselineDetailses.get(
				newAcBaseRateBaselineDetails1.getPrimaryKey()));
		Assert.assertEquals(newAcBaseRateBaselineDetails2,
			acBaseRateBaselineDetailses.get(
				newAcBaseRateBaselineDetails2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, AcBaseRateBaselineDetails> acBaseRateBaselineDetailses =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(acBaseRateBaselineDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		AcBaseRateBaselineDetails newAcBaseRateBaselineDetails = addAcBaseRateBaselineDetails();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newAcBaseRateBaselineDetails.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, AcBaseRateBaselineDetails> acBaseRateBaselineDetailses =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, acBaseRateBaselineDetailses.size());
		Assert.assertEquals(newAcBaseRateBaselineDetails,
			acBaseRateBaselineDetailses.get(
				newAcBaseRateBaselineDetails.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, AcBaseRateBaselineDetails> acBaseRateBaselineDetailses =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(acBaseRateBaselineDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		AcBaseRateBaselineDetails newAcBaseRateBaselineDetails = addAcBaseRateBaselineDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newAcBaseRateBaselineDetails.getPrimaryKey());

		Map<Serializable, AcBaseRateBaselineDetails> acBaseRateBaselineDetailses =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, acBaseRateBaselineDetailses.size());
		Assert.assertEquals(newAcBaseRateBaselineDetails,
			acBaseRateBaselineDetailses.get(
				newAcBaseRateBaselineDetails.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = AcBaseRateBaselineDetailsLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<AcBaseRateBaselineDetails>() {
				@Override
				public void performAction(
					AcBaseRateBaselineDetails acBaseRateBaselineDetails) {
					Assert.assertNotNull(acBaseRateBaselineDetails);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		AcBaseRateBaselineDetails newAcBaseRateBaselineDetails = addAcBaseRateBaselineDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AcBaseRateBaselineDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"acBrMethodologyDetailsSid",
				newAcBaseRateBaselineDetails.getAcBrMethodologyDetailsSid()));

		List<AcBaseRateBaselineDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		AcBaseRateBaselineDetails existingAcBaseRateBaselineDetails = result.get(0);

		Assert.assertEquals(existingAcBaseRateBaselineDetails,
			newAcBaseRateBaselineDetails);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AcBaseRateBaselineDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"acBrMethodologyDetailsSid", RandomTestUtil.nextInt()));

		List<AcBaseRateBaselineDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		AcBaseRateBaselineDetails newAcBaseRateBaselineDetails = addAcBaseRateBaselineDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AcBaseRateBaselineDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"acBrMethodologyDetailsSid"));

		Object newAcBrMethodologyDetailsSid = newAcBaseRateBaselineDetails.getAcBrMethodologyDetailsSid();

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
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AcBaseRateBaselineDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"acBrMethodologyDetailsSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"acBrMethodologyDetailsSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected AcBaseRateBaselineDetails addAcBaseRateBaselineDetails()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		AcBaseRateBaselineDetails acBaseRateBaselineDetails = _persistence.create(pk);

		acBaseRateBaselineDetails.setPeriodValue(RandomTestUtil.nextDouble());

		acBaseRateBaselineDetails.setPeriodSid(RandomTestUtil.nextInt());

		acBaseRateBaselineDetails.setPaymentsPeriod(RandomTestUtil.randomBoolean());

		acBaseRateBaselineDetails.setSalesPeriod(RandomTestUtil.randomBoolean());

		_acBaseRateBaselineDetailses.add(_persistence.update(
				acBaseRateBaselineDetails));

		return acBaseRateBaselineDetails;
	}

	private List<AcBaseRateBaselineDetails> _acBaseRateBaselineDetailses = new ArrayList<AcBaseRateBaselineDetails>();
	private AcBaseRateBaselineDetailsPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}