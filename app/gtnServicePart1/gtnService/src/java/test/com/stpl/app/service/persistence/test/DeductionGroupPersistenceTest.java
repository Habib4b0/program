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

import com.stpl.app.exception.NoSuchDeductionGroupException;
import com.stpl.app.model.DeductionGroup;
import com.stpl.app.service.DeductionGroupLocalServiceUtil;
import com.stpl.app.service.persistence.DeductionGroupPersistence;
import com.stpl.app.service.persistence.DeductionGroupUtil;

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
public class DeductionGroupPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = DeductionGroupUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<DeductionGroup> iterator = _deductionGroups.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		DeductionGroup deductionGroup = _persistence.create(pk);

		Assert.assertNotNull(deductionGroup);

		Assert.assertEquals(deductionGroup.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		DeductionGroup newDeductionGroup = addDeductionGroup();

		_persistence.remove(newDeductionGroup);

		DeductionGroup existingDeductionGroup = _persistence.fetchByPrimaryKey(newDeductionGroup.getPrimaryKey());

		Assert.assertNull(existingDeductionGroup);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addDeductionGroup();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		DeductionGroup newDeductionGroup = _persistence.create(pk);

		newDeductionGroup.setDeductionFilter(RandomTestUtil.randomString());

		newDeductionGroup.setCreatedDate(RandomTestUtil.nextDate());

		newDeductionGroup.setCreatedBy(RandomTestUtil.nextInt());

		newDeductionGroup.setDeductionGroupName(RandomTestUtil.randomString());

		newDeductionGroup.setVersionNo(RandomTestUtil.nextInt());

		newDeductionGroup.setDeductionGroupDescription(RandomTestUtil.randomString());

		newDeductionGroup.setModifiedBy(RandomTestUtil.nextInt());

		newDeductionGroup.setDeductionGroupNo(RandomTestUtil.randomString());

		newDeductionGroup.setModifiedDate(RandomTestUtil.nextDate());

		_deductionGroups.add(_persistence.update(newDeductionGroup));

		DeductionGroup existingDeductionGroup = _persistence.findByPrimaryKey(newDeductionGroup.getPrimaryKey());

		Assert.assertEquals(existingDeductionGroup.getDeductionFilter(),
			newDeductionGroup.getDeductionFilter());
		Assert.assertEquals(Time.getShortTimestamp(
				existingDeductionGroup.getCreatedDate()),
			Time.getShortTimestamp(newDeductionGroup.getCreatedDate()));
		Assert.assertEquals(existingDeductionGroup.getCreatedBy(),
			newDeductionGroup.getCreatedBy());
		Assert.assertEquals(existingDeductionGroup.getDeductionGroupSid(),
			newDeductionGroup.getDeductionGroupSid());
		Assert.assertEquals(existingDeductionGroup.getDeductionGroupName(),
			newDeductionGroup.getDeductionGroupName());
		Assert.assertEquals(existingDeductionGroup.getVersionNo(),
			newDeductionGroup.getVersionNo());
		Assert.assertEquals(existingDeductionGroup.getDeductionGroupDescription(),
			newDeductionGroup.getDeductionGroupDescription());
		Assert.assertEquals(existingDeductionGroup.getModifiedBy(),
			newDeductionGroup.getModifiedBy());
		Assert.assertEquals(existingDeductionGroup.getDeductionGroupNo(),
			newDeductionGroup.getDeductionGroupNo());
		Assert.assertEquals(Time.getShortTimestamp(
				existingDeductionGroup.getModifiedDate()),
			Time.getShortTimestamp(newDeductionGroup.getModifiedDate()));
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		DeductionGroup newDeductionGroup = addDeductionGroup();

		DeductionGroup existingDeductionGroup = _persistence.findByPrimaryKey(newDeductionGroup.getPrimaryKey());

		Assert.assertEquals(existingDeductionGroup, newDeductionGroup);
	}

	@Test(expected = NoSuchDeductionGroupException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<DeductionGroup> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("DEDUCTION_GROUP",
			"deductionFilter", true, "createdDate", true, "createdBy", true,
			"deductionGroupSid", true, "deductionGroupName", true, "versionNo",
			true, "deductionGroupDescription", true, "modifiedBy", true,
			"deductionGroupNo", true, "modifiedDate", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		DeductionGroup newDeductionGroup = addDeductionGroup();

		DeductionGroup existingDeductionGroup = _persistence.fetchByPrimaryKey(newDeductionGroup.getPrimaryKey());

		Assert.assertEquals(existingDeductionGroup, newDeductionGroup);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		DeductionGroup missingDeductionGroup = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingDeductionGroup);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		DeductionGroup newDeductionGroup1 = addDeductionGroup();
		DeductionGroup newDeductionGroup2 = addDeductionGroup();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDeductionGroup1.getPrimaryKey());
		primaryKeys.add(newDeductionGroup2.getPrimaryKey());

		Map<Serializable, DeductionGroup> deductionGroups = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, deductionGroups.size());
		Assert.assertEquals(newDeductionGroup1,
			deductionGroups.get(newDeductionGroup1.getPrimaryKey()));
		Assert.assertEquals(newDeductionGroup2,
			deductionGroups.get(newDeductionGroup2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, DeductionGroup> deductionGroups = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(deductionGroups.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		DeductionGroup newDeductionGroup = addDeductionGroup();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDeductionGroup.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, DeductionGroup> deductionGroups = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, deductionGroups.size());
		Assert.assertEquals(newDeductionGroup,
			deductionGroups.get(newDeductionGroup.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, DeductionGroup> deductionGroups = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(deductionGroups.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		DeductionGroup newDeductionGroup = addDeductionGroup();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDeductionGroup.getPrimaryKey());

		Map<Serializable, DeductionGroup> deductionGroups = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, deductionGroups.size());
		Assert.assertEquals(newDeductionGroup,
			deductionGroups.get(newDeductionGroup.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = DeductionGroupLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<DeductionGroup>() {
				@Override
				public void performAction(DeductionGroup deductionGroup) {
					Assert.assertNotNull(deductionGroup);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		DeductionGroup newDeductionGroup = addDeductionGroup();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(DeductionGroup.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("deductionGroupSid",
				newDeductionGroup.getDeductionGroupSid()));

		List<DeductionGroup> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		DeductionGroup existingDeductionGroup = result.get(0);

		Assert.assertEquals(existingDeductionGroup, newDeductionGroup);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(DeductionGroup.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("deductionGroupSid",
				RandomTestUtil.nextInt()));

		List<DeductionGroup> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		DeductionGroup newDeductionGroup = addDeductionGroup();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(DeductionGroup.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"deductionGroupSid"));

		Object newDeductionGroupSid = newDeductionGroup.getDeductionGroupSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("deductionGroupSid",
				new Object[] { newDeductionGroupSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingDeductionGroupSid = result.get(0);

		Assert.assertEquals(existingDeductionGroupSid, newDeductionGroupSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(DeductionGroup.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"deductionGroupSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("deductionGroupSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected DeductionGroup addDeductionGroup() throws Exception {
		int pk = RandomTestUtil.nextInt();

		DeductionGroup deductionGroup = _persistence.create(pk);

		deductionGroup.setDeductionFilter(RandomTestUtil.randomString());

		deductionGroup.setCreatedDate(RandomTestUtil.nextDate());

		deductionGroup.setCreatedBy(RandomTestUtil.nextInt());

		deductionGroup.setDeductionGroupName(RandomTestUtil.randomString());

		deductionGroup.setVersionNo(RandomTestUtil.nextInt());

		deductionGroup.setDeductionGroupDescription(RandomTestUtil.randomString());

		deductionGroup.setModifiedBy(RandomTestUtil.nextInt());

		deductionGroup.setDeductionGroupNo(RandomTestUtil.randomString());

		deductionGroup.setModifiedDate(RandomTestUtil.nextDate());

		_deductionGroups.add(_persistence.update(deductionGroup));

		return deductionGroup;
	}

	private List<DeductionGroup> _deductionGroups = new ArrayList<DeductionGroup>();
	private DeductionGroupPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}