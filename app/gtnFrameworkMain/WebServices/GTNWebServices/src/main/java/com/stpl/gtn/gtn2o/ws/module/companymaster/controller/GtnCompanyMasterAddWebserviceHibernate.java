
package com.stpl.gtn.gtn2o.ws.module.companymaster.controller;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterCompanyParentBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterCompanyTradeClassBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterIdentifierInfoBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterInformationBean;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.entity.HelperTable;
import com.stpl.gtn.gtn2o.ws.entity.companymaster.CompanyIdentifier;
import com.stpl.gtn.gtn2o.ws.entity.companymaster.CompanyMaster;
import com.stpl.gtn.gtn2o.ws.entity.companymaster.CompanyParentDetails;
import com.stpl.gtn.gtn2o.ws.entity.companymaster.CompanyQualifier;
import com.stpl.gtn.gtn2o.ws.entity.companymaster.CompanyTradeClass;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

@RestController
@RequestMapping(value = GtnWebServiceUrlConstants.GTN_WS_COMPANY_MASTER)
public class GtnCompanyMasterAddWebserviceHibernate {
	public GtnCompanyMasterAddWebserviceHibernate() {
		/**
		 * empty constructor
		 */
	}

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnCompanyMasterAddWebserviceHibernate.class);

	@Autowired
	private org.hibernate.SessionFactory sessionFactory;

	public org.hibernate.SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(org.hibernate.SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@RequestMapping(value = GtnWebServiceUrlConstants.GTN_WS_CM_HIBERNATE_SAVE_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse saveCompanyMaster(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter saveCompanyMaster");
		GtnWsGeneralRequest generalWSRequest = gtnWsRequest.getGtnWsGeneralRequest();
		GtnCMasterBean masterbean = gtnWsRequest.getGtnCMasterRequest().getGtnCMasterBean();
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			CompanyMaster masterData = generateCompanyMaster(masterbean, generalWSRequest, session);
			session.saveOrUpdate(masterData);
			getParentDetails(masterbean, masterData, generalWSRequest, session);
			getTradeClassDetails(masterbean, masterData, generalWSRequest, session);
			getIdentifierDetails(masterbean, masterData, generalWSRequest, session);
			tx.commit();
		} catch (Exception e) {
			logger.error(e.getMessage());
			tx.rollback();
		} finally {
			session.close();
		}
		return null;
	}

	private CompanyMaster generateCompanyMaster(GtnCMasterBean masterbean, GtnWsGeneralRequest generalWSRequest,
			Session session) {
		GtnCMasterInformationBean companyInformationBean = masterbean.getGtnCMasterInformationBean();
		int userId = Integer.parseInt(generalWSRequest.getUserId().trim());
		return new CompanyMaster(getHelperTable(companyInformationBean.getCompanyCategory(), session),
				getHelperTable(companyInformationBean.getState(), session),
				getHelperTable(companyInformationBean.getCompanyGroup(), session),
				getHelperTable(companyInformationBean.getOrganizationKey(), session),
				getHelperTable(companyInformationBean.getCompanyType(), session),
				getHelperTable(companyInformationBean.getCountry(), session),
				getHelperTable(companyInformationBean.getCompanyStatus(), session),
				companyInformationBean.getCompanyId(), companyInformationBean.getCompanyNo(),
				companyInformationBean.getCompanyName(), companyInformationBean.getCompanyStartDate(),
				companyInformationBean.getCompanyEndDate(), companyInformationBean.getLives(),
				companyInformationBean.getFinancialSystem(), companyInformationBean.getAddress1(),
				companyInformationBean.getAddress2(), companyInformationBean.getCity(),
				companyInformationBean.getZipCode(), companyInformationBean.getRegionCode(),
				companyInformationBean.getLastUpdatedDate(), companyInformationBean.getInternalNotes(), 'A', true,
				companyInformationBean.getBatchId(), companyInformationBean.getSource(), userId, new Date(), userId,
				new Date());
	}

	private HelperTable getHelperTable(int sID, Session session) {
		return session.load(HelperTable.class, sID);
	}

	private void getParentDetails(GtnCMasterBean masterbean, CompanyMaster masterData,
			GtnWsGeneralRequest generalWSRequest, Session session) {
		List<GtnCMasterCompanyParentBean> parentDetails = masterbean.getGtnCMasterCompanyParentBeanList();
		int userId = Integer.parseInt(generalWSRequest.getUserId().trim());
		for (GtnCMasterCompanyParentBean parentDetail : parentDetails) {
			Date todayDate = new Date();
			CompanyMaster parentCompanyMaster = new CompanyMaster();
			parentCompanyMaster.setCompanyMasterSid(parentDetail.getParentCompanyMasterSystemId());
			CompanyParentDetails details = new CompanyParentDetails(null, masterData, parentCompanyMaster,
					parentDetail.getCompanyParentStartDate(), parentDetail.getCompanyParentEndDate(), null, todayDate,
					'A', true, "", "GTN", userId, todayDate, userId, todayDate);
			session.saveOrUpdate(details);
		}
	}

	private void getTradeClassDetails(GtnCMasterBean masterbean, CompanyMaster masterData,
			GtnWsGeneralRequest generalWSRequest, Session session) {
		List<GtnCMasterCompanyTradeClassBean> tradeClassList = masterbean.getGtnCMasterCompanyTradeClassBeanList();
		int userId = Integer.parseInt(generalWSRequest.getUserId().trim());
		for (GtnCMasterCompanyTradeClassBean companyTradeClassBean : tradeClassList) {
			Date todayDate = new Date();
			CompanyTradeClass tradeClass = new CompanyTradeClass(masterData, null,
					getHelperTable(companyTradeClassBean.getCompanyTradeClassSid(), session),
					companyTradeClassBean.getCompanyTradeClassStartDate(),
					companyTradeClassBean.getCompanyTradeClassEndDate(), null, todayDate, 'A', true, "", "GTN", userId,
					todayDate, userId, todayDate);
			session.saveOrUpdate(tradeClass);
		}
	}

	private void getIdentifierDetails(GtnCMasterBean masterbean, CompanyMaster masterData,
			GtnWsGeneralRequest generalWSRequest, Session session) {
		List<GtnCMasterIdentifierInfoBean> companyIdentifierInfoBean = masterbean.getGtnCMasterIdentifierInfoBeanList();
		int userId = Integer.parseInt(generalWSRequest.getUserId().trim());
		for (GtnCMasterIdentifierInfoBean companyIdentifierInfoBean1 : companyIdentifierInfoBean) {
			CompanyQualifier qualifier = new CompanyQualifier();
			qualifier.setCompanyQualifierSid(companyIdentifierInfoBean1.getCompanyQualifierSid());
			Date todayDate = new Date();
			CompanyIdentifier companyIdentifier = new CompanyIdentifier(masterData, qualifier,
					getHelperTable(companyIdentifierInfoBean1.getIdentifierStatus(), session),
					companyIdentifierInfoBean1.getCompanyIdentifierValue(),
					companyIdentifierInfoBean1.getCompanyIdentifierStartDate(),
					companyIdentifierInfoBean1.getCompanyIdentifierEndDate(),
					companyIdentifierInfoBean1.getEntityCode(), 'A', true, "", "GTN", userId, todayDate, userId,
					todayDate);
			session.saveOrUpdate(companyIdentifier);
		}
	}

}
