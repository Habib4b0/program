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

import com.stpl.app.exception.NoSuchIvldItemHierarchyException;
import com.stpl.app.model.IvldItemHierarchy;
import com.stpl.app.service.IvldItemHierarchyLocalServiceUtil;
import com.stpl.app.service.persistence.IvldItemHierarchyPersistence;
import com.stpl.app.service.persistence.IvldItemHierarchyUtil;

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
public class IvldItemHierarchyPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = IvldItemHierarchyUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<IvldItemHierarchy> iterator = _ivldItemHierarchies.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldItemHierarchy ivldItemHierarchy = _persistence.create(pk);

		Assert.assertNotNull(ivldItemHierarchy);

		Assert.assertEquals(ivldItemHierarchy.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		IvldItemHierarchy newIvldItemHierarchy = addIvldItemHierarchy();

		_persistence.remove(newIvldItemHierarchy);

		IvldItemHierarchy existingIvldItemHierarchy = _persistence.fetchByPrimaryKey(newIvldItemHierarchy.getPrimaryKey());

		Assert.assertNull(existingIvldItemHierarchy);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addIvldItemHierarchy();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldItemHierarchy newIvldItemHierarchy = _persistence.create(pk);

		newIvldItemHierarchy.setLevel1Alias(RandomTestUtil.randomString());

		newIvldItemHierarchy.setLevel9Alias(RandomTestUtil.randomString());

		newIvldItemHierarchy.setLevel3Alias(RandomTestUtil.randomString());

		newIvldItemHierarchy.setModifiedDate(RandomTestUtil.nextDate());

		newIvldItemHierarchy.setLevel13Alias(RandomTestUtil.randomString());

		newIvldItemHierarchy.setGtnCompany(RandomTestUtil.randomString());

		newIvldItemHierarchy.setSource(RandomTestUtil.randomString());

		newIvldItemHierarchy.setLevel15Alias(RandomTestUtil.randomString());

		newIvldItemHierarchy.setAddChgDelIndicator(RandomTestUtil.randomString());

		newIvldItemHierarchy.setModifiedBy(RandomTestUtil.randomString());

		newIvldItemHierarchy.setLevel6Alias(RandomTestUtil.randomString());

		newIvldItemHierarchy.setReasonForFailure(RandomTestUtil.randomString());

		newIvldItemHierarchy.setLevel10Alias(RandomTestUtil.randomString());

		newIvldItemHierarchy.setItemHierarchyIntfid(RandomTestUtil.randomString());

		newIvldItemHierarchy.setLevel5Alias(RandomTestUtil.randomString());

		newIvldItemHierarchy.setLevel18Alias(RandomTestUtil.randomString());

		newIvldItemHierarchy.setErrorField(RandomTestUtil.randomString());

		newIvldItemHierarchy.setGtnTherapeuticClass(RandomTestUtil.randomString());

		newIvldItemHierarchy.setLevel8(RandomTestUtil.randomString());

		newIvldItemHierarchy.setLevel9(RandomTestUtil.randomString());

		newIvldItemHierarchy.setLevel11Alias(RandomTestUtil.randomString());

		newIvldItemHierarchy.setLevel20(RandomTestUtil.randomString());

		newIvldItemHierarchy.setLevel4(RandomTestUtil.randomString());

		newIvldItemHierarchy.setLevel5(RandomTestUtil.randomString());

		newIvldItemHierarchy.setLevel6(RandomTestUtil.randomString());

		newIvldItemHierarchy.setLevel7(RandomTestUtil.randomString());

		newIvldItemHierarchy.setLevel16Alias(RandomTestUtil.randomString());

		newIvldItemHierarchy.setCreatedDate(RandomTestUtil.nextDate());

		newIvldItemHierarchy.setCreatedBy(RandomTestUtil.randomString());

		newIvldItemHierarchy.setGtnBrand(RandomTestUtil.randomString());

		newIvldItemHierarchy.setLevel2Alias(RandomTestUtil.randomString());

		newIvldItemHierarchy.setLevel1(RandomTestUtil.randomString());

		newIvldItemHierarchy.setLevel0(RandomTestUtil.randomString());

		newIvldItemHierarchy.setErrorCode(RandomTestUtil.randomString());

		newIvldItemHierarchy.setLevel3(RandomTestUtil.randomString());

		newIvldItemHierarchy.setLevel17Alias(RandomTestUtil.randomString());

		newIvldItemHierarchy.setLevel20Alias(RandomTestUtil.randomString());

		newIvldItemHierarchy.setIntfInsertedDate(RandomTestUtil.nextDate());

		newIvldItemHierarchy.setLevel7Alias(RandomTestUtil.randomString());

		newIvldItemHierarchy.setLevel2(RandomTestUtil.randomString());

		newIvldItemHierarchy.setReprocessedFlag(RandomTestUtil.randomString());

		newIvldItemHierarchy.setLevel8Alias(RandomTestUtil.randomString());

		newIvldItemHierarchy.setLevel10(RandomTestUtil.randomString());

		newIvldItemHierarchy.setLevel4Alias(RandomTestUtil.randomString());

		newIvldItemHierarchy.setLevel12(RandomTestUtil.randomString());

		newIvldItemHierarchy.setLevel11(RandomTestUtil.randomString());

		newIvldItemHierarchy.setLevel14(RandomTestUtil.randomString());

		newIvldItemHierarchy.setLevel0Tag(RandomTestUtil.randomString());

		newIvldItemHierarchy.setLevel13(RandomTestUtil.randomString());

		newIvldItemHierarchy.setLevel16(RandomTestUtil.randomString());

		newIvldItemHierarchy.setLevel15(RandomTestUtil.randomString());

		newIvldItemHierarchy.setLevel18(RandomTestUtil.randomString());

		newIvldItemHierarchy.setLevel17(RandomTestUtil.randomString());

		newIvldItemHierarchy.setLevel19(RandomTestUtil.randomString());

		newIvldItemHierarchy.setLevel12Alias(RandomTestUtil.randomString());

		newIvldItemHierarchy.setLevel19Alias(RandomTestUtil.randomString());

		newIvldItemHierarchy.setBatchId(RandomTestUtil.randomString());

		newIvldItemHierarchy.setLevel0Alias(RandomTestUtil.randomString());

		newIvldItemHierarchy.setLevel14Alias(RandomTestUtil.randomString());

		newIvldItemHierarchy.setCheckRecord(RandomTestUtil.randomBoolean());

		_ivldItemHierarchies.add(_persistence.update(newIvldItemHierarchy));

		IvldItemHierarchy existingIvldItemHierarchy = _persistence.findByPrimaryKey(newIvldItemHierarchy.getPrimaryKey());

		Assert.assertEquals(existingIvldItemHierarchy.getLevel1Alias(),
			newIvldItemHierarchy.getLevel1Alias());
		Assert.assertEquals(existingIvldItemHierarchy.getLevel9Alias(),
			newIvldItemHierarchy.getLevel9Alias());
		Assert.assertEquals(existingIvldItemHierarchy.getLevel3Alias(),
			newIvldItemHierarchy.getLevel3Alias());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldItemHierarchy.getModifiedDate()),
			Time.getShortTimestamp(newIvldItemHierarchy.getModifiedDate()));
		Assert.assertEquals(existingIvldItemHierarchy.getLevel13Alias(),
			newIvldItemHierarchy.getLevel13Alias());
		Assert.assertEquals(existingIvldItemHierarchy.getGtnCompany(),
			newIvldItemHierarchy.getGtnCompany());
		Assert.assertEquals(existingIvldItemHierarchy.getSource(),
			newIvldItemHierarchy.getSource());
		Assert.assertEquals(existingIvldItemHierarchy.getLevel15Alias(),
			newIvldItemHierarchy.getLevel15Alias());
		Assert.assertEquals(existingIvldItemHierarchy.getAddChgDelIndicator(),
			newIvldItemHierarchy.getAddChgDelIndicator());
		Assert.assertEquals(existingIvldItemHierarchy.getIvldItemHierarchySid(),
			newIvldItemHierarchy.getIvldItemHierarchySid());
		Assert.assertEquals(existingIvldItemHierarchy.getModifiedBy(),
			newIvldItemHierarchy.getModifiedBy());
		Assert.assertEquals(existingIvldItemHierarchy.getLevel6Alias(),
			newIvldItemHierarchy.getLevel6Alias());
		Assert.assertEquals(existingIvldItemHierarchy.getReasonForFailure(),
			newIvldItemHierarchy.getReasonForFailure());
		Assert.assertEquals(existingIvldItemHierarchy.getLevel10Alias(),
			newIvldItemHierarchy.getLevel10Alias());
		Assert.assertEquals(existingIvldItemHierarchy.getItemHierarchyIntfid(),
			newIvldItemHierarchy.getItemHierarchyIntfid());
		Assert.assertEquals(existingIvldItemHierarchy.getLevel5Alias(),
			newIvldItemHierarchy.getLevel5Alias());
		Assert.assertEquals(existingIvldItemHierarchy.getLevel18Alias(),
			newIvldItemHierarchy.getLevel18Alias());
		Assert.assertEquals(existingIvldItemHierarchy.getErrorField(),
			newIvldItemHierarchy.getErrorField());
		Assert.assertEquals(existingIvldItemHierarchy.getGtnTherapeuticClass(),
			newIvldItemHierarchy.getGtnTherapeuticClass());
		Assert.assertEquals(existingIvldItemHierarchy.getLevel8(),
			newIvldItemHierarchy.getLevel8());
		Assert.assertEquals(existingIvldItemHierarchy.getLevel9(),
			newIvldItemHierarchy.getLevel9());
		Assert.assertEquals(existingIvldItemHierarchy.getLevel11Alias(),
			newIvldItemHierarchy.getLevel11Alias());
		Assert.assertEquals(existingIvldItemHierarchy.getLevel20(),
			newIvldItemHierarchy.getLevel20());
		Assert.assertEquals(existingIvldItemHierarchy.getLevel4(),
			newIvldItemHierarchy.getLevel4());
		Assert.assertEquals(existingIvldItemHierarchy.getLevel5(),
			newIvldItemHierarchy.getLevel5());
		Assert.assertEquals(existingIvldItemHierarchy.getLevel6(),
			newIvldItemHierarchy.getLevel6());
		Assert.assertEquals(existingIvldItemHierarchy.getLevel7(),
			newIvldItemHierarchy.getLevel7());
		Assert.assertEquals(existingIvldItemHierarchy.getLevel16Alias(),
			newIvldItemHierarchy.getLevel16Alias());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldItemHierarchy.getCreatedDate()),
			Time.getShortTimestamp(newIvldItemHierarchy.getCreatedDate()));
		Assert.assertEquals(existingIvldItemHierarchy.getCreatedBy(),
			newIvldItemHierarchy.getCreatedBy());
		Assert.assertEquals(existingIvldItemHierarchy.getGtnBrand(),
			newIvldItemHierarchy.getGtnBrand());
		Assert.assertEquals(existingIvldItemHierarchy.getLevel2Alias(),
			newIvldItemHierarchy.getLevel2Alias());
		Assert.assertEquals(existingIvldItemHierarchy.getLevel1(),
			newIvldItemHierarchy.getLevel1());
		Assert.assertEquals(existingIvldItemHierarchy.getLevel0(),
			newIvldItemHierarchy.getLevel0());
		Assert.assertEquals(existingIvldItemHierarchy.getErrorCode(),
			newIvldItemHierarchy.getErrorCode());
		Assert.assertEquals(existingIvldItemHierarchy.getLevel3(),
			newIvldItemHierarchy.getLevel3());
		Assert.assertEquals(existingIvldItemHierarchy.getLevel17Alias(),
			newIvldItemHierarchy.getLevel17Alias());
		Assert.assertEquals(existingIvldItemHierarchy.getLevel20Alias(),
			newIvldItemHierarchy.getLevel20Alias());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldItemHierarchy.getIntfInsertedDate()),
			Time.getShortTimestamp(newIvldItemHierarchy.getIntfInsertedDate()));
		Assert.assertEquals(existingIvldItemHierarchy.getLevel7Alias(),
			newIvldItemHierarchy.getLevel7Alias());
		Assert.assertEquals(existingIvldItemHierarchy.getLevel2(),
			newIvldItemHierarchy.getLevel2());
		Assert.assertEquals(existingIvldItemHierarchy.getReprocessedFlag(),
			newIvldItemHierarchy.getReprocessedFlag());
		Assert.assertEquals(existingIvldItemHierarchy.getLevel8Alias(),
			newIvldItemHierarchy.getLevel8Alias());
		Assert.assertEquals(existingIvldItemHierarchy.getLevel10(),
			newIvldItemHierarchy.getLevel10());
		Assert.assertEquals(existingIvldItemHierarchy.getLevel4Alias(),
			newIvldItemHierarchy.getLevel4Alias());
		Assert.assertEquals(existingIvldItemHierarchy.getLevel12(),
			newIvldItemHierarchy.getLevel12());
		Assert.assertEquals(existingIvldItemHierarchy.getLevel11(),
			newIvldItemHierarchy.getLevel11());
		Assert.assertEquals(existingIvldItemHierarchy.getLevel14(),
			newIvldItemHierarchy.getLevel14());
		Assert.assertEquals(existingIvldItemHierarchy.getLevel0Tag(),
			newIvldItemHierarchy.getLevel0Tag());
		Assert.assertEquals(existingIvldItemHierarchy.getLevel13(),
			newIvldItemHierarchy.getLevel13());
		Assert.assertEquals(existingIvldItemHierarchy.getLevel16(),
			newIvldItemHierarchy.getLevel16());
		Assert.assertEquals(existingIvldItemHierarchy.getLevel15(),
			newIvldItemHierarchy.getLevel15());
		Assert.assertEquals(existingIvldItemHierarchy.getLevel18(),
			newIvldItemHierarchy.getLevel18());
		Assert.assertEquals(existingIvldItemHierarchy.getLevel17(),
			newIvldItemHierarchy.getLevel17());
		Assert.assertEquals(existingIvldItemHierarchy.getLevel19(),
			newIvldItemHierarchy.getLevel19());
		Assert.assertEquals(existingIvldItemHierarchy.getLevel12Alias(),
			newIvldItemHierarchy.getLevel12Alias());
		Assert.assertEquals(existingIvldItemHierarchy.getLevel19Alias(),
			newIvldItemHierarchy.getLevel19Alias());
		Assert.assertEquals(existingIvldItemHierarchy.getBatchId(),
			newIvldItemHierarchy.getBatchId());
		Assert.assertEquals(existingIvldItemHierarchy.getLevel0Alias(),
			newIvldItemHierarchy.getLevel0Alias());
		Assert.assertEquals(existingIvldItemHierarchy.getLevel14Alias(),
			newIvldItemHierarchy.getLevel14Alias());
		Assert.assertEquals(existingIvldItemHierarchy.getCheckRecord(),
			newIvldItemHierarchy.getCheckRecord());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		IvldItemHierarchy newIvldItemHierarchy = addIvldItemHierarchy();

		IvldItemHierarchy existingIvldItemHierarchy = _persistence.findByPrimaryKey(newIvldItemHierarchy.getPrimaryKey());

		Assert.assertEquals(existingIvldItemHierarchy, newIvldItemHierarchy);
	}

	@Test(expected = NoSuchIvldItemHierarchyException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<IvldItemHierarchy> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("IVLD_ITEM_HIERARCHY",
			"level1Alias", true, "level9Alias", true, "level3Alias", true,
			"modifiedDate", true, "level13Alias", true, "gtnCompany", true,
			"source", true, "level15Alias", true, "addChgDelIndicator", true,
			"ivldItemHierarchySid", true, "modifiedBy", true, "level6Alias",
			true, "reasonForFailure", true, "level10Alias", true,
			"itemHierarchyIntfid", true, "level5Alias", true, "level18Alias",
			true, "errorField", true, "gtnTherapeuticClass", true, "level8",
			true, "level9", true, "level11Alias", true, "level20", true,
			"level4", true, "level5", true, "level6", true, "level7", true,
			"level16Alias", true, "createdDate", true, "createdBy", true,
			"gtnBrand", true, "level2Alias", true, "level1", true, "level0",
			true, "errorCode", true, "level3", true, "level17Alias", true,
			"level20Alias", true, "intfInsertedDate", true, "level7Alias",
			true, "level2", true, "reprocessedFlag", true, "level8Alias", true,
			"level10", true, "level4Alias", true, "level12", true, "level11",
			true, "level14", true, "level0Tag", true, "level13", true,
			"level16", true, "level15", true, "level18", true, "level17", true,
			"level19", true, "level12Alias", true, "level19Alias", true,
			"batchId", true, "level0Alias", true, "level14Alias", true,
			"checkRecord", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		IvldItemHierarchy newIvldItemHierarchy = addIvldItemHierarchy();

		IvldItemHierarchy existingIvldItemHierarchy = _persistence.fetchByPrimaryKey(newIvldItemHierarchy.getPrimaryKey());

		Assert.assertEquals(existingIvldItemHierarchy, newIvldItemHierarchy);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldItemHierarchy missingIvldItemHierarchy = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingIvldItemHierarchy);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		IvldItemHierarchy newIvldItemHierarchy1 = addIvldItemHierarchy();
		IvldItemHierarchy newIvldItemHierarchy2 = addIvldItemHierarchy();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldItemHierarchy1.getPrimaryKey());
		primaryKeys.add(newIvldItemHierarchy2.getPrimaryKey());

		Map<Serializable, IvldItemHierarchy> ivldItemHierarchies = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, ivldItemHierarchies.size());
		Assert.assertEquals(newIvldItemHierarchy1,
			ivldItemHierarchies.get(newIvldItemHierarchy1.getPrimaryKey()));
		Assert.assertEquals(newIvldItemHierarchy2,
			ivldItemHierarchies.get(newIvldItemHierarchy2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, IvldItemHierarchy> ivldItemHierarchies = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ivldItemHierarchies.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		IvldItemHierarchy newIvldItemHierarchy = addIvldItemHierarchy();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldItemHierarchy.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, IvldItemHierarchy> ivldItemHierarchies = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ivldItemHierarchies.size());
		Assert.assertEquals(newIvldItemHierarchy,
			ivldItemHierarchies.get(newIvldItemHierarchy.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, IvldItemHierarchy> ivldItemHierarchies = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ivldItemHierarchies.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		IvldItemHierarchy newIvldItemHierarchy = addIvldItemHierarchy();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldItemHierarchy.getPrimaryKey());

		Map<Serializable, IvldItemHierarchy> ivldItemHierarchies = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ivldItemHierarchies.size());
		Assert.assertEquals(newIvldItemHierarchy,
			ivldItemHierarchies.get(newIvldItemHierarchy.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = IvldItemHierarchyLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<IvldItemHierarchy>() {
				@Override
				public void performAction(IvldItemHierarchy ivldItemHierarchy) {
					Assert.assertNotNull(ivldItemHierarchy);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		IvldItemHierarchy newIvldItemHierarchy = addIvldItemHierarchy();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldItemHierarchy.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("ivldItemHierarchySid",
				newIvldItemHierarchy.getIvldItemHierarchySid()));

		List<IvldItemHierarchy> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		IvldItemHierarchy existingIvldItemHierarchy = result.get(0);

		Assert.assertEquals(existingIvldItemHierarchy, newIvldItemHierarchy);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldItemHierarchy.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("ivldItemHierarchySid",
				RandomTestUtil.nextInt()));

		List<IvldItemHierarchy> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		IvldItemHierarchy newIvldItemHierarchy = addIvldItemHierarchy();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldItemHierarchy.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"ivldItemHierarchySid"));

		Object newIvldItemHierarchySid = newIvldItemHierarchy.getIvldItemHierarchySid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("ivldItemHierarchySid",
				new Object[] { newIvldItemHierarchySid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingIvldItemHierarchySid = result.get(0);

		Assert.assertEquals(existingIvldItemHierarchySid,
			newIvldItemHierarchySid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldItemHierarchy.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"ivldItemHierarchySid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("ivldItemHierarchySid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected IvldItemHierarchy addIvldItemHierarchy()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldItemHierarchy ivldItemHierarchy = _persistence.create(pk);

		ivldItemHierarchy.setLevel1Alias(RandomTestUtil.randomString());

		ivldItemHierarchy.setLevel9Alias(RandomTestUtil.randomString());

		ivldItemHierarchy.setLevel3Alias(RandomTestUtil.randomString());

		ivldItemHierarchy.setModifiedDate(RandomTestUtil.nextDate());

		ivldItemHierarchy.setLevel13Alias(RandomTestUtil.randomString());

		ivldItemHierarchy.setGtnCompany(RandomTestUtil.randomString());

		ivldItemHierarchy.setSource(RandomTestUtil.randomString());

		ivldItemHierarchy.setLevel15Alias(RandomTestUtil.randomString());

		ivldItemHierarchy.setAddChgDelIndicator(RandomTestUtil.randomString());

		ivldItemHierarchy.setModifiedBy(RandomTestUtil.randomString());

		ivldItemHierarchy.setLevel6Alias(RandomTestUtil.randomString());

		ivldItemHierarchy.setReasonForFailure(RandomTestUtil.randomString());

		ivldItemHierarchy.setLevel10Alias(RandomTestUtil.randomString());

		ivldItemHierarchy.setItemHierarchyIntfid(RandomTestUtil.randomString());

		ivldItemHierarchy.setLevel5Alias(RandomTestUtil.randomString());

		ivldItemHierarchy.setLevel18Alias(RandomTestUtil.randomString());

		ivldItemHierarchy.setErrorField(RandomTestUtil.randomString());

		ivldItemHierarchy.setGtnTherapeuticClass(RandomTestUtil.randomString());

		ivldItemHierarchy.setLevel8(RandomTestUtil.randomString());

		ivldItemHierarchy.setLevel9(RandomTestUtil.randomString());

		ivldItemHierarchy.setLevel11Alias(RandomTestUtil.randomString());

		ivldItemHierarchy.setLevel20(RandomTestUtil.randomString());

		ivldItemHierarchy.setLevel4(RandomTestUtil.randomString());

		ivldItemHierarchy.setLevel5(RandomTestUtil.randomString());

		ivldItemHierarchy.setLevel6(RandomTestUtil.randomString());

		ivldItemHierarchy.setLevel7(RandomTestUtil.randomString());

		ivldItemHierarchy.setLevel16Alias(RandomTestUtil.randomString());

		ivldItemHierarchy.setCreatedDate(RandomTestUtil.nextDate());

		ivldItemHierarchy.setCreatedBy(RandomTestUtil.randomString());

		ivldItemHierarchy.setGtnBrand(RandomTestUtil.randomString());

		ivldItemHierarchy.setLevel2Alias(RandomTestUtil.randomString());

		ivldItemHierarchy.setLevel1(RandomTestUtil.randomString());

		ivldItemHierarchy.setLevel0(RandomTestUtil.randomString());

		ivldItemHierarchy.setErrorCode(RandomTestUtil.randomString());

		ivldItemHierarchy.setLevel3(RandomTestUtil.randomString());

		ivldItemHierarchy.setLevel17Alias(RandomTestUtil.randomString());

		ivldItemHierarchy.setLevel20Alias(RandomTestUtil.randomString());

		ivldItemHierarchy.setIntfInsertedDate(RandomTestUtil.nextDate());

		ivldItemHierarchy.setLevel7Alias(RandomTestUtil.randomString());

		ivldItemHierarchy.setLevel2(RandomTestUtil.randomString());

		ivldItemHierarchy.setReprocessedFlag(RandomTestUtil.randomString());

		ivldItemHierarchy.setLevel8Alias(RandomTestUtil.randomString());

		ivldItemHierarchy.setLevel10(RandomTestUtil.randomString());

		ivldItemHierarchy.setLevel4Alias(RandomTestUtil.randomString());

		ivldItemHierarchy.setLevel12(RandomTestUtil.randomString());

		ivldItemHierarchy.setLevel11(RandomTestUtil.randomString());

		ivldItemHierarchy.setLevel14(RandomTestUtil.randomString());

		ivldItemHierarchy.setLevel0Tag(RandomTestUtil.randomString());

		ivldItemHierarchy.setLevel13(RandomTestUtil.randomString());

		ivldItemHierarchy.setLevel16(RandomTestUtil.randomString());

		ivldItemHierarchy.setLevel15(RandomTestUtil.randomString());

		ivldItemHierarchy.setLevel18(RandomTestUtil.randomString());

		ivldItemHierarchy.setLevel17(RandomTestUtil.randomString());

		ivldItemHierarchy.setLevel19(RandomTestUtil.randomString());

		ivldItemHierarchy.setLevel12Alias(RandomTestUtil.randomString());

		ivldItemHierarchy.setLevel19Alias(RandomTestUtil.randomString());

		ivldItemHierarchy.setBatchId(RandomTestUtil.randomString());

		ivldItemHierarchy.setLevel0Alias(RandomTestUtil.randomString());

		ivldItemHierarchy.setLevel14Alias(RandomTestUtil.randomString());

		ivldItemHierarchy.setCheckRecord(RandomTestUtil.randomBoolean());

		_ivldItemHierarchies.add(_persistence.update(ivldItemHierarchy));

		return ivldItemHierarchy;
	}

	private List<IvldItemHierarchy> _ivldItemHierarchies = new ArrayList<IvldItemHierarchy>();
	private IvldItemHierarchyPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}