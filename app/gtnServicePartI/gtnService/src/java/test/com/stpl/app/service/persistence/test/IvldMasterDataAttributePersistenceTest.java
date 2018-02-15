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

import com.stpl.app.exception.NoSuchIvldMasterDataAttributeException;
import com.stpl.app.model.IvldMasterDataAttribute;
import com.stpl.app.service.IvldMasterDataAttributeLocalServiceUtil;
import com.stpl.app.service.persistence.IvldMasterDataAttributePersistence;
import com.stpl.app.service.persistence.IvldMasterDataAttributeUtil;

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
public class IvldMasterDataAttributePersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = IvldMasterDataAttributeUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<IvldMasterDataAttribute> iterator = _ivldMasterDataAttributes.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldMasterDataAttribute ivldMasterDataAttribute = _persistence.create(pk);

		Assert.assertNotNull(ivldMasterDataAttribute);

		Assert.assertEquals(ivldMasterDataAttribute.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		IvldMasterDataAttribute newIvldMasterDataAttribute = addIvldMasterDataAttribute();

		_persistence.remove(newIvldMasterDataAttribute);

		IvldMasterDataAttribute existingIvldMasterDataAttribute = _persistence.fetchByPrimaryKey(newIvldMasterDataAttribute.getPrimaryKey());

		Assert.assertNull(existingIvldMasterDataAttribute);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addIvldMasterDataAttribute();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldMasterDataAttribute newIvldMasterDataAttribute = _persistence.create(pk);

		newIvldMasterDataAttribute.setColumn19(RandomTestUtil.randomString());

		newIvldMasterDataAttribute.setColumn18(RandomTestUtil.randomString());

		newIvldMasterDataAttribute.setMasterAttribute(RandomTestUtil.randomString());

		newIvldMasterDataAttribute.setMasterType(RandomTestUtil.randomString());

		newIvldMasterDataAttribute.setDataAttributeIntfid(RandomTestUtil.randomString());

		newIvldMasterDataAttribute.setModifiedDate(RandomTestUtil.nextDate());

		newIvldMasterDataAttribute.setSource(RandomTestUtil.randomString());

		newIvldMasterDataAttribute.setCreatedDate(RandomTestUtil.nextDate());

		newIvldMasterDataAttribute.setCreatedBy(RandomTestUtil.randomString());

		newIvldMasterDataAttribute.setAddChgDelIndicator(RandomTestUtil.randomString());

		newIvldMasterDataAttribute.setMasterId(RandomTestUtil.randomString());

		newIvldMasterDataAttribute.setColumn10(RandomTestUtil.randomString());

		newIvldMasterDataAttribute.setColumn11(RandomTestUtil.randomString());

		newIvldMasterDataAttribute.setErrorCode(RandomTestUtil.randomString());

		newIvldMasterDataAttribute.setColumn12(RandomTestUtil.randomString());

		newIvldMasterDataAttribute.setIntfInsertedDate(RandomTestUtil.nextDate());

		newIvldMasterDataAttribute.setModifiedBy(RandomTestUtil.randomString());

		newIvldMasterDataAttribute.setColumn13(RandomTestUtil.randomString());

		newIvldMasterDataAttribute.setColumn14(RandomTestUtil.randomString());

		newIvldMasterDataAttribute.setReprocessedFlag(RandomTestUtil.randomString());

		newIvldMasterDataAttribute.setColumn15(RandomTestUtil.randomString());

		newIvldMasterDataAttribute.setColumn16(RandomTestUtil.randomString());

		newIvldMasterDataAttribute.setColumn17(RandomTestUtil.randomString());

		newIvldMasterDataAttribute.setColumn4(RandomTestUtil.randomString());

		newIvldMasterDataAttribute.setColumn3(RandomTestUtil.randomString());

		newIvldMasterDataAttribute.setColumn2(RandomTestUtil.randomString());

		newIvldMasterDataAttribute.setColumn1(RandomTestUtil.randomString());

		newIvldMasterDataAttribute.setColumn8(RandomTestUtil.randomString());

		newIvldMasterDataAttribute.setReasonForFailure(RandomTestUtil.randomString());

		newIvldMasterDataAttribute.setColumn7(RandomTestUtil.randomString());

		newIvldMasterDataAttribute.setColumn6(RandomTestUtil.randomString());

		newIvldMasterDataAttribute.setColumn5(RandomTestUtil.randomString());

		newIvldMasterDataAttribute.setColumn20(RandomTestUtil.randomString());

		newIvldMasterDataAttribute.setBatchId(RandomTestUtil.randomString());

		newIvldMasterDataAttribute.setErrorField(RandomTestUtil.randomString());

		newIvldMasterDataAttribute.setColumn9(RandomTestUtil.randomString());

		newIvldMasterDataAttribute.setCheckRecord(RandomTestUtil.randomBoolean());

		_ivldMasterDataAttributes.add(_persistence.update(
				newIvldMasterDataAttribute));

		IvldMasterDataAttribute existingIvldMasterDataAttribute = _persistence.findByPrimaryKey(newIvldMasterDataAttribute.getPrimaryKey());

		Assert.assertEquals(existingIvldMasterDataAttribute.getColumn19(),
			newIvldMasterDataAttribute.getColumn19());
		Assert.assertEquals(existingIvldMasterDataAttribute.getColumn18(),
			newIvldMasterDataAttribute.getColumn18());
		Assert.assertEquals(existingIvldMasterDataAttribute.getMasterAttribute(),
			newIvldMasterDataAttribute.getMasterAttribute());
		Assert.assertEquals(existingIvldMasterDataAttribute.getMasterType(),
			newIvldMasterDataAttribute.getMasterType());
		Assert.assertEquals(existingIvldMasterDataAttribute.getDataAttributeIntfid(),
			newIvldMasterDataAttribute.getDataAttributeIntfid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldMasterDataAttribute.getModifiedDate()),
			Time.getShortTimestamp(newIvldMasterDataAttribute.getModifiedDate()));
		Assert.assertEquals(existingIvldMasterDataAttribute.getSource(),
			newIvldMasterDataAttribute.getSource());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldMasterDataAttribute.getCreatedDate()),
			Time.getShortTimestamp(newIvldMasterDataAttribute.getCreatedDate()));
		Assert.assertEquals(existingIvldMasterDataAttribute.getCreatedBy(),
			newIvldMasterDataAttribute.getCreatedBy());
		Assert.assertEquals(existingIvldMasterDataAttribute.getAddChgDelIndicator(),
			newIvldMasterDataAttribute.getAddChgDelIndicator());
		Assert.assertEquals(existingIvldMasterDataAttribute.getMasterId(),
			newIvldMasterDataAttribute.getMasterId());
		Assert.assertEquals(existingIvldMasterDataAttribute.getColumn10(),
			newIvldMasterDataAttribute.getColumn10());
		Assert.assertEquals(existingIvldMasterDataAttribute.getColumn11(),
			newIvldMasterDataAttribute.getColumn11());
		Assert.assertEquals(existingIvldMasterDataAttribute.getErrorCode(),
			newIvldMasterDataAttribute.getErrorCode());
		Assert.assertEquals(existingIvldMasterDataAttribute.getColumn12(),
			newIvldMasterDataAttribute.getColumn12());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldMasterDataAttribute.getIntfInsertedDate()),
			Time.getShortTimestamp(
				newIvldMasterDataAttribute.getIntfInsertedDate()));
		Assert.assertEquals(existingIvldMasterDataAttribute.getModifiedBy(),
			newIvldMasterDataAttribute.getModifiedBy());
		Assert.assertEquals(existingIvldMasterDataAttribute.getColumn13(),
			newIvldMasterDataAttribute.getColumn13());
		Assert.assertEquals(existingIvldMasterDataAttribute.getColumn14(),
			newIvldMasterDataAttribute.getColumn14());
		Assert.assertEquals(existingIvldMasterDataAttribute.getReprocessedFlag(),
			newIvldMasterDataAttribute.getReprocessedFlag());
		Assert.assertEquals(existingIvldMasterDataAttribute.getColumn15(),
			newIvldMasterDataAttribute.getColumn15());
		Assert.assertEquals(existingIvldMasterDataAttribute.getColumn16(),
			newIvldMasterDataAttribute.getColumn16());
		Assert.assertEquals(existingIvldMasterDataAttribute.getColumn17(),
			newIvldMasterDataAttribute.getColumn17());
		Assert.assertEquals(existingIvldMasterDataAttribute.getColumn4(),
			newIvldMasterDataAttribute.getColumn4());
		Assert.assertEquals(existingIvldMasterDataAttribute.getColumn3(),
			newIvldMasterDataAttribute.getColumn3());
		Assert.assertEquals(existingIvldMasterDataAttribute.getColumn2(),
			newIvldMasterDataAttribute.getColumn2());
		Assert.assertEquals(existingIvldMasterDataAttribute.getColumn1(),
			newIvldMasterDataAttribute.getColumn1());
		Assert.assertEquals(existingIvldMasterDataAttribute.getColumn8(),
			newIvldMasterDataAttribute.getColumn8());
		Assert.assertEquals(existingIvldMasterDataAttribute.getReasonForFailure(),
			newIvldMasterDataAttribute.getReasonForFailure());
		Assert.assertEquals(existingIvldMasterDataAttribute.getColumn7(),
			newIvldMasterDataAttribute.getColumn7());
		Assert.assertEquals(existingIvldMasterDataAttribute.getColumn6(),
			newIvldMasterDataAttribute.getColumn6());
		Assert.assertEquals(existingIvldMasterDataAttribute.getColumn5(),
			newIvldMasterDataAttribute.getColumn5());
		Assert.assertEquals(existingIvldMasterDataAttribute.getColumn20(),
			newIvldMasterDataAttribute.getColumn20());
		Assert.assertEquals(existingIvldMasterDataAttribute.getBatchId(),
			newIvldMasterDataAttribute.getBatchId());
		Assert.assertEquals(existingIvldMasterDataAttribute.getErrorField(),
			newIvldMasterDataAttribute.getErrorField());
		Assert.assertEquals(existingIvldMasterDataAttribute.getColumn9(),
			newIvldMasterDataAttribute.getColumn9());
		Assert.assertEquals(existingIvldMasterDataAttribute.getIvldMasterDataAtbteSid(),
			newIvldMasterDataAttribute.getIvldMasterDataAtbteSid());
		Assert.assertEquals(existingIvldMasterDataAttribute.getCheckRecord(),
			newIvldMasterDataAttribute.getCheckRecord());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		IvldMasterDataAttribute newIvldMasterDataAttribute = addIvldMasterDataAttribute();

		IvldMasterDataAttribute existingIvldMasterDataAttribute = _persistence.findByPrimaryKey(newIvldMasterDataAttribute.getPrimaryKey());

		Assert.assertEquals(existingIvldMasterDataAttribute,
			newIvldMasterDataAttribute);
	}

	@Test(expected = NoSuchIvldMasterDataAttributeException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<IvldMasterDataAttribute> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("IVLD_MASTER_DATA_ATTRIBUTE",
			"column19", true, "column18", true, "masterAttribute", true,
			"masterType", true, "dataAttributeIntfid", true, "modifiedDate",
			true, "source", true, "createdDate", true, "createdBy", true,
			"addChgDelIndicator", true, "masterId", true, "column10", true,
			"column11", true, "errorCode", true, "column12", true,
			"intfInsertedDate", true, "modifiedBy", true, "column13", true,
			"column14", true, "reprocessedFlag", true, "column15", true,
			"column16", true, "column17", true, "column4", true, "column3",
			true, "column2", true, "column1", true, "column8", true,
			"reasonForFailure", true, "column7", true, "column6", true,
			"column5", true, "column20", true, "batchId", true, "errorField",
			true, "column9", true, "ivldMasterDataAtbteSid", true,
			"checkRecord", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		IvldMasterDataAttribute newIvldMasterDataAttribute = addIvldMasterDataAttribute();

		IvldMasterDataAttribute existingIvldMasterDataAttribute = _persistence.fetchByPrimaryKey(newIvldMasterDataAttribute.getPrimaryKey());

		Assert.assertEquals(existingIvldMasterDataAttribute,
			newIvldMasterDataAttribute);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldMasterDataAttribute missingIvldMasterDataAttribute = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingIvldMasterDataAttribute);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		IvldMasterDataAttribute newIvldMasterDataAttribute1 = addIvldMasterDataAttribute();
		IvldMasterDataAttribute newIvldMasterDataAttribute2 = addIvldMasterDataAttribute();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldMasterDataAttribute1.getPrimaryKey());
		primaryKeys.add(newIvldMasterDataAttribute2.getPrimaryKey());

		Map<Serializable, IvldMasterDataAttribute> ivldMasterDataAttributes = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, ivldMasterDataAttributes.size());
		Assert.assertEquals(newIvldMasterDataAttribute1,
			ivldMasterDataAttributes.get(
				newIvldMasterDataAttribute1.getPrimaryKey()));
		Assert.assertEquals(newIvldMasterDataAttribute2,
			ivldMasterDataAttributes.get(
				newIvldMasterDataAttribute2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, IvldMasterDataAttribute> ivldMasterDataAttributes = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ivldMasterDataAttributes.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		IvldMasterDataAttribute newIvldMasterDataAttribute = addIvldMasterDataAttribute();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldMasterDataAttribute.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, IvldMasterDataAttribute> ivldMasterDataAttributes = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ivldMasterDataAttributes.size());
		Assert.assertEquals(newIvldMasterDataAttribute,
			ivldMasterDataAttributes.get(
				newIvldMasterDataAttribute.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, IvldMasterDataAttribute> ivldMasterDataAttributes = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ivldMasterDataAttributes.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		IvldMasterDataAttribute newIvldMasterDataAttribute = addIvldMasterDataAttribute();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldMasterDataAttribute.getPrimaryKey());

		Map<Serializable, IvldMasterDataAttribute> ivldMasterDataAttributes = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ivldMasterDataAttributes.size());
		Assert.assertEquals(newIvldMasterDataAttribute,
			ivldMasterDataAttributes.get(
				newIvldMasterDataAttribute.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = IvldMasterDataAttributeLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<IvldMasterDataAttribute>() {
				@Override
				public void performAction(
					IvldMasterDataAttribute ivldMasterDataAttribute) {
					Assert.assertNotNull(ivldMasterDataAttribute);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		IvldMasterDataAttribute newIvldMasterDataAttribute = addIvldMasterDataAttribute();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldMasterDataAttribute.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("ivldMasterDataAtbteSid",
				newIvldMasterDataAttribute.getIvldMasterDataAtbteSid()));

		List<IvldMasterDataAttribute> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		IvldMasterDataAttribute existingIvldMasterDataAttribute = result.get(0);

		Assert.assertEquals(existingIvldMasterDataAttribute,
			newIvldMasterDataAttribute);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldMasterDataAttribute.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("ivldMasterDataAtbteSid",
				RandomTestUtil.nextInt()));

		List<IvldMasterDataAttribute> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		IvldMasterDataAttribute newIvldMasterDataAttribute = addIvldMasterDataAttribute();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldMasterDataAttribute.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"ivldMasterDataAtbteSid"));

		Object newIvldMasterDataAtbteSid = newIvldMasterDataAttribute.getIvldMasterDataAtbteSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("ivldMasterDataAtbteSid",
				new Object[] { newIvldMasterDataAtbteSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingIvldMasterDataAtbteSid = result.get(0);

		Assert.assertEquals(existingIvldMasterDataAtbteSid,
			newIvldMasterDataAtbteSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldMasterDataAttribute.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"ivldMasterDataAtbteSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("ivldMasterDataAtbteSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected IvldMasterDataAttribute addIvldMasterDataAttribute()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldMasterDataAttribute ivldMasterDataAttribute = _persistence.create(pk);

		ivldMasterDataAttribute.setColumn19(RandomTestUtil.randomString());

		ivldMasterDataAttribute.setColumn18(RandomTestUtil.randomString());

		ivldMasterDataAttribute.setMasterAttribute(RandomTestUtil.randomString());

		ivldMasterDataAttribute.setMasterType(RandomTestUtil.randomString());

		ivldMasterDataAttribute.setDataAttributeIntfid(RandomTestUtil.randomString());

		ivldMasterDataAttribute.setModifiedDate(RandomTestUtil.nextDate());

		ivldMasterDataAttribute.setSource(RandomTestUtil.randomString());

		ivldMasterDataAttribute.setCreatedDate(RandomTestUtil.nextDate());

		ivldMasterDataAttribute.setCreatedBy(RandomTestUtil.randomString());

		ivldMasterDataAttribute.setAddChgDelIndicator(RandomTestUtil.randomString());

		ivldMasterDataAttribute.setMasterId(RandomTestUtil.randomString());

		ivldMasterDataAttribute.setColumn10(RandomTestUtil.randomString());

		ivldMasterDataAttribute.setColumn11(RandomTestUtil.randomString());

		ivldMasterDataAttribute.setErrorCode(RandomTestUtil.randomString());

		ivldMasterDataAttribute.setColumn12(RandomTestUtil.randomString());

		ivldMasterDataAttribute.setIntfInsertedDate(RandomTestUtil.nextDate());

		ivldMasterDataAttribute.setModifiedBy(RandomTestUtil.randomString());

		ivldMasterDataAttribute.setColumn13(RandomTestUtil.randomString());

		ivldMasterDataAttribute.setColumn14(RandomTestUtil.randomString());

		ivldMasterDataAttribute.setReprocessedFlag(RandomTestUtil.randomString());

		ivldMasterDataAttribute.setColumn15(RandomTestUtil.randomString());

		ivldMasterDataAttribute.setColumn16(RandomTestUtil.randomString());

		ivldMasterDataAttribute.setColumn17(RandomTestUtil.randomString());

		ivldMasterDataAttribute.setColumn4(RandomTestUtil.randomString());

		ivldMasterDataAttribute.setColumn3(RandomTestUtil.randomString());

		ivldMasterDataAttribute.setColumn2(RandomTestUtil.randomString());

		ivldMasterDataAttribute.setColumn1(RandomTestUtil.randomString());

		ivldMasterDataAttribute.setColumn8(RandomTestUtil.randomString());

		ivldMasterDataAttribute.setReasonForFailure(RandomTestUtil.randomString());

		ivldMasterDataAttribute.setColumn7(RandomTestUtil.randomString());

		ivldMasterDataAttribute.setColumn6(RandomTestUtil.randomString());

		ivldMasterDataAttribute.setColumn5(RandomTestUtil.randomString());

		ivldMasterDataAttribute.setColumn20(RandomTestUtil.randomString());

		ivldMasterDataAttribute.setBatchId(RandomTestUtil.randomString());

		ivldMasterDataAttribute.setErrorField(RandomTestUtil.randomString());

		ivldMasterDataAttribute.setColumn9(RandomTestUtil.randomString());

		ivldMasterDataAttribute.setCheckRecord(RandomTestUtil.randomBoolean());

		_ivldMasterDataAttributes.add(_persistence.update(
				ivldMasterDataAttribute));

		return ivldMasterDataAttribute;
	}

	private List<IvldMasterDataAttribute> _ivldMasterDataAttributes = new ArrayList<IvldMasterDataAttribute>();
	private IvldMasterDataAttributePersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}