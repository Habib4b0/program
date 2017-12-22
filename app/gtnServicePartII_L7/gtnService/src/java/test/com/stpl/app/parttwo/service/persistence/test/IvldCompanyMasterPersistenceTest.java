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

import com.stpl.app.parttwo.exception.NoSuchIvldCompanyMasterException;
import com.stpl.app.parttwo.model.IvldCompanyMaster;
import com.stpl.app.parttwo.service.IvldCompanyMasterLocalServiceUtil;
import com.stpl.app.parttwo.service.persistence.IvldCompanyMasterPersistence;
import com.stpl.app.parttwo.service.persistence.IvldCompanyMasterUtil;

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
public class IvldCompanyMasterPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.parttwo.service"));

	@Before
	public void setUp() {
		_persistence = IvldCompanyMasterUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<IvldCompanyMaster> iterator = _ivldCompanyMasters.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldCompanyMaster ivldCompanyMaster = _persistence.create(pk);

		Assert.assertNotNull(ivldCompanyMaster);

		Assert.assertEquals(ivldCompanyMaster.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		IvldCompanyMaster newIvldCompanyMaster = addIvldCompanyMaster();

		_persistence.remove(newIvldCompanyMaster);

		IvldCompanyMaster existingIvldCompanyMaster = _persistence.fetchByPrimaryKey(newIvldCompanyMaster.getPrimaryKey());

		Assert.assertNull(existingIvldCompanyMaster);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addIvldCompanyMaster();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldCompanyMaster newIvldCompanyMaster = _persistence.create(pk);

		newIvldCompanyMaster.setState(RandomTestUtil.randomString());

		newIvldCompanyMaster.setFinancialSystem(RandomTestUtil.randomString());

		newIvldCompanyMaster.setCompanyName(RandomTestUtil.randomString());

		newIvldCompanyMaster.setCompanyGroup(RandomTestUtil.randomString());

		newIvldCompanyMaster.setCompanyCategory(RandomTestUtil.randomString());

		newIvldCompanyMaster.setLastUpdatedDate(RandomTestUtil.randomString());

		newIvldCompanyMaster.setModifiedDate(RandomTestUtil.nextDate());

		newIvldCompanyMaster.setStatus(RandomTestUtil.randomString());

		newIvldCompanyMaster.setLives(RandomTestUtil.randomString());

		newIvldCompanyMaster.setOrganizationKey(RandomTestUtil.randomString());

		newIvldCompanyMaster.setSource(RandomTestUtil.randomString());

		newIvldCompanyMaster.setCreatedDate(RandomTestUtil.nextDate());

		newIvldCompanyMaster.setCreatedBy(RandomTestUtil.randomString());

		newIvldCompanyMaster.setAddChgDelIndicator(RandomTestUtil.randomString());

		newIvldCompanyMaster.setErrorCode(RandomTestUtil.randomString());

		newIvldCompanyMaster.setIntfInsertedDate(RandomTestUtil.nextDate());

		newIvldCompanyMaster.setModifiedBy(RandomTestUtil.randomString());

		newIvldCompanyMaster.setCompanyMasterIntfid(RandomTestUtil.randomString());

		newIvldCompanyMaster.setReprocessedFlag(RandomTestUtil.randomString());

		newIvldCompanyMaster.setUdc6(RandomTestUtil.randomString());

		newIvldCompanyMaster.setUdc5(RandomTestUtil.randomString());

		newIvldCompanyMaster.setUdc4(RandomTestUtil.randomString());

		newIvldCompanyMaster.setUdc1(RandomTestUtil.randomString());

		newIvldCompanyMaster.setUdc2(RandomTestUtil.randomString());

		newIvldCompanyMaster.setZipCode(RandomTestUtil.randomString());

		newIvldCompanyMaster.setUdc3(RandomTestUtil.randomString());

		newIvldCompanyMaster.setReasonForFailure(RandomTestUtil.randomString());

		newIvldCompanyMaster.setCompanyId(RandomTestUtil.randomString());

		newIvldCompanyMaster.setAddress1(RandomTestUtil.randomString());

		newIvldCompanyMaster.setCountry(RandomTestUtil.randomString());

		newIvldCompanyMaster.setAddress2(RandomTestUtil.randomString());

		newIvldCompanyMaster.setCompanyType(RandomTestUtil.randomString());

		newIvldCompanyMaster.setCompanyStartDate(RandomTestUtil.randomString());

		newIvldCompanyMaster.setCompanyNo(RandomTestUtil.randomString());

		newIvldCompanyMaster.setBatchId(RandomTestUtil.randomString());

		newIvldCompanyMaster.setCompanyStatus(RandomTestUtil.randomString());

		newIvldCompanyMaster.setCompanyEndDate(RandomTestUtil.randomString());

		newIvldCompanyMaster.setErrorField(RandomTestUtil.randomString());

		newIvldCompanyMaster.setCity(RandomTestUtil.randomString());

		newIvldCompanyMaster.setRegionCode(RandomTestUtil.randomString());

		newIvldCompanyMaster.setCheckRecord(RandomTestUtil.randomBoolean());

		_ivldCompanyMasters.add(_persistence.update(newIvldCompanyMaster));

		IvldCompanyMaster existingIvldCompanyMaster = _persistence.findByPrimaryKey(newIvldCompanyMaster.getPrimaryKey());

		Assert.assertEquals(existingIvldCompanyMaster.getState(),
			newIvldCompanyMaster.getState());
		Assert.assertEquals(existingIvldCompanyMaster.getFinancialSystem(),
			newIvldCompanyMaster.getFinancialSystem());
		Assert.assertEquals(existingIvldCompanyMaster.getCompanyName(),
			newIvldCompanyMaster.getCompanyName());
		Assert.assertEquals(existingIvldCompanyMaster.getCompanyGroup(),
			newIvldCompanyMaster.getCompanyGroup());
		Assert.assertEquals(existingIvldCompanyMaster.getCompanyCategory(),
			newIvldCompanyMaster.getCompanyCategory());
		Assert.assertEquals(existingIvldCompanyMaster.getLastUpdatedDate(),
			newIvldCompanyMaster.getLastUpdatedDate());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldCompanyMaster.getModifiedDate()),
			Time.getShortTimestamp(newIvldCompanyMaster.getModifiedDate()));
		Assert.assertEquals(existingIvldCompanyMaster.getStatus(),
			newIvldCompanyMaster.getStatus());
		Assert.assertEquals(existingIvldCompanyMaster.getIvldCompanyMasterSid(),
			newIvldCompanyMaster.getIvldCompanyMasterSid());
		Assert.assertEquals(existingIvldCompanyMaster.getLives(),
			newIvldCompanyMaster.getLives());
		Assert.assertEquals(existingIvldCompanyMaster.getOrganizationKey(),
			newIvldCompanyMaster.getOrganizationKey());
		Assert.assertEquals(existingIvldCompanyMaster.getSource(),
			newIvldCompanyMaster.getSource());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldCompanyMaster.getCreatedDate()),
			Time.getShortTimestamp(newIvldCompanyMaster.getCreatedDate()));
		Assert.assertEquals(existingIvldCompanyMaster.getCreatedBy(),
			newIvldCompanyMaster.getCreatedBy());
		Assert.assertEquals(existingIvldCompanyMaster.getAddChgDelIndicator(),
			newIvldCompanyMaster.getAddChgDelIndicator());
		Assert.assertEquals(existingIvldCompanyMaster.getErrorCode(),
			newIvldCompanyMaster.getErrorCode());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldCompanyMaster.getIntfInsertedDate()),
			Time.getShortTimestamp(newIvldCompanyMaster.getIntfInsertedDate()));
		Assert.assertEquals(existingIvldCompanyMaster.getModifiedBy(),
			newIvldCompanyMaster.getModifiedBy());
		Assert.assertEquals(existingIvldCompanyMaster.getCompanyMasterIntfid(),
			newIvldCompanyMaster.getCompanyMasterIntfid());
		Assert.assertEquals(existingIvldCompanyMaster.getReprocessedFlag(),
			newIvldCompanyMaster.getReprocessedFlag());
		Assert.assertEquals(existingIvldCompanyMaster.getUdc6(),
			newIvldCompanyMaster.getUdc6());
		Assert.assertEquals(existingIvldCompanyMaster.getUdc5(),
			newIvldCompanyMaster.getUdc5());
		Assert.assertEquals(existingIvldCompanyMaster.getUdc4(),
			newIvldCompanyMaster.getUdc4());
		Assert.assertEquals(existingIvldCompanyMaster.getUdc1(),
			newIvldCompanyMaster.getUdc1());
		Assert.assertEquals(existingIvldCompanyMaster.getUdc2(),
			newIvldCompanyMaster.getUdc2());
		Assert.assertEquals(existingIvldCompanyMaster.getZipCode(),
			newIvldCompanyMaster.getZipCode());
		Assert.assertEquals(existingIvldCompanyMaster.getUdc3(),
			newIvldCompanyMaster.getUdc3());
		Assert.assertEquals(existingIvldCompanyMaster.getReasonForFailure(),
			newIvldCompanyMaster.getReasonForFailure());
		Assert.assertEquals(existingIvldCompanyMaster.getCompanyId(),
			newIvldCompanyMaster.getCompanyId());
		Assert.assertEquals(existingIvldCompanyMaster.getAddress1(),
			newIvldCompanyMaster.getAddress1());
		Assert.assertEquals(existingIvldCompanyMaster.getCountry(),
			newIvldCompanyMaster.getCountry());
		Assert.assertEquals(existingIvldCompanyMaster.getAddress2(),
			newIvldCompanyMaster.getAddress2());
		Assert.assertEquals(existingIvldCompanyMaster.getCompanyType(),
			newIvldCompanyMaster.getCompanyType());
		Assert.assertEquals(existingIvldCompanyMaster.getCompanyStartDate(),
			newIvldCompanyMaster.getCompanyStartDate());
		Assert.assertEquals(existingIvldCompanyMaster.getCompanyNo(),
			newIvldCompanyMaster.getCompanyNo());
		Assert.assertEquals(existingIvldCompanyMaster.getBatchId(),
			newIvldCompanyMaster.getBatchId());
		Assert.assertEquals(existingIvldCompanyMaster.getCompanyStatus(),
			newIvldCompanyMaster.getCompanyStatus());
		Assert.assertEquals(existingIvldCompanyMaster.getCompanyEndDate(),
			newIvldCompanyMaster.getCompanyEndDate());
		Assert.assertEquals(existingIvldCompanyMaster.getErrorField(),
			newIvldCompanyMaster.getErrorField());
		Assert.assertEquals(existingIvldCompanyMaster.getCity(),
			newIvldCompanyMaster.getCity());
		Assert.assertEquals(existingIvldCompanyMaster.getRegionCode(),
			newIvldCompanyMaster.getRegionCode());
		Assert.assertEquals(existingIvldCompanyMaster.getCheckRecord(),
			newIvldCompanyMaster.getCheckRecord());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		IvldCompanyMaster newIvldCompanyMaster = addIvldCompanyMaster();

		IvldCompanyMaster existingIvldCompanyMaster = _persistence.findByPrimaryKey(newIvldCompanyMaster.getPrimaryKey());

		Assert.assertEquals(existingIvldCompanyMaster, newIvldCompanyMaster);
	}

	@Test(expected = NoSuchIvldCompanyMasterException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<IvldCompanyMaster> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("IVLD_COMPANY_MASTER",
			"state", true, "financialSystem", true, "companyName", true,
			"companyGroup", true, "companyCategory", true, "lastUpdatedDate",
			true, "modifiedDate", true, "status", true, "ivldCompanyMasterSid",
			true, "lives", true, "organizationKey", true, "source", true,
			"createdDate", true, "createdBy", true, "addChgDelIndicator", true,
			"errorCode", true, "intfInsertedDate", true, "modifiedBy", true,
			"companyMasterIntfid", true, "reprocessedFlag", true, "udc6", true,
			"udc5", true, "udc4", true, "udc1", true, "udc2", true, "zipCode",
			true, "udc3", true, "reasonForFailure", true, "companyId", true,
			"address1", true, "country", true, "address2", true, "companyType",
			true, "companyStartDate", true, "companyNo", true, "batchId", true,
			"companyStatus", true, "companyEndDate", true, "errorField", true,
			"city", true, "regionCode", true, "checkRecord", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		IvldCompanyMaster newIvldCompanyMaster = addIvldCompanyMaster();

		IvldCompanyMaster existingIvldCompanyMaster = _persistence.fetchByPrimaryKey(newIvldCompanyMaster.getPrimaryKey());

		Assert.assertEquals(existingIvldCompanyMaster, newIvldCompanyMaster);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldCompanyMaster missingIvldCompanyMaster = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingIvldCompanyMaster);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		IvldCompanyMaster newIvldCompanyMaster1 = addIvldCompanyMaster();
		IvldCompanyMaster newIvldCompanyMaster2 = addIvldCompanyMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldCompanyMaster1.getPrimaryKey());
		primaryKeys.add(newIvldCompanyMaster2.getPrimaryKey());

		Map<Serializable, IvldCompanyMaster> ivldCompanyMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, ivldCompanyMasters.size());
		Assert.assertEquals(newIvldCompanyMaster1,
			ivldCompanyMasters.get(newIvldCompanyMaster1.getPrimaryKey()));
		Assert.assertEquals(newIvldCompanyMaster2,
			ivldCompanyMasters.get(newIvldCompanyMaster2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, IvldCompanyMaster> ivldCompanyMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ivldCompanyMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		IvldCompanyMaster newIvldCompanyMaster = addIvldCompanyMaster();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldCompanyMaster.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, IvldCompanyMaster> ivldCompanyMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ivldCompanyMasters.size());
		Assert.assertEquals(newIvldCompanyMaster,
			ivldCompanyMasters.get(newIvldCompanyMaster.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, IvldCompanyMaster> ivldCompanyMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ivldCompanyMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		IvldCompanyMaster newIvldCompanyMaster = addIvldCompanyMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldCompanyMaster.getPrimaryKey());

		Map<Serializable, IvldCompanyMaster> ivldCompanyMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ivldCompanyMasters.size());
		Assert.assertEquals(newIvldCompanyMaster,
			ivldCompanyMasters.get(newIvldCompanyMaster.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = IvldCompanyMasterLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<IvldCompanyMaster>() {
				@Override
				public void performAction(IvldCompanyMaster ivldCompanyMaster) {
					Assert.assertNotNull(ivldCompanyMaster);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		IvldCompanyMaster newIvldCompanyMaster = addIvldCompanyMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldCompanyMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("ivldCompanyMasterSid",
				newIvldCompanyMaster.getIvldCompanyMasterSid()));

		List<IvldCompanyMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		IvldCompanyMaster existingIvldCompanyMaster = result.get(0);

		Assert.assertEquals(existingIvldCompanyMaster, newIvldCompanyMaster);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldCompanyMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("ivldCompanyMasterSid",
				RandomTestUtil.nextInt()));

		List<IvldCompanyMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		IvldCompanyMaster newIvldCompanyMaster = addIvldCompanyMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldCompanyMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"ivldCompanyMasterSid"));

		Object newIvldCompanyMasterSid = newIvldCompanyMaster.getIvldCompanyMasterSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("ivldCompanyMasterSid",
				new Object[] { newIvldCompanyMasterSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingIvldCompanyMasterSid = result.get(0);

		Assert.assertEquals(existingIvldCompanyMasterSid,
			newIvldCompanyMasterSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldCompanyMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"ivldCompanyMasterSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("ivldCompanyMasterSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected IvldCompanyMaster addIvldCompanyMaster()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldCompanyMaster ivldCompanyMaster = _persistence.create(pk);

		ivldCompanyMaster.setState(RandomTestUtil.randomString());

		ivldCompanyMaster.setFinancialSystem(RandomTestUtil.randomString());

		ivldCompanyMaster.setCompanyName(RandomTestUtil.randomString());

		ivldCompanyMaster.setCompanyGroup(RandomTestUtil.randomString());

		ivldCompanyMaster.setCompanyCategory(RandomTestUtil.randomString());

		ivldCompanyMaster.setLastUpdatedDate(RandomTestUtil.randomString());

		ivldCompanyMaster.setModifiedDate(RandomTestUtil.nextDate());

		ivldCompanyMaster.setStatus(RandomTestUtil.randomString());

		ivldCompanyMaster.setLives(RandomTestUtil.randomString());

		ivldCompanyMaster.setOrganizationKey(RandomTestUtil.randomString());

		ivldCompanyMaster.setSource(RandomTestUtil.randomString());

		ivldCompanyMaster.setCreatedDate(RandomTestUtil.nextDate());

		ivldCompanyMaster.setCreatedBy(RandomTestUtil.randomString());

		ivldCompanyMaster.setAddChgDelIndicator(RandomTestUtil.randomString());

		ivldCompanyMaster.setErrorCode(RandomTestUtil.randomString());

		ivldCompanyMaster.setIntfInsertedDate(RandomTestUtil.nextDate());

		ivldCompanyMaster.setModifiedBy(RandomTestUtil.randomString());

		ivldCompanyMaster.setCompanyMasterIntfid(RandomTestUtil.randomString());

		ivldCompanyMaster.setReprocessedFlag(RandomTestUtil.randomString());

		ivldCompanyMaster.setUdc6(RandomTestUtil.randomString());

		ivldCompanyMaster.setUdc5(RandomTestUtil.randomString());

		ivldCompanyMaster.setUdc4(RandomTestUtil.randomString());

		ivldCompanyMaster.setUdc1(RandomTestUtil.randomString());

		ivldCompanyMaster.setUdc2(RandomTestUtil.randomString());

		ivldCompanyMaster.setZipCode(RandomTestUtil.randomString());

		ivldCompanyMaster.setUdc3(RandomTestUtil.randomString());

		ivldCompanyMaster.setReasonForFailure(RandomTestUtil.randomString());

		ivldCompanyMaster.setCompanyId(RandomTestUtil.randomString());

		ivldCompanyMaster.setAddress1(RandomTestUtil.randomString());

		ivldCompanyMaster.setCountry(RandomTestUtil.randomString());

		ivldCompanyMaster.setAddress2(RandomTestUtil.randomString());

		ivldCompanyMaster.setCompanyType(RandomTestUtil.randomString());

		ivldCompanyMaster.setCompanyStartDate(RandomTestUtil.randomString());

		ivldCompanyMaster.setCompanyNo(RandomTestUtil.randomString());

		ivldCompanyMaster.setBatchId(RandomTestUtil.randomString());

		ivldCompanyMaster.setCompanyStatus(RandomTestUtil.randomString());

		ivldCompanyMaster.setCompanyEndDate(RandomTestUtil.randomString());

		ivldCompanyMaster.setErrorField(RandomTestUtil.randomString());

		ivldCompanyMaster.setCity(RandomTestUtil.randomString());

		ivldCompanyMaster.setRegionCode(RandomTestUtil.randomString());

		ivldCompanyMaster.setCheckRecord(RandomTestUtil.randomBoolean());

		_ivldCompanyMasters.add(_persistence.update(ivldCompanyMaster));

		return ivldCompanyMaster;
	}

	private List<IvldCompanyMaster> _ivldCompanyMasters = new ArrayList<IvldCompanyMaster>();
	private IvldCompanyMasterPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}