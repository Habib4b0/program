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

import com.stpl.app.exception.NoSuchCdrModelException;
import com.stpl.app.model.CdrModel;
import com.stpl.app.service.CdrModelLocalServiceUtil;
import com.stpl.app.service.persistence.CdrModelPersistence;
import com.stpl.app.service.persistence.CdrModelUtil;

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
public class CdrModelPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = CdrModelUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<CdrModel> iterator = _cdrModels.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CdrModel cdrModel = _persistence.create(pk);

		Assert.assertNotNull(cdrModel);

		Assert.assertEquals(cdrModel.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		CdrModel newCdrModel = addCdrModel();

		_persistence.remove(newCdrModel);

		CdrModel existingCdrModel = _persistence.fetchByPrimaryKey(newCdrModel.getPrimaryKey());

		Assert.assertNull(existingCdrModel);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCdrModel();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CdrModel newCdrModel = _persistence.create(pk);

		newCdrModel.setCreatedBy(RandomTestUtil.nextInt());

		newCdrModel.setRuleCategory(RandomTestUtil.nextInt());

		newCdrModel.setRuleType(RandomTestUtil.nextInt());

		newCdrModel.setModifiedBy(RandomTestUtil.nextInt());

		newCdrModel.setInternalNotes(RandomTestUtil.randomString());

		newCdrModel.setCreatedDate(RandomTestUtil.nextDate());

		newCdrModel.setRuleName(RandomTestUtil.randomString());

		newCdrModel.setRuleNo(RandomTestUtil.randomString());

		newCdrModel.setModifiedDate(RandomTestUtil.nextDate());

		_cdrModels.add(_persistence.update(newCdrModel));

		CdrModel existingCdrModel = _persistence.findByPrimaryKey(newCdrModel.getPrimaryKey());

		Assert.assertEquals(existingCdrModel.getCreatedBy(),
			newCdrModel.getCreatedBy());
		Assert.assertEquals(existingCdrModel.getRuleCategory(),
			newCdrModel.getRuleCategory());
		Assert.assertEquals(existingCdrModel.getRuleType(),
			newCdrModel.getRuleType());
		Assert.assertEquals(existingCdrModel.getModifiedBy(),
			newCdrModel.getModifiedBy());
		Assert.assertEquals(existingCdrModel.getInternalNotes(),
			newCdrModel.getInternalNotes());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCdrModel.getCreatedDate()),
			Time.getShortTimestamp(newCdrModel.getCreatedDate()));
		Assert.assertEquals(existingCdrModel.getRuleName(),
			newCdrModel.getRuleName());
		Assert.assertEquals(existingCdrModel.getCdrModelSid(),
			newCdrModel.getCdrModelSid());
		Assert.assertEquals(existingCdrModel.getRuleNo(),
			newCdrModel.getRuleNo());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCdrModel.getModifiedDate()),
			Time.getShortTimestamp(newCdrModel.getModifiedDate()));
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		CdrModel newCdrModel = addCdrModel();

		CdrModel existingCdrModel = _persistence.findByPrimaryKey(newCdrModel.getPrimaryKey());

		Assert.assertEquals(existingCdrModel, newCdrModel);
	}

	@Test(expected = NoSuchCdrModelException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<CdrModel> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("CDR_MODEL", "createdBy",
			true, "ruleCategory", true, "ruleType", true, "modifiedBy", true,
			"internalNotes", true, "createdDate", true, "ruleName", true,
			"cdrModelSid", true, "ruleNo", true, "modifiedDate", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		CdrModel newCdrModel = addCdrModel();

		CdrModel existingCdrModel = _persistence.fetchByPrimaryKey(newCdrModel.getPrimaryKey());

		Assert.assertEquals(existingCdrModel, newCdrModel);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CdrModel missingCdrModel = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCdrModel);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		CdrModel newCdrModel1 = addCdrModel();
		CdrModel newCdrModel2 = addCdrModel();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCdrModel1.getPrimaryKey());
		primaryKeys.add(newCdrModel2.getPrimaryKey());

		Map<Serializable, CdrModel> cdrModels = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, cdrModels.size());
		Assert.assertEquals(newCdrModel1,
			cdrModels.get(newCdrModel1.getPrimaryKey()));
		Assert.assertEquals(newCdrModel2,
			cdrModels.get(newCdrModel2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, CdrModel> cdrModels = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(cdrModels.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		CdrModel newCdrModel = addCdrModel();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCdrModel.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, CdrModel> cdrModels = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, cdrModels.size());
		Assert.assertEquals(newCdrModel,
			cdrModels.get(newCdrModel.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, CdrModel> cdrModels = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(cdrModels.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		CdrModel newCdrModel = addCdrModel();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCdrModel.getPrimaryKey());

		Map<Serializable, CdrModel> cdrModels = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, cdrModels.size());
		Assert.assertEquals(newCdrModel,
			cdrModels.get(newCdrModel.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = CdrModelLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<CdrModel>() {
				@Override
				public void performAction(CdrModel cdrModel) {
					Assert.assertNotNull(cdrModel);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		CdrModel newCdrModel = addCdrModel();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CdrModel.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("cdrModelSid",
				newCdrModel.getCdrModelSid()));

		List<CdrModel> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		CdrModel existingCdrModel = result.get(0);

		Assert.assertEquals(existingCdrModel, newCdrModel);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CdrModel.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("cdrModelSid",
				RandomTestUtil.nextInt()));

		List<CdrModel> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		CdrModel newCdrModel = addCdrModel();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CdrModel.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("cdrModelSid"));

		Object newCdrModelSid = newCdrModel.getCdrModelSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("cdrModelSid",
				new Object[] { newCdrModelSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCdrModelSid = result.get(0);

		Assert.assertEquals(existingCdrModelSid, newCdrModelSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CdrModel.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("cdrModelSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("cdrModelSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected CdrModel addCdrModel() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CdrModel cdrModel = _persistence.create(pk);

		cdrModel.setCreatedBy(RandomTestUtil.nextInt());

		cdrModel.setRuleCategory(RandomTestUtil.nextInt());

		cdrModel.setRuleType(RandomTestUtil.nextInt());

		cdrModel.setModifiedBy(RandomTestUtil.nextInt());

		cdrModel.setInternalNotes(RandomTestUtil.randomString());

		cdrModel.setCreatedDate(RandomTestUtil.nextDate());

		cdrModel.setRuleName(RandomTestUtil.randomString());

		cdrModel.setRuleNo(RandomTestUtil.randomString());

		cdrModel.setModifiedDate(RandomTestUtil.nextDate());

		_cdrModels.add(_persistence.update(cdrModel));

		return cdrModel;
	}

	private List<CdrModel> _cdrModels = new ArrayList<CdrModel>();
	private CdrModelPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}