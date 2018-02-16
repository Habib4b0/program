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
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import com.stpl.app.exception.NoSuchDeductionCalendarDetailsException;
import com.stpl.app.model.DeductionCalendarDetails;
import com.stpl.app.service.DeductionCalendarDetailsLocalServiceUtil;
import com.stpl.app.service.persistence.DeductionCalendarDetailsPK;
import com.stpl.app.service.persistence.DeductionCalendarDetailsPersistence;
import com.stpl.app.service.persistence.DeductionCalendarDetailsUtil;

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
public class DeductionCalendarDetailsPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = DeductionCalendarDetailsUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<DeductionCalendarDetails> iterator = _deductionCalendarDetailses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		DeductionCalendarDetailsPK pk = new DeductionCalendarDetailsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		DeductionCalendarDetails deductionCalendarDetails = _persistence.create(pk);

		Assert.assertNotNull(deductionCalendarDetails);

		Assert.assertEquals(deductionCalendarDetails.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		DeductionCalendarDetails newDeductionCalendarDetails = addDeductionCalendarDetails();

		_persistence.remove(newDeductionCalendarDetails);

		DeductionCalendarDetails existingDeductionCalendarDetails = _persistence.fetchByPrimaryKey(newDeductionCalendarDetails.getPrimaryKey());

		Assert.assertNull(existingDeductionCalendarDetails);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addDeductionCalendarDetails();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		DeductionCalendarDetailsPK pk = new DeductionCalendarDetailsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		DeductionCalendarDetails newDeductionCalendarDetails = _persistence.create(pk);

		newDeductionCalendarDetails.setAdjustmentBasis(RandomTestUtil.randomString());

		newDeductionCalendarDetails.setAdjustmentValue(RandomTestUtil.randomString());

		newDeductionCalendarDetails.setAdjustmentAllocationMethodology(RandomTestUtil.randomString());

		newDeductionCalendarDetails.setDiscountAmount(RandomTestUtil.nextInt());

		newDeductionCalendarDetails.setAdjustmentVariable(RandomTestUtil.randomString());

		newDeductionCalendarDetails.setAdjustmentType(RandomTestUtil.randomString());

		newDeductionCalendarDetails.setCheckRecord(RandomTestUtil.randomBoolean());

		_deductionCalendarDetailses.add(_persistence.update(
				newDeductionCalendarDetails));

		DeductionCalendarDetails existingDeductionCalendarDetails = _persistence.findByPrimaryKey(newDeductionCalendarDetails.getPrimaryKey());

		Assert.assertEquals(existingDeductionCalendarDetails.getDeductionCalendarMasterSid(),
			newDeductionCalendarDetails.getDeductionCalendarMasterSid());
		Assert.assertEquals(existingDeductionCalendarDetails.getAdjustmentBasis(),
			newDeductionCalendarDetails.getAdjustmentBasis());
		Assert.assertEquals(existingDeductionCalendarDetails.getPeriodSid(),
			newDeductionCalendarDetails.getPeriodSid());
		Assert.assertEquals(existingDeductionCalendarDetails.getAdjustmentValue(),
			newDeductionCalendarDetails.getAdjustmentValue());
		Assert.assertEquals(existingDeductionCalendarDetails.getAdjustmentAllocationMethodology(),
			newDeductionCalendarDetails.getAdjustmentAllocationMethodology());
		Assert.assertEquals(existingDeductionCalendarDetails.getCompanyMasterSid(),
			newDeductionCalendarDetails.getCompanyMasterSid());
		Assert.assertEquals(existingDeductionCalendarDetails.getDiscountAmount(),
			newDeductionCalendarDetails.getDiscountAmount());
		Assert.assertEquals(existingDeductionCalendarDetails.getAdjustmentVariable(),
			newDeductionCalendarDetails.getAdjustmentVariable());
		Assert.assertEquals(existingDeductionCalendarDetails.getItemMasterSid(),
			newDeductionCalendarDetails.getItemMasterSid());
		Assert.assertEquals(existingDeductionCalendarDetails.getAdjustmentType(),
			newDeductionCalendarDetails.getAdjustmentType());
		Assert.assertEquals(existingDeductionCalendarDetails.getCheckRecord(),
			newDeductionCalendarDetails.getCheckRecord());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		DeductionCalendarDetails newDeductionCalendarDetails = addDeductionCalendarDetails();

		DeductionCalendarDetails existingDeductionCalendarDetails = _persistence.findByPrimaryKey(newDeductionCalendarDetails.getPrimaryKey());

		Assert.assertEquals(existingDeductionCalendarDetails,
			newDeductionCalendarDetails);
	}

	@Test(expected = NoSuchDeductionCalendarDetailsException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		DeductionCalendarDetailsPK pk = new DeductionCalendarDetailsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		DeductionCalendarDetails newDeductionCalendarDetails = addDeductionCalendarDetails();

		DeductionCalendarDetails existingDeductionCalendarDetails = _persistence.fetchByPrimaryKey(newDeductionCalendarDetails.getPrimaryKey());

		Assert.assertEquals(existingDeductionCalendarDetails,
			newDeductionCalendarDetails);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		DeductionCalendarDetailsPK pk = new DeductionCalendarDetailsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		DeductionCalendarDetails missingDeductionCalendarDetails = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingDeductionCalendarDetails);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		DeductionCalendarDetails newDeductionCalendarDetails1 = addDeductionCalendarDetails();
		DeductionCalendarDetails newDeductionCalendarDetails2 = addDeductionCalendarDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDeductionCalendarDetails1.getPrimaryKey());
		primaryKeys.add(newDeductionCalendarDetails2.getPrimaryKey());

		Map<Serializable, DeductionCalendarDetails> deductionCalendarDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, deductionCalendarDetailses.size());
		Assert.assertEquals(newDeductionCalendarDetails1,
			deductionCalendarDetailses.get(
				newDeductionCalendarDetails1.getPrimaryKey()));
		Assert.assertEquals(newDeductionCalendarDetails2,
			deductionCalendarDetailses.get(
				newDeductionCalendarDetails2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		DeductionCalendarDetailsPK pk1 = new DeductionCalendarDetailsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		DeductionCalendarDetailsPK pk2 = new DeductionCalendarDetailsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, DeductionCalendarDetails> deductionCalendarDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(deductionCalendarDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		DeductionCalendarDetails newDeductionCalendarDetails = addDeductionCalendarDetails();

		DeductionCalendarDetailsPK pk = new DeductionCalendarDetailsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDeductionCalendarDetails.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, DeductionCalendarDetails> deductionCalendarDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, deductionCalendarDetailses.size());
		Assert.assertEquals(newDeductionCalendarDetails,
			deductionCalendarDetailses.get(
				newDeductionCalendarDetails.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, DeductionCalendarDetails> deductionCalendarDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(deductionCalendarDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		DeductionCalendarDetails newDeductionCalendarDetails = addDeductionCalendarDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDeductionCalendarDetails.getPrimaryKey());

		Map<Serializable, DeductionCalendarDetails> deductionCalendarDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, deductionCalendarDetailses.size());
		Assert.assertEquals(newDeductionCalendarDetails,
			deductionCalendarDetailses.get(
				newDeductionCalendarDetails.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = DeductionCalendarDetailsLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<DeductionCalendarDetails>() {
				@Override
				public void performAction(
					DeductionCalendarDetails deductionCalendarDetails) {
					Assert.assertNotNull(deductionCalendarDetails);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		DeductionCalendarDetails newDeductionCalendarDetails = addDeductionCalendarDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(DeductionCalendarDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"id.deductionCalendarMasterSid",
				newDeductionCalendarDetails.getDeductionCalendarMasterSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.periodSid",
				newDeductionCalendarDetails.getPeriodSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.companyMasterSid",
				newDeductionCalendarDetails.getCompanyMasterSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.itemMasterSid",
				newDeductionCalendarDetails.getItemMasterSid()));

		List<DeductionCalendarDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		DeductionCalendarDetails existingDeductionCalendarDetails = result.get(0);

		Assert.assertEquals(existingDeductionCalendarDetails,
			newDeductionCalendarDetails);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(DeductionCalendarDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"id.deductionCalendarMasterSid", RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.periodSid",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.companyMasterSid",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.itemMasterSid",
				RandomTestUtil.nextInt()));

		List<DeductionCalendarDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		DeductionCalendarDetails newDeductionCalendarDetails = addDeductionCalendarDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(DeductionCalendarDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.deductionCalendarMasterSid"));

		Object newDeductionCalendarMasterSid = newDeductionCalendarDetails.getDeductionCalendarMasterSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"id.deductionCalendarMasterSid",
				new Object[] { newDeductionCalendarMasterSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingDeductionCalendarMasterSid = result.get(0);

		Assert.assertEquals(existingDeductionCalendarMasterSid,
			newDeductionCalendarMasterSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(DeductionCalendarDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.deductionCalendarMasterSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"id.deductionCalendarMasterSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected DeductionCalendarDetails addDeductionCalendarDetails()
		throws Exception {
		DeductionCalendarDetailsPK pk = new DeductionCalendarDetailsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		DeductionCalendarDetails deductionCalendarDetails = _persistence.create(pk);

		deductionCalendarDetails.setAdjustmentBasis(RandomTestUtil.randomString());

		deductionCalendarDetails.setAdjustmentValue(RandomTestUtil.randomString());

		deductionCalendarDetails.setAdjustmentAllocationMethodology(RandomTestUtil.randomString());

		deductionCalendarDetails.setDiscountAmount(RandomTestUtil.nextInt());

		deductionCalendarDetails.setAdjustmentVariable(RandomTestUtil.randomString());

		deductionCalendarDetails.setAdjustmentType(RandomTestUtil.randomString());

		deductionCalendarDetails.setCheckRecord(RandomTestUtil.randomBoolean());

		_deductionCalendarDetailses.add(_persistence.update(
				deductionCalendarDetails));

		return deductionCalendarDetails;
	}

	private List<DeductionCalendarDetails> _deductionCalendarDetailses = new ArrayList<DeductionCalendarDetails>();
	private DeductionCalendarDetailsPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}