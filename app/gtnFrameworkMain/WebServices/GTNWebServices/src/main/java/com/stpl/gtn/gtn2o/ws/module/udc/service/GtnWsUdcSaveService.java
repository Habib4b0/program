package com.stpl.gtn.gtn2o.ws.module.udc.service;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.ws.entity.HelperTable;
import com.stpl.gtn.gtn2o.ws.entity.itemmaster.BrandMaster;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsBrandMasterBean;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;
import com.stpl.gtn.gtn2o.ws.udc.bean.GtnWsUdcBean;
import com.stpl.gtn.gtn2o.ws.udc.bean.GtnWsUdcInfoBean;

@Service
@Scope(value = "singleton")
public class GtnWsUdcSaveService {

	public GtnWsUdcSaveService() {
		super();
	}

	private static final GtnWSLogger GTNWSLOGGER = GtnWSLogger.getGTNLogger(GtnWsUdcSaveService.class);

	@Autowired
	private org.hibernate.SessionFactory sessionFactory;

	public GtnUIFrameworkWebserviceResponse saveUdc(GtnWsUdcBean gtnWsUdcBean) {
		GTNWSLOGGER.info("Enter Webservice SaveUdc");
		GtnUIFrameworkWebserviceResponse gtnUIFrameworkWebserviceResponse = new GtnUIFrameworkWebserviceResponse();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			GtnWsUdcInfoBean gtnWsUdcInfoBean = gtnWsUdcBean.getGtnWsUdcInfoBean();
			HelperTable helperTableBean = new HelperTable();
			helperTableBean.setDescription(gtnWsUdcInfoBean.getUdcValue());
			HelperTable helperTableModel = getUdcModel(session, helperTableBean, gtnWsUdcInfoBean);
			session.save(helperTableModel);
			transaction.commit();
			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setSucess(true);
		} catch (Exception e) {
			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setSucess(false);
			transaction.rollback();
		} finally {
			session.close();
		}
		return gtnUIFrameworkWebserviceResponse;
	}

	private HelperTable getUdcModel(Session session, HelperTable helperTableBean, GtnWsUdcInfoBean gtnWsUdcInfoBean)
			throws GtnFrameworkGeneralException {
		HelperTable helperTableModel;
		Criteria criteria = session.createCriteria(HelperTable.class);
		criteria.add(Restrictions.eq("description", helperTableBean.getDescription()));
		List<HelperTable> helperTableList = (List<HelperTable>) criteria.list();
		if (helperTableList.isEmpty()) {
			helperTableModel = setValuesToUdcModel(gtnWsUdcInfoBean);
		} else {
			throw new GtnFrameworkGeneralException("Already Present");
		}
		return helperTableModel;
	}

	private HelperTable setValuesToUdcModel(GtnWsUdcInfoBean gtnWsUdcInfoBean) {
		HelperTable helperTableModel = new HelperTable();
		helperTableModel.setListName(gtnWsUdcInfoBean.getUdcCategory());
		helperTableModel.setDescription(gtnWsUdcInfoBean.getUdcValue());
		helperTableModel.setRefCount(BigDecimal.valueOf(gtnWsUdcInfoBean.getRefCount()));
		helperTableModel.setCreatedBy(gtnWsUdcInfoBean.getCreatedBy());
		helperTableModel.setModifiedBy(gtnWsUdcInfoBean.getModifiedBy());
		helperTableModel.setCreatedDate(gtnWsUdcInfoBean.getCreatedDate());
		helperTableModel.setModifiedDate(gtnWsUdcInfoBean.getModifiedDate());
		return helperTableModel;
	}

	public GtnUIFrameworkWebserviceResponse deleteUdc(HelperTable helperTableModel) {
		GtnUIFrameworkWebserviceResponse gtnUIFrameworkWebserviceResponse = new GtnUIFrameworkWebserviceResponse();
		gtnUIFrameworkWebserviceResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			int helperTableSid = helperTableModel.getHelperTableSid();
			if (helperTableSid != 0) {
				session.delete(helperTableModel);
				transaction.commit();
				gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setSucess(true);
			}
		} catch (Exception e) {
			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setSucess(false);
			transaction.rollback();
		} finally {
			session.close();
		}
		return gtnUIFrameworkWebserviceResponse;
	}

	public GtnUIFrameworkWebserviceResponse saveBrandMaster(GtnWsBrandMasterBean gtnWsBrandMasterBean) {
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			BrandMaster brandMasterModel = getBrandModel(session, gtnWsBrandMasterBean);
			session.saveOrUpdate(brandMasterModel);
			response.getGtnWsGeneralResponse().setSucess(true);
			transaction.commit();
		} catch (Exception e) {
			response.getGtnWsGeneralResponse().setSucess(false);
			transaction.rollback();
		} finally {
			session.close();
		}
		return response;
	}

	private BrandMaster getBrandModel(Session session, GtnWsBrandMasterBean gtnWsBrandMasterBean)
			throws GtnFrameworkGeneralException {
		BrandMaster brandMasterModel;
		Criteria criteria = session.createCriteria(BrandMaster.class);
		criteria.add(Restrictions.eq("brandId", gtnWsBrandMasterBean.getBrandId()));
		List<BrandMaster> brandMasterList = (List<BrandMaster>) criteria.list();
		if (brandMasterList.isEmpty()) {
			brandMasterModel = setValuesToModel(gtnWsBrandMasterBean);
		} else {
			brandMasterModel = brandMasterList.get(0);
			if (brandMasterModel.getInboundStatus() == 'A') {
				throw new GtnFrameworkGeneralException("Already Present");
			}
			brandMasterModel.setInboundStatus('A');
		}
		return brandMasterModel;
	}

	private BrandMaster setValuesToModel(GtnWsBrandMasterBean gtnWsBrandMasterBean) {
		BrandMaster brandMasterModel = new BrandMaster();
		brandMasterModel.setBrandId(gtnWsBrandMasterBean.getBrandId());
		brandMasterModel.setBrandName(gtnWsBrandMasterBean.getBrandName());
		brandMasterModel.setDisplayBrand(gtnWsBrandMasterBean.getDisplayBrand());
		brandMasterModel.setCreatedBy(gtnWsBrandMasterBean.getCreatedBy());
		brandMasterModel.setModifiedBy(gtnWsBrandMasterBean.getModifiedBy());
		brandMasterModel.setCreatedDate(gtnWsBrandMasterBean.getCreatedDate());
		brandMasterModel.setModifiedDate(gtnWsBrandMasterBean.getModifiedDate());
		brandMasterModel.setInboundStatus(gtnWsBrandMasterBean.getInboundStatus());
		return brandMasterModel;
	}

	public GtnUIFrameworkWebserviceResponse deleteBrandMaster(BrandMaster brandMasterModel) {
		GtnUIFrameworkWebserviceResponse gtnUIFrameworkWebserviceResponse = new GtnUIFrameworkWebserviceResponse();
		gtnUIFrameworkWebserviceResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			int brandMasterSid = brandMasterModel.getBrandMasterSid();
			if (brandMasterSid != 0) {
				brandMasterModel = session.load(BrandMaster.class, brandMasterSid);
				brandMasterModel.setInboundStatus('D');
				session.update(brandMasterModel);
				transaction.commit();
				gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setSucess(true);
			}
		} catch (Exception e) {
			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setSucess(false);
			transaction.rollback();
		} finally {
			session.close();
		}
		return gtnUIFrameworkWebserviceResponse;
	}
}
