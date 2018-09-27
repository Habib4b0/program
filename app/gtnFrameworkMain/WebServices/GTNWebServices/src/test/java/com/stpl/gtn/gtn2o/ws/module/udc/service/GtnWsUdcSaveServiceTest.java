package com.stpl.gtn.gtn2o.ws.module.udc.service;

import static org.junit.Assert.*;


import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.mockito.Mockito.doNothing;
import com.stpl.gtn.gtn2o.ws.entity.HelperTable;
import com.stpl.gtn.gtn2o.ws.entity.itemmaster.BrandMaster;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsBrandMasterBean;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.udc.bean.GtnWsUdcBean;
import com.stpl.gtn.gtn2o.ws.udc.bean.GtnWsUdcInfoBean;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/AutomaticContext.xml" })
public class GtnWsUdcSaveServiceTest {

	@InjectMocks	
	@Autowired
	GtnWsUdcSaveService ins;
	
	@Test
	public void testSaveUdc() {
		SessionFactory fac=Mockito.mock(SessionFactory.class);
		Session session=Mockito.mock(Session.class);
		Transaction trans=Mockito.mock(Transaction.class);
		Criteria criteria=Mockito.mock(Criteria.class);
		doReturn(session).when(fac).openSession();
		doReturn(trans).when(session).beginTransaction();
		GtnWsUdcInfoBean gtnWsUdcInfoBean=new GtnWsUdcInfoBean();
		GtnWsUdcBean gtnWsUdcBean=new GtnWsUdcBean();
		gtnWsUdcBean.setGtnWsUdcInfoBean(gtnWsUdcInfoBean);
		doReturn(criteria).when(session).createCriteria(HelperTable.class);
		gtnWsUdcInfoBean.setRefCount(1);
		gtnWsUdcInfoBean.setModifiedBy(1);
		gtnWsUdcInfoBean.setCreatedBy(1);
		gtnWsUdcInfoBean.setCreatedDate(new Date());
		gtnWsUdcInfoBean.setModifiedDate(new Date());
		ins.saveUdc(gtnWsUdcBean);
	}

	@Test
	public void testDeleteUdc_1() {
		SessionFactory fac=Mockito.mock(SessionFactory.class);
		Session session=Mockito.mock(Session.class);
		Transaction trans=Mockito.mock(Transaction.class);
		doReturn(session).when(fac).openSession();
		doReturn(trans).when(session).beginTransaction();
		HelperTable helperTableModel=new HelperTable();;
		doNothing().when(session).delete(Mockito.any());	
		helperTableModel.setHelperTableSid(126);	
		ins.deleteUdc(helperTableModel);
		}
	
	@Test
	public void testSaveBrandMaster_1() {
		SessionFactory fac=Mockito.mock(SessionFactory.class);
		Session session=Mockito.mock(Session.class);
		Transaction trans=Mockito.mock(Transaction.class);
		doReturn(session).when(fac).openSession();
		doReturn(trans).when(session).beginTransaction();
		GtnWsBrandMasterBean gtnWsBrandMasterBean=new GtnWsBrandMasterBean();
		ins.saveBrandMaster(gtnWsBrandMasterBean);
	}
	

	@Ignore
	public void testSaveBrandMaster_2(){
		SessionFactory fac=Mockito.mock(SessionFactory.class);
		Session session=Mockito.mock(Session.class);
		Transaction trans=Mockito.mock(Transaction.class);
		doReturn(session).when(fac).openSession();
		doReturn(trans).when(session).beginTransaction();
		GtnWsBrandMasterBean gtnWsBrandMasterBean=new GtnWsBrandMasterBean();

		List<BrandMaster> brandMasterList =new ArrayList<>();
		BrandMaster brand=new BrandMaster();
		brandMasterList.add(brand);
		ins.saveBrandMaster(gtnWsBrandMasterBean);
	}
	
	@Test
	public void testDeleteBrandMaster() {
		BrandMaster brandMasterModel=new BrandMaster();
		SessionFactory fac=Mockito.mock(SessionFactory.class);
		Session session=Mockito.mock(Session.class);
		Transaction trans=Mockito.mock(Transaction.class);
		doReturn(session).when(fac).openSession();
		doReturn(trans).when(session).beginTransaction();
		brandMasterModel.setBrandMasterSid(1);
		doReturn(new BrandMaster()).when(session).load(BrandMaster.class, 1);
		ins.deleteBrandMaster(brandMasterModel);
	}
}
