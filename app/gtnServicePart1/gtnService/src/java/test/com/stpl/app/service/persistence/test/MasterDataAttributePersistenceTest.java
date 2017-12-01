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
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import com.stpl.app.exception.NoSuchMasterDataAttributeException;
import com.stpl.app.model.MasterDataAttribute;
import com.stpl.app.service.MasterDataAttributeLocalServiceUtil;
import com.stpl.app.service.persistence.MasterDataAttributePersistence;
import com.stpl.app.service.persistence.MasterDataAttributeUtil;

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
public class MasterDataAttributePersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = MasterDataAttributeUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<MasterDataAttribute> iterator = _masterDataAttributes.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		MasterDataAttribute masterDataAttribute = _persistence.create(pk);

		Assert.assertNotNull(masterDataAttribute);

		Assert.assertEquals(masterDataAttribute.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		MasterDataAttribute newMasterDataAttribute = addMasterDataAttribute();

		_persistence.remove(newMasterDataAttribute);

		MasterDataAttribute existingMasterDataAttribute = _persistence.fetchByPrimaryKey(newMasterDataAttribute.getPrimaryKey());

		Assert.assertNull(existingMasterDataAttribute);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addMasterDataAttribute();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		MasterDataAttribute newMasterDataAttribute = _persistence.create(pk);

		newMasterDataAttribute.setColumn15(RandomTestUtil.randomString());

		newMasterDataAttribute.setColumn14(RandomTestUtil.randomString());

		newMasterDataAttribute.setColumn17(RandomTestUtil.randomString());

		newMasterDataAttribute.setColumn16(RandomTestUtil.randomString());

		newMasterDataAttribute.setColumn11(RandomTestUtil.randomString());

		newMasterDataAttribute.setModifiedBy(RandomTestUtil.nextInt());

		newMasterDataAttribute.setColumn10(RandomTestUtil.randomString());

		newMasterDataAttribute.setCreatedDate(RandomTestUtil.nextDate());

		newMasterDataAttribute.setColumn13(RandomTestUtil.randomString());

		newMasterDataAttribute.setColumn12(RandomTestUtil.randomString());

		newMasterDataAttribute.setBatchId(RandomTestUtil.randomString());

		newMasterDataAttribute.setColumn19(RandomTestUtil.randomString());

		newMasterDataAttribute.setColumn18(RandomTestUtil.randomString());

		newMasterDataAttribute.setMasterAttribute(RandomTestUtil.randomString());

		newMasterDataAttribute.setCreatedBy(RandomTestUtil.nextInt());

		newMasterDataAttribute.setMasterType(RandomTestUtil.randomString());

		newMasterDataAttribute.setMasterId(RandomTestUtil.randomString());

		newMasterDataAttribute.setColumn20(RandomTestUtil.randomString());

		newMasterDataAttribute.setColumn9(RandomTestUtil.randomString());

		newMasterDataAttribute.setModifiedDate(RandomTestUtil.nextDate());

		newMasterDataAttribute.setColumn6(RandomTestUtil.randomString());

		newMasterDataAttribute.setColumn5(RandomTestUtil.randomString());

		newMasterDataAttribute.setColumn8(RandomTestUtil.randomString());

		newMasterDataAttribute.setColumn7(RandomTestUtil.randomString());

		newMasterDataAttribute.setRecordLockStatus(RandomTestUtil.randomBoolean());

		newMasterDataAttribute.setColumn2(RandomTestUtil.randomString());

		newMasterDataAttribute.setColumn1(RandomTestUtil.randomString());

		newMasterDataAttribute.setColumn4(RandomTestUtil.randomString());

		newMasterDataAttribute.setColumn3(RandomTestUtil.randomString());

		newMasterDataAttribute.setSource(RandomTestUtil.randomString());

		newMasterDataAttribute.setInboundStatus(RandomTestUtil.randomString());

		_masterDataAttributes.add(_persistence.update(newMasterDataAttribute));

		MasterDataAttribute existingMasterDataAttribute = _persistence.findByPrimaryKey(newMasterDataAttribute.getPrimaryKey());

		Assert.assertEquals(existingMasterDataAttribute.getColumn15(),
			newMasterDataAttribute.getColumn15());
		Assert.assertEquals(existingMasterDataAttribute.getColumn14(),
			newMasterDataAttribute.getColumn14());
		Assert.assertEquals(existingMasterDataAttribute.getColumn17(),
			newMasterDataAttribute.getColumn17());
		Assert.assertEquals(existingMasterDataAttribute.getColumn16(),
			newMasterDataAttribute.getColumn16());
		Assert.assertEquals(existingMasterDataAttribute.getColumn11(),
			newMasterDataAttribute.getColumn11());
		Assert.assertEquals(existingMasterDataAttribute.getModifiedBy(),
			newMasterDataAttribute.getModifiedBy());
		Assert.assertEquals(existingMasterDataAttribute.getColumn10(),
			newMasterDataAttribute.getColumn10());
		Assert.assertEquals(Time.getShortTimestamp(
				existingMasterDataAttribute.getCreatedDate()),
			Time.getShortTimestamp(newMasterDataAttribute.getCreatedDate()));
		Assert.assertEquals(existingMasterDataAttribute.getColumn13(),
			newMasterDataAttribute.getColumn13());
		Assert.assertEquals(existingMasterDataAttribute.getColumn12(),
			newMasterDataAttribute.getColumn12());
		Assert.assertEquals(existingMasterDataAttribute.getBatchId(),
			newMasterDataAttribute.getBatchId());
		Assert.assertEquals(existingMasterDataAttribute.getColumn19(),
			newMasterDataAttribute.getColumn19());
		Assert.assertEquals(existingMasterDataAttribute.getColumn18(),
			newMasterDataAttribute.getColumn18());
		Assert.assertEquals(existingMasterDataAttribute.getMasterAttribute(),
			newMasterDataAttribute.getMasterAttribute());
		Assert.assertEquals(existingMasterDataAttribute.getCreatedBy(),
			newMasterDataAttribute.getCreatedBy());
		Assert.assertEquals(existingMasterDataAttribute.getMasterType(),
			newMasterDataAttribute.getMasterType());
		Assert.assertEquals(existingMasterDataAttribute.getMasterId(),
			newMasterDataAttribute.getMasterId());
		Assert.assertEquals(existingMasterDataAttribute.getColumn20(),
			newMasterDataAttribute.getColumn20());
		Assert.assertEquals(existingMasterDataAttribute.getColumn9(),
			newMasterDataAttribute.getColumn9());
		Assert.assertEquals(Time.getShortTimestamp(
				existingMasterDataAttribute.getModifiedDate()),
			Time.getShortTimestamp(newMasterDataAttribute.getModifiedDate()));
		Assert.assertEquals(existingMasterDataAttribute.getColumn6(),
			newMasterDataAttribute.getColumn6());
		Assert.assertEquals(existingMasterDataAttribute.getMasterDataAttributeSid(),
			newMasterDataAttribute.getMasterDataAttributeSid());
		Assert.assertEquals(existingMasterDataAttribute.getColumn5(),
			newMasterDataAttribute.getColumn5());
		Assert.assertEquals(existingMasterDataAttribute.getColumn8(),
			newMasterDataAttribute.getColumn8());
		Assert.assertEquals(existingMasterDataAttribute.getColumn7(),
			newMasterDataAttribute.getColumn7());
		Assert.assertEquals(existingMasterDataAttribute.getRecordLockStatus(),
			newMasterDataAttribute.getRecordLockStatus());
		Assert.assertEquals(existingMasterDataAttribute.getColumn2(),
			newMasterDataAttribute.getColumn2());
		Assert.assertEquals(existingMasterDataAttribute.getColumn1(),
			newMasterDataAttribute.getColumn1());
		Assert.assertEquals(existingMasterDataAttribute.getColumn4(),
			newMasterDataAttribute.getColumn4());
		Assert.assertEquals(existingMasterDataAttribute.getColumn3(),
			newMasterDataAttribute.getColumn3());
		Assert.assertEquals(existingMasterDataAttribute.getSource(),
			newMasterDataAttribute.getSource());
		Assert.assertEquals(existingMasterDataAttribute.getInboundStatus(),
			newMasterDataAttribute.getInboundStatus());
	}

	@Test
	public void testCountByMasterType() throws Exception {
		_persistence.countByMasterType(StringPool.BLANK);

		_persistence.countByMasterType(StringPool.NULL);

		_persistence.countByMasterType((String)null);
	}

	@Test
	public void testCountByMasterAttribute() throws Exception {
		_persistence.countByMasterAttribute(StringPool.BLANK);

		_persistence.countByMasterAttribute(StringPool.NULL);

		_persistence.countByMasterAttribute((String)null);
	}

	@Test
	public void testCountByMasterId() throws Exception {
		_persistence.countByMasterId(StringPool.BLANK);

		_persistence.countByMasterId(StringPool.NULL);

		_persistence.countByMasterId((String)null);
	}

	@Test
	public void testCountByMasterDataAttributeUnique()
		throws Exception {
		_persistence.countByMasterDataAttributeUnique(StringPool.BLANK,
			StringPool.BLANK, StringPool.BLANK);

		_persistence.countByMasterDataAttributeUnique(StringPool.NULL,
			StringPool.NULL, StringPool.NULL);

		_persistence.countByMasterDataAttributeUnique((String)null,
			(String)null, (String)null);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		MasterDataAttribute newMasterDataAttribute = addMasterDataAttribute();

		MasterDataAttribute existingMasterDataAttribute = _persistence.findByPrimaryKey(newMasterDataAttribute.getPrimaryKey());

		Assert.assertEquals(existingMasterDataAttribute, newMasterDataAttribute);
	}

	@Test(expected = NoSuchMasterDataAttributeException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<MasterDataAttribute> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("MASTER_DATA_ATTRIBUTE",
			"column15", true, "column14", true, "column17", true, "column16",
			true, "column11", true, "modifiedBy", true, "column10", true,
			"createdDate", true, "column13", true, "column12", true, "batchId",
			true, "column19", true, "column18", true, "masterAttribute", true,
			"createdBy", true, "masterType", true, "masterId", true,
			"column20", true, "column9", true, "modifiedDate", true, "column6",
			true, "masterDataAttributeSid", true, "column5", true, "column8",
			true, "column7", true, "recordLockStatus", true, "column2", true,
			"column1", true, "column4", true, "column3", true, "source", true,
			"inboundStatus", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		MasterDataAttribute newMasterDataAttribute = addMasterDataAttribute();

		MasterDataAttribute existingMasterDataAttribute = _persistence.fetchByPrimaryKey(newMasterDataAttribute.getPrimaryKey());

		Assert.assertEquals(existingMasterDataAttribute, newMasterDataAttribute);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		MasterDataAttribute missingMasterDataAttribute = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingMasterDataAttribute);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		MasterDataAttribute newMasterDataAttribute1 = addMasterDataAttribute();
		MasterDataAttribute newMasterDataAttribute2 = addMasterDataAttribute();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newMasterDataAttribute1.getPrimaryKey());
		primaryKeys.add(newMasterDataAttribute2.getPrimaryKey());

		Map<Serializable, MasterDataAttribute> masterDataAttributes = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, masterDataAttributes.size());
		Assert.assertEquals(newMasterDataAttribute1,
			masterDataAttributes.get(newMasterDataAttribute1.getPrimaryKey()));
		Assert.assertEquals(newMasterDataAttribute2,
			masterDataAttributes.get(newMasterDataAttribute2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, MasterDataAttribute> masterDataAttributes = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(masterDataAttributes.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		MasterDataAttribute newMasterDataAttribute = addMasterDataAttribute();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newMasterDataAttribute.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, MasterDataAttribute> masterDataAttributes = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, masterDataAttributes.size());
		Assert.assertEquals(newMasterDataAttribute,
			masterDataAttributes.get(newMasterDataAttribute.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, MasterDataAttribute> masterDataAttributes = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(masterDataAttributes.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		MasterDataAttribute newMasterDataAttribute = addMasterDataAttribute();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newMasterDataAttribute.getPrimaryKey());

		Map<Serializable, MasterDataAttribute> masterDataAttributes = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, masterDataAttributes.size());
		Assert.assertEquals(newMasterDataAttribute,
			masterDataAttributes.get(newMasterDataAttribute.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = MasterDataAttributeLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<MasterDataAttribute>() {
				@Override
				public void performAction(
					MasterDataAttribute masterDataAttribute) {
					Assert.assertNotNull(masterDataAttribute);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		MasterDataAttribute newMasterDataAttribute = addMasterDataAttribute();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MasterDataAttribute.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("masterDataAttributeSid",
				newMasterDataAttribute.getMasterDataAttributeSid()));

		List<MasterDataAttribute> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		MasterDataAttribute existingMasterDataAttribute = result.get(0);

		Assert.assertEquals(existingMasterDataAttribute, newMasterDataAttribute);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MasterDataAttribute.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("masterDataAttributeSid",
				RandomTestUtil.nextInt()));

		List<MasterDataAttribute> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		MasterDataAttribute newMasterDataAttribute = addMasterDataAttribute();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MasterDataAttribute.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"masterDataAttributeSid"));

		Object newMasterDataAttributeSid = newMasterDataAttribute.getMasterDataAttributeSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("masterDataAttributeSid",
				new Object[] { newMasterDataAttributeSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingMasterDataAttributeSid = result.get(0);

		Assert.assertEquals(existingMasterDataAttributeSid,
			newMasterDataAttributeSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MasterDataAttribute.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"masterDataAttributeSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("masterDataAttributeSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected MasterDataAttribute addMasterDataAttribute()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		MasterDataAttribute masterDataAttribute = _persistence.create(pk);

		masterDataAttribute.setColumn15(RandomTestUtil.randomString());

		masterDataAttribute.setColumn14(RandomTestUtil.randomString());

		masterDataAttribute.setColumn17(RandomTestUtil.randomString());

		masterDataAttribute.setColumn16(RandomTestUtil.randomString());

		masterDataAttribute.setColumn11(RandomTestUtil.randomString());

		masterDataAttribute.setModifiedBy(RandomTestUtil.nextInt());

		masterDataAttribute.setColumn10(RandomTestUtil.randomString());

		masterDataAttribute.setCreatedDate(RandomTestUtil.nextDate());

		masterDataAttribute.setColumn13(RandomTestUtil.randomString());

		masterDataAttribute.setColumn12(RandomTestUtil.randomString());

		masterDataAttribute.setBatchId(RandomTestUtil.randomString());

		masterDataAttribute.setColumn19(RandomTestUtil.randomString());

		masterDataAttribute.setColumn18(RandomTestUtil.randomString());

		masterDataAttribute.setMasterAttribute(RandomTestUtil.randomString());

		masterDataAttribute.setCreatedBy(RandomTestUtil.nextInt());

		masterDataAttribute.setMasterType(RandomTestUtil.randomString());

		masterDataAttribute.setMasterId(RandomTestUtil.randomString());

		masterDataAttribute.setColumn20(RandomTestUtil.randomString());

		masterDataAttribute.setColumn9(RandomTestUtil.randomString());

		masterDataAttribute.setModifiedDate(RandomTestUtil.nextDate());

		masterDataAttribute.setColumn6(RandomTestUtil.randomString());

		masterDataAttribute.setColumn5(RandomTestUtil.randomString());

		masterDataAttribute.setColumn8(RandomTestUtil.randomString());

		masterDataAttribute.setColumn7(RandomTestUtil.randomString());

		masterDataAttribute.setRecordLockStatus(RandomTestUtil.randomBoolean());

		masterDataAttribute.setColumn2(RandomTestUtil.randomString());

		masterDataAttribute.setColumn1(RandomTestUtil.randomString());

		masterDataAttribute.setColumn4(RandomTestUtil.randomString());

		masterDataAttribute.setColumn3(RandomTestUtil.randomString());

		masterDataAttribute.setSource(RandomTestUtil.randomString());

		masterDataAttribute.setInboundStatus(RandomTestUtil.randomString());

		_masterDataAttributes.add(_persistence.update(masterDataAttribute));

		return masterDataAttribute;
	}

	private List<MasterDataAttribute> _masterDataAttributes = new ArrayList<MasterDataAttribute>();
	private MasterDataAttributePersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}