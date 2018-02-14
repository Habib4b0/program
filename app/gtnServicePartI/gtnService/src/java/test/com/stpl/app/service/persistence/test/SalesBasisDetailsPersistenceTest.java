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

import com.stpl.app.exception.NoSuchSalesBasisDetailsException;
import com.stpl.app.model.SalesBasisDetails;
import com.stpl.app.service.SalesBasisDetailsLocalServiceUtil;
import com.stpl.app.service.persistence.SalesBasisDetailsPersistence;
import com.stpl.app.service.persistence.SalesBasisDetailsUtil;

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
public class SalesBasisDetailsPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = SalesBasisDetailsUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<SalesBasisDetails> iterator = _salesBasisDetailses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		SalesBasisDetails salesBasisDetails = _persistence.create(pk);

		Assert.assertNotNull(salesBasisDetails);

		Assert.assertEquals(salesBasisDetails.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		SalesBasisDetails newSalesBasisDetails = addSalesBasisDetails();

		_persistence.remove(newSalesBasisDetails);

		SalesBasisDetails existingSalesBasisDetails = _persistence.fetchByPrimaryKey(newSalesBasisDetails.getPrimaryKey());

		Assert.assertNull(existingSalesBasisDetails);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addSalesBasisDetails();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		SalesBasisDetails newSalesBasisDetails = _persistence.create(pk);

		newSalesBasisDetails.setCreatedBy(RandomTestUtil.nextInt());

		newSalesBasisDetails.setNetSalesFormulaMasterSid(RandomTestUtil.nextInt());

		newSalesBasisDetails.setRecordLockStatus(RandomTestUtil.randomBoolean());

		newSalesBasisDetails.setModifiedBy(RandomTestUtil.nextInt());

		newSalesBasisDetails.setCreatedDate(RandomTestUtil.nextDate());

		newSalesBasisDetails.setContractMasterSid(RandomTestUtil.nextInt());

		newSalesBasisDetails.setSource(RandomTestUtil.randomString());

		newSalesBasisDetails.setCdrModelSid(RandomTestUtil.nextInt());

		newSalesBasisDetails.setCfpContractDetailsSid(RandomTestUtil.nextInt());

		newSalesBasisDetails.setModifiedDate(RandomTestUtil.nextDate());

		newSalesBasisDetails.setInboundStatus(RandomTestUtil.randomString());

		_salesBasisDetailses.add(_persistence.update(newSalesBasisDetails));

		SalesBasisDetails existingSalesBasisDetails = _persistence.findByPrimaryKey(newSalesBasisDetails.getPrimaryKey());

		Assert.assertEquals(existingSalesBasisDetails.getCreatedBy(),
			newSalesBasisDetails.getCreatedBy());
		Assert.assertEquals(existingSalesBasisDetails.getNetSalesFormulaMasterSid(),
			newSalesBasisDetails.getNetSalesFormulaMasterSid());
		Assert.assertEquals(existingSalesBasisDetails.getRecordLockStatus(),
			newSalesBasisDetails.getRecordLockStatus());
		Assert.assertEquals(existingSalesBasisDetails.getModifiedBy(),
			newSalesBasisDetails.getModifiedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingSalesBasisDetails.getCreatedDate()),
			Time.getShortTimestamp(newSalesBasisDetails.getCreatedDate()));
		Assert.assertEquals(existingSalesBasisDetails.getContractMasterSid(),
			newSalesBasisDetails.getContractMasterSid());
		Assert.assertEquals(existingSalesBasisDetails.getSource(),
			newSalesBasisDetails.getSource());
		Assert.assertEquals(existingSalesBasisDetails.getCdrModelSid(),
			newSalesBasisDetails.getCdrModelSid());
		Assert.assertEquals(existingSalesBasisDetails.getSalesBasisDetailsSid(),
			newSalesBasisDetails.getSalesBasisDetailsSid());
		Assert.assertEquals(existingSalesBasisDetails.getCfpContractDetailsSid(),
			newSalesBasisDetails.getCfpContractDetailsSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingSalesBasisDetails.getModifiedDate()),
			Time.getShortTimestamp(newSalesBasisDetails.getModifiedDate()));
		Assert.assertEquals(existingSalesBasisDetails.getInboundStatus(),
			newSalesBasisDetails.getInboundStatus());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		SalesBasisDetails newSalesBasisDetails = addSalesBasisDetails();

		SalesBasisDetails existingSalesBasisDetails = _persistence.findByPrimaryKey(newSalesBasisDetails.getPrimaryKey());

		Assert.assertEquals(existingSalesBasisDetails, newSalesBasisDetails);
	}

	@Test(expected = NoSuchSalesBasisDetailsException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<SalesBasisDetails> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("SALES_BASIS_DETAILS",
			"createdBy", true, "netSalesFormulaMasterSid", true,
			"recordLockStatus", true, "modifiedBy", true, "createdDate", true,
			"contractMasterSid", true, "source", true, "cdrModelSid", true,
			"salesBasisDetailsSid", true, "cfpContractDetailsSid", true,
			"modifiedDate", true, "inboundStatus", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		SalesBasisDetails newSalesBasisDetails = addSalesBasisDetails();

		SalesBasisDetails existingSalesBasisDetails = _persistence.fetchByPrimaryKey(newSalesBasisDetails.getPrimaryKey());

		Assert.assertEquals(existingSalesBasisDetails, newSalesBasisDetails);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		SalesBasisDetails missingSalesBasisDetails = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingSalesBasisDetails);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		SalesBasisDetails newSalesBasisDetails1 = addSalesBasisDetails();
		SalesBasisDetails newSalesBasisDetails2 = addSalesBasisDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newSalesBasisDetails1.getPrimaryKey());
		primaryKeys.add(newSalesBasisDetails2.getPrimaryKey());

		Map<Serializable, SalesBasisDetails> salesBasisDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, salesBasisDetailses.size());
		Assert.assertEquals(newSalesBasisDetails1,
			salesBasisDetailses.get(newSalesBasisDetails1.getPrimaryKey()));
		Assert.assertEquals(newSalesBasisDetails2,
			salesBasisDetailses.get(newSalesBasisDetails2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, SalesBasisDetails> salesBasisDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(salesBasisDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		SalesBasisDetails newSalesBasisDetails = addSalesBasisDetails();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newSalesBasisDetails.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, SalesBasisDetails> salesBasisDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, salesBasisDetailses.size());
		Assert.assertEquals(newSalesBasisDetails,
			salesBasisDetailses.get(newSalesBasisDetails.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, SalesBasisDetails> salesBasisDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(salesBasisDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		SalesBasisDetails newSalesBasisDetails = addSalesBasisDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newSalesBasisDetails.getPrimaryKey());

		Map<Serializable, SalesBasisDetails> salesBasisDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, salesBasisDetailses.size());
		Assert.assertEquals(newSalesBasisDetails,
			salesBasisDetailses.get(newSalesBasisDetails.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = SalesBasisDetailsLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<SalesBasisDetails>() {
				@Override
				public void performAction(SalesBasisDetails salesBasisDetails) {
					Assert.assertNotNull(salesBasisDetails);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		SalesBasisDetails newSalesBasisDetails = addSalesBasisDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(SalesBasisDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("salesBasisDetailsSid",
				newSalesBasisDetails.getSalesBasisDetailsSid()));

		List<SalesBasisDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		SalesBasisDetails existingSalesBasisDetails = result.get(0);

		Assert.assertEquals(existingSalesBasisDetails, newSalesBasisDetails);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(SalesBasisDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("salesBasisDetailsSid",
				RandomTestUtil.nextInt()));

		List<SalesBasisDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		SalesBasisDetails newSalesBasisDetails = addSalesBasisDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(SalesBasisDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"salesBasisDetailsSid"));

		Object newSalesBasisDetailsSid = newSalesBasisDetails.getSalesBasisDetailsSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("salesBasisDetailsSid",
				new Object[] { newSalesBasisDetailsSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingSalesBasisDetailsSid = result.get(0);

		Assert.assertEquals(existingSalesBasisDetailsSid,
			newSalesBasisDetailsSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(SalesBasisDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"salesBasisDetailsSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("salesBasisDetailsSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected SalesBasisDetails addSalesBasisDetails()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		SalesBasisDetails salesBasisDetails = _persistence.create(pk);

		salesBasisDetails.setCreatedBy(RandomTestUtil.nextInt());

		salesBasisDetails.setNetSalesFormulaMasterSid(RandomTestUtil.nextInt());

		salesBasisDetails.setRecordLockStatus(RandomTestUtil.randomBoolean());

		salesBasisDetails.setModifiedBy(RandomTestUtil.nextInt());

		salesBasisDetails.setCreatedDate(RandomTestUtil.nextDate());

		salesBasisDetails.setContractMasterSid(RandomTestUtil.nextInt());

		salesBasisDetails.setSource(RandomTestUtil.randomString());

		salesBasisDetails.setCdrModelSid(RandomTestUtil.nextInt());

		salesBasisDetails.setCfpContractDetailsSid(RandomTestUtil.nextInt());

		salesBasisDetails.setModifiedDate(RandomTestUtil.nextDate());

		salesBasisDetails.setInboundStatus(RandomTestUtil.randomString());

		_salesBasisDetailses.add(_persistence.update(salesBasisDetails));

		return salesBasisDetails;
	}

	private List<SalesBasisDetails> _salesBasisDetailses = new ArrayList<SalesBasisDetails>();
	private SalesBasisDetailsPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}