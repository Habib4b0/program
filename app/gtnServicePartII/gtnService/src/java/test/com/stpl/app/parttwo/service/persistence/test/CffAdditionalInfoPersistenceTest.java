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

import com.stpl.app.parttwo.exception.NoSuchCffAdditionalInfoException;
import com.stpl.app.parttwo.model.CffAdditionalInfo;
import com.stpl.app.parttwo.service.CffAdditionalInfoLocalServiceUtil;
import com.stpl.app.parttwo.service.persistence.CffAdditionalInfoPersistence;
import com.stpl.app.parttwo.service.persistence.CffAdditionalInfoUtil;

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
public class CffAdditionalInfoPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.parttwo.service"));

	@Before
	public void setUp() {
		_persistence = CffAdditionalInfoUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<CffAdditionalInfo> iterator = _cffAdditionalInfos.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CffAdditionalInfo cffAdditionalInfo = _persistence.create(pk);

		Assert.assertNotNull(cffAdditionalInfo);

		Assert.assertEquals(cffAdditionalInfo.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		CffAdditionalInfo newCffAdditionalInfo = addCffAdditionalInfo();

		_persistence.remove(newCffAdditionalInfo);

		CffAdditionalInfo existingCffAdditionalInfo = _persistence.fetchByPrimaryKey(newCffAdditionalInfo.getPrimaryKey());

		Assert.assertNull(existingCffAdditionalInfo);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCffAdditionalInfo();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CffAdditionalInfo newCffAdditionalInfo = _persistence.create(pk);

		newCffAdditionalInfo.setCreatedDate(RandomTestUtil.nextDate());

		newCffAdditionalInfo.setCreatedBy(RandomTestUtil.nextInt());

		newCffAdditionalInfo.setCffMasterSid(RandomTestUtil.nextInt());

		newCffAdditionalInfo.setNotes(RandomTestUtil.randomString());

		_cffAdditionalInfos.add(_persistence.update(newCffAdditionalInfo));

		CffAdditionalInfo existingCffAdditionalInfo = _persistence.findByPrimaryKey(newCffAdditionalInfo.getPrimaryKey());

		Assert.assertEquals(Time.getShortTimestamp(
				existingCffAdditionalInfo.getCreatedDate()),
			Time.getShortTimestamp(newCffAdditionalInfo.getCreatedDate()));
		Assert.assertEquals(existingCffAdditionalInfo.getCreatedBy(),
			newCffAdditionalInfo.getCreatedBy());
		Assert.assertEquals(existingCffAdditionalInfo.getCffMasterSid(),
			newCffAdditionalInfo.getCffMasterSid());
		Assert.assertEquals(existingCffAdditionalInfo.getCffAdditionalInfoSid(),
			newCffAdditionalInfo.getCffAdditionalInfoSid());
		Assert.assertEquals(existingCffAdditionalInfo.getNotes(),
			newCffAdditionalInfo.getNotes());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		CffAdditionalInfo newCffAdditionalInfo = addCffAdditionalInfo();

		CffAdditionalInfo existingCffAdditionalInfo = _persistence.findByPrimaryKey(newCffAdditionalInfo.getPrimaryKey());

		Assert.assertEquals(existingCffAdditionalInfo, newCffAdditionalInfo);
	}

	@Test(expected = NoSuchCffAdditionalInfoException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<CffAdditionalInfo> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("CFF_ADDITIONAL_INFO",
			"createdDate", true, "createdBy", true, "cffMasterSid", true,
			"cffAdditionalInfoSid", true, "notes", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		CffAdditionalInfo newCffAdditionalInfo = addCffAdditionalInfo();

		CffAdditionalInfo existingCffAdditionalInfo = _persistence.fetchByPrimaryKey(newCffAdditionalInfo.getPrimaryKey());

		Assert.assertEquals(existingCffAdditionalInfo, newCffAdditionalInfo);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CffAdditionalInfo missingCffAdditionalInfo = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCffAdditionalInfo);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		CffAdditionalInfo newCffAdditionalInfo1 = addCffAdditionalInfo();
		CffAdditionalInfo newCffAdditionalInfo2 = addCffAdditionalInfo();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCffAdditionalInfo1.getPrimaryKey());
		primaryKeys.add(newCffAdditionalInfo2.getPrimaryKey());

		Map<Serializable, CffAdditionalInfo> cffAdditionalInfos = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, cffAdditionalInfos.size());
		Assert.assertEquals(newCffAdditionalInfo1,
			cffAdditionalInfos.get(newCffAdditionalInfo1.getPrimaryKey()));
		Assert.assertEquals(newCffAdditionalInfo2,
			cffAdditionalInfos.get(newCffAdditionalInfo2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, CffAdditionalInfo> cffAdditionalInfos = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(cffAdditionalInfos.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		CffAdditionalInfo newCffAdditionalInfo = addCffAdditionalInfo();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCffAdditionalInfo.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, CffAdditionalInfo> cffAdditionalInfos = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, cffAdditionalInfos.size());
		Assert.assertEquals(newCffAdditionalInfo,
			cffAdditionalInfos.get(newCffAdditionalInfo.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, CffAdditionalInfo> cffAdditionalInfos = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(cffAdditionalInfos.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		CffAdditionalInfo newCffAdditionalInfo = addCffAdditionalInfo();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCffAdditionalInfo.getPrimaryKey());

		Map<Serializable, CffAdditionalInfo> cffAdditionalInfos = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, cffAdditionalInfos.size());
		Assert.assertEquals(newCffAdditionalInfo,
			cffAdditionalInfos.get(newCffAdditionalInfo.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = CffAdditionalInfoLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<CffAdditionalInfo>() {
				@Override
				public void performAction(CffAdditionalInfo cffAdditionalInfo) {
					Assert.assertNotNull(cffAdditionalInfo);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		CffAdditionalInfo newCffAdditionalInfo = addCffAdditionalInfo();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CffAdditionalInfo.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("cffAdditionalInfoSid",
				newCffAdditionalInfo.getCffAdditionalInfoSid()));

		List<CffAdditionalInfo> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		CffAdditionalInfo existingCffAdditionalInfo = result.get(0);

		Assert.assertEquals(existingCffAdditionalInfo, newCffAdditionalInfo);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CffAdditionalInfo.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("cffAdditionalInfoSid",
				RandomTestUtil.nextInt()));

		List<CffAdditionalInfo> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		CffAdditionalInfo newCffAdditionalInfo = addCffAdditionalInfo();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CffAdditionalInfo.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"cffAdditionalInfoSid"));

		Object newCffAdditionalInfoSid = newCffAdditionalInfo.getCffAdditionalInfoSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("cffAdditionalInfoSid",
				new Object[] { newCffAdditionalInfoSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCffAdditionalInfoSid = result.get(0);

		Assert.assertEquals(existingCffAdditionalInfoSid,
			newCffAdditionalInfoSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CffAdditionalInfo.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"cffAdditionalInfoSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("cffAdditionalInfoSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected CffAdditionalInfo addCffAdditionalInfo()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		CffAdditionalInfo cffAdditionalInfo = _persistence.create(pk);

		cffAdditionalInfo.setCreatedDate(RandomTestUtil.nextDate());

		cffAdditionalInfo.setCreatedBy(RandomTestUtil.nextInt());

		cffAdditionalInfo.setCffMasterSid(RandomTestUtil.nextInt());

		cffAdditionalInfo.setNotes(RandomTestUtil.randomString());

		_cffAdditionalInfos.add(_persistence.update(cffAdditionalInfo));

		return cffAdditionalInfo;
	}

	private List<CffAdditionalInfo> _cffAdditionalInfos = new ArrayList<CffAdditionalInfo>();
	private CffAdditionalInfoPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}